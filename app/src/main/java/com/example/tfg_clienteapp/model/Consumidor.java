package com.example.tfg_clienteapp.model;


import java.util.List;
import java.util.Objects;

public class Consumidor {
    private int idConsumidor;

    private String username;

    private String email;

    private String password;

    private List<ActividadConsumidor> listaActividadesDeConsumidor;


    private List<ConsumidorActividadOfertante> listaConsumidoresActividadOfertantes;

    public Consumidor(){

    }
    public Consumidor(int idConsumidor, String username, String email, String password, List<ActividadConsumidor> listaActividadesDeConsumidor, List<ConsumidorActividadOfertante> listaConsumidoresActividadOfertantes) {
        this.idConsumidor = idConsumidor;
        this.username = username;
        this.email = email;
        this.password = password;
        this.listaActividadesDeConsumidor = listaActividadesDeConsumidor;
        this.listaConsumidoresActividadOfertantes = listaConsumidoresActividadOfertantes;
    }

    public int getIdConsumidor() {
        return idConsumidor;
    }

    public void setIdConsumidor(int idConsumidor) {
        this.idConsumidor = idConsumidor;
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

    public List<ActividadConsumidor> getListaActividadesDeConsumidor() {
        return listaActividadesDeConsumidor;
    }

    public void setListaActividadesDeConsumidor(List<ActividadConsumidor> listaActividadesDeConsumidor) {
        this.listaActividadesDeConsumidor = listaActividadesDeConsumidor;
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
        Consumidor that = (Consumidor) o;
        return idConsumidor == that.idConsumidor && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(listaActividadesDeConsumidor, that.listaActividadesDeConsumidor) && Objects.equals(listaConsumidoresActividadOfertantes, that.listaConsumidoresActividadOfertantes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "Consumidor{" +
                "idConsumidor=" + idConsumidor +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", listaActividadesDeConsumidor=" + ((listaActividadesDeConsumidor!=null)?listaActividadesDeConsumidor.size():"0") +
                ", listaConsumidoresActividadOfertantes=" +  ((listaConsumidoresActividadOfertantes!=null)?listaConsumidoresActividadOfertantes.size():"0") +
                '}';
    }
}
