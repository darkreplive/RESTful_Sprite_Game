/*
 * Session bean for the project
 * Emulates sprite game
 * Tyler King 040979598 3/18/2022
 */
package cst8218.ID040979598.game;

import cst8218.ID040979598.entity.Sprite;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author darkr
 */
@Stateless
public class GameSession {
    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    //finds all sprites and returns a list 
    public List<Sprite> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Sprite.class));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public void edit(Sprite entity) {
        getEntityManager().merge(entity);
    }
    
}
