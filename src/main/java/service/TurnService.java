package service;

import dto.TurnDTO;

import java.util.List;

public interface TurnService {
    TurnDTO save(TurnDTO user);
    void delete(TurnDTO user);
    List<TurnDTO> getAll();
    List<TurnDTO> getAllByDentistCredential(String credential);
    List<TurnDTO> getAllByPatientDni(String dni);

}
