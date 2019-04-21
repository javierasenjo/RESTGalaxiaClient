/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author javie
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Planeta")
public class Planeta implements Serializable {

    @XmlElement
    private String nombre;
    @XmlElement
    private Integer edad;
    @XmlElement
    private Double radio;

    public Planeta() {
    }

    public Planeta(String nombre, Integer edad, Double radio) {
        this.nombre = nombre;
        this.edad = edad;
        this.radio = radio;
    }

    @Override
    public String toString() {
        return " Planeta{" + "nombre=" + nombre + ", edad=" + edad + ", radio=" + radio + '}';
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the edad
     */
    public Integer getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    /**
     * @return the radio
     */
    public Double getRadio() {
        return radio;
    }

    /**
     * @param radio the radio to set
     */
    public void setRadio(Double radio) {
        this.radio = radio;
    }
}
