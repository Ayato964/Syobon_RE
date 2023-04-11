package ayato.util.animation;

import ayato.main.Main;
import ayato.system.CodeToon;

import java.awt.*;

public class Width extends DecorateTextLib implements Decorate{
    private final  int width;
    private boolean isFirst;
    public Width(int i ){
        width = i;
        isFirst = true;
    }
    @Override
    public void displayAction(Animation.Properties p, Graphics g) {
        p.removeAddon(this);
        AnimationText animationText = (AnimationText) p.getAnimation();
        StringBuilder text = new StringBuilder().append(animationText.getMsg());
        StringBuilder builder = new StringBuilder();
        if(getTextWidth(text.toString(), g) > width * Main.DW) {
            for (int i = text.length() - 1; i >= 0; i--) {
                builder.insert(0, text.charAt(i));
                text.deleteCharAt(i);
                if (getTextWidth(text.toString(), g) <= width * Main.DW) {
                    AnimationText.create(g).draw(builder.toString(), animationText.getX(), animationText.getY() + g.getFontMetrics().getHeight() /Main.DH, new Animation.Properties().addAddons(p.prop).width(width));
                    animationText.setMsg(text.toString());
                    return;
                }
            }
        }
    }
}
