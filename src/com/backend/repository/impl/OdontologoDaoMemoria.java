package com.backend.repository.impl;

import com.backend.entity.Odontologo;
import com.backend.repository.IDao;
import org.apache.log4j.Logger;
import java.util.List;

public class OdontologoDaoMemoria implements IDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDaoMemoria.class);
    private List<Odontologo> odontologos;

    public OdontologoDaoMemoria(List<Odontologo> odontologos) {
        this.odontologos = odontologos;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        //falta simular la generacion de id y recordar no retornar ni modificar lo recibido, sino un auxiliar
        for (Odontologo o : odontologos) {
            if (o.getNumeroMatricula().equals(odontologo.getNumeroMatricula())) {
                logger.warn("La matrícula ya está registrada.");
                return null;
            }
        }
        odontologos.add(odontologo);
        logger.info("Odontólogo guardado: " + odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        logger.info("Listado de todos los odontólogos: " + odontologos);
        return odontologos;
    }

}
