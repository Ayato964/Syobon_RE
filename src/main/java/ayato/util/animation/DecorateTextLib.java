package ayato.util.animation;

import java.awt.*;

public abstract class DecorateTextLib {

    protected int getTextWidth(String mes, Graphics g) {
        int len = 0;
        for(int i = 0; i < mes.length(); i ++){
            len += g.getFontMetrics().charWidth(mes.charAt(i));
        }
        return len;
    }
}
