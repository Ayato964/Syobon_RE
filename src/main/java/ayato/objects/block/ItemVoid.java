package ayato.objects.block;

import ayato.objects.addtions.ObjectAddon;
import ayato.util.animation.ImageMaker;

import java.util.ArrayList;

public class ItemVoid extends Block{
    public ItemVoid(int x, int y) {
        super(x, y, 1, 1);
    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
    }

    @Override
    protected ImageMaker setTexture() {
        return new ImageMaker("block/item_void");
    }
}
