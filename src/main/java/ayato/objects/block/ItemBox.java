package ayato.objects.block;

import ayato.main.Main;
import ayato.objects.addtions.InSide;
import ayato.objects.addtions.ObjectAddon;
import ayato.stage.Stage;
import ayato.system.CodeToon;
import ayato.util.animation.ImageMaker;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public class ItemBox extends Block{
    private final String category;
    private final int id;

    public ItemBox(int x, int y, JsonNode node) {
        super(x, y, 1, 1);
        if(node != null) {
            category = node.get("category").asText();
            id = node.get("id").asInt();
        }else{
            category = "blank";
            id= -1;
        }
    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
        addons.add(new InSide(()->(int) x + 2, ()->(int) y + CodeToon.BLOCK_HEIGHT + 5, ()-> CodeToon.BLOCK_WIDTH - 4, ()-> 5, i -> {
            if(i.isJumped) {
                replaceBlock(6);
                switch (category) {
                    case "item":
                        break;
                    case "block":
                        Block b = BlockLoader.getBlock(id, (int) x, (int) y - CodeToon.BLOCK_HEIGHT, null);
                        int myID = getID();
                        stage.endingTask(o -> {
                            stage.blocks.remove(myID - stage.w);
                            stage.blocks.add(myID - stage.w, b);
                        });
                        break;
                    case "blank": break;
                }
            }
        }, i -> {}));
    }

    @Override
    protected ImageMaker setTexture() {
        return new ImageMaker("block/item");
    }
}
