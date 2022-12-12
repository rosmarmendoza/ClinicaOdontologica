package controllers;

import dto.OdontologoDTO;
import exception.BadRequestException;
import exception.EntityNotFoundException;
import models.Odontologo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.OdontologoService;


import java.util.Collection;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {


    private OdontologoService odontologoService;
    private static final Logger logger = Logger.getLogger(OdontologoController.class);
    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<?> registrarOdontologo(@RequestBody Odontologo odontologo)throws BadRequestException {
        odontologoService.registrarOdontologo(odontologo);
        logger.info("El odontologo " + odontologo.getNombre() + " " + odontologo.getApellido() + " ha sido guardado correctamente en la base de datos");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    //2.Buscar odontologos por id
    @GetMapping("/{id}")
    public OdontologoDTO buscarOdontologo(@PathVariable Long id) throws EntityNotFoundException {
        return odontologoService.buscarOdontologoPorId(id);
    }

    //3.Listar todos los odontologos
    @GetMapping("/todos")
    public Collection<OdontologoDTO> listarTodosOdontologos() throws EntityNotFoundException {
        return odontologoService.buscarTodosOdontologos();
    }


    @PutMapping
    public ResponseEntity<?> actualizarOdontologo(@RequestBody Odontologo odontologo) throws EntityNotFoundException,BadRequestException{
       odontologoService.actualizarOdontologo(odontologo);
       logger.info("El odontologo " + odontologo.getNombre() + " " + odontologo.getApellido() + " ha sido actualizado correctamente en la base de datos");
       return ResponseEntity.ok(HttpStatus.OK);
    }

    //5.Eliminar odontologo por id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws EntityNotFoundException{
        odontologoService.eliminarOdontologoPorId(id);
        logger.info("El odontologo con id: " + id + " ha sido eliminado correctamente.");
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //6.Eliminar todos los odontologos de la base de datos
    @DeleteMapping("/todos")
    public ResponseEntity<?> eliminarTodosOdontologos() throws EntityNotFoundException{
        odontologoService.eliminarTodosOdontologos();
        logger.info("Todos los odontólogos han sido eliminados de la base de datos correctamente.");
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
