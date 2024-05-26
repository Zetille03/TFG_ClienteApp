package com.example.tfg_clienteapp.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class ActividadConsumidor implements Serializable {
    private int idActividadConsumidor;

    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    private String descripcion;

    private Date dueDate;

    private String categoria;

    private int numeroPlazas;

    private Consumidor consumidor;

    private Ofertante ofertanteActividadConsumidor;

    public ActividadConsumidor(String titulo, String descripcion, Date dueDate, String categoria, int numeroPlazas, Consumidor consumidor) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.dueDate = dueDate;
        this.categoria = categoria;
        this.numeroPlazas = numeroPlazas;
        this.consumidor = consumidor;
    }

    public ActividadConsumidor(){

    }


    public int getIdActividadConsumidor() {
        return idActividadConsumidor;
    }

    public void setIdActividadConsumidor(int idActividadConsumidor) {
        this.idActividadConsumidor = idActividadConsumidor;
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

    public Consumidor getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Consumidor consumidor) {
        this.consumidor = consumidor;
    }

    public Ofertante getOfertanteActividadConsumidor() {
        return ofertanteActividadConsumidor;
    }

    public void setOfertanteActividadConsumidor(Ofertante ofertanteActividadConsumidor) {
        this.ofertanteActividadConsumidor = ofertanteActividadConsumidor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActividadConsumidor that = (ActividadConsumidor) o;
        return idActividadConsumidor == that.idActividadConsumidor && numeroPlazas == that.numeroPlazas && Objects.equals(titulo, that.titulo) && Objects.equals(descripcion, that.descripcion) && Objects.equals(dueDate, that.dueDate) && Objects.equals(categoria, that.categoria) && Objects.equals(consumidor, that.consumidor) && Objects.equals(ofertanteActividadConsumidor, that.ofertanteActividadConsumidor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idActividadConsumidor, titulo, descripcion, dueDate, categoria, numeroPlazas, consumidor, ofertanteActividadConsumidor);
    }

}
