package ayato.stage;

import ayato.main.Main;
import ayato.map.Map;
import ayato.objects.block.Block;
import ayato.objects.block.BlockLoader;
import ayato.objects.entity.Entity;
import ayato.objects.entity.EntityLoader;
import ayato.objects.entity.Player;
import ayato.system.Background;
import ayato.system.CodeToon;
import ayato.system.KeyController;
import ayato.system.NextTask;
import ayato.util.Action;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Stage extends Map {
    public boolean worldMoveMode = false;
    public int stageX, stageY, w;
    public final StagePack pack;
    public final JsonNode stage;
    public Player player;
    public ArrayList<Block> blocks;
    public ArrayList<Entity> entities;
    private ArrayList<Action> task = new ArrayList<>();
    public int reaming;
    public Stage(StagePack pack, JsonNode main){
        this.pack = pack;
        stage = main;
        reaming = 5;
        stageX = 0;
        stageY = 0;
        w = stage.get("stage").get("width").asInt();
    }
    public Stage(StagePack pack, JsonNode main, int remaining){
        this(pack, main);
        this.reaming = remaining;

    }

    @Override
    public void setup(Graphics g) {
        Background.getInstance().mode = getMode();
        CodeToon.BLOCK_HEIGHT = Main.DESCTOP_BOUNDS.height / 15;
        CodeToon.BLOCK_WIDTH = CodeToon.BLOCK_HEIGHT;
        player = new Player(stage.get("stage").get("player"));
        blocks = BlockLoader.get(stage);
        entities = EntityLoader.get(stage);
        NextTask.generate();

    }

    @Override
    public void display(Graphics g) {
        worldMoveMode = player.x >= Main.DESCTOP_BOUNDS.width / 2;
        player.display(g);
        for(Block b : blocks) b.display(g);
        for(Entity e : entities) e.display(g);

        if(!task.isEmpty()) {
            for (Action a : task) a.action(-1);
            task = new ArrayList<>();
        }
        NextTask.action();
        NextTask.next();
    }
    public void endingTask(Action action){
        task.add(action);
    }
    private Background.BackgroundMode getMode(){
        return switch (stage.get("stage").get("background").asInt()){
            case 0-> Background.BackgroundMode.SKY;
            case 1-> Background.BackgroundMode.DARK;
            default -> Background.BackgroundMode.GRAY;
        };
    }

    public void replaceBlock(Integer x, Integer y, Integer id) {
        int blockNumber = stage.get("stage").get("width").asInt() * y + x;
        Block target = blocks.get(blockNumber);
        Block b = BlockLoader.getBlock(blockNumber, //BlockNumber
                id,(int) target.x, (int) target.y, stage);
        if(b !=null) {
            blocks.remove(blockNumber);
            blocks.add(blockNumber, b);
        }
    }
}
