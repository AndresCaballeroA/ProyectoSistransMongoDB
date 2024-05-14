package uniandes.edu.co.proyecto.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import uniandes.edu.co.proyecto.Modelos.Prestamo;

public interface PrestamoRepository extends MongoRepository<Prestamo, Integer> {

//        //@Query(value = "SELECT * FROM PRESTAMO", nativeQuery = true)
//         Collection<Prestamo> findAllPrestamos();

//         //@Query(value = "SELECT * FROM PRESTAMO WHERE id = :id", nativeQuery = true)
//         Prestamo findPrestamoById(@Param("id") int id);

//         //@Modifying
//         @Transactional
//         //@Query(value = "INSERT INTO PRESTAMO (id, estado, tipo, monto, interes, numCuotas, numMes, valorCuota, id_cliente) VALUES (BancAndes_secuencia_prestamo.nextval, :estado, :tipo, :monto, :interes, :numCuotas, :numMes, :valorCuota, :id_cliente)", nativeQuery = true)
//         void insertPrestamo(@Param("estado") String estado, @Param("tipo") String tipo,
//                         @Param("monto") Integer monto, @Param("interes") Float interes,
//                         @Param("numCuotas") Integer numCuotas,
//                         @Param("numMes") Integer numMes, @Param("valorCuota") Integer valorCuota,
//                         @Param("id_cliente") Integer id_cliente);

//         //@Modifying
//         @Transactional
//         //@Query(value = "UPDATE PRESTAMO SET estado = :estado, tipo = :tipo, monto = :monto, interes = :interes, numCuotas = :numCuotas, numMes = :numMes, valorCuota = :valorCuota, id_cliente = :id_cliente WHERE id = :id", nativeQuery = true)
//         void updatePrestamo(@Param("id") Integer id, @Param("estado") String estado, @Param("tipo") String tipo,
//                         @Param("monto") Integer monto, @Param("interes") Float interes,
//                         @Param("numCuotas") Integer numCuotas,
//                         @Param("numMes") Integer numMes, @Param("valorCuota") Integer valorCuota,
//                         @Param("id_cliente") Integer id_cliente);

//         //@Modifying
//         @Transactional
//         //@Query(value = "DELETE FROM PRESTAMO WHERE id = :id", nativeQuery = true)
//         void deletePrestamo(@Param("id") Integer id);

//         @Transactional
//         //@Query(value = "SELECT DISTINCT C.* FROM PRESTAMO C WHERE " + "(C.id_cliente = :id_cliente) ", nativeQuery = true)
//         Collection<Prestamo> darPrestamosPorCliente(@Param("id_cliente") Integer id_cliente);

//         //@Query(value = "SELECT p.* FROM PRESTAMO p " + "INNER JOIN CLIENTE cl ON p.id_cliente = cl.id " + "INNER JOIN CLIENTE c2 ON cl.id_oficina = c2.id_oficina " + "WHERE c2.id = :id_cliente", nativeQuery = true)
//         Collection<Prestamo> darPrestamosPorOficinaDeCliente(@Param("id_cliente") Integer idCliente);

//         //@Modifying
//         @Transactional
//         //@Query(value = "UPDATE Prestamo SET monto = monto - :monto WHERE id = :id", nativeQuery = true)
//         void retirarDinero(@Param("id") Integer id, @Param("monto") Integer monto);

//         //@Modifying
//         @Transactional
//         //@Query(value = "INSERT INTO OPERACIONPRESTAMO (id, numPrestamo, montoPago, id_operacion) VALUES (BancAndes_secuencia_operacioncuenta.nextval, :numPrestamo, :montoPago, :id_operacion)", nativeQuery = true)
//         void insertOperacionPrestamo(@Param("numPrestamo") Integer numPrestamo,
//                         @Param("montoPago") Integer montoPago, @Param("id_operacion") Integer id_operacion);

                }
