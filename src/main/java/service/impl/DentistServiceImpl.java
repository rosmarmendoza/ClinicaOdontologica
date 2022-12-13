package service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.DentistDTO;
import models.Dentist;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import repository.IDentistRepository;
import service.DentistService;

import java.util.List;

@Service
public class DentistServiceImpl implements DentistService {
    private final IDentistRepository dentistRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    private final Logger log = (Logger) LoggerFactory.getLogger(DentistServiceImpl.class);

    public DentistServiceImpl(IDentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    @Override
    public DentistDTO save(DentistDTO dentistDTO) {
        return mapToDto(dentistRepository.save(mapToEntity(dentistDTO)));
    }

    @Override
    public void delete(String credential) {
        log.info("Request to delete Dentist : {}");
        Dentist dentist = dentistRepository.findByCredential(credential);
        dentistRepository.delete(dentist);
    }

    @Override
    public DentistDTO getByCredential(String credential) {
        log.info("Request to get Dentist : {}");
        return mapToDto(dentistRepository.findByCredential(credential));
    }

    @Override
    public List<DentistDTO> getAll() {
        return null;
    }

    private DentistDTO mapToDto (Dentist dentist) {
        return mapper.convertValue(dentist, DentistDTO.class);
    }

    private Dentist mapToEntity (DentistDTO dentistDTO) {
        return mapper.convertValue(dentistDTO, Dentist.class);
    }


}
