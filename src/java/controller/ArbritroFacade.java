/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entidades.Arbritro;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author WILMER
 */
@Stateless
public class ArbritroFacade extends AbstractFacade<Arbritro> {
    @PersistenceContext(unitName = "proyectofutbolPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArbritroFacade() {
        super(Arbritro.class);
    }
    
}
