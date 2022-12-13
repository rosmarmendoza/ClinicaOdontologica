package controllers;
import dto.PatientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PatientService;

import java.util.List;


@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patient) {
        PatientDTO savedUser = patientService.save(patient);
        return ResponseEntity.ok().body(savedUser);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<PatientDTO> getByDni(@PathVariable String dni) {
        PatientDTO patient = patientService.getByDni(dni);
        return ResponseEntity.ok().body(patient);
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        List<PatientDTO> dentistDtos = patientService.getAll();
        return ResponseEntity.ok().body(dentistDtos);
    }
}
