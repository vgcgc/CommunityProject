package com.zerobase.communityproject.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
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

}
