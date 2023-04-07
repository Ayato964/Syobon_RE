package ayato.objects.entity;

import ayato.main.Main;
import ayato.objects.addtions.Gravity;
import ayato.objects.addtions.ObjectAddon;
import ayato.system.CodeToon;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Player extends Entity implements KeyListener {
    public boolean isMove = true;
    public Player(JsonNode info) {
        super(info, 1, 2);
        speed = 10;
        Main.getInstance().addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("ssssss");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(isMove){
            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                move(-1 * speed);
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                move(speed);
            if (e.getKeyCode() == KeyEvent.VK_UP)
                jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
        addons.add(new Gravity());
    }
}
