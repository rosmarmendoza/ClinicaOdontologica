package service;


import com.fasterxml.jackson.databind.ObjectMapper;
import dto.PacienteDTO;
import exception.BadRequestException;
import exception.EntityNotFoundException;
import models.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IPacienteRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PacienteService {
    private IPacienteRepository pacienteRepository;
    ObjectMapper mapper;

    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository, ObjectMapper mapper) {
        this.pacienteRepository = pacienteRepository;
        this.mapper = mapper;
    }
    //1. Guardar un paciente en la base de datos.
    public Paciente guardarPaciente(Paciente paciente) throws BadRequestException {
        if(paciente == null || paciente.getNombre() == null || paciente.getApellido() == null  || paciente.getDomicilio()==null) {
            throw new BadRequestException("Los datos ingresados para registrar el paciente no son correctos o están incompletos. Debe incluir obligatoriamente: nombre, apellido y domicilio del paciente.");
        } else {
            return pacienteRepository.save(paciente);
        }
    }

    //2. Registrar un paciente en la base de datos.
    public Paciente registrarPaciente(Paciente paciente) throws BadRequestException {
        return guardarPaciente(paciente);
    }

    //3. Buscar un paciente por id.
    public PacienteDTO buscarPacientePorId(Long id) throws EntityNotFoundException {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        PacienteDTO pacienteDTO = null;
        if(paciente.isEmpty()){
            throw new EntityNotFoundException("El paciente con el id: " + id + " no se encuentra en la base de datos");
        } else{
          pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);
        }
        return pacienteDTO;
    }

    //4. Obtener un listado de todos los pacientes registrados en la DB.
    public Set<PacienteDTO> buscarTodosPacientes() throws EntityNotFoundException{
        List<Paciente> pacientes = pacienteRepository.findAll();
        Set<PacienteDTO> pacientesDTO = new HashSet<>();
        if(pacientes.isEmpty()) {
            throw new EntityNotFoundException("No se encuentran pacientes en la base de datos");
        } else {
            for (Paciente paciente : pacientes) {
                pacientesDTO.add(mapper.convertValue(paciente, PacienteDTO.class));
            }
        }
        return pacientesDTO;
    }

    //5. Actualizar los datos de un paciente.
    public Paciente actualizarPaciente(Paciente paciente)throws EntityNotFoundException, BadRequestException{
        if(paciente.getId() == null || paciente.getDomicilio().getId() == null){
            throw new BadRequestException("Debe incluir el id del paciente y el id del domicilio para poder actualizarlo");
        }
        buscarPacientePorId(paciente.getId());
        return guardarPaciente(paciente);
    }

    //6.Eliminar un paciente por id.
    public void eliminarPacientePorId(Long id) throws EntityNotFoundException{
        buscarPacientePorId(id);
        pacienteRepository.deleteById(id);
    }

    //7.Eliminar todos los pacientes de la base de datos
    public void eliminarTodosPacientes() throws EntityNotFoundException{
        buscarTodosPacientes();
        pacienteRepository.deleteAll();
    }
}
