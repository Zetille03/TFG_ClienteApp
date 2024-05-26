package com.example.tfg_clienteapp.model;


import java.io.Serializable;
import java.util.Objects;

public class ConsumidorActividadOfertante implements Serializable {
    private int idConsumidorActividadOfertante;

    private ActividadOfertante actividadOfertante;

    private Consumidor consumidor;

    public int getIdConsumidorActividadOfertante() {
        return idConsumidorActividadOfertante;
    }

    public void setIdConsumidorActividadOfertante(int idConsumidorActividadOfertante) {
        this.idConsumidorActividadOfertante = idConsumidorActividadOfertante;
    }

    public ActividadOfertante getActividadOfertante() {
        return actividadOfertante;
    }

    public void setActividadOfertante(ActividadOfertante actividadOfertante) {
        this.actividadOfertante = actividadOfertante;
    }

    public Consumidor getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(Consumidor consumidor) {
        this.consumidor = consumidor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsumidorActividadOfertante that = (ConsumidorActividadOfertante) o;
        return idConsumidorActividadOfertante == that.idConsumidorActividadOfertante && Objects.equals(actividadOfertante, that.actividadOfertante) && Objects.equals(consumidor, that.consumidor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConsumidorActividadOfertante, actividadOfertante, consumidor);
    }

    @Override
    public String toString() {
        return "ConsumidorActividadOfertante{" +
                "idConsumidorActividadOfertante=" + idConsumidorActividadOfertante +
                ", actividadOfertante=" + ((actividadOfertante!=null)?actividadOfertante.getIdActividadOfertante():"null") +
                ", consumidor=" + ((consumidor!=null)?consumidor.getIdConsumidor():"null") +
                '}';
    }
}
