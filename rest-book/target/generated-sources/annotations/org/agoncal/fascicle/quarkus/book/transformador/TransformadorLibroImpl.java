package org.agoncal.fascicle.quarkus.book.transformador;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.agoncal.fascicle.quarkus.book.modelo.Book;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleLibro;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleLibroCrear;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-10T09:32:16-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (GraalVM Community)"
)
@ApplicationScoped
public class TransformadorLibroImpl implements TransformadorLibro {

    @Override
    public TransferibleLibro toTransferible(Book book) {
        if ( book == null ) {
            return null;
        }

        TransferibleLibro transferibleLibro = new TransferibleLibro();

        transferibleLibro.setId( book.getId() );
        transferibleLibro.setTitle( book.getTitle() );
        transferibleLibro.setIsbn13( book.getIsbn13() );
        transferibleLibro.setIsbn10( book.getIsbn10() );
        transferibleLibro.setAuthor( book.getAuthor() );
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

        book.setId( dto.getId() );
        book.setTitle( dto.getTitle() );
        book.setIsbn13( dto.getIsbn13() );
        book.setIsbn10( dto.getIsbn10() );
        book.setAuthor( dto.getAuthor() );
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
        book.setAuthor( dto.getAuthor() );

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
}
