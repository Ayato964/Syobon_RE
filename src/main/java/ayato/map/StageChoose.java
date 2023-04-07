package ayato.map;

import ayato.main.Main;
import ayato.stage.Stage;
import ayato.stage.StageLoader;
import ayato.system.Background;
import ayato.system.CodeToon;
import ayato.util.animation.Animation;

import java.awt.*;

public class StageChoose extends Map{
    private int mapID = 0;
    @Override
    public void setup(Graphics g) {
        Background.getInstance().mode = Background.BackgroundMode.DARK;
        Animation.create(g).draw("stagechoose.mes1", 20, 10, new Animation.Properties().font("", 0, 64).center()
        );
        StageLoader.update();
        StageLoader.STAGES.get(mapID).view(g, 40, 30);

        Animation.create(g).draw(null, "<", 20, 65, new Animation.Properties()
                .font("", 0, 120)
                .background(CodeToon.categoryBg, 10, 40, 25, 60)
                .frame(CodeToon.frameColor, 10, 40, 25, 60, ()-> true)
                .button(e -> {
                    if(mapID > 0){
                        StageLoader.STAGES.get(mapID).isVisible = false;
                        mapID --;
                        StageLoader.STAGES.get(mapID).isVisible = true;
                        StageLoader.STAGES.get(mapID).view(g, 40, 30);
                    }
                })
        );

        Animation.create(g).draw(null, ">", 165, 65, new Animation.Properties()
                .font("", 0, 120)
                .background(CodeToon.categoryBg, 165, 40, 25, 60)
                .frame(CodeToon.frameColor, 165, 40, 25, 60, ()-> true)
                .button(e -> {
                    if(mapID < StageLoader.STAGES.size() - 1){
                        StageLoader.STAGES.get(mapID).isVisible = false;
                        mapID ++;
                        StageLoader.STAGES.get(mapID).isVisible = true;
                        StageLoader.STAGES.get(mapID).view(g, 40, 30);
                    }
                })
        );
        Animation.create(g).draw("stagechoose.end", 10, 100, new Animation.Properties()
                .font("", Font.PLAIN, 32)
                .background(CodeToon.categoryBg)
                .frame(CodeToon.frameColor)
                .color(CodeToon.textColor)
                .button(e -> Main.getInstance().run(new Title()))

        );
        Animation.create(g).draw("stagechoose.enter", 180, 100, new Animation.Properties()
                .font("", 0, 32)
                .background(CodeToon.categoryBg, 180, 100, 20 , 10)
                .frame(CodeToon.frameColor, 180, 100, 20, 10, ()->true)
                .color(CodeToon.textColor)
                .button(e -> StageLoader.STAGES.get(mapID).load())
        );
    }

    @Override
    public void display(Graphics g) {

    }
}
