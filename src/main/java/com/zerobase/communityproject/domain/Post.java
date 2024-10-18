package com.zerobase.communityproject.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "writer_id")
    private Long writerId;
    private String title;
    private String content;
    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();
    private String writer;
}
