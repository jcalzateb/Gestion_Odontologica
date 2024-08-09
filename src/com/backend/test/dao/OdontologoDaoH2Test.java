package com.backend.test.dao;

import com.backend.entity.Odontologo;
import com.backend.repository.impl.OdontologoDaoH2;
import com.backend.repository.IDao;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
public class OdontologoDaoH2Test {
    private  static IDao<Odontologo> odontologoIDao = new OdontologoDaoH2();

    @BeforeEach
    public void guardarOdontologosf() {
        odontologoIDao.guardar(new Odontologo("12345","Juan", "Alzate"));
        odontologoIDao.guardar(new Odontologo("56789","Luis", "Riofrio"));
        odontologoIDao.guardar(new Odontologo("15975","Pepe", "Bella"));
    }
    @Test
    public void verLoGuardado() {
        Odontologo odontologo = new Odontologo("75641", "Manuel", "Salas");
        Odontologo result = odontologoIDao.guardar(odontologo);
        assertEquals(odontologo, result, "El odontólogo guardado debe ser igual al que se consultó");
        assertNotNull(result, "El dontólogo no debería ser nula");
    }
    @Test
    public  void listarTodosLosOdontologos() {
        List<Odontologo> odontologos = odontologoIDao.listarTodos();
        assertNotNull(odontologos, "La lista de odontólogos no debería ser nula");
        assertTrue(odontologos.size() > 0, "La lista debería contener al menos un elemento");
    }
}
