package uniandes.edu.co.proyecto.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.proyecto.Modelos.OperacionCuenta;
import org.bson.Document;

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

        @Query("{'$and': [{'numeroOrigen': ?0}, {'id_operacion.fechaYHora': {$regex: ?1}}]}")
        Collection<OperacionCuenta> findByNumeroOrigenAndMonth(Integer numeroOrigen, String mes);

        @Query("{'numeroOrigen': ?0}")
        Collection<OperacionCuenta> findByNumeroOrigen(Integer numeroOrigen);

        @Query("{'id_operacion.fechaYHora': {$regex: ?0}}")
        Collection<OperacionCuenta> findByMonth(String mes);

        @Query("{'$and': [{'id_operacion.tipoOperacion': 'CONSIGNACION'}, {'numeroDestino': ?0}, {'id_operacion.fechaYHora': {$regex: ?1}}]}")
        Integer sumConsignacionesByNumeroDestinoAndMonth(Integer numeroCuenta, String mes);

        @Query("{'$and': [{'id_operacion.tipoOperacion': 'RETIRO'}, {'numeroOrigen': ?0}, {'id_operacion.fechaYHora': {$regex: ?1}}]}")
        Integer sumRetirosByNumeroOrigenAndMonth(Integer numeroCuenta, String mes);

        @Query("{'$and': [{'id_operacion.tipoOperacion': 'TRANSFERENCIA'}, {'numeroOrigen': ?0}, {'numeroDestino': {$ne: ?0}}, {'id_operacion.fechaYHora': {$regex: ?1}}]}")
        Integer sumTransferenciasByNumeroOrigenAndMonth(Integer numeroCuenta, String mes);

        @Query(value = "{'$and': [{'id_operacion.tipoOperacion': 'CONSIGNACION'}, {'numeroDestino': ?1}, {'id_operacion.fechaYHora': {$regex: ?0}}]}", fields = "{'monto': 1, '_id': 0}")
        Document sumConsignacionesByMonthAndNumeroDestino(String mes, Integer numeroCuenta);

        default Integer sumConsignacionesByMonthAndNumeroDestinoTotal(String mes, Integer numeroCuenta) {
                Document result = sumConsignacionesByMonthAndNumeroDestino(mes, numeroCuenta);
                Integer total = 0;
                if (result != null && result.containsKey("monto")) {
                        total = result.getInteger("monto");
                }
                return total;
        }

        @Query(value = "{'$and': [{'id_operacion.tipoOperacion': 'RETIRO'}, {'numeroOrigen': ?1}, {'id_operacion.fechaYHora': {$regex: ?0}}]}", fields = "{'monto': 1, '_id': 0}")
        Document sumRetirosByMonthAndNumeroOrigen(String mes, Integer numeroCuenta);

        default Integer sumRetirosByMonthAndNumeroOrigenTotal(String mes, Integer numeroCuenta) {
                Document result = sumRetirosByMonthAndNumeroOrigen(mes, numeroCuenta);
                Integer total = 0;
                if (result != null && result.containsKey("monto")) {
                        total = result.getInteger("monto");
                }
                return total;
        }

        @Query(value = "{'$and': [{'id_operacion.tipoOperacion': 'TRANSFERENCIA'}, {'numeroOrigen': ?1}, {'numeroDestino': {$ne: ?1}}, {'id_operacion.fechaYHora': {$regex: ?0}}]}", fields = "{'monto': 1, '_id': 0}")
        Document sumTransferenciasByMonthAndNumeroOrigen(String mes, Integer numeroCuenta);

        default Integer sumTransferenciasByMonthAndNumeroOrigenTotal(String mes, Integer numeroCuenta) {
                Document result = sumTransferenciasByMonthAndNumeroOrigen(mes, numeroCuenta);
                Integer total = 0;
                if (result != null && result.containsKey("monto")) {
                        total = result.getInteger("monto");
                }
                return total;
        }
        




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
