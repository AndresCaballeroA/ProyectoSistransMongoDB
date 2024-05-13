package uniandes.edu.co.proyecto.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.Modelos.Cuenta;
import uniandes.edu.co.proyecto.Modelos.OperacionCuenta;
import uniandes.edu.co.proyecto.Modelos.Cuenta;

import org.springframework.data.mongodb.repository.Query;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

public interface CuentaRepository extends MongoRepository<Cuenta, String> {

        @Query(value = "{}")
        List<Cuenta> findAllCuentas();

        @Query("{'id' : ?0}")
        Cuenta findCuentaById(String id);

        @Transactional
        default void insertCuenta(Cuenta cuenta) {
                save(cuenta);
        }

        @Transactional
        default void updateCuenta(Cuenta cuenta) {
                save(cuenta);
        }

        @Transactional
        default void deleteCuenta(String id) {
                deleteById(id);
        }

        @Transactional
        @Query("{'_id' : ?0}")
        void actualizarEstadoCuenta(@Param("_id") String idCuenta, @Param("estado") String nuevoEstado);
        
        // @Transactional
        // @Query(value = "SELECT OPERACIONCUENTA.* FROM OPERACIONCUENTA JOIN OPERACION ON OPERACIONCUENTA.ID_OPERACION = OPERACION.ID WHERE OPERACIONCUENTA.NUMEROORIGEN = :id AND TO_DATE(OPERACION.FECHAYHORA, 'dd/MM/yy') >= TRUNC(SYSDATE) - INTERVAL '30' DAY", nativeQuery = true)
        // Collection<OperacionCuenta> darOperaciones(@Param("id") Integer id);

        // @Transactional
        // @Query(value = "SELECT SUM(saldo) FROM CUENTA WHERE id = :id", nativeQuery = true)
        // int DineroCuenta(@Param("id") Integer id);

        Collection<Cuenta> findByTipoAndSaldoBetweenAndFechaCreacion(String tipo, Integer minSaldo, Integer maxSaldo, String fechaCreacion);

        @Query("{'tipo' : ?0}")
        Collection<Cuenta> darCuentasPortipo(@Param("tipo") String tipo);

        @Transactional
        Collection<Cuenta> findBySaldoBetween(Integer minSaldo, Integer maxSaldo);

        @Query("{'fechaCreacion' : ?0}")
        Collection<Cuenta> darCuentasPorFecha(@Param("fechaCreacion") String fechaCreacion);

        @Query("{'cliente' : ?0}")
        Collection<Cuenta> darCuentasPorCliente(@Param("cliente") String cliente);

        // @Query(value = "SELECT c.* FROM CUENTA c INNER JOIN CLIENTE cl ON c.id_cliente = cl.id " + "INNER JOIN CLIENTE c2 ON cl.id_oficina = c2.id_oficina " + "WHERE c2.id = :id_cliente", nativeQuery = true)
        // Collection<Cuenta> darCuentasPorOficinaDeCliente(@Param("id_cliente") Integer idCliente);
}
