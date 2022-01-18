package com.mycompany.comandasinterfaz;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private TableColumn<Pedido, String> CNombre;
    @FXML
    private TableColumn<Pedido, String> CDescripcion;
    @FXML
    private TableColumn<Pedido, String> CPrecio;
    @FXML
    private TableColumn<Pedido, String> CEstado;
    @FXML
    private Label pendientes;
    /**
     * Initializes the controller class.
     */

    private static SessionFactory sf = new Configuration().configure().buildSessionFactory();
    private static Session s = sf.openSession();
    @FXML
    private Button btnCarta;
    @FXML
    private Button btnPedido;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Pedido> contenido = FXCollections.observableArrayList();

        CNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        CDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
//            (var row) -> {
//                return new SimpleStringProperty(""+ row.getValue().getCarta().getDescripcion());
//            }
//        );
        
        CPrecio.setCellValueFactory(
            (var row) -> {
                return new SimpleStringProperty(""+ row.getValue().getCarta().getPrecio());
            }
        );
        
        CEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        

 
        
        java.util.Date actual = new java.util.Date();
        java.sql.Date fechaActual = new java.sql.Date(actual.getTime());
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("FROM Pedido ", Pedido.class); //AND p.fecha_pedido = :fecha"
        //q.setParameter("fecha", fechaActual);
        ArrayList<Pedido> resultado = (ArrayList<Pedido>) q.list();

        contenido.addAll(listarPedido());
    
        tvTabla.setItems(contenido);

    }

    public void count() {
        pendientes.setText("Pedidos pendientes: " + String.valueOf(ComandasPendientes().size()));
    }

    public static ArrayList<Pedido> listarPedido() {

        Query q = s.createQuery("FROM Pedido", Pedido.class);
        ArrayList<Pedido> qres = (ArrayList<Pedido>) q.getResultList();

        return qres;

    }

    public static ArrayList<Pedido> ComandasPendientes() {

        Query q = s.createQuery("FROM Pedido WHERE estado = 'pendiente'", Pedido.class);
        ArrayList<Pedido> qres = (ArrayList<Pedido>) q.getResultList();

        return qres;
    }

    private void click(MouseEvent event) {

        Pedido p = tvTabla.getSelectionModel().getSelectedItem();
        Transaction t = s.beginTransaction();
        p.setEstado("Recogido");
        s.update(p);
        t.commit();
        listarPedido();
    }

    @FXML
    private void verCarta(ActionEvent event) {
        String archivo = "Carta.jrxml";
        
    }

    @FXML
    private void verPedido(ActionEvent event) {
        String archivo = "Pedido.jrxml";
    }
}
