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

public abstract class Block extends MyObjects {
    private Stage stage;
    protected boolean isVisible = true;
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

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
    }
}
