package ayato.objects.entity;

import ayato.objects.MyObjects;
import ayato.system.CodeToon;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;

public abstract class Entity extends MyObjects {
    protected int hp;
    protected int speed;

    public Entity(JsonNode info, int w, int h) {
        super(info.get("x").asInt() * CodeToon.BLOCK_WIDTH, info.get("y").asInt() * CodeToon.BLOCK_HEIGHT, w, h);
        if(info.get("speed") != null){
            speed = info.get("speed").asInt();
        }
    }

    @Override
    public void display(Graphics g) {
        super.display(g);

        g.setColor(Color.WHITE);
        g.fillRect((int) x,(int) y, w * CodeToon.BLOCK_WIDTH, h * CodeToon.BLOCK_HEIGHT);

    }

    protected int getSpeed(){
        return speed;
    }

}
