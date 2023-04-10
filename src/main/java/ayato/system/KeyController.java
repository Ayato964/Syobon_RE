package ayato.system;

import ayato.main.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyController implements KeyListener {
    private static final HashMap<Integer, Boolean> KEY = new HashMap<>();
    private static final KeyController INSTANCE = new KeyController();
    private KeyController(){}
    public static void generate(){
        KEY.put(KeyEvent.VK_UP, false);
        KEY.put(KeyEvent.VK_DOWN, false);
        KEY.put(KeyEvent.VK_LEFT, false);
        KEY.put(KeyEvent.VK_RIGHT, false);
        Main.getInstance().addKeyListener(INSTANCE);
    }
    public static boolean get(int key){
        return KEY.get(key);
    }
    public static void changeTrue(int key){
        KEY.put(key, true);
    }
    public static void changeFalse(int key){
        KEY.put(key, false);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        changeTrue(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        changeFalse(e.getKeyCode());
    }
}
