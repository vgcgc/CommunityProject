package com.zerobase.communityproject.service;

import com.zerobase.communityproject.domain.Post;
import com.zerobase.communityproject.exception.CustomException;
import com.zerobase.communityproject.exception.ErrorCode;
import com.zerobase.communityproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;

    public Page<Post> getMyPost(String writer, Pageable pageable) {
        return postRepository.findAllByWriterId(memberService.getUserIdx(writer), pageable);
    }

    public Page<Post> searchingPost(String title, Pageable pageable) {
        return postRepository.findAllByTitle(title, pageable);
    }


    public Post createPost(Map<String, String> inputPost) {

        Post post = new Post();
        post.setWriterId(checkPost(inputPost, true));
        post.setTitle(inputPost.get("title"));
        post.setContent(inputPost.get("content"));
        post.setCreatedAt(LocalDateTime.now());
        post.setWriter(inputPost.get("id"));
        postRepository.save(post);

        return post;
    }

    public Post updatePost(Map<String, String> inputPost) {

        Long writerIdx = checkPost(inputPost, false);
        Post post = postRepository.findByTitleAndWriterId(inputPost.get("title"), writerIdx);
        if (inputPost.get("newTitle") != null) {
            post.setTitle(inputPost.get("newTitle"));
        } else {
            post.setContent(inputPost.get("content"));
        }
        postRepository.save(post);
        return post;
    }

    public void deletePost(String title, String writer) {
        postRepository.deleteByTitleAndWriter(title, writer);
    }

    private Long checkPost(Map<String, String> inputPost, boolean titleCheck) {

        if (!inputPost.containsKey("title") || inputPost.get("title").isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, ErrorCode.TITLE_IS_EMPTY);
        }

        Long writerIdx = memberService.getUserIdx(inputPost.get("id"));

        if (titleCheck && postRepository.existsByTitleAndWriterId(inputPost.get("title"), writerIdx)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.TITLE_IS_DUPLICATE);
        }

        return writerIdx;
    }
}
