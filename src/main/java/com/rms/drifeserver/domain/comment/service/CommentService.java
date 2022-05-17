package com.rms.drifeserver.domain.comment.service;

import com.rms.drifeserver.domain.comment.service.dto.request.AddCommentRequest;
import com.rms.drifeserver.domain.comment.service.dto.request.UpdateCommentRequest;
import com.rms.drifeserver.domain.comment.service.dto.response.CommentInfoResponse;
import com.rms.drifeserver.domain.user.model.User;

public interface CommentService {

    void addComment(AddCommentRequest request, User user);

    CommentInfoResponse updateComment(UpdateCommentRequest request, Long commentId, User user);

    void deleteComment(Long commentId, User user);

    void toggleCommentLike(Long commentId, User user);
}
