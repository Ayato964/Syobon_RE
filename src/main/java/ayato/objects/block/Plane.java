package ayato.objects.block;

import ayato.objects.addtions.ObjectAddon;
import ayato.util.animation.ImageMaker;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Plane extends Block{

    public Plane(int x, int y) {
        super(x, y, 1, 1);
        texture = (BufferedImage) new ImageMaker("block/plane", 64, 64).get();
    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
        super.setAddons(addons);
    }



}
