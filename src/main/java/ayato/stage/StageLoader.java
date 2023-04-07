package ayato.stage;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class StageLoader {
    public static ArrayList<StagePack> STAGES = new ArrayList<>();

    public static void update(){
        STAGES = new ArrayList<>();
        URL url = ClassLoader.getSystemResource("assets/ayato/stage/");
        if(url.toString().indexOf("jar:") != -1) {
            try {
                JarFile stages = new JarFile(url.getFile());
                List<JarEntry> directory = stages.stream().toList();
                for (JarEntry e : directory){
                    if(e.isDirectory())
                        System.out.println(e.getName());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            File stages = new File(url.getFile());
            File[] directory = stages.listFiles();
            for(File f : directory)
                if(f.isDirectory())
                    createPack(f);



        }
    }
    private static void createPack(File f){
        StagePack pack = new StagePack();
        System.out.println(f.getPath());
        //add Main Stage Data
        File data = new File(f.getPath() + "/data.json");
        if(data.isFile()){
            try {
                pack.addMainData(new ObjectMapper().readTree(data));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //add Icon
        File icon = new File(f.getPath() + "/asset/icon/icon.png");
        if(icon.isFile()){
            try {
                pack.addIcon(ImageIO.read(icon));
            } catch (IOException e) {
                System.out.println("Not Icon File");
                throw new RuntimeException(e);
            }
        }
        //add Custom File
        File[] custom = new File(f.getPath() + "/asset/image/").listFiles();
        if(custom != null)for(File c : custom){
            if(c.isFile()) {
                try {
                    pack.customImages.add(ImageIO.read(c));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        //add Custom Stage
        File[] s = f.listFiles();
        for(File c : s){
            if(c.isFile() && c.getName().contains(".json") && !c.getName().contains("data.json")) {
                try {
                    pack.subStage.add(new ObjectMapper().readTree(c));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        STAGES.add(pack);
    }
    private static void createPack(JarFile f){
    }
}
