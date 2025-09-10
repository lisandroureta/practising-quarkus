package org.agoncal.fascicle.quarkus.book.transferible;

import java.math.BigDecimal;
import java.net.URL;

public class TransferibleLibroCrear {

  private String title;
  private String author;
  private String isbn13;     // opcional si lo genera el back
  private String isbn10;     // idem
  private Integer yearOfPublication;
  private Integer nbOfPages;
  private Integer rank;
  private BigDecimal price;
  private URL smallImageUrl;
  private URL mediumImageUrl;
  private String description;

  // Constructor vacío
  public TransferibleLibroCrear() {
  }

  // Constructor con todos los datos
  public TransferibleLibroCrear(String title, String author, String isbn13, String isbn10, Integer yearOfPublication, Integer nbOfPages, Integer rank, BigDecimal price, URL smallImageUrl, URL mediumImageUrl, String description) {
    this.title = title;
    this.author = author;
    this.isbn13 = isbn13;
    this.isbn10 = isbn10;
    this.yearOfPublication = yearOfPublication;
    this.nbOfPages = nbOfPages;
    this.rank = rank;
    this.price = price;
    this.smallImageUrl = smallImageUrl;
    this.mediumImageUrl = mediumImageUrl;
    this.description = description;
  }

  // Getters y Setters
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getAuthor() {
    return author;
  }
  public void setAuthor(String author) {
    this.author = author;
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
}
