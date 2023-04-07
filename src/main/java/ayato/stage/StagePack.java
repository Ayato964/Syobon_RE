package ayato.stage;


import com.fasterxml.jackson.databind.JsonNode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.jar.JarFile;

public class StagePack {
    public final ArrayList<BufferedImage> customImages = new ArrayList<>();
    public final ArrayList<JsonNode> subStage = new ArrayList<>();

    private BufferedImage icon;
    private JsonNode mainStage;
    public void view(Graphics g, int x, int y){

    }
    public void addMainData(JsonNode o){
        mainStage = o;
    }

    public void addIcon(BufferedImage icon) {
        this.icon = icon;
    }
}
