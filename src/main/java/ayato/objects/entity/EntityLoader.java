package ayato.objects.entity;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public class EntityLoader {
    private EntityLoader(){}
    public static ArrayList<Entity> get(JsonNode node){
        ArrayList<Entity> e= new ArrayList<>();
        int i = 0;
        if(node.get("entities") != null)
            while (node.get("entities").get(String.valueOf(i)) != null){
                JsonNode entityJSON = node.get("entities").get(String.valueOf(i));
                Entity entity = summon(entityJSON);
                if(entity != null)
                    e.add(entity);
                i++;
            }
        return e;
    }
    public static Entity summon(JsonNode entity){
        return switch (entity.get("id") != null ? entity.get("id").asInt() : -1){
            case 0->new Diener(entity);
            case 1->new ICBM(entity);
            default -> null;
        };
    }
}
