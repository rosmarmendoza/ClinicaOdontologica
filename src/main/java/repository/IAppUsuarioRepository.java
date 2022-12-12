package repository;

import models.AppUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface IAppUsuarioRepository extends JpaRepository<AppUsuario, Long>{
    Optional<AppUsuario> findByEmail(String email);
}
