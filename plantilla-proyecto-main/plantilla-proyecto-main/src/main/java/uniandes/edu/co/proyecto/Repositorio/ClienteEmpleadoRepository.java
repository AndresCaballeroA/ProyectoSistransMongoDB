package uniandes.edu.co.proyecto.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import uniandes.edu.co.proyecto.Modelos.ClienteEmpleado;
import uniandes.edu.co.proyecto.Modelos.ClienteEmpleadoId;

public interface ClienteEmpleadoRepository extends MongoRepository<ClienteEmpleado, ClienteEmpleadoId> {

    // Collection<ClienteEmpleado> findAllClienteEmpleados();

    // ClienteEmpleado findClienteEmpleadoById(int idEmpleado, int idCliente);

    // @Transactional
    // //@Query(value = "INSERT INTO CLIENTE_EMPLEADO (id_empleado, id_cliente) VALUES (:idEmpleado, :idCliente)", nativeQuery = true)
    // void insertClienteEmpleado(@Param("idEmpleado") Integer idEmpleado, @Param("idCliente") Integer idCliente);

    // @Transactional
    // //@Query(value = "UPDATE CLIENTE_EMPLEADO SET id_empleado = :idEmpleado, id_cliente = :idCliente WHERE id_empleado = :idEmpleado AND id_cliente = :idCliente", nativeQuery = true)
    // void updateClienteEmpleado(@Param("idEmpleado") Integer idEmpleado, @Param("idCliente") Integer idCliente);

    // @Transactional
    // //@Query(value = "DELETE FROM CLIENTE_EMPLEADO WHERE id_empleado = :idEmpleado AND id_cliente = :idCliente", nativeQuery = true)
    // void deleteClienteEmpleado(@Param("idEmpleado") Integer idEmpleado, @Param("idCliente") Integer idCliente);
}