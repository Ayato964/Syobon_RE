package ayato.objects.block;

import ayato.main.Main;
import ayato.objects.MyObjects;
import ayato.objects.addtions.ObjectAddon;
import ayato.stage.Stage;
import ayato.system.CodeToon;
import ayato.system.KeyController;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Block extends MyObjects {

    protected Stage stage;
    protected boolean isVisible = true;
    public boolean isCollider = true;
    public Block(int x, int y, int w, int h) {
        super(x, y, w, h);

        stage = (Stage) Main.getInstance().displayMap;
    }

    @Override
    public void display(Graphics g) {
        super.display(g);

        if(stage.worldMoveMode){
            if(KeyController.get(KeyEvent.VK_RIGHT))
                move(-1 * stage.player.speed);
        }
        if(x < -CodeToon.BLOCK_WIDTH)
            isVisible = false;
        if(isVisible)
            g.drawImage(texture, (int) x + stage.stageX,(int) y + stage.stageY, w * CodeToon.BLOCK_WIDTH, h * CodeToon.BLOCK_HEIGHT, null);

    }
    protected void replaceBlock(int id){
        Block b = BlockLoader.getBlock(id,(int) x,(int) y, null);
        Stage s = (Stage) Main.getInstance().displayMap;
        s.endingTask(i -> {
            for(int c = 0; i < s.blocks.size(); c ++){
                if(s.blocks.get(c).equals(this)){
                    s.blocks.remove(c);
                    s.blocks.add(c, b);
                    return;
                }
            }
        });

    }
    protected int getID(){
        for(int i = 0; i < stage.blocks.size(); i ++){
            if(stage.blocks.get(i).equals(this)){
                return i;
            }
        }
        return -1;
    }
}
