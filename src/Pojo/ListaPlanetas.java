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
@XmlRootElement(name = "Planetas")
public class ListaPlanetas implements Serializable {

    public ListaPlanetas() {

    }

    public ListaPlanetas(List<Planeta> lPlanetas) {
        this.planetas=lPlanetas;
    }
    @XmlElement(name = "Planeta")
    private List<Planeta> planetas = new ArrayList();

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

}
