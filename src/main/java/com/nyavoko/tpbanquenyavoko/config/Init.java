/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nyavoko.tpbanquenyavoko.config;

import com.nyavoko.tpbanquenyavoko.entity.CompteBancaire;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import com.nyavoko.tpbanquenyavoko.service.GestionnaireCompte;
import jakarta.enterprise.context.RequestScoped;

/**
 *
 * @author Ny Avoko
 */
@RequestScoped
public class Init {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public void init(
            @Observes
            @Initialized(ApplicationScoped.class) ServletContext context) {
        gestionnaireCompte.creerCompte(new CompteBancaire("John Lennon", 150000));
        gestionnaireCompte.creerCompte(new CompteBancaire("Paul McCartney", 950000 ));
        gestionnaireCompte.creerCompte(new CompteBancaire("Ringo Starr", 20000 ));
        gestionnaireCompte.creerCompte(new CompteBancaire("Georges Harrisson", 100000));

    }
}
