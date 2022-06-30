package com.rms.drifeserver.integrationTest.board;

import com.rms.drifeserver.domain.board.dao.BoardRepository;
import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.board.service.BoardService;
import com.rms.drifeserver.domain.board.service.dto.request.AddBoardRequest;
import com.rms.drifeserver.domain.board.service.dto.request.UpdateBoardRequest;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.oauth.entity.ProviderType;
import com.rms.drifeserver.domain.oauth.entity.RoleType;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.integrationTest.SetupUserIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@Transactional
public class BoardServiceTest extends SetupUserIntegrationTest {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @AfterEach
    void cleanUp() {
        boardRepository.deleteAll();
        userRepository.deleteAll();
    }

    public static void assertBoard(Board board, String contents, Long userId) {
        assertAll(
                () -> assertThat(board.getContents()).isEqualTo(contents),
                () -> assertThat(board.getUser().getId()).isEqualTo(userId)
        );
    }

    @Nested
    class AddBoardTest {

        @Test
        void 새로운_게시글을_작성합니다() {
            // given
            String title = "test-title";
            String contents = "test-contents";
            List<String> imageUrls = new ArrayList<>(Arrays.asList("test1.png","test2.png"));

            AddBoardRequest request = AddBoardRequest.testBuilder()
                    .title(title)
                    .contents(contents)
                    .imageUrls(imageUrls)
                    .build();

            //when
            boardService.addBoard(request, user);

            //then
            List<Board> boards = boardRepository.findAll();

            assertAll(
                    () -> assertThat(boards).hasSize(1),
                    () -> assertThat(boards.get(0).getBoardImages()).hasSize(2),
                    () -> assertBoard(boards.get(0), contents, userId)
            );
        }
    }

    @Nested
    class UpdateBoardTest {

        @Test
        void 내가_작성한_게시글을_수정합니다() {
            // given
            String title = "test-title";
            String contents = "test-contents";
            List<String> imageUrls = new ArrayList<>(Arrays.asList("test1.png","test2.png"));

            String updatedTitle = "updated-test-title";
            String updatedContents = "updated-test-contents";
            List<String> updatedImageUrls = new ArrayList<>(Arrays.asList("updated-test.png"));

            Board board = Board.of(user, title, contents, imageUrls);
            boardRepository.save(board);

            UpdateBoardRequest request = UpdateBoardRequest.testBuilder()
                    .title(updatedTitle)
                    .contents(updatedContents)
                    .imageUrls(updatedImageUrls)
                    .build();

            // when
            boardService.updateBoard(request, board.getId(), user);

            // then
            List<Board> boards = boardRepository.findAll();

            assertAll(
                    () -> assertThat(boards).hasSize(1),
                    () -> assertThat(boards.get(0).getBoardImages()).hasSize(1),
                    () -> assertBoard(boards.get(0), updatedContents, userId)
            );
        }

        @Test
        void 존재하지_않는_게시글을_수정하는경우_NOTFOUND_에러가_발생합니다() {
            // given
            Long notFoundBoardId = -1L;
            String updatedTitle = "updated-test-title";
            String updatedContents = "updated-test-contents";
            List<String> updatedImageUrls = new ArrayList<>(Arrays.asList("updated-test.png"));

            UpdateBoardRequest request = UpdateBoardRequest.testBuilder()
                    .title(updatedTitle)
                    .contents(updatedContents)
                    .imageUrls(updatedImageUrls)
                    .build();

            // when & then
            assertThatThrownBy(() -> boardService.updateBoard(request, notFoundBoardId, user))
                    .isInstanceOf(BaseException.class);
        }

        @Test
        void 내가_작성하지_않은_게시글을_수정하는_경우_NOT_FOUND_에러가_발생한다() {
            // given
            String title = "test-title";
            String contents = "test-contents";
            List<String> imageUrls = new ArrayList<>(Arrays.asList("test1.png","test2.png"));

            String updatedTitle = "updated-test-title";
            String updatedContents = "updated-test-contents";
            List<String> updatedImageUrls = new ArrayList<>(Arrays.asList("updated-test.png"));

            Board board = Board.of(user, title, contents, imageUrls);
            boardRepository.save(board);

            UpdateBoardRequest request = UpdateBoardRequest.testBuilder()
                    .title(updatedTitle)
                    .contents(updatedContents)
                    .imageUrls(updatedImageUrls)
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

            assertThatThrownBy(() -> boardService.updateBoard(request, board.getId(), diffUser))
                    .isInstanceOf(BaseException.class);
        }
    }

    @Nested
    class DeleteBoardTest {

        @Test
        void 내가_작성한_게시글을_삭제합니다() {
            // given
            String title = "test-title";
            String contents = "test-contents";
            List<String> imageUrls = new ArrayList<>(Arrays.asList("test1.png","test2.png"));

            Board board = Board.of(user, title, contents, imageUrls);
            boardRepository.save(board);

            // when
            boardService.deleteBoard(board.getId(), user);

            // then
            List<Board> boards = boardRepository.findAll();

            assertThat(boards).hasSize(0);
        }

        @Test
        void 없는_게시글을_삭제하려하면_NOT_FOUND_에러가_발생한다() {
            // given
            Long notFoundBoardId = -1L;

            // when & then
            assertThatThrownBy(() -> boardService.deleteBoard(notFoundBoardId, user))
                    .isInstanceOf(BaseException.class);
        }

        @Test
        void 내가_작성하지_않은_게시글을_삭제하려하면_NOT_FOUND_에러가_발생한다() {
            // given
            String title = "test-title";
            String contents = "test-contents";
            List<String> imageUrls = new ArrayList<>(Arrays.asList("test1.png","test2.png"));

            Board board = Board.of(user, title, contents, imageUrls);
            boardRepository.save(board);

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

            assertThatThrownBy(() -> boardService.deleteBoard(board.getId(), diffUser))
                    .isInstanceOf(BaseException.class);
        }

    }
}
