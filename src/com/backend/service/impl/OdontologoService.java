package com.backend.service.impl;
import com.backend.entity.Odontologo;
import com.backend.repository.IDao;
import com.backend.service.IOdontologoService;
import java.util.List;

public class OdontologoService implements IOdontologoService{
    private IDao<Odontologo> odontologoDao;

    public OdontologoService(IDao<Odontologo> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        odontologoDao.guardar(odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodosLosOdontologos() {
        return odontologoDao.listarTodos();
    }
}
