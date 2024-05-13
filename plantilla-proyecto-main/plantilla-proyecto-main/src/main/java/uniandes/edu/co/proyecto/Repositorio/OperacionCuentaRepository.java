package uniandes.edu.co.proyecto.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.data.mongodb.repository.Query;
import uniandes.edu.co.proyecto.Modelos.OperacionCuenta;
import uniandes.edu.co.proyecto.Modelos.OperacionCuenta;

import java.util.Collection;
import java.util.List;

public interface OperacionCuentaRepository extends MongoRepository<OperacionCuenta, String> {

        @Query(value = "{}")
        List<OperacionCuenta> findAllOperacionCuentas();

        @Query("{'id' : ?0}")
        OperacionCuenta findOperacionCuentaById(String id);

        @Transactional
        default void insertOperacionCuenta(OperacionCuenta operacionCuenta) {
                save(operacionCuenta);
        }

        @Transactional
        default void updateOperacionCuenta(OperacionCuenta operacionCuenta) {
                save(operacionCuenta);
        }

        @Transactional
        default void deleteOperacionCuenta(String id) {
                deleteById(id);
        }

        
        // @Transactional
        // //@Query(value = "UPDATE Cuenta SET estado = CERRADO WHERE id = :id AND saldo = 0", nativeQuery = true)
        // void cerrarCuenta(@Param("id") Integer id);

        
        // @Transactional
        // //@Query(value = "UPDATE Cuenta SET estado = DESACTIVADO WHERE id = :id", nativeQuery = true)
        // void desactivarCuenta(@Param("id") Integer id);

        
        // @Transactional
        // //@Query(value = "UPDATE Cuenta SET saldo = saldo + :monto WHERE id = :id", nativeQuery = true)
        // void consignarDinero(@Param("id") Integer id, @Param("monto") Integer monto);

        
        // @Transactional
        // //@Query(value = "UPDATE Cuenta SET saldo = saldo - :monto WHERE id = :id AND saldo >= :monto", nativeQuery = true)
        // void retirarDinero(@Param("id") Integer id, @Param("monto") Integer monto);

        @Query("{'id_operacion.fechaYHora': ?0}")
        Collection<OperacionCuenta> findById_operacionFechaYHora(String fechaYHora);

        // @Transactional
        // //@Query(value = "SELECT OC.* FROM OPERACIONCUENTA OC  INNER JOIN OPERACION OP ON OC.ID_OPERACION = OP.ID WHERE numeroOrigen = :id AND EXTRACT(MONTH FROM fechaYHora) = :mes", nativeQuery = true)
        // Collection<OperacionCuenta> FiltrarPorCM(@Param("id") Integer id, @Param("mes") Integer mes);

        // @Transactional
        // //@Query(value = "SELECT OC.* FROM OPERACIONCUENTA OC  INNER JOIN OPERACION OP ON OC.ID_OPERACION = OP.ID WHERE numeroOrigen = :id", nativeQuery = true)
        // Collection<OperacionCuenta> FiltrarPorC(@Param("id") Integer id);

        // @Transactional
        // //@Query(value = "SELECT OC.* FROM OPERACIONCUENTA OC  INNER JOIN OPERACION OP ON OC.ID_OPERACION = OP.ID WHERE EXTRACT(MONTH FROM fechaYHora) = :mes", nativeQuery = true)
        // Collection<OperacionCuenta> FiltrarPorM(@Param("mes") Integer mes);

        // @Transactional
        // //@Query(value = "SELECT saldo FROM OPERACIONCUENTA OC  INNER JOIN OPERACION OP ON OC.ID_OPERACION = OP.ID WHERE EXTRACT(MONTH FROM fechaYHora) = :mes", nativeQuery = true)
        // Integer Sacarsaldo();

        // //@Query(value = "SELECT SUM(monto) FROM OPERACIONCUENTA WHERE numeroDestino = :numeroCuenta", nativeQuery = true)
        // Integer calcularSumaSaldosPorNumeroCuentaDestino(@Param("numeroCuenta") Integer numeroCuenta);

        // //@Query(value = "SELECT SUM(OC.monto) FROM OPERACIONCUENTA OC INNER JOIN OPERACION OP ON OC.ID_OPERACION = OP.ID WHERE OC.numeroOrigen = :numeroCuenta AND OP.tipooperacion = 'RETIRO'", nativeQuery = true)
        // Integer calcularSumaSaldosRetirosPorNumeroCuentaOrigen(@Param("numeroCuenta") Integer numeroCuenta);

        // //@Query(value = "SELECT SUM(OC.monto) FROM OPERACIONCUENTA OC INNER JOIN OPERACION OP ON OC.ID_OPERACION = OP.ID WHERE OC.numeroOrigen = :numeroCuenta AND OC.numeroDestino <> :numeroCuenta AND OP.tipooperacion = 'TRANSFERENCIA'", nativeQuery = true)
        // Integer calcularSumaTransferenciasSalientes(@Param("numeroCuenta") Integer numeroCuenta);


}
