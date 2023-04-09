package ayato.stage;

import ayato.main.Main;
import ayato.map.Map;
import ayato.objects.block.Block;
import ayato.objects.block.BlockLoader;
import ayato.objects.entity.Player;
import ayato.system.Background;
import ayato.system.CodeToon;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;
import java.util.ArrayList;

public class Stage extends Map {
    private final StagePack pack;
    private final JsonNode stage;
    public Player player;
    public ArrayList<Block> blocks;
    public Stage(StagePack pack, JsonNode main){
        this.pack = pack;
        stage = main;
    }

    @Override
    public void setup(Graphics g) {
        Background.getInstance().mode = getMode();
        CodeToon.BLOCK_HEIGHT = Main.DESCTOP_BOUNDS.height / 15;
        CodeToon.BLOCK_WIDTH = CodeToon.BLOCK_HEIGHT;
        player = new Player(stage.get("stage").get("player"));
        blocks = BlockLoader.get(stage);

    }

    @Override
    public void display(Graphics g) {
        for(Block b : blocks){
            b.display(g);
        }
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
