package com.example.tfg_clienteapp.model;



import java.util.List;
import java.util.Objects;

public class Ofertante {
    private int idOfertante;

    private String username;

    private String email;

    private String password;

    private List<ActividadOfertante> listaActividadesOfertante;

    private List<ActividadConsumidor> listaActividadesConsumidor;


    public int getIdOfertante() {
        return idOfertante;
    }

    public void setIdOfertante(int idOfertante) {
        this.idOfertante = idOfertante;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ActividadOfertante> getListaActividadesOfertante() {
        return listaActividadesOfertante;
    }

    public void setListaActividadesOfertante(List<ActividadOfertante> listaActividadesOfertante) {
        this.listaActividadesOfertante = listaActividadesOfertante;
    }

    public List<ActividadConsumidor> getListaActividadesConsumidor() {
        return listaActividadesConsumidor;
    }

    public void setListaActividadesConsumidor(List<ActividadConsumidor> listaActividadesConsumidor) {
        this.listaActividadesConsumidor = listaActividadesConsumidor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ofertante ofertante = (Ofertante) o;
        return idOfertante == ofertante.idOfertante && Objects.equals(username, ofertante.username) && Objects.equals(email, ofertante.email) && Objects.equals(password, ofertante.password) && Objects.equals(listaActividadesOfertante, ofertante.listaActividadesOfertante) && Objects.equals(listaActividadesConsumidor, ofertante.listaActividadesConsumidor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "Ofertante{" +
                "idOfertante=" + idOfertante +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", listaActividadesOfertante=" + ((listaActividadesOfertante!=null)?listaActividadesOfertante.size():0) +
                ", listaActividadesConsumidor=" + ((listaActividadesConsumidor!=null)?listaActividadesConsumidor.size():0) +
                '}';
    }
}
