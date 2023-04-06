package ayato.util.animation.image;

import ayato.util.animation.Animation;
import ayato.util.animation.AnimationImage;
import ayato.util.animation.Decorate;

import java.awt.*;

public abstract class AbstractImageDecorate implements Decorate {
    @Override
    public void displayAction(Animation.Properties p, Graphics g) {
        displayAction((AnimationImage.PropertiesImage) p, g);
    }
    abstract void displayAction(AnimationImage.PropertiesImage p, Graphics g);
}
