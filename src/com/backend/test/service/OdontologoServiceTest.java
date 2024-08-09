package com.backend.test.service;

import com.backend.entity.Odontologo;
import com.backend.service.impl.OdontologoService;
import com.backend.repository.impl.OdontologoDaoH2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class OdontologoServiceTest {
    private OdontologoService odontologoService;

    @Test
    public void deberiaGuardarOdontologosYListarTodosLosOdontologos() {
        odontologoService = new OdontologoService(new OdontologoDaoH2());

        Odontologo odontologoARegistrar = new Odontologo( "98765", "Manuel", "Salas");

        Odontologo odontologoRegistrado = odontologoService.guardarOdontologo(odontologoARegistrar);

        assertNotNull(odontologoRegistrado.getId());

        Odontologo odontologo1 = new Odontologo("12345", "Juan", "Alzate");
        Odontologo odontologo2 = new Odontologo("56789", "Luis", "Riofrio");

        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);

        List<Odontologo> odontologos = odontologoService.listarTodosLosOdontologos();

        assertEquals("Pepito", odontologos.get(0).getNombre());
        assertEquals("Juana", odontologos.get(1).getNombre());
    }
}
