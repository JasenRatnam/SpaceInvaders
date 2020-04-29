package spaceinvaders;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.ImagePattern;

/**
 * @author Jasen Ratnam
 */
public class AssetManager {
    
    static private Background backgroundImage = null;
    static private Background gameOverBackgroundImage = null;
    static private Background winnerBackgroundImage = null;
    static private ImagePattern GreenAlien = null;
    static private ImagePattern RedAlien = null;
    static private ImagePattern YellowAlien = null;
    static private ImagePattern SpaceShip = null;
    static private ImagePattern SpaceShipBullet = null;
    static private ImagePattern AlienBullet = null;
    
    static private Media backgroundMusic = null;
    static private AudioClip AlienShot = null;
    static private AudioClip shootingSound = null;
    static private AudioClip gameOver = null;
    static private AudioClip winner = null;
    
    static private String fileURL(String relativePath)
    {
        return new File(relativePath).toURI().toString();
    }
    
    static public void preloadAllAssets()
    {
        // Preload all images
        Image background = new Image(fileURL("./assets/images/background.png"));
        backgroundImage = new Background(
                            new BackgroundImage(background, 
                                                BackgroundRepeat.NO_REPEAT, 
                                                BackgroundRepeat.NO_REPEAT, 
                                                BackgroundPosition.DEFAULT,
                                                BackgroundSize.DEFAULT));
        
        Image GameOverbackground = new Image(fileURL("./assets/images/gameOverBackground.png"));
        gameOverBackgroundImage = new Background(
                            new BackgroundImage(GameOverbackground, 
                                                BackgroundRepeat.NO_REPEAT, 
                                                BackgroundRepeat.NO_REPEAT, 
                                                BackgroundPosition.DEFAULT,
                                                BackgroundSize.DEFAULT));
        
        Image Winnerbackground = new Image(fileURL("./assets/images/Winner.png"));
        winnerBackgroundImage = new Background(
                            new BackgroundImage(Winnerbackground, 
                                                BackgroundRepeat.NO_REPEAT, 
                                                BackgroundRepeat.NO_REPEAT, 
                                                BackgroundPosition.DEFAULT,
                                                BackgroundSize.DEFAULT));
        
        GreenAlien = new ImagePattern(new Image(fileURL("./assets/images/GreenAlien.gif")));
        RedAlien = new ImagePattern(new Image(fileURL("./assets/images/RedAlien.gif")));
        YellowAlien = new ImagePattern(new Image(fileURL("./assets/images/YellowAlien.gif")));
        SpaceShip = new ImagePattern(new Image(fileURL("./assets/images/spaceship.png")));
        AlienBullet = new ImagePattern(new Image(fileURL("./assets/images/AlienBullet.png")));
        SpaceShipBullet = new ImagePattern(new Image(fileURL("./assets/images/SpaceShipBullet.png")));

        // Preload all music tracks
        backgroundMusic = new Media(fileURL("./assets/music/backgroundMusic.mp3"));

        // Preload all sound effects
        AlienShot = new AudioClip(fileURL("./assets/soundfx/alienShot.wav"));
        shootingSound =  new AudioClip(fileURL("./assets/soundfx/shooting.wav"));
        gameOver = new AudioClip(fileURL("./assets/soundfx/Gameover.wav"));
        winner = new AudioClip(fileURL("./assets/soundfx/Winner.wav"));
    }
    
    static public Background getBackgroundImage()
    {
        return backgroundImage;        
    }
    
    static public Background getGameOverBackgroundImage()
    {
        return gameOverBackgroundImage;        
    }

    static public ImagePattern getGreenAlienImage()
    {
        return GreenAlien;
    }
    
    static public ImagePattern getRedAlienImage()
    {
        return RedAlien;
    }
    
    static public ImagePattern getYellowAlienImage()
    {
        return YellowAlien;
    }
    
    static public ImagePattern getSpaceShipImage()
    {
        return SpaceShip;
    }
    
    static public ImagePattern getAlienBulletImage()
    {
        return AlienBullet;
    }
    
    static public ImagePattern getSpaceShipBulletImage()
    {
        return SpaceShipBullet;
    }
    
    static public Background getWinnerbackgorundImage()
    {
        return winnerBackgroundImage;
    }
     
    static public Media getBackgroundMusic()
    {
        return backgroundMusic;
    }
    
    static public AudioClip getAlienShotSound()
    {
        return AlienShot;
    }
    
    static public AudioClip getShootingSound()
    {
        return shootingSound;
    }
    
    static public AudioClip getGameoverSound()
    {
        return gameOver;
    }
    
    static public AudioClip getWinnerSound()
    {
        return winner;
    }
}
