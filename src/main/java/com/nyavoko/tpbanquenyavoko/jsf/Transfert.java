/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nyavoko.tpbanquenyavoko.jsf;

import com.nyavoko.tpbanquenyavoko.entity.CompteBancaire;
import com.nyavoko.tpbanquenyavoko.service.GestionnaireCompte;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Objects;


/**
 *
 * @author Ny Avoko
 */
@Named(value = "transfert")
@ViewScoped
public class Transfert implements Serializable {

    private Long idCompteSource;
    private Long idCompteDestinataire;
    private int montant;
    private boolean erreur;

    public Long getCompteSource() {
        return idCompteSource;
    }

    public void setCompteSource(Long compteSource) {
        this.idCompteSource = compteSource;
    }

    public Long getCompteDestinataire() {
        return idCompteDestinataire;
    }

    public void setCompteDestinataire(Long compteDestinataire) {
        this.idCompteDestinataire = compteDestinataire;
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

        CompteBancaire compteSource = gestionnaireCompte.findById(idCompteSource);
        CompteBancaire compteDestinataire = gestionnaireCompte.findById(idCompteDestinataire);

        if (compteSource == null) {
            System.out.print("compteSource NULL");
            Util.messageErreur("Compte inexistant: " + idCompteSource, "Compte inexistant : " + idCompteSource, "form:source");
            // Message d'erreur associé au composant source ; form:source est l'id client
            // si l'id du formulaire est "form" et l'id du champ de saisie de l'id de la source est "source"
            // dans la page JSF qui lance le transfert.
            erreur = true;
        }
        if (compteDestinataire == null) {
            Util.messageErreur("Compte inexistant: " + idCompteDestinataire, "Compte inexistant: " + idCompteDestinataire, "form:destinataire");
            // Message d'erreur associé au composant source ; form:source est l'id client
            // si l'id du formulaire est "form" et l'id du champ de saisie de l'id de la source est "source"
            // dans la page JSF qui lance le transfert.
            erreur = true;
        }
        if (Objects.equals(idCompteSource, idCompteDestinataire)) {
            Util.messageErreur("Les comptes source et destination doivent être différents", "Les comptes source et destination doivent être différents", "form:source");
            Util.messageErreur("Les comptes source et destination doivent être différents", "Les comptes source et destination doivent être différents", "form:destinataire");
            erreur = true;
        }
        if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }

        gestionnaireCompte.transfertArgent(compteSource, compteDestinataire, montant);
        Util.addFlashInfoMessage("Transfert correctement effectué");
        return "listeComptes?faces-redirect=true";
    }

}