package ayato.objects.block;

import ayato.system.CodeToon;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public class BlockLoader {
    private BlockLoader(){}
    public static ArrayList<Block> get(JsonNode node){
        ArrayList<Block> blocks = new ArrayList<>();
        final int w = node.get("stage").get("width").asInt();
        JsonNode stage = node.get("stage").get("map");
        int x = 0;
        int y = 0;
        int c = 0;
        for(int i = 0; i < stage.size(); i ++){
            blocks.add(getBlock(stage.get(i).asInt(), x * CodeToon.BLOCK_WIDTH, y * CodeToon.BLOCK_HEIGHT));
            x += 1;
            if(x >= w){
                x = 0;
                y ++;
            }
        }

        return blocks;
    }
    private static Block getBlock(int id, int x, int y){
        return switch (id){
            default -> new Air(x, y);
            case 1 -> new Plane(x, y);
        };
    }

}
