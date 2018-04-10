package com.cice.db;

import java.sql.*;
import java.util.List;

/**
 * Clase encargada de generar el acceso y uso de una base de datos.
 */

public class Manager {

    private final String DRIVER;
    private final String HOST;
    private final String PUERTO;
    private final String USER;
    private final String PASS;
    private final String DATABASE;
    private Connection connection;
    private Statement statement;


    public Manager(String DRIVER, String HOST, String PUERTO, String USER, String PASS, String DATABASE) {
        this.DRIVER = DRIVER;
        this.HOST = HOST;
        this.PUERTO = PUERTO;
        this.USER = USER;
        this.PASS = PASS;
        this.DATABASE = DATABASE;
    }

    public Manager(){
        this.DRIVER = "com.mysql.jdbc.Driver";
        this.HOST = "localhost";
        this.PUERTO = "8889";
        this.USER = "root";
        this.PASS = "root";
        this.DATABASE = "prueba";
    }
    private String generarUrl(){
        return "jdbc:mysql://"+HOST+":"+PUERTO+"/"+DATABASE;
    }

    /**
     * Metodo que se utiliza para conectar contra una base de datos
     *
     */

    private boolean conectaBaseDatos(){
        boolean esConectado = false;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(generarUrl(),USER,PASS);
            if(connection != null){
                esConectado = true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return esConectado;
    }

    /**
     * Metodo que usaremos para desconectar de la base de datos y así liberar recursos.
     *
     * @return estado de la conexión ( true si está conectado)
     */
    private boolean desconectarBaseDatos(){
        boolean esDesconectado = false;
        try {
            connection.close();
            esDesconectado = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return esDesconectado;
    }
    private void ejecutarSelect (String sql){
        conectaBaseDatos();
        try {
            ResultSet resultado = statement.executeQuery(sql);
            while (resultado.next()){
                int numeroColumnas = resultado.getMetaData().getColumnCount();
                for(int i = 0; i< numeroColumnas; i++){
                    String dato = resultado.getString(i);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        desconectarBaseDatos();

    }
}