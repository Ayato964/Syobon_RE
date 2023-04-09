package ayato.objects.entity;

import ayato.main.Main;
import ayato.objects.addtions.Gravity;
import ayato.objects.addtions.InSide;
import ayato.objects.addtions.Jump;
import ayato.objects.addtions.ObjectAddon;
import ayato.system.CodeToon;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.function.IntSupplier;

public class Player extends Entity implements KeyListener {
    public boolean isMove = true;
    public Player(JsonNode info) {
        super(info, 1, 2);
        speed = 10;
        Main.getInstance().addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(isMove){
            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                move(-1 * speed);
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                move(speed);
            if (e.getKeyCode() == KeyEvent.VK_UP && !isDownVoid)
                jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

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
    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
        addons.add(new Jump());
        addons.add(new Gravity());
        addons.add(new InSide(() ->(int) x,()-> (int)y + h * CodeToon.BLOCK_HEIGHT ,()->w* CodeToon.BLOCK_WIDTH,()->5, i -> {
            isDownVoid = false;
            setY(y-1);
        }, i->isDownVoid = true));
        addons.add(new InSide(() ->(int) x + w * CodeToon.BLOCK_WIDTH + 7,()-> (int)y,()->5,()->h * CodeToon.BLOCK_HEIGHT, i -> {
            isRightVoid = false;
        }, i->isRightVoid = true));
        addons.add(new InSide(() ->(int) x - 8,()-> (int)y,()->5,()->h * CodeToon.BLOCK_HEIGHT, i -> {
            isLeftVoid = false;
        }, i->isLeftVoid = true));
        addons.add(new InSide(() ->(int) x,()-> (int)y - 5,()->w * CodeToon.BLOCK_WIDTH,()-> 5, i -> {
            isUPVoid = false;
        }, i->isUPVoid = true));
    }
}
