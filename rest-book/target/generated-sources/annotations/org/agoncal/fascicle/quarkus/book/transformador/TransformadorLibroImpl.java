package org.agoncal.fascicle.quarkus.book.transformador;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.agoncal.fascicle.quarkus.book.modelo.Author;
import org.agoncal.fascicle.quarkus.book.modelo.Book;
import org.agoncal.fascicle.quarkus.book.modelo.Category;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleAutor;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleLibro;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleLibroCrear;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-16T12:48:44-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 17.0.9 (GraalVM Community)"
)
@ApplicationScoped
public class TransformadorLibroImpl implements TransformadorLibro {

    @Inject
    private TransformadorAutor transformadorAutor;

    @Override
    public TransferibleLibro toTransferible(Book book) {
        if ( book == null ) {
            return null;
        }

        TransferibleLibro transferibleLibro = new TransferibleLibro();

        transferibleLibro.setCategoryName( bookCategoryNombre( book ) );
        transferibleLibro.setAuthors( transformadorAutor.toTransferibleList( book.getAuthors() ) );
        transferibleLibro.setId( book.getId() );
        transferibleLibro.setTitle( book.getTitle() );
        transferibleLibro.setIsbn13( book.getIsbn13() );
        transferibleLibro.setIsbn10( book.getIsbn10() );
        transferibleLibro.setYearOfPublication( book.getYearOfPublication() );
        transferibleLibro.setNbOfPages( book.getNbOfPages() );
        transferibleLibro.setRank( book.getRank() );
        transferibleLibro.setPrice( book.getPrice() );
        transferibleLibro.setSmallImageUrl( book.getSmallImageUrl() );
        transferibleLibro.setMediumImageUrl( book.getMediumImageUrl() );
        transferibleLibro.setDescription( book.getDescription() );

        return transferibleLibro;
    }

    @Override
    public Book toEntity(TransferibleLibro dto) {
        if ( dto == null ) {
            return null;
        }

        Book book = new Book();

        book.setAuthors( transferibleAutorListToAuthorList( dto.getAuthors() ) );
        book.setId( dto.getId() );
        book.setTitle( dto.getTitle() );
        book.setIsbn13( dto.getIsbn13() );
        book.setIsbn10( dto.getIsbn10() );
        book.setYearOfPublication( dto.getYearOfPublication() );
        book.setNbOfPages( dto.getNbOfPages() );
        book.setRank( dto.getRank() );
        book.setPrice( dto.getPrice() );
        book.setSmallImageUrl( dto.getSmallImageUrl() );
        book.setMediumImageUrl( dto.getMediumImageUrl() );
        book.setDescription( dto.getDescription() );

        return book;
    }

    @Override
    public Book toEntity(TransferibleLibroCrear dto) {
        if ( dto == null ) {
            return null;
        }

        Book book = new Book();

        book.setTitle( dto.getTitle() );
        book.setYearOfPublication( dto.getYearOfPublication() );
        book.setNbOfPages( dto.getNbOfPages() );
        book.setRank( dto.getRank() );
        book.setPrice( dto.getPrice() );
        book.setDescription( dto.getDescription() );

        return book;
    }

    @Override
    public List<TransferibleLibro> toTransferibleList(List<Book> books) {
        if ( books == null ) {
            return null;
        }

        List<TransferibleLibro> list = new ArrayList<TransferibleLibro>( books.size() );
        for ( Book book : books ) {
            list.add( toTransferible( book ) );
        }

        return list;
    }

    @Override
    public List<Book> toEntityList(List<TransferibleLibro> transferibleLibroList) {
        if ( transferibleLibroList == null ) {
            return null;
        }

        List<Book> list = new ArrayList<Book>( transferibleLibroList.size() );
        for ( TransferibleLibro transferibleLibro : transferibleLibroList ) {
            list.add( toEntity( transferibleLibro ) );
        }

        return list;
    }

    private String bookCategoryNombre(Book book) {
        Category category = book.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getNombre();
    }

    protected Author transferibleAutorToAuthor(TransferibleAutor transferibleAutor) {
        if ( transferibleAutor == null ) {
            return null;
        }

        Author author = new Author();

        author.setId( transferibleAutor.getId() );
        author.setNombre( transferibleAutor.getNombre() );
        author.setApellido( transferibleAutor.getApellido() );
        author.setNacionalidad( transferibleAutor.getNacionalidad() );

        return author;
    }

    protected List<Author> transferibleAutorListToAuthorList(List<TransferibleAutor> list) {
        if ( list == null ) {
            return null;
        }

        List<Author> list1 = new ArrayList<Author>( list.size() );
        for ( TransferibleAutor transferibleAutor : list ) {
            list1.add( transferibleAutorToAuthor( transferibleAutor ) );
        }

        return list1;
    }
}
