package com.zerobase.communityproject.service;

import com.zerobase.communityproject.entity.Member;
import com.zerobase.communityproject.entity.Post;
import com.zerobase.communityproject.exception.CustomException;
import com.zerobase.communityproject.exception.ErrorCode;
import com.zerobase.communityproject.model.request.CreatePostRequest;
import com.zerobase.communityproject.model.request.UpdatePostRequest;
import com.zerobase.communityproject.model.response.CommentDto;
import com.zerobase.communityproject.model.response.PostComment;
import com.zerobase.communityproject.repository.CommentRepository;
import com.zerobase.communityproject.repository.MemberRepository;
import com.zerobase.communityproject.repository.PostRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final MemberService memberService;
  private final MemberRepository memberRepository;
  private final CommentRepository commentRepository;

  public Page<Post> getPostList(Pageable pageable) {
    return postRepository.findFirstByOrderByIdDesc(pageable);
  }

  public Page<Post> getMyPost(String writer, Pageable pageable) {
    return postRepository.findAllByMember_Id(memberService.getId(), pageable); // 여기 변경
  }

  public Page<Post> searchingPost(String title, Pageable pageable) {
    return postRepository.findAllByTitle(title, pageable);
  }

  public PostComment getPostInfo(String title, String writer) {
    Post post = postRepository.findByTitleAndMember_Idx(title, memberService.getUserIdx(writer)) // 여기 변경
        .orElseThrow(() -> new CustomException(ErrorCode.POST_IS_NOT_FOUND));
    List<CommentDto> comments = commentRepository.findAllByPostId(post.getId())
        .stream().map(CommentDto::new).collect(Collectors.toList());
    return new PostComment(post, comments);
  }


  @Transactional
  public Post createPost(String id, CreatePostRequest inputPost) {

    Member writer = memberRepository.findById(id);
    if (postRepository.existsByTitleAndMember_Idx(inputPost.getTitle(), writer.getIdx())) {
      throw new CustomException(ErrorCode.TITLE_IS_DUPLICATE);
    }

    return Post.builder()
        .member(writer)
        .title(inputPost.getTitle())
        .content(inputPost.getContent())
        .build();

  }

  @Transactional
  public Post updatePost(String id, UpdatePostRequest inputPost) {

    Long writerIdx = memberService.getUserIdx(id);
    Post post = postRepository.findByTitleAndMember_Idx(inputPost.getTitle(), writerIdx)
        .orElseThrow(() -> new CustomException(ErrorCode.POST_IS_NOT_FOUND));

    if (inputPost.getTitle() != null) {
      post.updateTitle(inputPost.getTitle());
    } else {
      post.updateContent(inputPost.getContent());
    }
    return post;
  }

  public void deletePost(String title, String writer) {
    Long writerIdx = memberService.getUserIdx(writer);
    postRepository.deleteByTitleAndMember_Idx(title, writerIdx);
  }

}
