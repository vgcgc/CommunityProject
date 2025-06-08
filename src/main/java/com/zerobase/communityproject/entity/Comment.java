package com.zerobase.communityproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "writer_id")
  private Long writerId;
  @Column(name = "post_id")
  private Long postId;
  private String text;
  @Column(name = "create_date")
  @CreationTimestamp
  private LocalDateTime createdAt = LocalDateTime.now();
  private String writer;

  public void updateText(String text) {
    this.text = text;
  }
}
