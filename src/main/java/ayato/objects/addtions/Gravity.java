package ayato.objects.addtions;

import ayato.objects.MyObjects;

public class Gravity implements ObjectAddon{
    private float g = 9.8f;
    private int time = 0;
    public Gravity(){}
    public Gravity(float grav){
        g = grav;
    }
    @Override
    public void application(MyObjects object) {
        if(object.isDownVoid && !object.isJumped) {
            time++;
            float v = g * time / 10;
            object.setY(object.y + v);
        }else {
            time = 0;
        }
    }
}
