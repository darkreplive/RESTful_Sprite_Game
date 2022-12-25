/*
 * Sprite entity class
 * Stores sprite data thats shows on view jsf page
 * Tyler King 040979598 3/18/2022
 */
package cst8218.ID040979598.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;

/**
 *
 * @author darkr
 */
@Entity
public class Sprite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Min(0)
    private Integer x;
    @Min(0)
    private Integer y;
    private Integer xSpeed;
    private Integer ySpeed;
    
    //getters and setters
    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getxSpeed() {
        return xSpeed;
    }

    public Integer getySpeed() {
        return ySpeed;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void setxSpeed(Integer xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setySpeed(Integer ySpeed) {
        this.ySpeed = ySpeed;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * update x and y location by adding x and y speed values
     */
    public void move(){
        x=x+xSpeed;
        y=y+ySpeed;
    }
    
    /**
     * Bounce methods invert direction of sprite entity when they reach any border
     */
    public void bounce_Top(){
        ySpeed*=-1;
        if(y < 0){
            y =0;
        }
    }
    
    public void bounce_Bottom(){
        ySpeed*=-1;
        if(y > 500){
            y =500;
        }
    }
    
    public void bounce_Left(){
        xSpeed*=-1;
        if(x < 0){
            x =0;
        }
    }
    
    public void bounce_Right(){
        xSpeed*=-1;
        if(x > 500){
            x =500;
        }
    }
    
    /**
     * updates variable info with given sprite
     * null values are replaced with the value from current version of entity given
     * @param entity 
     */
    public void updates(Sprite entity){
        if(entity.getX()!=null){
                this.setX(entity.getX());
            }
            if(entity.getY()!=null){
                this.setY(entity.getY());
            }
            if(entity.getxSpeed()!=null){
                this.setxSpeed(entity.getxSpeed());
            }
            if(entity.getySpeed()!=null){
                this.setySpeed(entity.getySpeed());
            }
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
        if (!(object instanceof Sprite)) {
            return false;
        }
        Sprite other = (Sprite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cst8218.entity.Sprite[ id=" + id + " ]";
    }
    
}
