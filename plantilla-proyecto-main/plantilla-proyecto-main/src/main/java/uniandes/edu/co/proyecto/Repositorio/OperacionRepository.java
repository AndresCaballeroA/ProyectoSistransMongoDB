package uniandes.edu.co.proyecto.Repositorio;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import uniandes.edu.co.proyecto.Modelos.Operacion;
import java.util.List;

public interface OperacionRepository extends MongoRepository<Operacion, String> {

        @Query(value = "{}")
        List<Operacion> findAllOperacions();

        @Query("{'id' : ?0}")
        Operacion findOperacionById(String id);

        @Transactional
        default void insertOperacion(Operacion operacion) {
                save(operacion);
        }

        @Transactional
        default void updateOperacion(Operacion operacion) {
                save(operacion);
        }

        @Transactional
        default void deleteOperacion(String id) {
                deleteById(id);
        }

        // @Transactional
        // //@Query(value = "SELECT CUENTA.saldo, OPERACIONCUENTA.*, OPERACION.fecha, OPERACION.tipo FROM OPERACION INNER JOIN OPERACIONCUENTA ON OPERACION.ID = OPERACIONCUENTA.ID_OPERACION INNER JOIN CUENTA ON OPERACIONCUENTA.ID_CUENTA = CUENTA.ID WHERE CUENTA.ID = :idCuenta AND MONTH(OPERACION.fecha) = :mes ORDER BY OPERACION.fecha ASC", nativeQuery = true)
        // Collection<OperacionCuenta> darTodasLasOperacionesPorCuenta(@Param("idCuenta") String idCuenta,
        //                 @Param("mes") int mes);

        // @Transactional
        // //@Query(value = "SELECT * FROM OPERACION WHERE id = (SELECT MAX(id) FROM OPERACION)", nativeQuery = true)
        // Operacion findLastInsertedOperacion();


}
