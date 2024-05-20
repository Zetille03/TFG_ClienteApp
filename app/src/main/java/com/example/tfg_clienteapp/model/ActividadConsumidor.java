package com.example.tfg_clienteapp.model;

import java.sql.Timestamp;
import java.util.Objects;

public class ActividadConsumidor {
    private int idActividadConsumidor;

    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    private String descripcion;

    private Timestamp dueDate;

    private String categoria;

    private int numeroPlazas;

    private Consumidor consumidor;

    private Ofertante ofertanteActividadConsumidor;

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

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
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

    @Override
    public String toString() {
        return "ActividadConsumidor{" +
                "idActividadConsumidor=" + idActividadConsumidor +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", dueDate=" + dueDate +
                ", categoria='" + categoria + '\'' +
                ", numeroPlazas=" + numeroPlazas +
                ", consumidor=" + consumidor +
                ", ofertanteActividadConsumidor=" + ofertanteActividadConsumidor +
                '}';
    }
}
