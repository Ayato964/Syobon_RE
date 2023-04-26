package ayato.objects.block;

import ayato.objects.addtions.InSide;
import ayato.objects.addtions.ObjectAddon;
import ayato.objects.entity.Entity;
import ayato.objects.entity.EntityLoader;
import ayato.system.CodeToon;
import ayato.util.animation.ImageMaker;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public class Event extends Block{
    private boolean isValid = true;
    private ArrayList<Entity> entities;
    public Event(int x, int y, JsonNode eventJson, JsonNode data, int blockID) {
        super(x, y, 1, 1);
        isCollider = false;
        entities = new ArrayList<>();
        JsonNode summon = eventJson.get("summon");
        if(summon != null)
            if(!summon.isArray()){
                Entity e = EntityLoader.summon(data.get("entities").get("e-" + summon.asInt()));
                if(e != null)
                    entities.add(e);
            }

    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
        addons.add(new InSide(()->(int) x + CodeToon.BLOCK_WIDTH / 2 - 1, ()->(int) y + CodeToon.BLOCK_HEIGHT / 2, ()-> CodeToon.BLOCK_WIDTH / 5, ()->CodeToon.BLOCK_HEIGHT / 5, o -> {
            if(isValid){
                isValid = false;

                if(!entities.isEmpty())
                    stage.endingTask(i -> {
                        for(Entity e : entities){
                            e.setX(e.x - stage.stageX);
                            stage.entities.add(e);
                        }
                    });

            }
        }, null));
    }

    @Override
    protected ImageMaker setTexture() {
        return new ImageMaker("block/blank");
    }
}
