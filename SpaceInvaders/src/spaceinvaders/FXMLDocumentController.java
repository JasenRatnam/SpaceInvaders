package spaceinvaders;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;

/**
 * Space invaders 
 * 12/5/2017
 * @Jasen Ratnam 1622549
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    AnchorPane pane;
    
    @FXML
    private Label lifes;
    
    @FXML
    private Label scores;
    
    private double lastFrameTime = 0.0;
    private ArrayList<GameObject> Aliens;
//    private ArrayList<GameObject> GreenAlien;
//    private ArrayList<GameObject> RedAlien;
//    private ArrayList<GameObject> YellowAlien;
    private GameObject SpaceShip;
    private ArrayList<GameObject> SpaceShipBullet;
    private ArrayList<GameObject> AlienBullet;

            
    private int numCircleDestroyed = 0;
    private double mouseX=0.0, mouseY=0.0;
    
    private int radius = 50;
    private int diameter = radius*2;
    
    private double BOARD_WIDTH  = 1024.0;
    private double BOARD_HEIGHT  = 768.0;
    private double BORDER_RIGHT  = 1024.0 - diameter;
    private double BORDER_LEFT  = diameter;
    private int direction = 1;
    private int score = 0;
    private boolean isGameOver = false;
    
    private int lives = 3;
    
    @FXML
    public void onMouseMove(MouseEvent event)
    {
        if(isGameOver == false)
        {
        System.out.println(pane.getHeight());

        System.out.println("(" + event.getX() + ")" + "(" + event.getY() + ")");
        mouseX = event.getX();
        mouseY = pane.getHeight()-radius;
        
        SpaceShip.setPosition(mouseX, mouseY);
        }
    }
    
    @FXML
    public void onMouseClick(MouseEvent event)
    {
        System.out.println("Clicked: " + event.getButton());
        
        if (event.getButton() == MouseButton.PRIMARY && isGameOver == false)
        {
            String colour = "shipbullet";
            GameObject bullet = new GameObject(new Vector2D(mouseX, mouseY),
                                            new Vector2D(0.0,-500.0),
                                            new Vector2D(0.0,0.0),
                                            10, colour);
            SpaceShipBullet.add(bullet);
            addToPane(bullet.getCircle());
            AssetManager.getShootingSound().play();
        }
        
    }
    
    public void addToPane(Node node)
    {
        pane.getChildren().add(node);
    }
    
    public void addGreenAliens()
    {
        String colour = "green";
//        GreenAlien = new ArrayList<GameObject>();
                    
        for(int i = 0; i < 9; i++)
        {
            GameObject greenAlien = new GameObject( new Vector2D(diameter + ((diameter)*i), diameter), 
                                            new Vector2D(0.0,0.0),
                                            new Vector2D(0.0,0.0),
                                            radius, colour);
            Aliens.add(greenAlien);
            addToPane(greenAlien.getCircle());
        }
        
        
    }
    
    public void addRedAliens()
    {
        String colour = "red";
//        RedAlien = new ArrayList<GameObject>();
        
        for(int i = 0; i < 9; i++)
        {
          
            GameObject redAlien = new GameObject( new Vector2D(diameter + ((diameter)*i), diameter*2), 
                                                new Vector2D(0.0,0.0),
                                                new Vector2D(0.0,0.0),
                                                radius, colour);
            
            Aliens.add(redAlien);
            addToPane(redAlien.getCircle());
        }
    }
    
    public void addYellowAliens()
    {
        String colour = "yellow";
//        YellowAlien = new ArrayList<GameObject>();
        
        for(int i = 0; i < 9; i++)
        {
          
            GameObject yellowAlien = new GameObject( new Vector2D(diameter + ((diameter)*i), diameter*3), 
                                                new Vector2D(0.0,0.0),
                                                new Vector2D(0.0,0.0),
                                                radius, colour);
            Aliens.add(yellowAlien);
            addToPane(yellowAlien.getCircle());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lastFrameTime = 0.0f;
        long initialTime = System.nanoTime();


        AssetManager.preloadAllAssets();
        
        MediaPlayer musicPlayer = new MediaPlayer(AssetManager.getBackgroundMusic());
        musicPlayer.play();
        
        pane.setBackground(AssetManager.getBackgroundImage());
        
        Aliens = new ArrayList<GameObject>();
        AlienBullet = new ArrayList<GameObject>();
        
        addGreenAliens();
        addRedAliens();
        addYellowAliens();
        String colour = "ship";
        mouseX = (BOARD_WIDTH/2);
        mouseY = (BOARD_HEIGHT);
        SpaceShip = new GameObject(new Vector2D(mouseX, mouseY),
                                   new Vector2D(0.0,0.0),
                                   new Vector2D(0.0,-10.0),
                                   radius, colour);
        addToPane(SpaceShip.getCircle());
        
        
        SpaceShipBullet = new ArrayList<>();
        
        new AnimationTimer()
       {
           @Override
            public void handle(long now) {
//                // Time calculation                
                double currentTime = (now - initialTime) / 1000000000.0;
                double  frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;
             
                lifes.setText("Lives: " + (lives));
                scores.setText("Scores: " + score);
//                
                if ((int)currentTime > 0 && isGameOver == false)
                {
                    for (GameObject alien: Aliens)
                    {
                        if(direction == 1)
                        {
                            alien.setPosition((alien.getPosition().getX() + 0.2), (alien.getPosition().getY()));
                        }
                        if(direction == -1)
                        {
                            alien.setPosition((alien.getPosition().getX() + -0.2), (alien.getPosition().getY()));
                        }


                        double x = alien.getPosition().getX();

                        if (x >= BOARD_WIDTH - radius && direction != -1) {

                            direction = -1;
                            Iterator i1 = Aliens.iterator();

                            while (i1.hasNext()) {

                                GameObject a2 = (GameObject) i1.next();
                                a2.setPosition(a2.getPosition().getX(),(a2.getPosition().getY() + diameter));
                            }
                        }

                        if (x <= BORDER_LEFT && direction != 1) {

                            direction = 1;

                            Iterator i2 = Aliens.iterator();

                            while (i2.hasNext()) {

                                GameObject a = (GameObject) i2.next();
                                a.setPosition(a.getPosition().getX(),(a.getPosition().getY() + diameter));
                                
                            }
                        }
                    }
                }
                
                if ((int)currentTime > 1+AlienBullet.size() + numCircleDestroyed && isGameOver == false)
                {
                    String bullt = "alienbullet";
                    Random rng = new Random();
//                    int AlienNumber = rng.nextInt((int) Aliens.size());
                    int x = rng.nextInt((int) Aliens.size());
                    int y = rng.nextInt((int) Aliens.size());

                    GameObject circle = new GameObject( new Vector2D(Aliens.get(x).getPosition().getX(),Aliens.get(y).getPosition().getY()), 
                                                        new Vector2D(0.0,250.0),
                                                        new Vector2D(0.0, 0.0),
                                                        radius,bullt);
                    AlienBullet.add(circle);
                    addToPane(circle.getCircle());
                }

//                // Test for collisions
                for (int i=0; i<SpaceShipBullet.size(); ++i)
                {
                    GameObject c1 = SpaceShipBullet.get(i); 
                    for (int j=i; j<Aliens.size(); ++j)
                    {
                        GameObject c2 = Aliens.get(j);
                        double d = Vector2D.distance(c1.getPosition(), c2.getPosition());
                        
                        if (d < c1.getCircle().getRadius() + c2.getCircle().getRadius())
                        {
                            System.out.println("collision!!");
                            
                            SpaceShipBullet.remove(c1);
                            Aliens.remove(c2);
                            pane.getChildren().remove(c1.getCircle());
                            pane.getChildren().remove(c2.getCircle());
                            j = SpaceShipBullet.size();
                            AssetManager.getAlienShotSound().play();
                            score += 10;
                            
                            System.out.println(score);
                        }
                    }
                }
                
                for (int i=0; i<AlienBullet.size(); ++i)
                {
                    GameObject c1 = AlienBullet.get(i); 

                    GameObject c2 = SpaceShip;
                    
                    double d = Vector2D.distance(c1.getPosition(), c2.getPosition());

                    if (d < c1.getCircle().getRadius() + c2.getCircle().getRadius() && isGameOver == false)
                    {
                        System.out.println("collision!!");

                        AlienBullet.remove(c1);
                        pane.getChildren().remove(c1.getCircle());
//                        pane.getChildren().remove(c2.getCircle());
                        numCircleDestroyed +=1;
                        lives--;
                        
                        AssetManager.getGameoverSound().play();
                        
                        if(lives == 0)
                        {
                            pane.setBackground(AssetManager.getGameOverBackgroundImage());
                            musicPlayer.stop();
                            AssetManager.getGameoverSound().play();
                            isGameOver = true;
                        }
                    }
                }
                
                for (int i=0; i<Aliens.size(); ++i)
                {
                    GameObject c1 = Aliens.get(i); 

                    GameObject c2 = SpaceShip;
                    
                    double d = Vector2D.distance(c1.getPosition(), c2.getPosition());

                    if (d < c1.getCircle().getRadius() + c2.getCircle().getRadius() && isGameOver == false)
                    {
                        System.out.println("collision!!");

//                        Aliens.remove(c1);
//                        pane.getChildren().remove(c1.getCircle());
                        pane.getChildren().remove(c2.getCircle());
                        numCircleDestroyed +=1;
                        lives--;
                        
                        AssetManager.getGameoverSound().play();
                        
                        if(lives == 0)
                        {
                            pane.setBackground(AssetManager.getGameOverBackgroundImage());
                            musicPlayer.stop();
                            AssetManager.getGameoverSound().play();
                            isGameOver = true;
                        }
                    }
                }
                
                for (int j = 0; j<SpaceShipBullet.size(); ++j)
                {
                    if(SpaceShipBullet.get(j).getPosition().getY() < 0)
                    {
                        pane.getChildren().remove(SpaceShipBullet.get(j).getCircle());
                        SpaceShipBullet.remove(j);
                    }
                }
                
                for (int j = 0; j<AlienBullet.size(); ++j)
                {
                    if(AlienBullet.get(j).getPosition().getY() > pane.getHeight()-radius)
                    {
                        pane.getChildren().remove(AlienBullet.get(j).getCircle());
                        AlienBullet.remove(j);
                        numCircleDestroyed +=1;
                    }
                }
                
                System.out.print(SpaceShipBullet.size());
                 
                for (GameObject obj : AlienBullet)
                {
                    obj.update(frameDeltaTime);
                }
                
//                // Update existing bullets
                for (GameObject obj : SpaceShipBullet)
                {
                    obj.update(frameDeltaTime);
                }
                
                if(Aliens.isEmpty() && isGameOver == false)
                {
                    isGameOver = true;
                    pane.setBackground(AssetManager.getWinnerbackgorundImage());
                    musicPlayer.stop();
                    AssetManager.getWinnerSound().play();
                }
               
            }
        }.start(); 
    }    
    
}
