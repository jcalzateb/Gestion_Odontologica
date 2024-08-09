package com.backend.repository.impl;

import com.backend.dbconnection.H2Connection;
import com.backend.entity.Odontologo;
import com.backend.repository.IDao;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Odontologo a registrar: " + odontologo);
        Odontologo odontologoRegistrado = null;
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES ( ?, ?, ?)");
            preparedStatement.setString(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()){
                odontologoRegistrado = new Odontologo(resultSet.getLong("id"), odontologo.getNumeroMatricula(), odontologo.getNombre(), odontologo.getApellido());
            }

            logger.info("Odontólogo guardado: " + odontologo);
            connection.commit();
        } catch (Exception exception){
            logger.error(exception.getMessage());
            exception.printStackTrace();
            if(connection != null){
                try{
                    connection.rollback();
                    logger.error("Tuvimos un problema. " + exception.getMessage());
                    exception.printStackTrace();
                } catch (SQLException sqlException){
                    logger.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        }finally {
            try {
                connection.close();
            } catch (Exception ex) {
                logger.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }
        return odontologo;
    }
    @Override
    public List<Odontologo> listarTodos() {
        List<Odontologo> odontologos = new ArrayList<>();
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Odontologo odontologo = new Odontologo(
                        resultSet.getLong("id"),
                        resultSet.getString("matricula"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido")
                );
                odontologos.add(odontologo);
            }
            logger.info("Listado de todos los odontólogos: " + odontologos);
        }catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                logger.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        logger.info("Listado de todos los odontologos: " + odontologos);
        return odontologos;
    }
}
