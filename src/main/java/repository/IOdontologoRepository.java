package repository;


import models.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {

}
