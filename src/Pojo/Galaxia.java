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

    public Galaxia() {
        this.planetas = new ArrayList<Planeta>();
    }

    public Galaxia(String nombre, List<Planeta> planetas) {
        this.nombre = nombre;
        this.planetas = planetas;
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
        if(getPlanetas().isEmpty()){
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
           respuesta += ("\n Planeta " +i +": "+ planetas.get(i).toString());
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
    
    public Planeta getPlaneta(int numPlaneta){
        return getPlanetas().get(numPlaneta);
    }
}
