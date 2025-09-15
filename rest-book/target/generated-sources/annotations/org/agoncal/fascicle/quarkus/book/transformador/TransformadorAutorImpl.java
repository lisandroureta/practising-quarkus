package org.agoncal.fascicle.quarkus.book.transformador;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.agoncal.fascicle.quarkus.book.modelo.Author;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleAutor;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleAutorCrear;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-15T10:45:39-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (GraalVM Community)"
)
@ApplicationScoped
public class TransformadorAutorImpl implements TransformadorAutor {

    @Override
    public TransferibleAutor toTransferible(Author author) {
        if ( author == null ) {
            return null;
        }

        TransferibleAutor transferibleAutor = new TransferibleAutor();

        transferibleAutor.setId( author.getId() );
        transferibleAutor.setNombre( author.getNombre() );
        transferibleAutor.setApellido( author.getApellido() );
        transferibleAutor.setNacionalidad( author.getNacionalidad() );

        return transferibleAutor;
    }

    @Override
    public List<TransferibleAutor> toTransferibleList(List<Author> authors) {
        if ( authors == null ) {
            return null;
        }

        List<TransferibleAutor> list = new ArrayList<TransferibleAutor>( authors.size() );
        for ( Author author : authors ) {
            list.add( toTransferible( author ) );
        }

        return list;
    }

    @Override
    public Author toEntity(TransferibleAutorCrear dto) {
        if ( dto == null ) {
            return null;
        }

        Author author = new Author();

        author.setNombre( dto.getNombre() );
        author.setApellido( dto.getApellido() );
        author.setNacionalidad( dto.getNacionalidad() );

        return author;
    }
}
