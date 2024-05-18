package uniandes.edu.co.proyecto.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.mongodb.repository.Query;
import uniandes.edu.co.proyecto.Modelos.Empleado;

import java.util.List;

public interface EmpleadoRepository extends MongoRepository<Empleado, String> {

        @Query(value = "{}")
        List<Empleado> findAllEmpleados();

        @Query("{id : ?0}")
        Empleado findEmpleadoById(String id);

        @Transactional
        default void insertEmpleado(Empleado empleado) {
                save(empleado);
        }

        @Transactional
        default void updateEmpleado(Empleado empleado) {
                save(empleado);
        }

        @Transactional
        default void deleteEmpleado(String id) {
                deleteById(id);
        }

    // // //@Query(value = "SELECT * FROM CLIENTE INNER JOIN CUENTA ON CLIENTE.id =
    // // CUENTA.id INNER JOIN OFICINA ON CUENTA.id = OFICINA.id INNER JOIN PRESTAMO ON
    // // CLIENTE.id = PRESTAMO.id WHERE EMPLEADO.id = :empleadoid AND :rol =
    // // 'gerente_general'")
    // // Collection<Cliente> darInformacionClientesGerenteGeneral(@Param("empleadoId")
    // // String empleadoId, @Param("rol") String rol);

    // // //@Query(value = "SELECT * FROM CLIENTE INNER JOIN CUENTA ON CLIENTE.id =
    // // CUENTA.id INNER JOIN OFICINA ON CUENTA.id = OFICINA.id INNER JOIN PRESTAMO ON
    // // CLIENTE.id = PRESTAMO.id WHERE OFICINA.id = (SELECT oficina_id FROM EMPLEADO
    // // WHERE id = :empleadoId) AND :rol = 'gerente_oficina'")
    // // Collection<Cliente> darInformacionClientesGerenteOficina(@Param("empleadoId")
    // // String empleadoId, @Param("rol") String rol);
}
