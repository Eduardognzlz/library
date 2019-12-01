package com.viewBook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import com.database.*;
import com.viewBook.ControllerBook;
import com.viewBook.ViewBook;
 
public class ControllerBook implements ActionListener,
                                    MouseListener {
    private ViewBook viewbook;
 
    //CONSTRUCTOR
    public ControllerBook( ViewBook viewbook ){
        this.viewbook   = viewbook;
        cargarTabla();
    }
 
    @Override
    public void actionPerformed(ActionEvent arg0) {
        //Objeto para ejecutar los procedimientos almacenados
        //   en la base de datos
        CallableStatement cs;
 
        //COMANDO EJECTUADO
        String comando  = arg0.getActionCommand();
 
        //Deber� coincidir con alguno de los par�metros
        //  indicados en setActionCommand invocado en la
        //  clase View
        switch (comando) {
            case "INSERTAR":
                try{
                    //Preparar la llamada
                    cs  = BD.getConexion().prepareCall(
                            "{CALL insertarLibro(?,?,?,?,?,?,?}");
                    //Indicar qu� informaci�n se pasa al
                    //  procedimiento
                    cs.setString(1,
                        this.viewbook.txtTitulo.getText());
                    cs.setString(2,
                        this.viewbook.txtAutor.getText());
                    cs.setString(3,
                        this.viewbook.txtEditorial.getText());
                    cs.setString(4,
                            this.viewbook.txtISBN.getText());
                    cs.setString(5,
                            this.viewbook.txtGenero.getText());
                    cs.setString(6,
                            this.viewbook.txtEdicion.getText());
                    cs.setString(7,
                            this.viewbook.txtPaginas.getText());
                    //Ejecutar el procedimiento
                    cs.execute();
                }catch (SQLException e) {
                    System.err.println("Error en la INSERCI�N");
                }
 
            break;
 
            case "BORRAR":
                //Recoger qu� fila se ha pulsado
                int filaPulsada = this.viewbook.tablaLibros.getSelectedRow();
                //Si se ha pulsado una fila
                if(filaPulsada>=0){
                    //Se recoge el id de la fila marcada
                    int identificador   = (int)this.viewbook.dtm.getValueAt(filaPulsada, 0);
                    try{
                        //Preparar la llamada
                        cs  = BD.getConexion().prepareCall(
                            "{CALL borrarLibro(?)}");
                        //Indicar qu� informaci�n se pasa al procedimiento
                        cs.setInt(1, identificador);
                        //Ejecutar el procedimiento
                        cs.execute();
                        //System.out.println(this.view.dtm.getValueAt(filaPulsada, 0));
                    }catch (SQLException e) {
                        System.err.println("Error en el BORRADO");
                    }
                }
 
            break;
 
            case "MODIFICAR":
                //Recoger qu� fila se ha pulsadao en la tabla
                filaPulsada = this.viewbook.tablaLibros.getSelectedRow();
                //Si se ha pulsado una fila
                if(filaPulsada>=0){
                    //Se recoge el id de la fila marcada
                    int identificador   = (int)this.viewbook.dtm.getValueAt(filaPulsada, 0);
                    try{
                        //Preparar la llamada
                        cs  = BD.getConexion().prepareCall(
                            "{CALL modificarLibro(?,?,?,?,?,?,?)}");
                        //Indicar qu� informaci�n se pasa al procedimiento
                        cs.setInt(1, identificador);
                        cs.setString(2,
                                this.viewbook.txtTitulo.getText());
                            cs.setString(3,
                                this.viewbook.txtAutor.getText());
                            cs.setString(4,
                                this.viewbook.txtEditorial.getText());
                            cs.setString(5,
                                    this.viewbook.txtISBN.getText());
                            cs.setString(6,
                                    this.viewbook.txtGenero.getText());
                            cs.setString(7,
                                    this.viewbook.txtEdicion.getText());
                            cs.setString(8,
                                    this.viewbook.txtPaginas.getText());
                        //Ejecutar el procedimiento
                        cs.execute();
                        //System.out.println(this.view.dtm.getValueAt(filaPulsada, 0));
                    }catch (SQLException e) {
                        System.err.println("Error en la MODIFICACION");
                    }
                }
            break;
 
            default:
                System.err.println("Comando no definido");
            break;
        }
        //limpiar el formulario
        limpia();
 
        //refrescar la tabla
        cargarTabla();
    }
 
    //M�todo para limpiar los campos de la ventana
    private void limpia(){
        this.viewbook.txtTitulo.setText("");
        this.viewbook.txtAutor.setText("");
        this.viewbook.txtEditorial.setText("");
        this.viewbook.txtISBN.setText("");
        this.viewbook.txtGenero.setText("");
        this.viewbook.txtEdicion.setText("");
        this.viewbook.txtPaginas.setText("");
    }
 
    //M�todo que recarga los datos de la tabla de la base de datos
    // en la tabla de la clase View
    protected void cargarTabla(){
        //Objeto para ejecutar los procedimientos almacenados en la base de datos
        CallableStatement cs;
        //Objeto para recoger los datos devueltos por el procedimiento almacenado
        ResultSet rs;
        //Objeto para recorrer el resultado del procedimiento almacenado y
        //  a�adirlo a la tabla definida en la clase View
        Vector<Object> fila;
 
        //Limpiar los datos de la tabla
        for(int i=this.viewbook.dtm.getRowCount(); i>0; i--){
            this.viewbook.dtm.removeRow(i-1);
        }
 
        //Cargar datos en la tabla
        try {
            //Preparar la llamada
            cs  = BD.getConexion().prepareCall(
                            "{CALL getLibros()}");
            //Ejecutarla y recoger el resultado
            rs  = cs.executeQuery();
            //Recorrer el resultado
            while(rs.next()){
                //A�adir registro a registro en el vector
                fila    = new Vector<Object>();
                fila.add(rs.getInt("ID_Libro"));
                fila.add(rs.getString("Titulo"));
                fila.add(rs.getString("Autor"));
                fila.add(rs.getString("Editorial"));
                fila.add(rs.getInt("ISBN"));
                fila.add(rs.getString("Genero"));
                fila.add(rs.getDate("Edicion"));
                fila.add(rs.getInt("Paginas"));
                //A�adir el vector a la tabla de la clase View
                this.viewbook.dtm.addRow(fila);
            }
 
        } catch (SQLException e) {
            System.err.println("Error al CARGAR DATOS");
        }
    }
 
    @Override
    public void mouseClicked(MouseEvent arg0) {
        //Objeto para ejecutar los procedimientos almacenados en la base de datos
        CallableStatement cs;
        //Objeto para recoger los datos devueltos por el procedimiento almacenado
        ResultSet rs;
 
        //Recoger qu� fila se ha pulsadao en la tabla
        int filaPulsada = this.viewbook.tablaLibros.getSelectedRow();
        //Si se ha pulsado una fila
        if(filaPulsada>=0){
            //Se recoge el id de la fila marcada
            int identificador= (int)this.viewbook.dtm.getValueAt(
                            filaPulsada, 0);
            try{
                //Preparar la llamada
                cs  = BD.getConexion().prepareCall(
                            "{CALL getLibro(?)}");
                //Indicar qu� informaci�n se pasa al procedimiento
                cs.setInt(1, identificador);
                //Ejecutar el procedimiento
                rs  = cs.executeQuery();
                //Cargar los datos devueltos en los cuadros de texto
                if(rs.next()){
                    this.viewbook.txtTitulo.setText(rs.getString(1));
                    this.viewbook.txtAutor.setText(rs.getString(2));
                    this.viewbook.txtEditorial.setText(rs.getString(3));
                    this.viewbook.txtISBN.setText(rs.getString(4));
                    this.viewbook.txtGenero.setText(rs.getString(5));
                    this.viewbook.txtEdicion.setText(rs.getString(6));
                    this.viewbook.txtPaginas.setText(rs.getString(7));
                }
                //System.out.println(this.view.dtm.getValueAt(filaPulsada, 0));
            }catch (SQLException e) {
                System.err.println("Error al CARGAR UN LIBRO");
            }
        }
    }
 
    @Override
    public void mouseEntered(MouseEvent arg0) {}
    @Override
    public void mouseExited(MouseEvent arg0) {}
    @Override
    public void mousePressed(MouseEvent arg0) {}
    @Override
    public void mouseReleased(MouseEvent arg0) {}
}
