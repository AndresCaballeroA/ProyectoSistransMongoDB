package uniandes.edu.co.proyecto.Repositorio;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uniandes.edu.co.proyecto.Modelos.Oficina;
import java.util.List;

public interface OficinaRepository extends MongoRepository<Oficina, String> {

        @Query(value = "{}")
        List<Oficina> findAllOficinas();

        @Query("{'id' : ?0}")
        Oficina findOficinaById(String id);

        @Transactional
        default void insertOficina(Oficina oficina) {
                save(oficina);
        }

        @Transactional
        default void updateOficina(Oficina oficina) {
                save(oficina);
        }

        @Transactional
        default void deleteOficina(String id) {
                deleteById(id);
        }

        // @Transactional
        // @Query(value = "SELECT * FROM OFICINA WHERE nombre = :nombre AND direccion = :direccion AND numPuntosPosibles = :numPuntosPosibles AND gerente = :gerente", nativeQuery = true)
        // Oficina findOficinaExists(@Param("nombre") String nombre, @Param("direccion") String direccion, @Param("numPuntosPosibles") Integer numPuntosPosibles, @Param("gerente") int gerenteId);

}
