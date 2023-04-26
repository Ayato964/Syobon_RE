package ayato.objects.entity;

import ayato.objects.addtions.HPController;
import ayato.objects.addtions.ObjectAddon;
import ayato.system.LamdaPattern;
import ayato.util.animation.ImageMaker;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public class ICBM extends Entity{
    public ICBM(JsonNode info) {
        super(info, 6, 2);
    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
        collider(addons, LamdaPattern.addDamageToPlayer(),LamdaPattern.addDamageToPlayer(),LamdaPattern.addDamageToPlayer(),LamdaPattern.addDamageToPlayer());
    }

    @Override
    protected ImageMaker setTexture() {
        return new ImageMaker("mob/jet", 192, 64);
      //  return null;
    }
}
