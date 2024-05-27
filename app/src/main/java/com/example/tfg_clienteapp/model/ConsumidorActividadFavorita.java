package com.example.tfg_clienteapp.model;

import java.util.Objects;

public class ConsumidorActividadFavorita {
    private int idConsumidorActividadFavorita;

    private ActividadOfertante actividadOfertante;

    private Consumidor consumidor;

    public int getIdConsumidorActividadFavorita() {
        return idConsumidorActividadFavorita;
    }

    public void setIdConsumidorActividadFavorita(int idConsumidorActividadFavorita) {
        this.idConsumidorActividadFavorita = idConsumidorActividadFavorita;
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
        ConsumidorActividadFavorita that = (ConsumidorActividadFavorita) o;
        return idConsumidorActividadFavorita == that.idConsumidorActividadFavorita && Objects.equals(actividadOfertante, that.actividadOfertante) && Objects.equals(consumidor, that.consumidor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConsumidorActividadFavorita, actividadOfertante, consumidor);
    }

    @Override
    public String toString() {
        return "ConsumidorActividadOfertante{" +
                "idConsumidorActividadOfertante=" + idConsumidorActividadFavorita +
                ", actividadOfertante=" + ((actividadOfertante!=null)?actividadOfertante.getIdActividadOfertante():"null") +
                ", consumidor=" + ((consumidor!=null)?consumidor.getIdConsumidor():"null") +
                '}';
    }
}
