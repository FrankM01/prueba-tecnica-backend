package com.fmbh.pt.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "futbolistas")
public class Futbolista implements Serializable {
    @Id
    @Column(name = "id_futbolista")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFutbolista;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    @Column(name = "caracteristicas")
    private String caracteristicas;
    @ManyToOne
    @JoinColumn(name = "id_posiciones")
    private Posicion posicion;
}
