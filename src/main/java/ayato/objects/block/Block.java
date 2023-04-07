package ayato.objects.block;

import ayato.objects.MyObjects;
import ayato.system.CodeToon;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;

public abstract class Block extends MyObjects {
    public Block(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void display(Graphics g) {
        super.display(g);

        g.setColor(Color.WHITE);
        g.fillRect((int) x,(int) y, w * CodeToon.BLOCK_WIDTH, h * CodeToon.BLOCK_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect((int) x, (int) y, w * CodeToon.BLOCK_WIDTH, h * CodeToon.BLOCK_HEIGHT);
    }
}
