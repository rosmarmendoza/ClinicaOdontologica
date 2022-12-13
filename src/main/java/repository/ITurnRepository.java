package repository;

import models.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITurnRepository extends JpaRepository<Turn, Long> {
    List<Turn> findAllByDentistCredential(String credential);

    List<Turn> findAllByPatientDni(String dni);
}
