package ayato.system;

import ayato.objects.ObjectAction;
import ayato.objects.entity.Entity;
import ayato.objects.entity.Player;
import ayato.stage.Stage;
import ayato.util.Action;

public class LamdaPattern {
    private LamdaPattern() {
    }

    public static ObjectAction addDamageToPlayer() {
        return o1 -> {
            if (o1 instanceof Player) {
                Entity e = (Entity) o1;
                NextTask.add(o2 -> e.hp--);
            }
        };
    }
    public static Action removeToEntityOf(Entity entity, Stage s){
        return i->s.endingTask(i1 -> s.entities.remove(entity));
    }
    public static ObjectAction takeDamageByPlayer(Entity e) {
        return o1 -> {
            if (o1 instanceof Player) {
                e.hp--;
            }
        };
    }
}
