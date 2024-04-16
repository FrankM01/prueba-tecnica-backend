package com.fmbh.pt.model.dto;

import com.fmbh.pt.model.entity.Posicion;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
@Builder
public class FutbolistaDto implements Serializable {
    private Integer idFutbolista;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String caracteristicas;
    private Posicion posicion;
}
