package uniandes.edu.co.proyecto.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.Modelos.OperacionPrestamo;
import org.springframework.data.mongodb.repository.Query;
import java.util.Collection;

public interface OperacionPrestamoRepository extends MongoRepository<OperacionPrestamo, Integer> {

        // //@Query(value = "SELECT * FROM OPERACIONPRESTAMO", nativeQuery = true)
        // Collection<OperacionPrestamo> findAllOperacionPrestamos();

        // //@Query(value = "SELECT * FROM OPERACIONPRESTAMO WHERE id = :id", nativeQuery = true)
        // OperacionPrestamo findOperacionPrestamoById(@Param("id") int id);

        
        // @Transactional
        // //@Query(value = "INSERT INTO OPERACIONPRESTAMO (id, numPrestamo, montoPago, id_operacion) VALUES (BancAndes_secuencia_operacionprestamo.nextval, :numPrestamo, :montoPago, :id_operacion)", nativeQuery = true)
        // void insertOperacionPrestamo(@Param("numPrestamo") Integer numPrestamo,
        //                 @Param("montoPago") Integer montoPago, @Param("id_operacion") Integer id_operacion);

        
        // @Transactional
        // //@Query(value = "UPDATE OPERACIONPRESTAMO SET numPrestamo = :numPrestamo, montoPago = :montoPago WHERE id = :id", nativeQuery = true)
        // void updateOperacionPrestamo(@Param("id") Integer id, @Param("numPrestamo") Integer numPrestamo,
        //                 @Param("montoPago") Integer montoPago);

        
        // @Transactional
        // //@Query(value = "DELETE FROM OPERACIONPRESTAMO WHERE id = :id", nativeQuery = true)
        // void deleteOperacionPrestamo(@Param("id") Integer id);
}
