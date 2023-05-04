package ayato.objects.block;

import ayato.objects.addtions.ObjectAddon;
import ayato.util.animation.ImageMaker;

import java.awt.*;
import java.util.ArrayList;

public class Air extends Block{

    public Air(int x, int y) {
        super(x, y, 1, 1);
        isVisible = false;
    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
    }

    @Override
    protected ImageMaker setTexture() {
        return null;
    }
/*
    @Override
    public void display(Graphics g) {

    }

 */
}
