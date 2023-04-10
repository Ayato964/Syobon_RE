package ayato.util.animation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageMaker {
    public Image subImage;
    public BufferedImage percentImage;
    public ImageMaker(String str){
        URL filePath =  getClass().getClassLoader().getResource("assets/ayato/" + str + ".png");
        try {
            percentImage = ImageIO.read(filePath);
            subImage = percentImage.getSubimage(0, 0, 64, 64);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public ImageMaker(Image m){
        percentImage = (BufferedImage) m;
        subImage = percentImage;
    }
    public ImageMaker(String str, int w, int h){
        URL filePath =  getClass().getClassLoader().getResource("assets/ayato/" + str + ".png");
        try {
            percentImage = ImageIO.read(filePath);
            subImage = percentImage.getSubimage(0, 0, w, h);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Image get(){
        return subImage;
    }
}
