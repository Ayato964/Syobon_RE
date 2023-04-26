package ayato.objects.entity;

import ayato.main.Main;
import ayato.objects.MyObjects;
import ayato.objects.ObjectAction;
import ayato.objects.addtions.Gravity;
import ayato.objects.addtions.InSide;
import ayato.objects.addtions.ObjectAddon;
import ayato.objects.block.Block;
import ayato.stage.Stage;
import ayato.system.CodeToon;
import ayato.system.KeyController;
import ayato.system.NextTask;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public abstract class Entity extends MyObjects {
    protected boolean isNPC = true;
    public int hp, mHp;
    public int speed;
    protected String direction;
    protected Stage stage;

    public Entity(JsonNode info, int w, int h) {
        super(info.get("x").asInt() * CodeToon.BLOCK_WIDTH, info.get("y").asInt() * CodeToon.BLOCK_HEIGHT, w, h);
        if(info.get("speed") != null)
            speed = info.get("speed").asInt();
        else
            speed = 0;
        if(info.get("hp") != null)
            mHp = info.get("hp").asInt();
        else
            mHp = 1;

        if(info.get("direction") != null)
            direction = info.get("direction").asText();
        else
            direction = "left";

        hp = mHp;

        stage = (Stage) Main.getInstance().displayMap;


    }

    @Override
    public void display(Graphics g) {
        super.display(g);
        if(y >= Main.DESCTOP_BOUNDS.height){
            hp --;
        }
        if(isNPC){
            g.setColor(Color.WHITE);
            g.drawImage(texture, (int) x, (int) y , w * CodeToon.BLOCK_WIDTH, h * CodeToon.BLOCK_HEIGHT, null);
            switch (direction){
                case "left":if(stage.worldMoveMode && KeyController.get(KeyEvent.VK_RIGHT)) move(-1 * (stage.player.speed + speed));else move(-1 * speed);break;
                case "right":move(speed);break;
                case "up":jump();break;
                case "down":break;
            }
            if(x + CodeToon.BLOCK_WIDTH< 0){
                stage.endingTask(i->stage.entities.remove(this));
            }
        }else {
            g.setColor(Color.WHITE);
            g.drawImage(texture, (int) x, (int) y, w * CodeToon.BLOCK_WIDTH, h * CodeToon.BLOCK_HEIGHT, null);
        }
    }
    protected void collider(ArrayList<ObjectAddon> addons, ObjectAction up,ObjectAction down,ObjectAction left,ObjectAction right){

        addons.add(new InSide(() ->(int) x,()-> (int)y + h * CodeToon.BLOCK_HEIGHT ,()->w* CodeToon.BLOCK_WIDTH,()->5, i -> {
            if(i instanceof Block) {
                if(((Block) i).isCollider) {
                    isDownVoid = false;
                    setY(y - 1);
                }
            }
            down.action(i);
        }, i->isDownVoid = true));
        addons.add(new InSide(() ->(int) x + w * CodeToon.BLOCK_WIDTH + 7,()-> (int)y + 8,()->5,()->h * CodeToon.BLOCK_HEIGHT - 13, i -> {
            if(i instanceof Block) {
                if(((Block) i).isCollider) {
                    isRightVoid = false;
                }
            }
            right.action(i);

        }, i->isRightVoid = true));
        addons.add(new InSide(() ->(int) x - 8,()-> (int)y + 8,()->5,()->h * CodeToon.BLOCK_HEIGHT - 13, i -> {
            if(i instanceof Block) {
                if(((Block) i).isCollider) {
                    isLeftVoid = false;
                }
            }
            left.action(i);
        }, i->isLeftVoid = true));
        addons.add(new InSide(() ->(int) x,()-> (int)y - 5,()->w * CodeToon.BLOCK_WIDTH,()-> 5, i -> {
            if(i instanceof Block) {
                if (((Block) i).isCollider) {
                    isUPVoid = false;
                }
            }
            up.action(i);
        }, i->isUPVoid = true));
    }

    protected int getSpeed(){
        return speed;
    }
}
