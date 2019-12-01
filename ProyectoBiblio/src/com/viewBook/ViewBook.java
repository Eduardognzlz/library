package com.viewBook;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;
import com.viewBook.ControllerBook;
import com.viewBook.ViewBook;

@SuppressWarnings("serial")
public class ViewBook extends JFrame{

	
	/**************** ATRIBUTOS ***************************/
    //CONTENEDOR PRINCIPAL
    private JPanel contenedor;
 
    //DEFINICIÓN DE LAS ETIQUETAS
    private JLabel lblTitulo;
    private JLabel lblAutor;
    private JLabel lblEditorial;
    private JLabel lblISBN;
    private JLabel lblGenero;
    private JLabel lblEdicion;
    private JLabel lblPaginas;
    
 
 
    //DEFINICIÓN DE LOS CUADROS DE TEXTO
    protected JTextField txtTitulo;
    protected JTextField txtAutor;
    protected JTextField txtEditorial;
    protected JTextField txtISBN;
    protected JTextField txtGenero;
    protected JTextField txtEdicion;
    protected JTextField txtPaginas;
 
    //DEFINICIÓN DE LOS BOTONES
    protected JButton btnAdd;
    protected JButton btnDel;
    protected JButton btnUpd;
    protected JButton btnBack;
 
    //DEFINICIÓN DE LOS OBJETOS PARA LA TABLA
    private JScrollPane scroll; //Panel de scroll que contiene la tabla
    protected Object[][] datos; //Cuerpo de la tabla
    protected String[] cabecera;    //Cabecera de la tabla
    protected DefaultTableModel dtm;//Unión de la cabecera y la tabla
    protected JTable tablaLibros; //Tabla propiamente dicha
 
    /**************** MÉTODOS 
     * @return ***************************/
    public //CONSTRUCTOR
    void View(){
        //Métodos de la JFrame
        setBounds(100, 100, 450, 300);//Definir las dimensiones de la ventana
        setTitle("LIBROS BIBLIOTECA");    //Barra de título
        setDefaultCloseOperation(EXIT_ON_CLOSE);    //Acción al pulsar salir
 
        //CREAR EL CONTENEDOR PRINCIPAL Y AÑADIRLO A LA VENTANA
        contenedor = new JPanel();
        getContentPane().add(contenedor);
 
        //INDICAR QUE SE QUIERE USAR SPRINGLAYOUT
        SpringLayout sp = new SpringLayout();
        contenedor.setLayout(sp);
 
        //Vamos al lío
        /**************** BOF ETIQUETAS  vvvvvvvvvvvvvvvv **/
        //ETIQUETA TITULO
        lblTitulo = new JLabel("Titulo:");  //Crear el objeto
        contenedor.add(lblAutor);      //Añadirlo al contenedor
        sp.putConstraint(SpringLayout.NORTH, lblTitulo, 10,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblTitulo,  10,
                        SpringLayout.WEST, contenedor);
        //ETIQUETA AUTOR
        lblAutor = new JLabel("AUTOR:");
        contenedor.add(lblAutor);
        sp.putConstraint(SpringLayout.NORTH, lblAutor, 25,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblAutor,  10,
                        SpringLayout.WEST, contenedor);
        //ETIQUETA EDITORIAL
        lblEditorial = new JLabel("Editorial:");
        contenedor.add(lblEditorial);
        sp.putConstraint(SpringLayout.NORTH, lblEditorial, 40,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblEditorial,  10,
                        SpringLayout.WEST, contenedor);
        
      //ETIQUETA ISBN
        lblISBN = new JLabel("ISBN:");
        contenedor.add(lblISBN);
        sp.putConstraint(SpringLayout.NORTH, lblISBN, 55,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblISBN,  10,
                        SpringLayout.WEST, contenedor);
        
       //ETIQUETA GENERO 
        lblGenero = new JLabel("Genero:");
        contenedor.add(lblGenero);
        sp.putConstraint(SpringLayout.NORTH, lblGenero, 70,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblGenero,  10,
                        SpringLayout.WEST, contenedor);
        
      //ETIQUETA EDICION
        lblEdicion = new JLabel("Edicion:");
        contenedor.add(lblEditorial);
        sp.putConstraint(SpringLayout.NORTH, lblEdicion, 85,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblEdicion,  10,
                        SpringLayout.WEST, contenedor);
        
        //ETIQUETA PAGINAS
        lblPaginas = new JLabel("Numero de Paginas:");
        contenedor.add(lblPaginas);
        sp.putConstraint(SpringLayout.NORTH, lblPaginas, 100,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblPaginas,  10,
                        SpringLayout.WEST, contenedor);
        
        /**************** EOF ETIQUETAS  ^^^^^^^^^^^^^^^^ **/
 
        /**************** BOF CUADROS DE  TEXTO vvvvvvvvv **/
        //CUADRO DE TEXTO PARA EL TITULO
        txtTitulo       = new JTextField();
        contenedor.add(txtTitulo);
        sp.putConstraint(SpringLayout.NORTH, txtTitulo, 10,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtTitulo, 100,
                        SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, txtTitulo, 300,
                        SpringLayout.WEST, contenedor);
        //CUADRO DE TEXTO PARA EL AUTOR
        txtAutor = new JTextField();
        contenedor.add(txtAutor);    //añadir al contenedor
        sp.putConstraint(SpringLayout.NORTH, txtAutor, 25,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtAutor, 100,
                        SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, txtAutor, 300,
                        SpringLayout.WEST, contenedor);
        //CUADRO DE TEXTO PARA LA EDITORIAL
        txtEditorial = new JTextField();
        contenedor.add(txtEditorial);
        sp.putConstraint(SpringLayout.NORTH, txtEditorial, 40, SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtEditorial, 100, SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, txtEditorial, 300, SpringLayout.WEST, contenedor);
        
      //CUADRO DE TEXTO PARA EL ISBN
        txtISBN      = new JTextField();
        contenedor.add(txtISBN);
        sp.putConstraint(SpringLayout.NORTH, txtISBN, 55, SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtISBN, 100, SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, txtISBN, 300, SpringLayout.WEST, contenedor);
        
      //CUADRO DE TEXTO PARA EL GENERO
        txtGenero      = new JTextField();
        contenedor.add(txtGenero);
        sp.putConstraint(SpringLayout.NORTH, txtGenero, 70, SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtGenero, 100, SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, txtGenero, 300, SpringLayout.WEST, contenedor);
        
      //CUADRO DE TEXTO PARA LA EDICION
        txtEdicion      = new JTextField();
        contenedor.add(txtEdicion);
        sp.putConstraint(SpringLayout.NORTH, txtEdicion, 85, SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtEdicion, 100, SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, txtEdicion, 300, SpringLayout.WEST, contenedor);
        
      //CUADRO DE TEXTO PARA LAS PAGINAS
        txtPaginas      = new JTextField();
        contenedor.add(txtPaginas);
        sp.putConstraint(SpringLayout.NORTH, txtPaginas, 100, SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtPaginas, 100, SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, txtPaginas, 300, SpringLayout.WEST, contenedor);
        
        /**************** EOF CUADROS DE  TEXTO ^^^^^^^^^ **/
 
        /**************** BOF TABLA  vvvvvvvvvvvvvvvvvvvv **/
        scroll      = new JScrollPane();
        cabecera    = new String[] {"ID","TITULO","AUTOR","EDITORIAL","ISBN", "GENERO", "EDICION", "PAGINAS"};
        dtm         = new DefaultTableModel(datos,cabecera);
        tablaLibros       = new JTable(dtm);
        scroll.setViewportView(tablaLibros);
        //y ahora se coloca el scrollpane...
        contenedor.add(scroll); //añadir al contenedor
        sp.putConstraint(SpringLayout.NORTH, scroll, 120,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, scroll,   10,
                        SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, scroll,  -10,
                        SpringLayout.EAST, contenedor);
        sp.putConstraint(SpringLayout.SOUTH, scroll, -50,
                        SpringLayout.SOUTH, contenedor);
        /**************** EOF TABLA ^^^^^^^^^^^^^^^^^^^^ **/
 
        /**************** BOF BOTONES vvvvvvvvvvvvvvvvvv **/
        //BOTÓN AÑADIR
        btnAdd          = new JButton("Añadir");
        contenedor.add(btnAdd);
        sp.putConstraint(SpringLayout.SOUTH, btnAdd, -10,
                        SpringLayout.SOUTH, contenedor);//colocarlo
        sp.putConstraint(SpringLayout.WEST, btnAdd,   60,
                        SpringLayout.WEST, contenedor);
        //BOTÓN BORRAR
        btnDel          = new JButton("Borrar");
        contenedor.add(btnDel);
        sp.putConstraint(SpringLayout.SOUTH, btnDel, -10,
                        SpringLayout.SOUTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, btnDel,  190,
                        SpringLayout.WEST, contenedor);
        //BOTÓN MODIFICAR
        btnUpd          = new JButton("Editar");
        contenedor.add(btnUpd);
        sp.putConstraint(SpringLayout.SOUTH, btnUpd, -10,
                        SpringLayout.SOUTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, btnUpd,  310,
                        SpringLayout.WEST, contenedor);
        
      //BOTÓN MODIFICAR
        btnBack          = new JButton("REGRESAR");
        contenedor.add(btnUpd);
        sp.putConstraint(SpringLayout.NORTH, btnUpd, 30,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, btnUpd,  320,
                        SpringLayout.WEST, contenedor);
        /**************** EOF BOTONES ^^^^^^^^^^^^^^^^^^^^ **/
 
        //Se hace visible la ventana
        setVisible(true);
 
    }
 
    public void conectaControlador(  ControllerBook c  ){
 
        btnAdd.addActionListener(c);
        btnAdd.setActionCommand("INSERTAR");
 
        btnDel.addActionListener(c);
        btnDel.setActionCommand("BORRAR");
 
        btnUpd.addActionListener(c);
        btnUpd.setActionCommand("MODIFICAR");
 
        tablaLibros.addMouseListener(c);
        //sólo se permite pulsar una fila a la vez.
        tablaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }


	
}
