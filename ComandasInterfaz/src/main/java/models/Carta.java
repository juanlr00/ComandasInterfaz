/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 *
 * @author JuanLamasRubio
 */
@Entity
@Table(name="carta")
public class Carta implements Serializable {
    
    @Id
    @GeneratedValue ( strategy=IDENTITY)
    
    
    @OneToMany( mappedBy = "carta", cascade = CascadeType.ALL)
    //Importante iniciarla
    private Set<Pedido> pedidos = new HashSet(0);
    
    private Long id;
    private String estado;
    private String descripcion;
    private int precio;

    public Carta() {
    }

    public Carta(String estado, int precio) {
        this.estado = estado;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Carta{" + "pedidos=" + pedidos + ", id=" + id + ", estado=" + estado + ", descripcion=" + descripcion + ", precio=" + precio + '}';
    }

   
    
}
