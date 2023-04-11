package ayato.objects.block;

import ayato.objects.ObjectAction;
import ayato.objects.addtions.InSide;
import ayato.objects.addtions.ObjectAddon;
import ayato.objects.entity.Entity;
import ayato.system.CodeToon;
import ayato.util.Action;
import ayato.util.animation.ImageMaker;

import java.util.ArrayList;

public class Thorn extends Block{
    public Thorn(int x, int y) {
        super(x, y, 1, 1);
    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
        ObjectAction t = o -> {
            if(o instanceof Entity){
                Entity e = (Entity) o;
                e.hp --;
            }
        };
        addons.add(new InSide(() ->(int) x,()-> (int)y + h * CodeToon.BLOCK_HEIGHT ,()->w* CodeToon.BLOCK_WIDTH,()->2, t, i->{}));
        addons.add(new InSide(() ->(int) x + w * CodeToon.BLOCK_WIDTH + 1,()-> (int)y,()->5,()->h * CodeToon.BLOCK_HEIGHT - 10, t, i->{}));
        addons.add(new InSide(() ->(int) x - 2,()-> (int)y,()->2,()->h * CodeToon.BLOCK_HEIGHT - 10, t, i->{}));
        addons.add(new InSide(() ->(int) x,()-> (int)y - 2,()->w * CodeToon.BLOCK_WIDTH,()-> 2, t, i->{}));
    }

    @Override
    protected ImageMaker setTexture() {
        return new ImageMaker("block/thorn");
    }
}
