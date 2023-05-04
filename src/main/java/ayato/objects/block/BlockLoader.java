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
            blocks.add(getBlock(i, stage.get(i).asInt(), x * CodeToon.BLOCK_WIDTH, y * CodeToon.BLOCK_HEIGHT,node));
            x += 1;
            if(x >= w){
                x = 0;
                y ++;
            }
        }

        return blocks;
    }
    public static Block getBlock(int id, int x, int y, JsonNode node){
        return getBlock(-1, id, x, y, node);
    }
    public static Block getBlock(int number, int id, int x, int y, JsonNode node){
        int width = node.get("stage").get("width").asInt();
        int bx = number % width;
        int by = number / width;
        return switch (id){
            default -> new Air(x, y);
            case 1 -> new Plane(x, y);
            case 2-> new Renga(x, y);
            case 3->new Thorn(x, y);
            case 4->new NotVisible(x, y);
            case 5->new ItemBox(x, y, node.get("events").get("i-" + number));
            case 6->new ItemVoid(x, y);
            case 7->new ThornBall(x, y);
            case 8->new DieFlower(x, y);
            case 10->new Pipe_Top(x, y);
            case 11->new Pipe_Tube(x, y);
            case 20->new Event(x, y, node.get("events").get(bx + "x" + by), node, number);
            case 99->new Home(x, y);

        };
    }

}
