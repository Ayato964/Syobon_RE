package ayato.system;

import ayato.util.Action;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NextTask {
    private static NextTask task;
    private NextTask next;

    private ArrayList<Action> tasks;
    private NextTask(){
        tasks = new ArrayList<>();
    }
    public static void action(){
        if(!task.tasks.isEmpty()){
            for(Action a : task.tasks) {
                a.action(-1);
            }

        }
    }
    @Contract(value = " -> new", pure = true)
    public static @NotNull NextTask generate(){
        task = new NextTask();
        task.next = new NextTask();
        return   task;
    }
    public static void next(){
        task = task.next;
        task.next = new NextTask();
    }
    public static void add(Action a){
        task.next.tasks.add(a);
    }



}
