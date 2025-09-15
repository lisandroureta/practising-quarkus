package org.agoncal.fascicle.quarkus.book.transformador;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.agoncal.fascicle.quarkus.book.modelo.Comment;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleComentario;
import org.agoncal.fascicle.quarkus.book.transferible.TransferibleComentarioCrear;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-15T10:45:39-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (GraalVM Community)"
)
@ApplicationScoped
public class TransformadorComentarioImpl implements TransformadorComentario {

    @Override
    public TransferibleComentario toTransferible(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        TransferibleComentario transferibleComentario = new TransferibleComentario();

        transferibleComentario.setId( comment.getId() );
        transferibleComentario.setTexto( comment.getTexto() );
        transferibleComentario.setPuntuacion( comment.getPuntuacion() );

        return transferibleComentario;
    }

    @Override
    public List<TransferibleComentario> toTransferibleList(List<Comment> comments) {
        if ( comments == null ) {
            return null;
        }

        List<TransferibleComentario> list = new ArrayList<TransferibleComentario>( comments.size() );
        for ( Comment comment : comments ) {
            list.add( toTransferible( comment ) );
        }

        return list;
    }

    @Override
    public Comment toEntity(TransferibleComentarioCrear dto) {
        if ( dto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setTexto( dto.getTexto() );
        comment.setPuntuacion( dto.getPuntuacion() );

        return comment;
    }
}
