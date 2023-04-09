package ayato.objects.addtions;

import ayato.main.Main;
import ayato.objects.MyObjects;
import ayato.objects.block.Air;
import ayato.objects.block.Block;
import ayato.stage.Stage;
import ayato.system.CodeToon;
import ayato.util.Action;

import java.awt.*;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class InSide implements ObjectAddon{
    private Action action;
    private Action notAction;
    private IntSupplier x, y, w, h;
    public InSide(IntSupplier x, IntSupplier y, IntSupplier w, IntSupplier h, Action a, Action b){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        action = a;
        notAction = b;
    }
    @Override
    public void application(MyObjects object) {
        Stage stage = (Stage) Main.getInstance().displayMap;
        Rectangle mySelf = new Rectangle(x.getAsInt(), y.getAsInt(), w.getAsInt(), h.getAsInt());
        for(Block b : stage.blocks){
            Rectangle target1 = new Rectangle((int) b.x, (int) b.y, b.w * CodeToon.BLOCK_WIDTH, b.h * CodeToon.BLOCK_HEIGHT);
            if(target1.intersects(mySelf) && !b.equals(object) && b.getClass() != Air.class) {
                action.action(-1);
                return;
            }else {
                notAction.action(-1);
            }
        }
        Rectangle player =  new Rectangle((int) stage.player.x, (int) stage.player.y, stage.player.w * CodeToon.BLOCK_WIDTH, stage.player.h * CodeToon.BLOCK_HEIGHT);
        if(player.intersects(mySelf)) {
            action.action(-1);
        }else {
            notAction.action(-1);
        }

    }
}
