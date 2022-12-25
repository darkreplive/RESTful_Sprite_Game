/*
 * Responsible for game logic and storing all active sprites
 * Runs a loop in a thread that moves all sprites according to their x and y speeds 
   then checks if they need to bounce
   Sleeps for 1/10th of a second and repeats
 * Tyler King 040979598 3/18/2022
 */
package cst8218.ID040979598.game;

import cst8218.ID040979598.entity.Sprite;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Tyler King
 */
@Startup
@Singleton
public class SpriteGame {
    //sets borders/size of map
    private Integer x_size = 500;
    private Integer y_size = 500;
    //list of all sprites in the game
    private List<Sprite> spriteList;

    @Inject
    private GameSession session;

    //set a thread 
    //  infinite loop
    //      retrieve all sprites
    //      move sprites
    //      pause 1/10 sec
    //new Thread().start();
    private void doBounce(Sprite s){
        if(s.getX() < 0 && s.getxSpeed() < 0){
            s.bounce_Left();
        }
        if(s.getY() < 0 && s.getySpeed() < 0){
            s.bounce_Top();
        }
        if(s.getX() > x_size && s.getxSpeed() > 0){
            s.bounce_Right();
        }
        if(s.getY() > y_size && s.getySpeed() > 0){
            s.bounce_Bottom();
        }
    }
    @PostConstruct
    public void go() {
        new Thread(new Runnable() {
            //@Override
            public void run() {

                while (true) {
                    spriteList = session.findAll();
                    //makes every single sprite move and check if it needs to bounce
                    for (Sprite s : spriteList) {
                        s.move();
                        doBounce(s);
                        //updates entity info
                        session.edit(s);
                    }
                    
                    //sleep to slow to 1/10 sec
                    try {Thread.sleep(100);}
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }//while end
            }//run end
        }).start();//thread end
    }// go end
    
}//class ends
