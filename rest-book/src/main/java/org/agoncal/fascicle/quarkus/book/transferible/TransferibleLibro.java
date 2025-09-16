package org.agoncal.fascicle.quarkus.book.transferible;

import jakarta.persistence.Column;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

public class TransferibleLibro {
  private Long id;
  private String title;
  private String isbn13;
  private String isbn10;
  private Integer yearOfPublication;
  private Integer nbOfPages;
  private Integer rank;
  private BigDecimal price;
  private URL smallImageUrl;
  private URL mediumImageUrl;
  private String description;
  private String categoryName;
  private List<TransferibleAutor> authors;

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getIsbn13() {
    return isbn13;
  }
  public void setIsbn13(String isbn13) {
    this.isbn13 = isbn13;
  }
  public String getIsbn10() {
    return isbn10;
  }
  public void setIsbn10(String isbn10) {
    this.isbn10 = isbn10;
  }
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
  public URL getSmallImageUrl() {
    return smallImageUrl;
  }
  public void setSmallImageUrl(URL smallImageUrl) {
    this.smallImageUrl = smallImageUrl;
  }
  public URL getMediumImageUrl() {
    return mediumImageUrl;
  }
  public void setMediumImageUrl(URL mediumImageUrl) {
    this.mediumImageUrl = mediumImageUrl;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getCategoryName() {
    return categoryName;
  }
  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
  public List<TransferibleAutor> getAuthors() {
    return authors;
  }
  public void setAuthors(List<TransferibleAutor> authors) {
    this.authors = authors;
  }
}
