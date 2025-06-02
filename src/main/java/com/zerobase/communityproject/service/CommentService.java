package com.zerobase.communityproject.service;

import com.zerobase.communityproject.domain.Comment;
import com.zerobase.communityproject.domain.Post;
import com.zerobase.communityproject.exception.CustomException;
import com.zerobase.communityproject.exception.ErrorCode;
import com.zerobase.communityproject.model.request.CommentRequest;
import com.zerobase.communityproject.model.response.CommentDto;
import com.zerobase.communityproject.repository.CommentRepository;
import com.zerobase.communityproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberService memberService;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentDto createComment(CommentRequest request){

        Long writerIdx = memberService.getUserIdx(request.getUser());
        Post post = postRepository.findByTitleAndWriterId(request.getPostTitle(), writerIdx)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.POST_IS_NOT_FOUND));

        Comment comment = Comment.builder()
                                .writerId(writerIdx)
                                .postId(post.getId())
                                .text(request.getText())
                                .writer(request.getUser()).build();

        return new CommentDto(comment.getText(), comment.getWriter(), comment.getCreatedAt());
    }

    public CommentDto updateComment (CommentRequest request) {

        Comment comment = getComment(request);

        comment.setText(request.getText());
        commentRepository.save(comment);

        return new CommentDto(comment.getText(), comment.getWriter(), comment.getCreatedAt());
    }

    public String deleteComment (CommentRequest request) {

        Comment comment = getComment(request);
        commentRepository.delete(comment);
        return "삭제 완료";
    }

    private Comment getComment(CommentRequest request) {
        Long writerIdx = memberService.getUserIdx(request.getUser());

        Post post = postRepository.findByTitleAndWriterId(request.getPostTitle(), writerIdx)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.POST_IS_NOT_FOUND));

      return commentRepository.findCommentByWriterIdAndPostIdAndCreatedAt(writerIdx, post.getId(), request.getCreatedAt())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.POST_IS_NOT_FOUND));
    }
}
