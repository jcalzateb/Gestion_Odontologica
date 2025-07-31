package com.backend.test.dao;

import com.backend.entity.Odontologo;
import com.backend.repository.IDao;
import com.backend.repository.impl.OdontologoDaoMemoria;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class OdontologoDaoMemoriaTest {
    //los tets unitarios no se realizan sobre la capa repository sino sobre la capa service
    private IDao<Odontologo> odontologoIDao = new OdontologoDaoMemoria(new ArrayList());
    @Test
    public void guardarYlistarLosOdontologos() {
        odontologoIDao.guardar(new Odontologo("12345","Juan", "Alzate"));
        odontologoIDao.guardar(new Odontologo("56789","Luis", "Rios"));
        odontologoIDao.guardar(new Odontologo("15975","Pepe", "Bella"));
        odontologoIDao.guardar(new Odontologo("98765", "Manuel", "Salas"));
        List<Odontologo> odontologos = odontologoIDao.listarTodos();
        Assert.assertEquals(4, odontologos.size());

    }

}
