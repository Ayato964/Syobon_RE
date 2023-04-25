package ayato.objects.entity;

import ayato.main.Main;
import ayato.objects.ObjectAction;
import ayato.objects.addtions.Gravity;
import ayato.objects.addtions.HPController;
import ayato.objects.addtions.InSide;
import ayato.objects.addtions.ObjectAddon;
import ayato.objects.block.Block;
import ayato.stage.Stage;
import ayato.system.CodeToon;
import ayato.system.NextTask;
import ayato.util.animation.ImageMaker;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public class Diener extends Entity{
    public Diener(JsonNode info) {
        super(info, 1, 1);

    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
        addons.add(new Gravity());
        Stage s = (Stage) Main.getInstance().displayMap;
        ObjectAction o = o1 -> {
            if(o1 instanceof Player){
                Entity e = (Entity) o1;
                NextTask.add(o2->e.hp --);
            }
        };
        collider(addons, o1 -> {
            if(o1 instanceof Player){
                hp --;
            }
        }, o, o, o);
        addons.add(new HPController(null, null, i ->s.endingTask(i1 -> s.entities.remove(this))));
    }

    @Override
    protected ImageMaker setTexture() {
        return new ImageMaker("mob/diener");
    }
}
