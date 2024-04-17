/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nyavoko.tpbanquenyavoko.jsf;

import com.nyavoko.tpbanquenyavoko.service.GestionnaireCompte;
import com.nyavoko.tpbanquenyavoko.entity.CompteBancaire;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


/**
 *
 * @author Ny Avoko
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    @Inject
    GestionnaireCompte gestionnaireCompte;

    private String nom;

    private int solde;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String faire() {
        CompteBancaire compte = new CompteBancaire(nom, solde);
        gestionnaireCompte.creerCompte(compte);
        Util.addFlashInfoMessage("L'ajout a été une réussite");
        return "listeComptes?faces-redirect=true";
    }
}



