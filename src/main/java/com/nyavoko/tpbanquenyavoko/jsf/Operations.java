/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nyavoko.tpbanquenyavoko.jsf;

import com.nyavoko.tpbanquenyavoko.entity.CompteBancaire;
import com.nyavoko.tpbanquenyavoko.entity.OperationBancaire;
import com.nyavoko.tpbanquenyavoko.service.GestionnaireCompte;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Avoko
 */
@Named(value = "operations")
@RequestScoped
public class Operations implements Serializable {

    /**
     * Creates a new instance of Operations
     */
    public Operations() {
    }

    private Long compteId;
    @Inject
    private GestionnaireCompte gestionnaireCompte;

    private CompteBancaire compteBancaire;

    public Long getCompteId() {
        return compteId;
    }

    public void setCompteId(Long id) {
        this.compteId = id;
    }

    public void recupererCompte() {
        this.compteBancaire = gestionnaireCompte.findById(compteId);
    }

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public List<OperationBancaire> listeOperationBancaires() {
        return compteBancaire.getOperations();
    }

}
