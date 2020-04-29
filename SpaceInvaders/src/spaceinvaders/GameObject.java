package spaceinvaders;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

/**
 * @author Jasen Ratnam
 */
public class GameObject {
    protected Circle circle;
    protected Vector2D position;
    protected Vector2D velocity;
    protected Vector2D acceleration;
    protected String colour;
    
    public GameObject(Vector2D position, Vector2D velocity, Vector2D acceleration, double radius, String colour)
    {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration; 
        this.colour = colour;
        
        circle = new Circle(0.0, 0.0, radius);
        circle.setLayoutX(position.getX());
        circle.setLayoutY(position.getY());
        if(colour.equalsIgnoreCase("green"))
        {
            circle.setFill(AssetManager.getGreenAlienImage());
        }
        else if(colour.equalsIgnoreCase("red"))
        {
            circle.setFill(AssetManager.getRedAlienImage());
        }
        else if(colour.equalsIgnoreCase("yellow"))
        {
            circle.setFill(AssetManager.getYellowAlienImage());
        }
        else if(colour.equalsIgnoreCase("ship"))
        {
            circle.setFill(AssetManager.getSpaceShipImage());
        }
        else if(colour.equalsIgnoreCase("shipbullet"))
        {
            circle.setFill(AssetManager.getSpaceShipBulletImage());
        }
        else if(colour.equalsIgnoreCase("alienbullet"))
        {
            circle.setFill(AssetManager.getAlienBulletImage());
        }
       
    }
    
    public Vector2D getPosition()
    {
        return position;
    }
    
    public void setPosition(double x, double y)
    {
        position.setX(x);
        position.setY(y);
        circle.setLayoutX(position.getX());
        circle.setLayoutY(position.getY());        
    }
    
    public Circle getCircle()
    {
        return circle;
    }
    
    public void update(double dt)
    {
        // Euler Integration
        // Update velocity
        Vector2D frameAcceleration = acceleration.mul(dt);
        velocity = velocity.add(frameAcceleration);

        // Update position
        position = position.add(velocity.mul(dt));
        circle.setLayoutX(position.getX());
        circle.setLayoutY(position.getY());
        
        // slow down
        AnchorPane p = (AnchorPane)circle.getParent();
        if (position.getY() > p.getHeight() - circle.getRadius())
        {
            double absVelocityY = Math.abs(velocity.getY());
            absVelocityY *= 0.8;
            velocity.setY(-absVelocityY);
        }
    }
}
