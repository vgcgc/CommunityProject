package com.zerobase.communityproject.service;

import com.zerobase.communityproject.domain.Post;
import com.zerobase.communityproject.exception.CustomException;
import com.zerobase.communityproject.exception.ErrorCode;
import com.zerobase.communityproject.model.request.CreatePostRequest;
import com.zerobase.communityproject.model.request.UpdatePostRequest;
import com.zerobase.communityproject.model.response.CommentDto;
import com.zerobase.communityproject.model.response.PostComment;
import com.zerobase.communityproject.repository.CommentRepository;
import com.zerobase.communityproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;
    private final CommentRepository commentRepository;

    public Page<Post> getPostList(Pageable pageable) {
        return postRepository.findFirstByOrderByIdDesc(pageable);
    }

    public Page<Post> getMyPost(String writer, Pageable pageable) {
        return postRepository.findAllByWriterId(memberService.getUserIdx(writer), pageable);
    }

    public Page<Post> searchingPost(String title, Pageable pageable) {
        return postRepository.findAllByTitle(title, pageable);
    }

    public PostComment getPostInfo(String title, String writer) {
        Post post = postRepository.findByTitleAndWriterId(title, memberService.getUserIdx(writer))
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.POST_IS_NOT_FOUND));
        List<CommentDto> comments = commentRepository.findAllByPostId(post.getId())
                        .stream().map(CommentDto::new).collect(Collectors.toList());
        return new PostComment(post, comments);
    }


    public Post createPost(String id, CreatePostRequest inputPost) {

        Long writerIdx = memberService.getUserIdx(id);
        if (postRepository.existsByTitleAndWriterId(inputPost.getTitle(), writerIdx)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.TITLE_IS_DUPLICATE);
        }

        Post post = Post.builder()
                        .writerId(writerIdx)
                        .title(inputPost.getTitle())
                        .content(inputPost.getContent())
                        .writer(id)
                        .build();

        postRepository.save(post);

        return post;
    }

    public Post updatePost(String id, UpdatePostRequest inputPost) {

        Long writerIdx = memberService.getUserIdx(id);
        Post post = postRepository.findByTitleAndWriterId(inputPost.getTitle(), writerIdx)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, ErrorCode.POST_IS_NOT_FOUND));

        if (inputPost.getTitle() != null) {
            post.setTitle(inputPost.getTitle());
        } else {
            post.setContent(inputPost.getContent());
        }

        postRepository.save(post);
        return post;
    }

    public void deletePost(String title, String writer) {
        postRepository.deleteByTitleAndWriter(title, writer);
    }

}
