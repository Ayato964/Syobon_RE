package ayato.objects.entity;

import ayato.main.Main;
import ayato.map.Continue;
import ayato.objects.addtions.*;
import ayato.objects.block.Block;
import ayato.stage.Stage;
import ayato.system.CodeToon;
import ayato.system.KeyController;
import ayato.util.animation.ImageMaker;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {
    public boolean isMove = true;
    private BufferedImage texture_Left;
    private BufferedImage texture_Right;
    public Player(JsonNode info) {
        super(info, 1, 2);
        isNPC = false;
        speed = 10;
        texture_Left = (BufferedImage) new ImageMaker("mob/player_left", 64, 128).get();
        texture_Right = (BufferedImage) new ImageMaker("mob/player_right", 64, 128).get();
        KeyController.generate();
    }

    @Override
    public void display(Graphics g) {
        super.display(g);
/*
        g.fillRect((int)x , (int)y + h * CodeToon.BLOCK_HEIGHT ,w* CodeToon.BLOCK_WIDTH,5); //下部
        g.fillRect((int) x + w * CodeToon.BLOCK_WIDTH + 7, (int)y,5,h * CodeToon.BLOCK_HEIGHT);
        g.fillRect((int) x - 8, (int)y,5,h * CodeToon.BLOCK_HEIGHT);
        g.fillRect((int) x, (int)y - 1 ,w * CodeToon.BLOCK_WIDTH, 5);
 */

        if(isMove){
            if(KeyController.get(KeyEvent.VK_UP) && !isDownVoid)
                jump();
            if(KeyController.get(KeyEvent.VK_RIGHT) && !stage.worldMoveMode) {
                move(speed);
                texture = texture_Right;
            }
            if(KeyController.get(KeyEvent.VK_LEFT)) {
                move(-1 * speed);
                texture = texture_Left;
            }
        }
    }



    @Override
    protected void setAddons(ArrayList<ObjectAddon> addons) {
        addons.add(new Jump());
        addons.add(new Gravity());
        addons.add(new InSide(() ->(int) x,()-> (int)y + h * CodeToon.BLOCK_HEIGHT ,()->w* CodeToon.BLOCK_WIDTH,()->5, i -> {
            if(i instanceof Block) {
                if(((Block) i).isCollider) {
                    isDownVoid = false;
                    setY(y - 1);
                }
            }
        }, i->isDownVoid = true));
        addons.add(new InSide(() ->(int) x + w * CodeToon.BLOCK_WIDTH + 7,()-> (int)y + 5,()->5,()->h * CodeToon.BLOCK_HEIGHT - 10, i -> {
            if(i instanceof Block) {
                if(((Block) i).isCollider) {
                    isRightVoid = false;
                }
            }
        }, i->isRightVoid = true));
        addons.add(new InSide(() ->(int) x - 8,()-> (int)y + 5,()->5,()->h * CodeToon.BLOCK_HEIGHT - 10, i -> {
            if(i instanceof Block) {
                if(((Block) i).isCollider) {
                    isLeftVoid = false;
                }
            }
        }, i->isLeftVoid = true));
        addons.add(new InSide(() ->(int) x,()-> (int)y - 5,()->w * CodeToon.BLOCK_WIDTH,()-> 5, i -> {
            if(i instanceof Block) {
                if (((Block) i).isCollider) {
                    isUPVoid = false;
                }
            }
        }, i->isUPVoid = true));
        addons.add(new HPController(null, null, i->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Main.getInstance().run(new Continue(stage.reaming, stage.pack, stage.stage));

        }));
    }

    @Override
    protected ImageMaker setTexture() {
        return new ImageMaker("mob/player_left", 64, 128);
    }
}
