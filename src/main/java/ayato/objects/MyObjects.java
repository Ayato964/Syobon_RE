package ayato.objects;

import ayato.main.Main;
import ayato.objects.addtions.ObjectAddon;
import ayato.system.CodeToon;
import ayato.util.Display;

import java.awt.*;
import java.util.ArrayList;

public abstract class MyObjects implements Display {
    public float x;
    public float y;
    public int w;
    public int h;
    public int centerX;
    public int centerY;
    public boolean isDownVoid = true;
    public boolean isUPVoid = true;
    public boolean isRightVoid = true;
    public boolean isLeftVoid = true;
    public boolean isJumped = false;

    private ArrayList<ObjectAddon> addons;
    public MyObjects(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        centerX = x + w / 2;
        centerY = y + h / 2;
        addons = new ArrayList<>();
        setAddons(addons);
    }
    protected void move(int moveX){
            float tempX = x + moveX;
            if((x > tempX && isLeftVoid) || (x < tempX && isRightVoid))
            if (tempX > 0) {
                if (tempX + w * CodeToon.BLOCK_WIDTH < Main.DESCTOP_BOUNDS.width) {
                    setX(tempX);
                }
            }
    }

    public void setY(float newY){
        y = newY;
        centerY =(int) y + h * CodeToon.BLOCK_HEIGHT/ 2;
    }

    public void setX(float x) {
        this.x = x;
        centerX = (int) x + w * CodeToon.BLOCK_WIDTH/ 2;
    }

    protected void jump(){
        isJumped = true;
    }

    private void runAddon(){
        for(ObjectAddon ad : addons){
            ad.application(this);
        }
    }
    @Override
    public void display(Graphics g) {
        runAddon();
    }
    protected abstract void setAddons(ArrayList<ObjectAddon> addons);
}
