package service;


import com.fasterxml.jackson.databind.ObjectMapper;
import dto.OdontologoDTO;
import exception.BadRequestException;
import exception.EntityNotFoundException;
import models.Odontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.IOdontologoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private IOdontologoRepository odontologoRepository;
    ObjectMapper mapper;

    @Autowired
    public OdontologoService(IOdontologoRepository odontologoRepository, ObjectMapper mapper) {
        this.odontologoRepository = odontologoRepository;
        this.mapper = mapper;
    }

    //métodos
    //1. Guardar un odontólogo en la base de datos.
    public Odontologo guardarOdontologo(Odontologo odontologo) throws BadRequestException {
       if(odontologo == null ||odontologo.getNombre() == null || odontologo.getApellido() == null || odontologo.getMatricula() == null){
           throw new BadRequestException("Los datos ingresados para registrar el odontólogo no son correctos o están incompletos. Debe incluir obligatoriamente: nombre, apellido y matricula del odontólogo.");
       } else {
           return odontologoRepository.save(odontologo);
       }
    }

    //2. Registrar un odontólogo en la base de datos
    public Odontologo registrarOdontologo(Odontologo odontologo) throws BadRequestException{
        return guardarOdontologo(odontologo);
    }

    //3. Buscar un odontólogo por id.
    public OdontologoDTO buscarOdontologoPorId(Long id) throws EntityNotFoundException {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        OdontologoDTO odontologoDTO = null;
        if(odontologo.isEmpty()){
            throw new EntityNotFoundException("El odontologo con el id: " + id + " no se encuentra en la base de datos");
        } else{
            odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);
        }

        return odontologoDTO;
    }

    public List<OdontologoDTO> buscarTodosOdontologos() throws EntityNotFoundException {

        List<Odontologo> odontologos = odontologoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        List<OdontologoDTO> odontologosDTO = new ArrayList<OdontologoDTO>();
        if(odontologos.isEmpty()) {
            throw new EntityNotFoundException("No se encuentran odontólogos en la base de datos");
        } else {
            for (Odontologo odontologo : odontologos) {
                odontologosDTO.add(mapper.convertValue(odontologo, OdontologoDTO.class));
            }
        }
        return odontologosDTO;
    }

    public Odontologo actualizarOdontologo(Odontologo odontologo) throws EntityNotFoundException, BadRequestException{
        if(odontologo.getId() == null){
            throw new BadRequestException("Debe incluir el id del odontólogo para poder actualizarlo");
        }
        buscarOdontologoPorId(odontologo.getId());
        return guardarOdontologo(odontologo);
    }

    public void eliminarOdontologoPorId(Long id) throws EntityNotFoundException{
        buscarOdontologoPorId(id);
        odontologoRepository.deleteById(id);
    }

    public void eliminarTodosOdontologos() throws EntityNotFoundException{
        buscarTodosOdontologos();
        odontologoRepository.deleteAll();
    }

}
