package com.fmbh.pt.service.impl;

import com.fmbh.pt.model.dao.FutbolistaDao;
import com.fmbh.pt.model.dto.FutbolistaDto;
import com.fmbh.pt.model.entity.Futbolista;
import com.fmbh.pt.model.entity.Posicion;
import com.fmbh.pt.service.IFutbolistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class FutbolistaServiceImpl implements IFutbolistaService {
    @Autowired
    private FutbolistaDao futbolistaDao;

    @Override
    public List<Futbolista> listAll() {
        return (List)futbolistaDao.findAll();
    }
    @Transactional
    @Override
    public Futbolista save(FutbolistaDto futbolistaDto) {
        Posicion posicion = futbolistaDto.getPosicion();
        Futbolista futbolista = Futbolista.builder()
                .idFutbolista(futbolistaDto.getIdFutbolista())
                .nombre(futbolistaDto.getNombre())
                .apellido(futbolistaDto.getApellido())
                .fechaNacimiento(futbolistaDto.getFechaNacimiento())
                .caracteristicas(futbolistaDto.getCaracteristicas())
                .posicion(posicion)
                .build();
        return futbolistaDao.save(futbolista);
    }
    @Transactional(readOnly = true)
    @Override
    public Futbolista findById(Integer id) {
        return futbolistaDao.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public void delete(Futbolista futbolista) {
        futbolistaDao.delete(futbolista);
    }

    @Override
    public boolean existsById(Integer id) {
        return futbolistaDao.existsById(id);
    }
}
