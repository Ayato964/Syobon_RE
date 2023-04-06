package ayato.map;

import ayato.system.CodeToon;
import ayato.util.animation.Animation;

import java.awt.*;

public class Title extends Map{
    @Override
    public void setup(Graphics g) {
        Animation.create(g).draw("test.hehe", 20, 20, new Animation.Properties().font("", Font.PLAIN, 32).color(CodeToon.textColor));
    }

    @Override
    public void display(Graphics g) {

    }
}
