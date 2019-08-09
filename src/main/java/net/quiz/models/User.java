package net.quiz.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_USER")
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private Integer id;

  @Column(name = "user_id")
  private String userId;

  @Column(name = "full_name")
  private String name;

  private String role;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
