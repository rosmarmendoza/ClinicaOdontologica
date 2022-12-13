package controllers;

import dto.DentistDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.DentistService;

import java.util.List;

@RestController
@RequestMapping("/dentists")
public class DentistController {
    private final DentistService dentistService;

    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @PostMapping
    public ResponseEntity<DentistDTO> createDentist(@RequestBody DentistDTO dentistDTO) {
        DentistDTO savedUser = dentistService.save(dentistDTO);
        return ResponseEntity.ok().body(savedUser);
    }

    @GetMapping("/{credential}")
    public ResponseEntity<DentistDTO> getDentistByCredential(@PathVariable String credential) {
        DentistDTO dentist = dentistService.getByCredential(credential);
        return ResponseEntity.ok().body(dentist);
    }

    @GetMapping
    public ResponseEntity<List<DentistDTO>> getAllDentists() {
        List<DentistDTO> dentistDtos = dentistService.getAll();
        return ResponseEntity.ok().body(dentistDtos);
    }
}
