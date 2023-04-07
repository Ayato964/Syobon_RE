package ayato.stage;

import ayato.main.Main;
import ayato.map.Map;
import ayato.objects.entity.Player;
import ayato.system.Background;
import ayato.system.CodeToon;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;

public class Stage extends Map {
    private final StagePack pack;
    private final JsonNode stage;
    private Player player;
    public Stage(StagePack pack, JsonNode main){
        this.pack = pack;
        stage = main;
    }

    @Override
    public void setup(Graphics g) {
        Background.getInstance().mode = getMode();
        CodeToon.BLOCK_HEIGHT = Main.DESCTOP_BOUNDS.height / 20;
        CodeToon.BLOCK_WIDTH = CodeToon.BLOCK_HEIGHT;
        player = new Player(stage.get("stage").get("player"));

    }

    @Override
    public void display(Graphics g) {
        player.display(g);
    }
    private Background.BackgroundMode getMode(){
        return switch (stage.get("stage").get("background").asInt()){
            case 0-> Background.BackgroundMode.SKY;
            case 1-> Background.BackgroundMode.DARK;
            default -> Background.BackgroundMode.GRAY;
        };
    }
}
