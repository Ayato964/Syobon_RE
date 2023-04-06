package ayato.map;

import ayato.util.Display;
import ayato.util.Setup;

import java.awt.*;

public abstract class Map implements Display, Setup {
    public Map(){
    }
    @Override
    public abstract void setup(Graphics g);
    @Override
    public abstract void display(Graphics g);
  }