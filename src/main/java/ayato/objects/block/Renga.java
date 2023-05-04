package ayato.objects.block;

import ayato.objects.addtions.InSide;
import ayato.objects.addtions.ObjectAddon;
import ayato.system.CodeToon;
import ayato.util.animation.ImageMaker;

import java.util.ArrayList;

public class Renga extends Block{

    public Renga(int x, int y) {
        super(x, y, 1, 1);
    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
        addons.add(new InSide(()->(int) x + 2, ()->(int) y + CodeToon.BLOCK_HEIGHT + 5, ()-> CodeToon.BLOCK_WIDTH - 4, ()-> 5, i -> {
            if(!(i instanceof  Block))
                replaceBlock(0);
        }, i -> {}));
    }

    @Override
    protected ImageMaker setTexture() {
        return new ImageMaker("block/renga");
    }
}
