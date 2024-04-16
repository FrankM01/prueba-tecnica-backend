package com.fmbh.pt.service;

import com.fmbh.pt.model.dto.FutbolistaDto;
import com.fmbh.pt.model.entity.Futbolista;

import java.util.List;

public interface IFutbolistaService {
    List<Futbolista> listAll();
    Futbolista save(FutbolistaDto futbolista);
    Futbolista findById(Integer id);
    void delete(Futbolista futbolista);
    boolean existsById(Integer id);

}
