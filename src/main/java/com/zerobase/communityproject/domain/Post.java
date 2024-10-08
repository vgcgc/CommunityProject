package com.zerobase.communityproject.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
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
    private LocalDateTime createdAt;
    private String writer;
}
