/*
 * Basic Facade class for sprite
   Face of the data access/ encapsulaes interaction with sql db
 * SpriteFacadeRest is used over this
 * Tyler King 040979598 3/18/2022
 */
package com.mycompany.assignment1;

import cst8218.ID040979598.entity.Sprite;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author darkr
 */
@Stateless
public class SpriteFacade extends AbstractFacade<Sprite> {

    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SpriteFacade() {
        super(Sprite.class);
    }
    
}
