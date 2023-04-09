package ayato.objects.block;

import ayato.objects.addtions.ObjectAddon;

import java.awt.*;
import java.util.ArrayList;

public class Air extends Block{

    public Air(int x, int y) {
        super(x, y, 1, 1);
    }

    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
    }

    @Override
    public void display(Graphics g) {

    }
}
