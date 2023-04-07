package ayato.stage;


import ayato.main.Main;
import ayato.system.CodeToon;
import ayato.util.animation.Animation;
import ayato.util.animation.AnimationImage;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class StagePack {
    public boolean isVisible = true;
    public final ArrayList<BufferedImage> customImages = new ArrayList<>();
    public final ArrayList<JsonNode> subStage = new ArrayList<>();

    private BufferedImage icon;
    private JsonNode mainStage;
    public void view(Graphics g, int x, int y){
        Animation.create(g).draw(mainStage.get("meta").get("name").asText(), x + 60, y, new Animation.Properties().font("", 0, 64)
                .background(CodeToon.categoryBg, x, y, 120, 80)
                .color(CodeToon.textColor)
                .remove(()->!isVisible)
        );
        AnimationImage.createImage(g).draw(icon, x, y + 10, 50, 50, new AnimationImage.PropertiesImage().of(500, 500).remove(() ->!isVisible));
        Animation.create(g).draw(mainStage.get("meta").get("overview").asText(), x + 60, y + 20, new Animation.Properties()
                .font("", Font.PLAIN, 32).remove(()->!isVisible));
    }
    public void addMainData(JsonNode o){
        mainStage = o;
    }

    public void addIcon(BufferedImage icon) {
        this.icon = icon;
    }

    public JsonNode getMainStage() {
        return mainStage;
    }

    public void load() {
        Main.getInstance().run(new Stage(this, mainStage));
    }
}
