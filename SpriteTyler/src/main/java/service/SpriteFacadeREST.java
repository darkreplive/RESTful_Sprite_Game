/* Face of the data access/ encapsulaes interaction with sql db
 * Restful api logic of Sprite project
 * Defines http methods that can be ran and the scenarios
 * Tyler King 040979598 3/18/2022
 */
package service;

import cst8218.ID040979598.entity.Sprite;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author darkr
 */
@Stateless
@Path("cst8218.ID040979598.entity")
public class SpriteFacadeREST extends AbstractFacade<Sprite> {

    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager em;

    public SpriteFacadeREST() {
        super(Sprite.class);
    }
    
    /**
     * Creates new sprites with given info or updates non null information if id already exists
     * @param entity 
     */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Sprite entity) {
        //creates a new entity if the id is null
        //Sprite hold = entity;
        if(entity.getId()== null){
        entity=valid(entity);
        super.create(entity);
        }
        /**
         *if id exists changes values where input isn't null
         */
        if(find(entity.getId()) != null){
            updates(entity);
        }     
    }
    
    /**
     * checks if given entity has null values before running an edit
     * null values are replaced with the value from current version of entity given (sprite class)
     * @param entity 
     */
    public void updates(Sprite entity){
        Sprite temp = find(entity.getId());
        temp.updates(entity);
        super.edit(temp);
    }
    /**
     * if x or y positions are negative they become positive
     * if x or y is above 500 its changed to 500
     * i.e -1000 -> 1000 -> 500
     * i.e -3 -> 3
     * @param entity 
     */
     public Sprite valid(Sprite entity) {
        Sprite temp =entity;
        //if entity is null create sprite with 0 values
        if (temp == null) {
                temp.setX(0);
                temp.setY(0);
                temp.setxSpeed(0);
                temp.setySpeed(0);
        } 
        //if entity isn't null check values
        else {
            //check null values
            if (temp.getX() == null) {
                temp.setX(0);
            }
            if (temp.getY() == null) {
                temp.setY(0);
            }
            if (temp.getxSpeed() == null) {
                temp.setxSpeed(0);
            }
            if (temp.getxSpeed() == null) {
                temp.setySpeed(0);
            }
            //check negatives
            if (temp.getX() < 0) {
                temp.setX(-temp.getX());
            }
            if (temp.getY() < 0) {
                temp.setY(-temp.getY());
            }
            //check maximums
            if (temp.getX() > 500) {
                temp.setX(500);
            }
            if (temp.getY() > 500) {
                temp.setY(500);
            }
        }
        return temp;
    }
    
    /**
     * PUT on a specific id completely replaces (needs to already exist) with new given values
     * @param id
     * @param entity 
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Long id, Sprite entity) {
        Sprite old = find(id);
        if(old==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if(entity.getId() != null && entity.getId() != id){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        //if(find(entity.getId()) != null){
        entity=valid(entity);  
        entity.setId(id);
        //remove(id);
        super.edit(entity);
        return Response
                .ok()
                .build();
        //}
//        else{
//        return Response
//                .notModified()
//                .build();
//        }
    }

    /**
     * Post on a specific id changes non null values
     * @param id
     * @param entity 
     */
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit2(@PathParam("id") Long id, Sprite entity) {
        //if find(id) == null then error of somesort
        //otherwise if entity.getId() != null and different from id then error
        Sprite old = find(id);
        if(old==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if(entity.getId() != null && entity.getId() != id){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        entity.setId(id);
        //if(find(entity.getId()) != null ){
        //   valid(entity);
            updates(entity);
            return Response
                .ok()
                .build();
       // }
        //else{ 
        //return Response
        //        .notModified()
        //        .build();
        //}
    }
    
    /**
     * Deletes entity with specific id
     * @param id 
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }
    
    /**
     * returns id with specific id
     * @param id
     * @return 
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Sprite find(@PathParam("id") Long id) {
        return super.find(id);
    }
    
    /**
     * Gets and returns all entities
     * @return 
     */
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findAll() {
        return super.findAll();
    }
    
    /**
     * gets range of entities 
     * @param from
     * @param to
     * @return 
     */
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
    /**
     * returns total number of entities
     * @return 
     */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    /**
     * Returns entity manager 
     * @return 
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
