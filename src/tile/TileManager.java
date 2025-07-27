package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {

    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage(){

        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass _with_flowers 1.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand_with_small_wave.png"));

//            tile[3] = new Tile();
//            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void draw(Graphics2D g2){
        g2.drawImage(tile[0].image,0 ,0, gp.tileSize, gp.tileSize,  null);
        g2.drawImage(tile[1].image,80 ,0, gp.tileSize, gp.tileSize,  null);
        g2.drawImage(tile[2].image,160 ,0, gp.tileSize, gp.tileSize,  null);
        g2.drawImage(tile[2].image,240 ,0, gp.tileSize, gp.tileSize,  null);
        g2.drawImage(tile[2].image,320 ,0, gp.tileSize, gp.tileSize,  null);
        g2.drawImage(tile[2].image,400   ,0, gp.tileSize, gp.tileSize,  null);

    }
}
