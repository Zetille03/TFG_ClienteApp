package com.example.tfg_clienteapp.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class ActividadOfertante implements Serializable {
    private int idActividadOfertante;

    private String titulo;

    private String descripcion;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    private Date dueDate;

    private String categoria;

    private int numeroPlazas;

    private Ofertante ofertante;

    private List<ConsumidorActividadOfertante> listaConsumidoresActividadOfertantes;

    public int getIdActividadOfertante() {
        return idActividadOfertante;
    }

    public void setIdActividadOfertante(int idActividadOfertante) {
        this.idActividadOfertante = idActividadOfertante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getNumeroPlazas() {
        return numeroPlazas;
    }

    public void setNumeroPlazas(int numeroPlazas) {
        this.numeroPlazas = numeroPlazas;
    }

    public Ofertante getOfertante() {
        return ofertante;
    }

    public void setOfertante(Ofertante ofertante) {
        this.ofertante = ofertante;
    }

    public List<ConsumidorActividadOfertante> getListaConsumidoresActividadOfertantes() {
        return listaConsumidoresActividadOfertantes;
    }

    public void setListaConsumidoresActividadOfertantes(List<ConsumidorActividadOfertante> listaConsumidoresActividadOfertantes) {
        this.listaConsumidoresActividadOfertantes = listaConsumidoresActividadOfertantes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActividadOfertante that = (ActividadOfertante) o;
        return idActividadOfertante == that.idActividadOfertante && numeroPlazas == that.numeroPlazas && Objects.equals(descripcion, that.descripcion) && Objects.equals(dueDate, that.dueDate) && Objects.equals(categoria, that.categoria) && Objects.equals(ofertante, that.ofertante) && Objects.equals(listaConsumidoresActividadOfertantes, that.listaConsumidoresActividadOfertantes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idActividadOfertante, descripcion, dueDate, categoria, numeroPlazas, ofertante, listaConsumidoresActividadOfertantes);
    }

    @Override
    public String toString() {
        return "ActividadOfertante{" +
                "idActividadOfertante=" + idActividadOfertante +
                ", descripcion='" + descripcion + '\'' +
                ", dueDate=" + dueDate +
                ", categoria='" + categoria + '\'' +
                ", numeroPlazas=" + numeroPlazas +
                ", ofertante=" + ((ofertante!=null)?ofertante.getIdOfertante():"null") +
                ", listaConsumidoresActividadOfertantes=" +  ((listaConsumidoresActividadOfertantes!=null)?listaConsumidoresActividadOfertantes.size():0) +
                '}';
    }
}