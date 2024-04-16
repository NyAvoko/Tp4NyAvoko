/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nyavoko.tpbanquenyavoko.jsf;

import com.nyavoko.tpbanquenyavoko.service.GestionnaireCompte;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author Ny Avoko
 */
@Named(value = "transfert")
@ViewScoped
public class Transfert implements Serializable {

    private Long compteSource;
    private Long compteDestinataire;
    private int montant;

    public Long getCompteSource() {
        return compteSource;
    }

    public void setCompteSource(Long compteSource) {
        this.compteSource = compteSource;
    }

    public Long getCompteDestinataire() {
        return compteDestinataire;
    }

    public void setCompteDestinataire(Long compteDestinataire) {
        this.compteDestinataire = compteDestinataire;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    /**
     * Creates a new instance of Transfert
     */
    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public Transfert() {
    }

    public String faireTransfert() {
        gestionnaireCompte.transfertArgent(compteSource, compteDestinataire, montant);
        return "listeComptes";
    }

}
