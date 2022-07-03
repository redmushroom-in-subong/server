package com.rms.drifeserver.integrationTest.comment;

import com.rms.drifeserver.domain.comment.dao.CommentRepository;
import com.rms.drifeserver.domain.comment.model.Comment;
import com.rms.drifeserver.domain.comment.service.CommentService;
import com.rms.drifeserver.domain.comment.service.dto.request.AddCommentRequest;
import com.rms.drifeserver.domain.comment.service.dto.request.UpdateCommentRequest;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.oauth.entity.ProviderType;
import com.rms.drifeserver.domain.oauth.entity.RoleType;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.integrationTest.SetupBoardIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@Transactional
public class CommentServiceTest extends SetupBoardIntegrationTest {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentRepository commentRepository;

    @AfterEach
    void cleanUp() {
        commentRepository.deleteAll();
        boardRepository.deleteAll();
        userRepository.deleteAll();
    }

    public static void assertComment(Comment comment, String contents, Long boardId, Long userId) {
        assertAll(
                () -> assertThat(comment.getContents()).isEqualTo(contents),
                () -> assertThat(comment.getBoard().getId()).isEqualTo(boardId),
                () -> assertThat(comment.getUser().getId()).isEqualTo(userId)
        );
    }

    @Nested
    class AddCommentTest {

        @Test
        void 게시글에_새로운_댓글을_작성합니다() {
            // given
            String contents = "test-contents";

            AddCommentRequest request = AddCommentRequest.testBuilder()
                    .boardId(boardId)
                    .contents(contents)
                    .build();

            //when
            commentService.addComment(request, user);

            //then
            List<Comment> comments = commentRepository.findAll();

            assertAll(
                    () -> assertThat(comments).hasSize(1),
                    () -> assertComment(comments.get(0), contents, boardId, userId)
            );
        }

        @Test
        void 존재하지_않는_게시글에_댓글을_작성하는경우_NOTFOUND_에러가_발생합니다() {
            // given
            Long notFoundBoardId = -1L;

            String contents = "test-contents";

            AddCommentRequest request = AddCommentRequest.testBuilder()
                    .boardId(notFoundBoardId)
                    .contents(contents)
                    .build();

            //when & then
            assertThatThrownBy(() -> commentService.addComment(request, user))
                    .isInstanceOf(BaseException.class);
        }
    }

    @Nested
    class UpdateCommentTest {

        @Test
        void 내가_작성한_댓글을_수정합니다() {
            // given
            String contents = "test-contents";

            String updatedContents = "updated-test-contents";

            Comment comment = Comment.of(board, user, contents);
            commentRepository.save(comment);

            UpdateCommentRequest request = UpdateCommentRequest.testBuilder()
                    .contents(updatedContents)
                    .build();

            // when
            commentService.updateComment(request, comment.getId(), user);

            // then
            List<Comment> comments = commentRepository.findAll();

            assertAll(
                    () -> assertThat(comments).hasSize(1),
                    () -> assertComment(comments.get(0), updatedContents, boardId, userId)
            );
        }

        @Test
        void 존재하지_않는_댓글을_수정하는경우_NOTFOUND_에러가_발생합니다() {
            // given
            Long notFoundCommentId = -1L;
            String updatedContents = "updated-test-contents";

            UpdateCommentRequest request = UpdateCommentRequest.testBuilder()
                    .contents(updatedContents)
                    .build();

            // when & then
            assertThatThrownBy(() -> commentService.updateComment(request, notFoundCommentId, user))
                    .isInstanceOf(BaseException.class);
        }

        @Test
        void 내가_작성하지_않은_댓글을_수정하는_경우_NOT_FOUND_에러가_발생한다() {
            // given
            String contents = "test-contents";

            String updatedContents = "updated-test-contents";

            Comment comment = Comment.of(board, user, contents);
            commentRepository.save(comment);

            UpdateCommentRequest request = UpdateCommentRequest.testBuilder()
                    .contents(updatedContents)
                    .build();

            // when & then
            User diffUser = new User(
                    "diff-social-id",
                    "diff-name",
                    "diff-email",
                    null,
                    "Y",
                    "diff-img",
                    ProviderType.LOCAL,
                    RoleType.USER,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );

            userRepository.save(diffUser);

            assertThatThrownBy(() -> commentService.updateComment(request, board.getId(), diffUser))
                    .isInstanceOf(BaseException.class);
        }
    }

    @Nested
    class DeleteCommentTest {

        @Test
        void 내가_작성한_댓글을_삭제합니다() {
            // given
            String contents = "test-contents";

            Comment comment = Comment.of(board, user, contents);
            commentRepository.save(comment);

            // when
            commentService.deleteComment(comment.getId(), user);

            // then
            List<Comment> comments = commentRepository.findAll();

            assertThat(comments).hasSize(0);
        }

        @Test
        void 없는_댓글을_삭제하려하면_NOT_FOUND_에러가_발생한다() {
            // given
            Long notFoundBoardId = -1L;

            // when & then
            assertThatThrownBy(() -> commentService.deleteComment(notFoundBoardId, user))
                    .isInstanceOf(BaseException.class);
        }

        @Test
        void 내가_작성하지_않은_댓글을_삭제하려하면_NOT_FOUND_에러가_발생한다() {
            // given
            String contents = "test-contents";

            Comment comment = Comment.of(board, user, contents);
            commentRepository.save(comment);

            // when & then
            User diffUser = new User(
                    "diff-social-id",
                    "diff-name",
                    "diff-email",
                    null,
                    "Y",
                    "diff-img",
                    ProviderType.LOCAL,
                    RoleType.USER,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );

            userRepository.save(diffUser);

            assertThatThrownBy(() -> commentService.deleteComment(board.getId(), diffUser))
                    .isInstanceOf(BaseException.class);
        }

    }
}
