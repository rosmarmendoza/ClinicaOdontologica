package service;

import dto.DentistDTO;

import java.util.List;

public interface DentistService {
    DentistDTO save(DentistDTO dentistDTO);
    void delete(String credential);
    DentistDTO getByCredential(String credential);
    List<DentistDTO> getAll();
}
