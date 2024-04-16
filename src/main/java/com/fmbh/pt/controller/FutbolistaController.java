package com.fmbh.pt.controller;

import com.fmbh.pt.model.dto.FutbolistaDto;
import com.fmbh.pt.model.entity.Futbolista;
import com.fmbh.pt.model.entity.Posicion;
import com.fmbh.pt.model.payload.MensajeResponse;
import com.fmbh.pt.service.IFutbolistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")

public class FutbolistaController {
    @Autowired
    private IFutbolistaService futbolistaService;
    @GetMapping("futbolistas")
    public ResponseEntity<?> showAll(){
        List<Futbolista> getList = futbolistaService.listAll();
        if(getList == null){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No hay registros")
                    .object(null)
                    .build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("")
                .object(getList)
                .build(), HttpStatus.OK);
    }
    @PostMapping("futbolista")
    public ResponseEntity<?> create(@RequestBody FutbolistaDto futbolistaDto){
        Futbolista futbolistaSave = null;
        try {
            futbolistaSave = futbolistaService.save(futbolistaDto);
            futbolistaDto = FutbolistaDto.builder()
                    .idFutbolista(futbolistaSave.getIdFutbolista())
                    .nombre(futbolistaSave.getNombre())
                    .apellido(futbolistaSave.getApellido())
                    .fechaNacimiento(futbolistaSave.getFechaNacimiento())
                    .caracteristicas(futbolistaSave.getCaracteristicas())
                    .posicion(futbolistaSave.getPosicion())
                    .build();
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado Correctamente")
                    .object(futbolistaDto)
                    .build(),HttpStatus.CREATED);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .object(null)
                    .build(),HttpStatus.METHOD_NOT_ALLOWED);

        }

    }
    @PutMapping("futbolista/{id}")
    public ResponseEntity<?> update(@RequestBody FutbolistaDto futbolistaDto, @PathVariable Integer id){
        Futbolista futbolistaUpdate = null;
        try {
            if (futbolistaService.existsById(id)){
                futbolistaDto.setIdFutbolista(id);
                futbolistaUpdate = futbolistaService.save(futbolistaDto);
                futbolistaDto = FutbolistaDto.builder()
                        .idFutbolista(futbolistaUpdate.getIdFutbolista())
                        .nombre(futbolistaUpdate.getNombre())
                        .apellido(futbolistaUpdate.getApellido())
                        .fechaNacimiento(futbolistaUpdate.getFechaNacimiento())
                        .caracteristicas(futbolistaUpdate.getCaracteristicas())
                        .posicion(futbolistaUpdate.getPosicion())
                        .build();
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Actualizado Correctamente")
                        .object(futbolistaDto)
                        .build(),HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intenta actualizar no se encuentra en la base de datos")
                        .object(null)
                        .build(), HttpStatus.NOT_FOUND);
            }

        }catch (DataAccessException exDt){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .object(null)
                    .build(),HttpStatus.METHOD_NOT_ALLOWED);

        }

    }
    @DeleteMapping("futbolista/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            Futbolista futbolistaDelete = futbolistaService.findById(id);
            futbolistaService.delete(futbolistaDelete);
            return new ResponseEntity<>(futbolistaDelete, HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .object(null)
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("futbolista/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        Futbolista futbolista = futbolistaService.findById(id);
        if(futbolista == null){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intenta buscar no existe")
                    .object(null)
                    .build(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Consulta Existosa")
                .object(FutbolistaDto.builder()
                        .idFutbolista(futbolista.getIdFutbolista())
                        .nombre(futbolista.getNombre())
                        .apellido(futbolista.getApellido())
                        .fechaNacimiento(futbolista.getFechaNacimiento())
                        .caracteristicas(futbolista.getCaracteristicas())
                        .posicion(futbolista.getPosicion())
                        .build())
                .build(),HttpStatus.OK);
    }

}
