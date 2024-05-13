package uniandes.edu.co.proyecto.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.Modelos.PuntoDeAtencion;
import uniandes.edu.co.proyecto.Modelos.PuntoDeAtencion;

import org.springframework.data.mongodb.repository.Query;
import java.util.Collection;
import java.util.List;

public interface PuntoDeAtencionRepository extends MongoRepository<PuntoDeAtencion, String> {

        @Query(value = "{}")
        List<PuntoDeAtencion> findAllPuntoDeAtencion();

        @Query("{'id' : ?0}")
        PuntoDeAtencion findPuntoDeAtencionById(String id);

        @Transactional
        default void insertPuntoDeAtencion(PuntoDeAtencion puntoDeAtencion) {
                save(puntoDeAtencion);
        }

        @Transactional
        default void updatePuntoDeAtencion(PuntoDeAtencion puntoDeAtencion) {
                save(puntoDeAtencion);
        }

        @Transactional
        default void deletePuntoDeAtencion(String id) {
                deleteById(id);
        }
}
