package ayato.objects.block;

import ayato.objects.addtions.ObjectAddon;
import ayato.util.animation.ImageMaker;

import java.util.ArrayList;

public class Pipe_Carb_LeftToUp extends Block{
    public Pipe_Carb_LeftToUp(int x, int y) {
        super(x, y, 1, 1);
    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {

    }

    @Override
    protected ImageMaker setTexture() {
        return new ImageMaker("block/pipe_carb_left_to_up");
    }
}
