package ayato.objects.entity;

import ayato.objects.MyObjects;
import ayato.system.CodeToon;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;

public abstract class Entity extends MyObjects {
    protected int hp;
    protected int speed;

    public Entity(JsonNode info, int w, int h) {
        super(info.get("x").asInt(), info.get("y").asInt(), w, h);
        if(info.get("speed") != null){
            speed = info.get("speed").asInt();
        }
    }
    protected int getSpeed(){
        return speed;
    }

}
