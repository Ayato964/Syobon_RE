package ayato.map;

import ayato.main.Main;
import ayato.system.CodeToon;
import ayato.util.animation.Animation;
import ayato.util.lang.LangLoader;

import java.awt.*;

public class Title extends Map{
    @Override
    public void setup(Graphics g) {
        Animation.create(g).draw("main.title", 0, 20, new Animation.Properties()
                .font("", Font.BOLD, 64)
                .color(Color.GRAY)
                .center());
        Animation.create(g).draw( "main.chooser.1", 0, 50, new Animation.Properties()
                .font("", 0, 40)
                .center()
                .backgroundCenter(CodeToon.categoryBg, 100, 10)
                .frameCenter(CodeToon.frameColor, 100, 10)
                .color(CodeToon.textColor)
                .button(e -> Main.getInstance().run(new StageChoose()))
        );
        Animation.create(g).draw( "main.chooser.2", 0, 60, new Animation.Properties()
                .font("", 0, 40)
                .center()
                .backgroundCenter(CodeToon.categoryBg, 100, 10)
                .frameCenter(CodeToon.frameColor, 100, 10)
                .color(CodeToon.textColor)
                .button(e -> Main.getInstance().run(new StageChoose()))
        );
        Animation.create(g).draw( "main.chooser.3", 0, 70, new Animation.Properties()
                .font("", 0, 40)
                .center()
                .backgroundCenter(CodeToon.categoryBg, 100, 10)
                .frameCenter(CodeToon.frameColor, 100, 10)
                .color(CodeToon.textColor)
                .button(e -> System.exit(-1))
        );
    }

    @Override
    public void display(Graphics g) {

    }
}
