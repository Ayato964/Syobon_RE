package ayato.objects.addtions;

import ayato.objects.MyObjects;
import ayato.system.KeyController;

import java.awt.event.KeyEvent;

public class Jump implements ObjectAddon{
    private float v0 = 25;
    private float gravity = 15.8f;
    private int time = 10;
    @Override
    public void application(MyObjects object) {

        if(object.isJumped && object.isUPVoid && KeyController.get(KeyEvent.VK_UP)){
                time++;
                float v1 = (float) (v0 * (time / 7) - 0.5 * gravity * Math.pow(time / 7, 2));
                //System.out.println(v1);
                object.setY(object.y - v1);
                if (v1 < 2) {
                    time = 10;
                    object.isJumped = false;
                }
        }else {
            time = 10;
            object.isJumped = false;
        }
    }
}
