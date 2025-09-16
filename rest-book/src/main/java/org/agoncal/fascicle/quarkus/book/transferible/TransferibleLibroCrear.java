package org.agoncal.fascicle.quarkus.book.transferible;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

public class TransferibleLibroCrear {

  private String title;
  private List<Long> authorIds;
  private Long categoryId;
  private Integer yearOfPublication;
  private Integer nbOfPages;
  private Integer rank;
  private BigDecimal price;
  private String description;

  // Constructor vacío
  public TransferibleLibroCrear() {
  }

  // Constructor con todos los datos
  public TransferibleLibroCrear(String title, List<Long> authorIds, Long categoryId, Integer yearOfPublication, Integer nbOfPages, Integer rank, BigDecimal price, URL smallImageUrl, URL mediumImageUrl, String description) {
    this.title = title;
    this.authorIds = authorIds;
    this.categoryId = categoryId;
    this.yearOfPublication = yearOfPublication;
    this.nbOfPages = nbOfPages;
    this.rank = rank;
    this.price = price;
    this.description = description;
  }

  // Getters y Setters
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public List<Long> getAuthorIds() {
    return authorIds;
  }
  public void setAuthorsIds(List<Long> authorIds) {
    this.authorIds = authorIds;
  }
  public Long getCategoryId() { return categoryId; }
  public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
  public Integer getYearOfPublication() {
    return yearOfPublication;
  }
  public void setYearOfPublication(Integer yearOfPublication) {
    this.yearOfPublication = yearOfPublication;
  }
  public Integer getNbOfPages() {
    return nbOfPages;
  }
  public void setNbOfPages(Integer nbOfPages) {
    this.nbOfPages = nbOfPages;
  }
  public Integer getRank() {
    return rank;
  }
  public void setRank(Integer rank) {
    this.rank = rank;
  }
  public BigDecimal getPrice() {
    return price;
  }
  public void setPrice(BigDecimal price) {
    this.price = price;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
}
