/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author javie
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Galaxia")
public class Galaxia implements Serializable {

    @XmlElement
    private String nombre;

    @XmlElement(name = "Planeta")
    @XmlElementWrapper(name = "Planetas")
    private List<Planeta> planetas = new ArrayList();

    @XmlElement()
    private String linkGalaxia;
    @XmlElement()
    private Integer idGalaxia;

    public Galaxia() {
        this.planetas = new ArrayList<Planeta>();
    }

    public Galaxia(String nombre, List<Planeta> planetas) {
        this.nombre = nombre;
        this.planetas = planetas;
    }

    public Galaxia(Integer galaxiaId, String nombre, List<Planeta> planetas, String link) {
        this.nombre = nombre;
        this.planetas = planetas;
        this.idGalaxia = galaxiaId;
        this.linkGalaxia = link;
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

    public void annadirPlaneta(Planeta planeta) {
        if (getPlanetas().isEmpty()) {
            planetas = new ArrayList<>();
        }
        planetas.add(planeta);
    }

    public int contarPlanetas() {
        int numPlanetas = getPlanetas().size();
        return numPlanetas;
    }

    @Override
    public String toString() {
        String respuesta = "";
        respuesta += ("Nombre de la galaxia: " + nombre);
        for (int i = 0; i < planetas.size(); i++) {
            respuesta += ("\n Planeta " + i + ": " + planetas.get(i).toString());
        };
        return respuesta;
    }

    /**
     * @return the planetas
     */
    public List<Planeta> getPlanetas() {
        return planetas;
    }

    /**
     * @param planetas the planetas to set
     */
    public void setPlanetas(List<Planeta> planetas) {
        this.planetas = planetas;
    }

    public Planeta getPlaneta(int numPlaneta) {
        return getPlanetas().get(numPlaneta);
    }

    /**
     * @return the linkGalaxia
     */
    public String getLinkGalaxia() {
        return linkGalaxia;
    }

    /**
     * @param linkGalaxia the linkGalaxia to set
     */
    public void setLinkGalaxia(String linkGalaxia) {
        this.linkGalaxia = linkGalaxia;
    }

    /**
     * @return the idGalaxia
     */
    public Integer getIdGalaxia() {
        return idGalaxia;
    }

    /**
     * @param idGalaxia the idGalaxia to set
     */
    public void setIdGalaxia(Integer idGalaxia) {
        this.idGalaxia = idGalaxia;
    }

    public String crearLink(int idGalaxia) {
        return "http://localhost:8080/RestGalaxia/webresources/galaxia/";
    }
}
