package com.zerobase.communityproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idx;

  private String id;
  private String pw;
  private String name;
  private String role;

  @OneToMany(mappedBy = "member")
  private List<Post> posts;

  @OneToMany(mappedBy = "member")
  private List<Comment> comments;

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

  public void updateName(String newName) {
    this.name = newName;
  }

  public void updateRole(String newRole) {
    this.role = newRole;
  }
}
