package service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.TurnController;
import dto.TurnDTO;
import models.Dentist;
import models.Patient;
import models.Turn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import repository.IDentistRepository;
import repository.IPatientRepository;
import repository.ITurnRepository;
import service.TurnService;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurnServiceImpl implements TurnService {

    private final ObjectMapper mapper = new ObjectMapper();

    private final ITurnRepository iTurnRepository;

    private final IPatientRepository iPatientRepository;

    private final IDentistRepository iDentistRepository;


    public TurnServiceImpl(ITurnRepository iTurnRepository, IPatientRepository iPatientRepository, repository.IDentistRepository iDentistRepository) {
        this.iTurnRepository = iTurnRepository;
        this.iPatientRepository = iPatientRepository;
        this.iDentistRepository = iDentistRepository;
    }

    private final Logger log = LoggerFactory.getLogger(TurnController.class);
    @Override
    public TurnDTO save(TurnDTO turnDTO) {
        Patient patient = iPatientRepository.findByDni(turnDTO.getPatientDTO().getDni());
        Dentist dentist = iDentistRepository.findByCredential(turnDTO.getDentistDTO().getCredential());
        Turn turn = mapToEntity(turnDTO);
        turn.setPatient(patient);
        turn.setDentist(dentist);
        log.info("Request to save Appointment : {}", turn);
        return mapToDto(iTurnRepository.save(turn));
    }

    @Override
    public void delete(TurnDTO user) {
        iTurnRepository.delete(mapToEntity(user));
    }

    @Override
    public List<TurnDTO> getAll() {
        log.info("Request to get all Appointments");
        return iTurnRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<TurnDTO> getAllByDentistCredential(String credential) {
        log.info("Request to get all Appointments by Dentist credential {}", credential);
        return iTurnRepository.findAllByDentistCredential(credential).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<TurnDTO> getAllByPatientDni(String dni) {
        log.info("Request to get all Appointments by Patient dni {}", dni);
        return iTurnRepository.findAllByPatientDni(dni).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private TurnDTO mapToDto (Turn turn) {
        return mapper.convertValue(turn, TurnDTO.class);
    }

    private Turn mapToEntity (TurnDTO turnDTO) {
        return mapper.convertValue(turnDTO, Turn.class);
    }
}
