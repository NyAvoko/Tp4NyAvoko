/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nyavoko.tpbanquenyavoko.service;

import com.nyavoko.tpbanquenyavoko.entity.CompteBancaire;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;

/**
 *
 * @author Ny Avoko
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root", // nom et
        password = "Root@1234#*", // mot de passe que vous avez donnés lors de la création de la base de données
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)
@ApplicationScoped
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    public List<CompteBancaire> getAllComptes() {
        TypedQuery<CompteBancaire> query = em.createQuery("SELECT c FROM CompteBancaire c", CompteBancaire.class);
        return query.getResultList();
    }

    public CompteBancaire findById(Long idCompteBancaire) {
        return em.find(CompteBancaire.class, idCompteBancaire);
    }

    @Transactional
    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }

    @Transactional
    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    public long nbComptes() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM CompteBancaire c", Long.class);
        return query.getSingleResult();
    }

    @Transactional
    public void transfertArgent(CompteBancaire source, CompteBancaire destinataire, int montant) {
        destinataire.deposer(montant);
        source.retirer(montant);
        update(source);
        update(destinataire);
    }

    @Transactional
    public void deposer(CompteBancaire c, int montant) {
        c.deposer(montant);
        update(c);
    }

    @Transactional
    public void retirer(CompteBancaire c, int montant) {
        c.retirer(montant);
        update(c);
    }

    @Transactional
    public void supprimerCompte(CompteBancaire compte) {
        CompteBancaire c = em.merge(compte);
        em.remove(c);
    }

}
