package com.example.tfg_clienteapp.model;

import java.util.Objects;

public class OfertanteActividadFavorita {

    private int idOfertanteActividadFavorita;

    private ActividadConsumidor actividadConsumidor;

    private Ofertante ofertante;

    public int getIdOfertanteActividadFavorita() {
        return idOfertanteActividadFavorita;
    }

    public void setIdOfertanteActividadFavorita(int idOfertanteActividadFavorita) {
        this.idOfertanteActividadFavorita = idOfertanteActividadFavorita;
    }

    public com.example.tfg_clienteapp.model.ActividadConsumidor getActividadConsumidor() {
        return actividadConsumidor;
    }

    public void setActividadConsumidor(com.example.tfg_clienteapp.model.ActividadConsumidor actividadConsumidor) {
        this.actividadConsumidor = actividadConsumidor;
    }

    public Ofertante getOfertante() {
        return ofertante;
    }

    public void setOfertante(Ofertante ofertante) {
        this.ofertante = ofertante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfertanteActividadFavorita that = (OfertanteActividadFavorita) o;
        return idOfertanteActividadFavorita == that.idOfertanteActividadFavorita && Objects.equals(actividadConsumidor, that.actividadConsumidor) && Objects.equals(ofertante, that.ofertante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOfertanteActividadFavorita, actividadConsumidor, ofertante);
    }

    @Override
    public String toString() {
        return "ConsumidorActividadOfertante{" +
                "idOfertanteActividadFavorita=" + idOfertanteActividadFavorita +
                ", actividadConsumidor=" + ((actividadConsumidor!=null)?actividadConsumidor.getIdActividadConsumidor():"null") +
                ", ofertante=" + ((ofertante!=null)?ofertante.getIdOfertante():"null") +
                '}';
    }
}
