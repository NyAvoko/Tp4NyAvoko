/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.nyavoko.tpbanquenyavoko.jsf;

import com.nyavoko.tpbanquenyavoko.entity.CompteBancaire;
import com.nyavoko.tpbanquenyavoko.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Ny Avoko
 */
@Named(value = "listeComptes")
@Dependent
public class ListeComptes implements Serializable {

    private List<CompteBancaire> allComptes;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }

    public List<CompteBancaire> getAllComptes() {
        allComptes = gestionnaireCompte.getAllComptes();
        return allComptes;
    }

}
