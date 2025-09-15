package org.agoncal.fascicle.quarkus.book.transformador;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.agoncal.fascicle.quarkus.book.modelo.Category;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleCategoria;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleCategoriaCrear;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-15T10:45:39-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (GraalVM Community)"
)
@ApplicationScoped
public class TransformadorCategoriaImpl implements TransformadorCategoria {

    @Override
    public TransferibleCategoria toTransferible(Category category) {
        if ( category == null ) {
            return null;
        }

        TransferibleCategoria transferibleCategoria = new TransferibleCategoria();

        transferibleCategoria.setId( category.getId() );
        transferibleCategoria.setNombre( category.getNombre() );

        return transferibleCategoria;
    }

    @Override
    public List<TransferibleCategoria> toTransferibleList(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<TransferibleCategoria> list = new ArrayList<TransferibleCategoria>( categories.size() );
        for ( Category category : categories ) {
            list.add( toTransferible( category ) );
        }

        return list;
    }

    @Override
    public Category toEntity(TransferibleCategoriaCrear dto) {
        if ( dto == null ) {
            return null;
        }

        Category category = new Category();

        category.setNombre( dto.getNombre() );

        return category;
    }
}
