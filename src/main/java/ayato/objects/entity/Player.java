package ayato.objects.entity;

import ayato.main.Main;
import ayato.map.Title;
import ayato.objects.addtions.*;
import ayato.system.CodeToon;
import ayato.system.KeyController;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.function.IntSupplier;

public class Player extends Entity {
    public boolean isMove = true;
    public Player(JsonNode info) {
        super(info, 1, 2);
        speed = 10;
        KeyController.generate();
    }

    @Override
    public void display(Graphics g) {
        super.display(g);
        /*
        g.fillRect((int) x, (int)y + h * CodeToon.BLOCK_HEIGHT ,w* CodeToon.BLOCK_WIDTH,5);
        g.fillRect((int) x + w * CodeToon.BLOCK_WIDTH + 7, (int)y,5,h * CodeToon.BLOCK_HEIGHT);
        g.fillRect((int) x - 8, (int)y,5,h * CodeToon.BLOCK_HEIGHT);
        g.fillRect((int) x, (int)y - 5,w * CodeToon.BLOCK_WIDTH, 5);

         */
        if(isMove){
            if(KeyController.get(KeyEvent.VK_UP) && !isDownVoid)
                jump();
            if(KeyController.get(KeyEvent.VK_RIGHT))
                move(speed);
            if(KeyController.get(KeyEvent.VK_LEFT))
                move(-1 * speed);
        }
    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
        addons.add(new Jump());
        addons.add(new Gravity());
        addons.add(new InSide(() ->(int) x,()-> (int)y + h * CodeToon.BLOCK_HEIGHT ,()->w* CodeToon.BLOCK_WIDTH,()->5, i -> {
            isDownVoid = false;
            setY(y-1);
        }, i->isDownVoid = true));
        addons.add(new InSide(() ->(int) x + w * CodeToon.BLOCK_WIDTH + 7,()-> (int)y,()->5,()->h * CodeToon.BLOCK_HEIGHT - 10, i -> {
            isRightVoid = false;
        }, i->isRightVoid = true));
        addons.add(new InSide(() ->(int) x - 8,()-> (int)y,()->5,()->h * CodeToon.BLOCK_HEIGHT - 10, i -> {
            isLeftVoid = false;
        }, i->isLeftVoid = true));
        addons.add(new InSide(() ->(int) x,()-> (int)y - 5,()->w * CodeToon.BLOCK_WIDTH,()-> 5, i -> {
            isUPVoid = false;
        }, i->isUPVoid = true));
        addons.add(new HPController(null, null, i->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Main.getInstance().run(new Title());

        }));
    }
}
