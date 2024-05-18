package uniandes.edu.co.proyecto.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.Modelos.Usuario;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

        @Query(value = "{}")
        List<Usuario> findAllUsuarios();

        @Query("{'id' : ?0}")
        Usuario findUsuarioById(String id);

        @Transactional
        default void insertUsuario(Usuario usuario) {
                save(usuario);
        }

        @Transactional
        default void updateUsuario(Usuario usuario) {
                save(usuario);
        }

        @Transactional
        default void deleteUsuario(String id) {
                deleteById(id);
        }

}
