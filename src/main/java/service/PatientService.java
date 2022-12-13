package service;

import dto.PatientDTO;
import models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface PatientService extends JpaRepository<Patient, Long> {

    PatientDTO save(PatientDTO patientDTO);
    void delete(PatientDTO patientDTO);
    PatientDTO getByDni(String dni);
    List<PatientDTO> getAll();
}
