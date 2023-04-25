package ayato.objects.block;

import ayato.main.Main;
import ayato.map.Clear;
import ayato.map.Title;
import ayato.objects.ObjectAction;
import ayato.objects.addtions.InSide;
import ayato.objects.addtions.ObjectAddon;
import ayato.objects.entity.Player;
import ayato.system.CodeToon;
import ayato.util.animation.ImageMaker;

import java.util.ArrayList;

public class Home extends Block{
    public Home(int x, int y) {
        super(x, y, 5, 5);
        isCollider = false;
    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
        ObjectAction t = o -> {
          if (o instanceof Player){
              try {
                  Thread.sleep(2000);
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
              Main.getInstance().run(new Clear(stage.reaming, stage.pack, stage.stage));
          }
        };
        addons.add(new InSide(() ->(int) x,()-> (int)y + h * CodeToon.BLOCK_HEIGHT ,()->w* CodeToon.BLOCK_WIDTH,()->2, t, i->{}));
        addons.add(new InSide(() ->(int) x + w * CodeToon.BLOCK_WIDTH + 1,()-> (int)y,()->5,()->h * CodeToon.BLOCK_HEIGHT - 10, t, i->{}));
        addons.add(new InSide(() ->(int) x - 2,()-> (int)y,()->2,()->h * CodeToon.BLOCK_HEIGHT - 10, t, i->{}));
        addons.add(new InSide(() ->(int) x,()-> (int)y - 2,()->w * CodeToon.BLOCK_WIDTH,()-> 2, t, i->{}));

    }

    @Override
    protected ImageMaker setTexture() {
        return new ImageMaker("block/home", 320, 320);
    }
}
