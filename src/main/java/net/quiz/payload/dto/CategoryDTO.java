package net.quiz.payload.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {
  private int id;
  private String catName;
  private CategoryDTO parent;
  private List<CategoryDTO> children;

  public CategoryDTO() {
  }

  public CategoryDTO(int id, String catName) {
    this.id = id;
    this.catName = catName;
  }

  public CategoryDTO(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCatName() {
    return catName;
  }

  public void setCatName(String catName) {
    this.catName = catName;
  }

  public List<CategoryDTO> getChildren() {
    return children;
  }

  public void setChildren(List<CategoryDTO> children) {
    this.children = children;
  }

  public CategoryDTO getParent() {
    return parent;
  }

  public void setParent(CategoryDTO parent) {
    this.parent = parent;
  }
}
