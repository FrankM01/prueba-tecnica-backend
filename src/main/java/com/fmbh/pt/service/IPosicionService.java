package com.fmbh.pt.service;

import com.fmbh.pt.model.dto.PosicionDto;
import com.fmbh.pt.model.entity.Posicion;

import java.util.List;

public interface IPosicionService {
    List<Posicion> listAll();
    Posicion save(PosicionDto posicion);
    Posicion findById(Integer id);
    void delete(Posicion posicion);
    boolean existsById(Integer id);
}
