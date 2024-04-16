package com.fmbh.pt.controller;

import com.fmbh.pt.model.dto.PosicionDto;
import com.fmbh.pt.model.entity.Posicion;
import com.fmbh.pt.model.payload.MensajeResponse;
import com.fmbh.pt.service.IPosicionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PosicionController {
    @Autowired
    private IPosicionService posicionService;


    @GetMapping("posiciones")
    public ResponseEntity<?> showAll(){
        List<Posicion> getList = posicionService.listAll();
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

    @PostMapping("posicion")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody PosicionDto posicionDto){
        Posicion posicionSave = null;
        try {
            posicionSave = posicionService.save(posicionDto);
            posicionDto = PosicionDto.builder()
                    .idPosicion(posicionSave.getIdPosicion())
                    .nombrePosicion(posicionSave.getNombrePosicion())
                    .build();
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado Correctamente")
                    .object(posicionDto)
                    .build(), HttpStatus.CREATED);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("posicion/{id}")
    public ResponseEntity<?> update(@RequestBody PosicionDto posicionDto, @PathVariable Integer id){
        Posicion posicionUpdate = null;
        try {
            if (posicionService.existsById(id)) {
                posicionDto.setIdPosicion(id);
                posicionUpdate = posicionService.save(posicionDto);
                posicionDto = PosicionDto.builder()
                        .idPosicion(posicionUpdate.getIdPosicion())
                        .nombrePosicion(posicionUpdate.getNombrePosicion())
                        .build();
                return  new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Actualizado Correctamente")
                        .object(posicionDto)
                        .build(),HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intenta actualizar no se encuentra en la base de datos")
                        .object(null)
                        .build(),HttpStatus.NOT_FOUND);
            }

        }catch (DataAccessException exDt){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .object(null)
                    .build(),HttpStatus.METHOD_NOT_ALLOWED);

        }
    }
    @DeleteMapping("posicion/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            Posicion posicionDelete = posicionService.findById(id);
            posicionService.delete(posicionDelete);
            return new ResponseEntity<>(posicionDelete, HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .object(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("posicion/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        Posicion posicion = posicionService.findById(id);

        if (posicion == null){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intenta buscar no existe")
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Consulta Existosa")
                .object(PosicionDto.builder()
                        .idPosicion(posicion.getIdPosicion())
                        .nombrePosicion(posicion.getNombrePosicion())
                        .build())
                .build(),HttpStatus.OK);
    }

}
