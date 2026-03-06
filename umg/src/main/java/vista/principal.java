/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.carreras;
import modelo.carreraDAO;
import controlador.jornadas;
import modelo.jornadasDAO;

/**
 *
 * @author miais
 */
public class principal {
    public static void main(String[] args) {

        //Se crea el DAO (Data Acces Object) que nos permite utilizar los métodos del CRUD
        carreraDAO carreradDao = new carreraDAO();
        jornadasDAO jornadaDao = new jornadasDAO();

        //Crear objeto carrera que representa un registro en la tabla   
        carreras carrera = new carreras();
        jornadas jornada = new jornadas();

        //Aqui se van asignando los valores al objeto 
        carrera.setCarcodigo(1);
        carrera.setCarnombre("Ingenieria en Sistemas");
        carrera.setCarestatus("A");
        
        //Aqui vamos a asignar los valodes al objeto
        jornada.setJorcodigo(1);
        jornada.setJornombre("Diario Matutina");

        //Insertar en base de datos
        carreradDao.insert(carrera);
        jornadaDao.insert(jornada);

        //Muestra el resultado
        System.out.println(carrera.toString());
        System.out.println(jornada.toString());

    }
}
