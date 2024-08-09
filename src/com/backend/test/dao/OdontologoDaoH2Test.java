package com.backend.test.dao;

import com.backend.entity.Odontologo;
import com.backend.repository.impl.OdontologoDaoH2;
import com.backend.repository.IDao;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
public class OdontologoDaoH2Test {
    private  static IDao<Odontologo> odontologoIDao = new OdontologoDaoH2();

    @Test
    public void guardarOdontologosf() {
        odontologoIDao.guardar(new Odontologo("12345","Juan", "Alzate"));
        odontologoIDao.guardar(new Odontologo("56789","Luis", "Riofrio"));
        odontologoIDao.guardar(new Odontologo("15975","Pepe", "Bella"));

        Odontologo odontologo = new Odontologo("123456", "Manuel", "Salas");
        Odontologo result = odontologoIDao.guardar(odontologo);
        assertEquals(odontologo, result, "El odontólogo guardado debe ser igual al que se consultó");
    }
    @Test
    public void verLoGuardado() {
        List<Odontologo> result = odontologoIDao.listarTodos();
        assertEquals(7, result.size(), "Debe haber un odontólogo en la base de datos");
    }
    @Test
    public  void listarTodosLosOdontologos() {
        List<Odontologo> odontologos = odontologoIDao.listarTodos();
        Assert.assertTrue(odontologos.size() > 0);
    }
}
