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
import java.security.Key;
import java.util.ArrayList;

public class Pipe_Top extends Block{
    JsonNode subMap;
    StagePack pack;
    public Pipe_Top(int x, int y, JsonNode loadMapInfo, StagePack pack) {
        super(x, y, 1, 1);
        this.pack = pack;
        int loadMapID = loadMapInfo.get("loadStage").asInt();
        subMap = pack.serchSubMap(loadMapID);

    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
        addons.add(new InSide(() ->(int) x,()-> (int) y - 6,()-> w * CodeToon.BLOCK_WIDTH,()-> 3, object -> {
            if(object instanceof Player)
                if(KeyController.get(KeyEvent.VK_DOWN))
                    Main.getInstance().run(new Stage(pack, subMap, stage));
        }, null));
    }

    @Override
    protected ImageMaker setTexture() {
        return new ImageMaker("block/pipe_top");
    }
}
