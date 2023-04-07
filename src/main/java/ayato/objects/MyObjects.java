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

    private ArrayList<ObjectAddon> addons;
    public MyObjects(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        addons = new ArrayList<>();
        setAddons(addons);
    }
    protected void move(int moveX){
        float tempX = x + moveX;
        if(tempX > 0){
            if(tempX + w * CodeToon.BLOCK_WIDTH < Main.DESCTOP_BOUNDS.width){
                x = tempX;
            }
        }
    }
    protected void jump(){

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
