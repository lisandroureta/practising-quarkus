package org.agoncal.fascicle.quarkus.book.transferible;

import jakarta.persistence.Column;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.net.URL;

public class TransferibleLibro {


  public String title;

  public String isbn13;

  public String isbn10;
  public String author;

  public Integer yearOfPublication;

  public Integer nbOfPages;
  public Integer rank;
  public BigDecimal price;

  public URL smallImageUrl;

  public URL mediumImageUrl;


  public String description;

}
