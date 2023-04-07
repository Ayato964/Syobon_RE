package ayato.util.animation;

import ayato.main.Main;
import ayato.util.animation.image.ImageAnimation;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Properties;

public class AnimationImage extends Animation{
    public ImageMaker maker;
    private  int w, h;
    protected AnimationImage(Graphics g) {
        super(g);
    }

    @Override
    public Animation draw() {
        g.drawImage(maker.get(), getX() * Main.DW, getY() * Main.DH, w * Main.DW, h * Main.DH, null);
        return this;
    }
    public Animation draw(String imageName, int x, int y, int w, int h){
        return draw(imageName, x, y, w, h, new PropertiesImage());
    }
    public Animation draw(String imageName, int x, int y, int w, int h, Properties p){
        super.setX(x);
        super.setY(y);
        this.w = w;
        this.h = h;
        maker = new ImageMaker(imageName);

        myProp = p;
        p.set(this, g);
        return draw();

    }
    public Animation draw(Image image, int x, int y, int w, int h, Properties p){
        super.setX(x);
        super.setY(y);
        this.w = w;
        this.h = h;
        maker = new ImageMaker(image);

        myProp = p;
        p.set(this, g);
        return draw();
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public static class PropertiesImage extends Animation.Properties{
        public int w = 120, h = 120;
        AnimationImage animation;
        boolean isFirst = true;
        @Override
        protected void set(Animation a, @NotNull Graphics g) {
            if(isFirst) {
                animation = (AnimationImage) a;
                animation.maker.subImage = animation.maker.percentImage.getSubimage(0, 0, w, h);
                isFirst = false;
            }
            super.set(a, g);

        }

        public PropertiesImage of(int w, int h){
            this.w = w;
            this.h = h;
            return this;
        }
        public PropertiesImage animation(){
            prop.add(new ImageAnimation());
            return this;
        }

        @Override
        public AnimationImage getAnimation() {
            return animation;
        }
    }
}
