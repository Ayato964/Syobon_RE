package ayato.map;

import ayato.system.Background;

import java.awt.*;

public class StageChoose extends Map{

    @Override
    public void setup(Graphics g) {
        Background.getInstance().mode = Background.BackgroundMode.DARK;
    }

    @Override
    public void display(Graphics g) {

    }
}
