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
import ayato.system.LamdaPattern;
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
        collider(addons, LamdaPattern.takeDamageByPlayer(this), LamdaPattern.addDamageToPlayer(), LamdaPattern.addDamageToPlayer(), LamdaPattern.addDamageToPlayer());
        addons.add(new HPController(null, null, i ->s.endingTask(i1 -> s.entities.remove(this))));
    }

    @Override
    protected ImageMaker setTexture() {
        return new ImageMaker("mob/diener");
    }
}
