/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.pokemon.entities;

import co.edu.uniandes.csw.pokemon.constants.PokemonType;
import java.io.Serializable;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class PokemonEntity extends BaseEntity implements Serializable{

    private String nombre; 
    private String descripcion; 
    private double altura;
    private int peso;
    private PokemonType tipo;
     private PokemonType debilidad;
    private int ataque; 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public PokemonType getTipo() {
        return tipo;
    }
    public PokemonType getDebilidad() {
        return debilidad;
    }
    public int getAtaque() {
        return ataque;
    }

  
    public void setataque(int ataque) {
        this.ataque = ataque;
    }


    public void setTipo(PokemonType tipo) {
        this.tipo = tipo;}
     public void setDebilidad(PokemonType debilidad) {
        this.debilidad = debilidad;
    }
     

}
