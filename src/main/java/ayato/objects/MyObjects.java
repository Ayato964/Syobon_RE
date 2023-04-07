package ayato.objects;

import ayato.main.Main;
import ayato.util.Display;

public abstract class MyObjects implements Display {
    protected int x;
    protected int y;
    protected int w = 60;
    protected int h = 60;
    public MyObjects(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

    }
    protected void move(int moveX){
        int tempX = x + moveX;
        if(tempX > 0){
            if(tempX + w < Main.DESCTOP_BOUNDS.height){
                x = tempX;
            }
        }
    }
    protected void jump(){

    }
}
