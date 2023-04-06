package ayato.system;

import ayato.main.Main;
import ayato.util.Display;
import ayato.util.animation.ImageMaker;

import java.awt.*;

public class Background implements Display {
    public BackgroundMode mode;
    private static final Background instance = new Background();
    private ImageMaker bgImage;

    private Background(){

        //bgImage = new ImageMaker("title/background", 256, 144);
        mode = BackgroundMode.SKY;
    }

    public static Background getInstance() {
        return instance;
    }

    @Override
    public void display(Graphics g) {
        Rectangle r = Main.DESCTOP_BOUNDS;
        switch (mode) {
            case COLOR_ANIMATION : g.drawImage(bgImage.get(), 0, 0, r.width, r.height, null);break;
            case DARK:g.setColor(Color.BLACK); g.fillRect(0, 0, r.width, r.height);break;
            case GRAY :g.setColor(Color.GRAY); g.fillRect(0, 0, r.width, r.height);break;
            case SKY:g.setColor(Color.CYAN);g.fillRect(0, 0, r.width, r.height);break;
        }
    }
    public enum BackgroundMode{
        COLOR_ANIMATION("detail.video.bg.def"),
        DARK("detail.video.bg.dark"),
        SKY("detail.video.bg.sky"),
        GRAY("detail.video.bg.gray");

        private String a;
        BackgroundMode(String i) {
            a = i;
        }

        public String get() {
            return a;
        }
    }
}
