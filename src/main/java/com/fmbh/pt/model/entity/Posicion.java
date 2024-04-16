package com.fmbh.pt.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Entity
@Table(name = "posiciones")
public class Posicion implements Serializable {
    @Id
    @Column(name = "id_posiciones")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPosicion;
    @Column(name = "nombre_posicion")
    private String nombrePosicion;
}
