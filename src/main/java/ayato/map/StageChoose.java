package ayato.map;

import ayato.stage.StageLoader;
import ayato.system.Background;
import ayato.util.animation.Animation;

import java.awt.*;

public class StageChoose extends Map{

    @Override
    public void setup(Graphics g) {
        Background.getInstance().mode = Background.BackgroundMode.DARK;
        Animation.create(g).draw("stagechoose.mes1", 20, 10, new Animation.Properties().font("", 0, 64).center()
        );
        StageLoader.update();

    }

    @Override
    public void display(Graphics g) {

    }
}
