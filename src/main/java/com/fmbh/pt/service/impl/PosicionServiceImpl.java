package com.fmbh.pt.service.impl;

import com.fmbh.pt.model.dao.PosicionDao;
import com.fmbh.pt.model.dto.PosicionDto;
import com.fmbh.pt.model.entity.Posicion;
import com.fmbh.pt.service.IPosicionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class PosicionServiceImpl implements IPosicionService {
    @Autowired
    private PosicionDao posicionDao;


    @Override
    public List<Posicion> listAll() {
        return (List)posicionDao.findAll();
    }

    @Transactional
    @Override
    public Posicion save(PosicionDto posicionDto) {
        Posicion posicion = Posicion.builder()
                .idPosicion(posicionDto.getIdPosicion())
                .nombrePosicion(posicionDto.getNombrePosicion())
                .build();
        return posicionDao.save(posicion);
    }
    @Transactional(readOnly = true)
    @Override
    public Posicion findById(Integer id) {
        return posicionDao.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public void delete(Posicion posicion) {
        posicionDao.delete(posicion);
    }

    public boolean existsById(Integer id) {
        return posicionDao.existsById(id);
    }
}
