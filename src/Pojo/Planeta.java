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
    private Integer idPlaneta;
    @XmlElement
    private String nombre;
    @XmlElement
    private Integer edad;
    @XmlElement
    private Double radio;
    @XmlElement
    private String linkPlaneta;

    public Planeta() {
    }

    public Planeta(String nombre, Integer edad, Double radio) {
        this.nombre = nombre;
        this.edad = edad;
        this.radio = radio;
    }

    public Planeta(Integer planetaId, String nombre, Integer edad, Double radio, String link) {
        this.nombre = nombre;
        this.edad = edad;
        this.radio = radio;
        this.idPlaneta = planetaId;
        this.linkPlaneta = link;
    }

    @Override
    public String toString() {
        return "Planeta{" + "nombre=" + nombre + ", edad=" + edad + ", radio=" + radio + ", linkPlaneta=" + linkPlaneta + ", idPlaneta=" + idPlaneta + '}';
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

    /**
     * @return the linkPlaneta
     */
    public String getLinkPlaneta() {
        return linkPlaneta;
    }

    /**
     * @param linkPlaneta the linkPlaneta to set
     */
    public void setLinkPlaneta(String linkPlaneta) {
        this.linkPlaneta = linkPlaneta;
    }

    /**
     * @return the idPlaneta
     */
    public Integer getIdPlaneta() {
        return idPlaneta;
    }

    /**
     * @param idPlaneta the idPlaneta to set
     */
    public void setIdPlaneta(Integer idPlaneta) {
        this.idPlaneta = idPlaneta;
    }

    public String crearLink(int idPlaneta, int idGalaxia) {
        return "http://localhost:8080/RestGalaxia/webresources/galaxia/planeta/" + idPlaneta;
    }
}
