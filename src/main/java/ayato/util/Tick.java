package ayato.util;

import ayato.main.Main;
import ayato.system.Background;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class Tick{
    private static final Tick INSTANCE = new Tick();
    private ArrayList<TickRegistory> animation = new ArrayList<>();
    public ArrayList<Display> display;
    private int count = 0;
    private float animationCount = 0;
    private Tick(){
        display = new ArrayList<>();

        display.add(Background.getInstance());
        Timer timer = new Timer(false);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                animationCount += 1;
                if(animationCount / 1000 >= 0.03 ) {
                    animationCount = 0;

                    Iterator<Display> d = display.iterator();
                    while (d.hasNext()){
                        d.next().display(Main.getMainGraphics());
                    }

                    if (!animation.isEmpty()) {
                        while (count < animation.size()) {
                            animation.get(count).run_tick();
                            count++;
                        }
                        count = 0;
                    }

                    Main.getInstance().repaint();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0,1);

    }
    public void removeAnimation(TickRegistory regi){
        ArrayList<TickRegistory> temp = new ArrayList<>();

        for(int i = 0; i <animation.size(); i ++){
            if(!animation.get(i).equals(regi)){
                temp.add(animation.get(i));
            }
        }
        animation = temp;
        count = 0;
    }
    public void removeAllAnimation(){
        animation = new ArrayList<>();
        count = 0;
    }
    public void addAnimation(TickRegistory run_tick){
        animation.add(run_tick);
    }
    public static Tick getInstance(){
        return INSTANCE;
    }
}
