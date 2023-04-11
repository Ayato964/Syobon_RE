package ayato.objects.block;

import ayato.objects.addtions.InSide;
import ayato.objects.addtions.ObjectAddon;
import ayato.system.CodeToon;
import ayato.util.animation.ImageMaker;

import java.util.ArrayList;

public class NotVisible extends Block{

    public NotVisible(int x, int y) {
        super(x, y, 1, 1);
        isCollider = false;
    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
        addons.add(new InSide(()->(int) x + 2, ()->(int) y + CodeToon.BLOCK_HEIGHT + 5, ()-> CodeToon.BLOCK_WIDTH - 4, ()-> 5, i -> {
            replaceBlock(6);
        }, i -> {}));
    }

    @Override
    protected ImageMaker setTexture() {
        return new ImageMaker("block/blank");
    }
}
