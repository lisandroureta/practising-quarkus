package org.agoncal.fascicle.quarkus.book.client;

import io.quarkus.test.Mock;
import org.agoncal.fascicle.quarkus.book.RecursoLibroTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;

@Mock
@ApplicationScoped
@RestClient
public class MockNumberProxy implements NumberProxy {

  @Override
  public IsbnNumbers generateIsbnNumbers() {
    IsbnNumbers isbnNumbers = new IsbnNumbers();
    isbnNumbers.setIsbn13(RecursoLibroTest.MOCK_ISBN_13);
    isbnNumbers.setIsbn10(RecursoLibroTest.MOCK_ISBN_10);
    return isbnNumbers;
  }
}
