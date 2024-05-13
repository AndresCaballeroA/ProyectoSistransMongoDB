package uniandes.edu.co.proyecto.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import uniandes.edu.co.proyecto.Modelos.Cliente;
import uniandes.edu.co.proyecto.Modelos.Cliente;

import java.util.Collection;
import java.util.List;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

        @Query(value = "{}")
        List<Cliente> findAllClientes();

        @Query("{'id' : ?0}")
        Cliente findClienteById(String id);

        @Transactional
        default void insertCliente(Cliente cliente) {
                save(cliente);
        }

        @Transactional
        default void updateCliente(Cliente cliente) {
                save(cliente);
        }

        @Transactional
        default void deleteCliente(String id) {
                deleteById(id);
        }
    
    // @Transactional
    // @Query(value = "SELECT C.* FROM CLIENTE C WHERE " + "(C.id = :id) ", nativeQuery = true)
    // Collection<Cliente> darCuentasPorCliente(@Param("id") Integer id);

    // @Query(value = "SELECT c.* FROM CLIENTE c INNER JOIN CLIENTE c2 ON c.id_oficina = c2.id_oficina " + "WHERE c2.id = :id_cliente", nativeQuery = true)
    // Collection<Cliente> darClientesPorOficinaDeCliente(@Param("id_cliente") Integer idCliente);

}
