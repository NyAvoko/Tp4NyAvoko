/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nyavoko.tpbanquenyavoko.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Avoko
 */
@Entity
@Table(name = "COMPTEBANCAIRE")
@XmlRootElement
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOM")
    String nom;

    @Column(name = "SOLDE")
    int solde;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OperationBancaire> operations = new ArrayList<>();

    public CompteBancaire() {
    }

    public List<OperationBancaire> getOperations() {
        return operations;
    }

    public CompteBancaire(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
        this.operations.add(new OperationBancaire("Création du compte", solde));
    }

    public void deposer(int montant) {
        solde += montant;
        this.operations.add(new OperationBancaire("Débit", montant));
    }

    public void retirer(int montant) {
        if (montant < solde) {
            solde -= montant;
        } else {
            solde = 0;
        }
        this.operations.add(new OperationBancaire("Crédit", montant));
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nyavoko.tpbanquenyavoko.entity.CompteBancaire[ id=" + id + " ]";
    }

}
