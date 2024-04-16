package com.fmbh.pt.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class PosicionDto implements Serializable {
    private Integer idPosicion;
    private String nombrePosicion;
}
