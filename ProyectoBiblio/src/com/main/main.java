package com.main;

import com.database.*;
import com.viewBook.ControllerBook;
import com.viewBook.ViewBook;

public class main {
	public static void main(String[] args) {
        //Invocar al constructor de la clase Bd
        new BD("biblioteca_tec");
        //Crear un objeto de la clase View
        ViewBook vistaLibros  = new ViewBook();
        //Crear un objeto de la clase Controller
        ControllerBook controladorBook  = new ControllerBook(vistaLibros);
        //Vincular la vista y el controlador
        vistaLibros.conectaControlador(controladorBook);
 
    }
}
