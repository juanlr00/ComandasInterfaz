package com.mycompany.comandasinterfaz;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Pedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class PrimaryController implements Initializable {

    @FXML
    private TableView<Pedido> tvTabla;
    @FXML
    private TableColumn<Pedido, String> CCiclo;
    @FXML
    private TableColumn<Pedido, String> CAlumno;
    @FXML
    private TableColumn<Pedido, String> CTipo;
    @FXML
    private TableColumn<Pedido, String> CProducto;
    @FXML
    private TableColumn<Pedido, Integer> CPrecio;
    @FXML
    private TableColumn<Pedido, String> CEstado;
    @FXML
    private Label pendientes;
    /**
     * Initializes the controller class.
     */
    
    private static SessionFactory sf = new Configuration().configure().buildSessionFactory();
    private static Session s = sf.openSession();
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Pedido> contenido = FXCollections.observableArrayList(); 
        
        tvTabla.setItems(contenido); 
        
        CCiclo.setCellValueFactory(new PropertyValueFactory<>("ciclo"));
         CAlumno.setCellValueFactory(new PropertyValueFactory<>("alumno"));
          CTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
           CProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
            CPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
              CEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
              
        contenido.addAll(listarPedido());
    }    
    
     public void count(){
         pendientes.setText("Pedidos pendientes: " + String.valueOf(ComandasPendientes().size()));
        }
        public static ArrayList<Pedido> listarPedido(){
        
        Query q = s.createQuery("FROM Pedido", Pedido.class);     
        ArrayList<Pedido> qres = (ArrayList<Pedido>) q.getResultList();
       
        return qres;
       
    }
        
        public static ArrayList<Pedido> ComandasPendientes(){
        
        Query q = s.createQuery("FROM Pedido WHERE estado = 'pendiente'", Pedido.class);
        ArrayList<Pedido> qres = (ArrayList<Pedido>) q.getResultList();
       
        return qres;
    }
        
    @FXML
    private void click(MouseEvent event) {

       Pedido p = tvTabla.getSelectionModel().getSelectedItem();
         Transaction t = s.beginTransaction();
            p.setEstado("Recogido");
            s.update(p);
            t.commit();
            listarPedido();
    }
}
