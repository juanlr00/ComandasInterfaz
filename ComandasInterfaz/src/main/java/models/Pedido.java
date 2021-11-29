/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author JuanLamasRubio
 */
@Entity
@Table(name="pedido")
public class Pedido implements Serializable {
    
    @Id
    @GeneratedValue( strategy=IDENTITY)
    
    private Long id;
    private String nombre;
    private String estado;
    private Date fecha;
    
    @ManyToOne
    @JoinColumn( name = "carta_id")    
    private Carta carta;


    public Pedido() {
    }

    public Pedido(String nombre, String estado, Date fecha) {
        this.nombre = nombre;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", nombre=" + nombre + ", estado=" + estado + ", fecha=" + fecha + '}';
    }
    
    
}
