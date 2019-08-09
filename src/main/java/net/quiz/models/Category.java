package net.quiz.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_CATEGORY")
public class Category implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "cat_name")
  private String catName;

  @Column(name = "parent_id")
  private Integer parent;

  public Category() {
  }

  public Category(String catName) {
    this.catName = catName;
  }

  public Category(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCatName() {
    return catName;
  }

  public void setCatName(String catName) {
    this.catName = catName;
  }

  public Integer getParent() {
    return parent;
  }

  public void setParent(Integer parent) {
    this.parent = parent;
  }
}
