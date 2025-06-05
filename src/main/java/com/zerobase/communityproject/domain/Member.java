package com.zerobase.communityproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idx;
  private String id;
  private String pw;
  private String name;
  private String role;

  public Member(String id, String pw) {
    this.id = id;
    this.pw = pw;
  }

  public Member(String id, String pw, String name, String role) {
    this.id = id;
    this.pw = pw;
    this.name = name;
    this.role = role;
  }


}
