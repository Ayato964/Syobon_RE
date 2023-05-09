package ayato.objects.block;

import ayato.main.Main;
import ayato.objects.addtions.InSide;
import ayato.objects.addtions.ObjectAddon;
import ayato.objects.entity.Player;
import ayato.stage.Stage;
import ayato.stage.StagePack;
import ayato.system.CodeToon;
import ayato.system.KeyController;
import ayato.util.animation.ImageMaker;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Pipe_Top_Left extends Pipe_Top{
    public Pipe_Top_Left(int x, int y, JsonNode loadMapInfo, StagePack pack) {
        super(x, y, loadMapInfo, pack);
    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
        addons.add(new InSide(() ->(int) x - 12,()-> (int) y,()-> 12,()-> h * CodeToon.BLOCK_HEIGHT, object -> {
            if(object instanceof Player)
                if(KeyController.get(KeyEvent.VK_RIGHT))
                    Main.getInstance().run(new Stage(pack, subMap, stage));
        }, null));
    }

    @Override
    protected ImageMaker setTexture() {
        return new ImageMaker("block/pipe_top_left");
    }
}
