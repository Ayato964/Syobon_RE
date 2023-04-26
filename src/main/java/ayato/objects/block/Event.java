package ayato.objects.block;

import ayato.objects.addtions.InSide;
import ayato.objects.addtions.ObjectAddon;
import ayato.objects.entity.Entity;
import ayato.objects.entity.EntityLoader;
import ayato.system.CodeToon;
import ayato.util.animation.ImageMaker;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

public class Event extends Block{
    protected boolean isValid = true;
    protected boolean trigger = false;
    private final ArrayList<Entity> entities;
    private final ArrayList<Integer> subjectIDs;
    private final ArrayList<BooleanSupplier> conditions;
    public Event(int x, int y, JsonNode eventJson, JsonNode data, int blockID) {
        super(x, y, 1, 1);
        isCollider = false;
        entities = new ArrayList<>();
        subjectIDs = new ArrayList<>();
        conditions = new ArrayList<>();
        triggerSetting(eventJson, data);
        summonSetting(eventJson, data);
        conditionSetting(eventJson, data);


    }

    private void conditionSetting(JsonNode eventJson, JsonNode data) {
        JsonNode condition = eventJson.get("condition");
        if(condition != null){
            if(!condition.isArray()){
                conditions.add(condition(condition.asInt()));
            }
        }
    }
    private BooleanSupplier condition(int id){
        return switch (id){
            case 0->TriggerCondition.getType0(this);
            default -> null;
        };
    }

    private void triggerSetting(JsonNode eventJson, JsonNode data) {
        JsonNode trigger = eventJson.get("trigger");
        if(trigger != null){
            if(!trigger.isArray()){
                subjectIDs.add(trigger.asInt());
            }
        }
    }

    private void summonSetting(JsonNode eventJson, JsonNode data){
        JsonNode summon = eventJson.get("summon");
        if(summon != null)
            if(!summon.isArray()){
                Entity e = EntityLoader.summon(data.get("entities").get("e-" + summon.asInt()));
                if(e != null)
                    entities.add(e);
            }else{
                for(int i = 0; i < summon.size(); i ++){
                    Entity e = EntityLoader.summon(data.get("entities").get("e-" + summon.get(i).asInt()));
                    if(e != null)
                        entities.add(e);
                }
            }
    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
        addons.add(new InSide(()->(int) x + CodeToon.BLOCK_WIDTH / 2 - 1, ()->(int) y + CodeToon.BLOCK_HEIGHT / 2, ()-> CodeToon.BLOCK_WIDTH / 5, ()->CodeToon.BLOCK_HEIGHT / 5, o -> {
            if(isValid) {
                if (getJudgeConditions()) {
                    isValid = false;

                    if (!entities.isEmpty())
                        stage.endingTask(i -> {
                            for (Entity e : entities) {
                                e.setX(e.x - stage.stageX);
                                stage.entities.add(e);
                            }
                        });
                    if(!subjectIDs.isEmpty()){
                        stage.endingTask(i -> {
                            for(int in : subjectIDs){
                                Block b = stage.blocks.get(in);
                                if(b instanceof Event)
                                    ((Event) b).trigger = true;
                            }
                        });
                    }

                }
            }
        }, null));
    }

    private boolean getJudgeConditions() {
        for(BooleanSupplier b : conditions){
            if(!b.getAsBoolean()){
                return false;
            }
        }
        return true;
    }

    @Override
    protected ImageMaker setTexture() {
        return new ImageMaker("block/blank");
    }

    protected static class TriggerCondition{
        public static BooleanSupplier getType0(Event e){
            return ()-> e.trigger;
        }
    }
}
