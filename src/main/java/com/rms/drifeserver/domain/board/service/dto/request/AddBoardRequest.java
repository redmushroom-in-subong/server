package com.rms.drifeserver.domain.board.service.dto.request;

import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.user.model.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddBoardRequest {

    @NotBlank(message = "게시글 제목이 비어있습니다.")
    private String title;

    @NotBlank(message = "게시글 내용이 비어있습니다.")
    private String contents;

    private List<String> imageUrls;

    @Builder(builderMethodName = "testBuilder")
    private AddBoardRequest(String title , String contents, List<String> imageUrls) {
        this.title = title;
        this.contents = contents;
        this.imageUrls = imageUrls;
    }

    public Board toBoard(User user) {
        return Board.of(user, this.title, this.contents, this.imageUrls);
    }
}
