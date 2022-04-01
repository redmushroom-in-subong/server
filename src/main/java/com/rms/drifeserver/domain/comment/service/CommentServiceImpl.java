package com.rms.drifeserver.domain.comment.service;

import com.rms.drifeserver.domain.comment.service.dto.request.AddCommentRequest;
import com.rms.drifeserver.domain.comment.service.dto.request.UpdateCommentRequest;
import com.rms.drifeserver.domain.comment.service.dto.response.CommentInfoResponse;
import com.rms.drifeserver.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    @Override
    public void addComment(AddCommentRequest request, Long boardId, User user) {

    }

    @Override
    public CommentInfoResponse updateComment(UpdateCommentRequest request, Long commentId, User user) {
        return null;
    }

    @Override
    public void deleteComment(Long commentId, User user) {

    }
}
