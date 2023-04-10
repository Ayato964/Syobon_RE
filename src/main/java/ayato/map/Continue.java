package ayato.map;

import ayato.main.Main;
import ayato.stage.Stage;
import ayato.stage.StagePack;
import ayato.system.Background;
import ayato.system.KeyController;
import ayato.util.animation.Animation;
import ayato.util.animation.AnimationImage;
import com.fasterxml.jackson.databind.JsonNode;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Continue extends Map{
    private int reaming;
    private StagePack pack;
    private JsonNode main;
    public Continue(int reaming, StagePack pack, JsonNode node){
        this.reaming = reaming - 1;
        this.pack = pack;
        main = node;
    }
    @Override
    public void setup(Graphics g) {
        Background.getInstance().mode = Background.BackgroundMode.DARK;
        Animation.create(g).draw("continue.mes", 0, 20, new Animation.Properties().font("", 0, 64).center());
        AnimationImage.createImage(g).draw("mob/player_left", 20, 30, 5, 10, new AnimationImage.PropertiesImage().of(64, 128).center());
        Animation.create(g).draw(new String[]{String.valueOf(reaming)},"continue.reaming", 120, 35, new Animation.Properties()
                .font("", 0, 32)
        );
        KeyController.generate();
    }

    @Override
    public void display(Graphics g) {
        if(KeyController.get(KeyEvent.VK_SPACE) || KeyController.get(KeyEvent.VK_ENTER)){
            Main.getInstance().run(new Stage(pack, main, reaming));
        }

    }
}
