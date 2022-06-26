package com.agario;

import java.awt.AWTException;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicComboBoxUI.FocusHandler;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import com.agario.additionalClasses.Genome;
import com.agario.additionalClasses.MyColor;
import com.agario.additionalClasses.Pair;
import com.agario.additionalClasses.SizeInt;
import com.agario.additionalClasses;

public class Bot {
    
    static boolean RELOADIND_THE_TRY = false;

    long time;

    long botName;

    long howLongItLive = 0;


    ///////////////////////////////////////////////////////////////////


    static protected Toolkit toolkit = Toolkit.getDefaultToolkit();
    static Dimension size = toolkit.getScreenSize();
    static int screenshot_x = (size.width - Double.valueOf(size.width / 1.5).intValue())/2;
    static int screenshot_y = (size.height - Double.valueOf(size.height / 1.5).intValue())/2;        
    static int screenshot_w = Double.valueOf(size.width / 1.5).intValue();
    static int screenshot_h = Double.valueOf(size.height / 1.5).intValue();

    
    static int side_width = screenshot_w/3;
    static int side_height = screenshot_h/3;

    Genome gen;

    
    // static HashMap<String,SizeInt> sidesSize = new HashMap<String,SizeInt>();
    
    static ArrayList<Pair<String,SizeInt>> sidesSize = new ArrayList<Pair<String,SizeInt>>();


    // Деление скриншота на 9 равных частей.
    


    Bot(int name){

        this.botName = name;

        additionalClasses a = new additionalClasses();
        this.gen = a.new Genome();
        
        sidesSize.add(new Pair<String,SizeInt>("слево-сверху",new SizeInt(0,0, side_width,side_height )));
        sidesSize.add(new Pair<String,SizeInt>("сверху" ,new SizeInt(side_width,0, side_width,side_height )));
        sidesSize.add(new Pair<String,SizeInt>("сраво-сверху" ,new SizeInt(side_width * 2,0, side_width,side_height )));
        sidesSize.add(new Pair<String,SizeInt>("справа" ,new SizeInt(side_width*2,side_height, side_width,side_height )));
        sidesSize.add(new Pair<String,SizeInt>("снизу-справа" ,new SizeInt(side_width*2,side_height*2, side_width,side_height )));
        sidesSize.add(new Pair<String,SizeInt>("снизу" ,new SizeInt(side_width,side_height*2, side_width,side_height )));
        sidesSize.add(new Pair<String,SizeInt>("снизу-слева" ,new SizeInt(0,side_height * 2, side_width,side_height )));
        sidesSize.add(new Pair<String,SizeInt>("слева" ,new SizeInt(0,side_height , side_width,side_height )));
    
        
    }

    // long howLongItLive(){
    //     return System.currentTimeMillis() - time;
    // }

    /*Запускает бота */
    void start() throws IOException, AWTException, InterruptedException{
    /*Запускает бота */

        time = System.currentTimeMillis();


        Robot robot = new Robot();

        Rectangle rectagle = new Rectangle(580, 350, 250, 50);
        // Rectangle rectagle = new Rectangle(580, 500, 300, 100);
        BufferedImage image;

        // ImageIO.write(image, "png", new File("endScreen.png"));

        
        BufferedImage example = ImageIO.read(new File("endScreen2.png"));
        additionalClasses a = new additionalClasses();

        while (true){
            // this.gen = a.new Genome();
            
            for (int i = 0; i < 10; i++) {
                way_decide();
            }
            
            image = robot.createScreenCapture(rectagle);
            int ___first = 0;
            int ___second = 0;
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    
                    ___first++;
                    if(image.getRGB(x, y) == example.getRGB(x, y)){
                        ___second++;
                    }
                
                }
            }
            if (___first == ___second){
                // GameOver;
                
                this.howLongItLive = System.currentTimeMillis() - time;
                this.gen.save(botName + ".txt");
                return;
                
            }
        }

    }

    



    void way_decide() throws IOException, AWTException, InterruptedException{


        // HashMap<String,Integer> blackPixels = new HashMap<String,Integer>();

        // String sides[] = {"слево-сверху","сверху","сраво-сверху",
        // "справа","снизу-справа","снизу","снизу-слева","слева"};

        // for (String string : sides) {
        //     blackPixels.put(string, 0);
        // }


        
        Robot robot = new Robot();

        


        Rectangle rectagle = new Rectangle(screenshot_x, screenshot_y, screenshot_w, screenshot_h);
        BufferedImage image = robot.createScreenCapture(rectagle);

        ArrayList<Integer> blackPixels = new ArrayList<Integer>();
        HashMap <Integer,String> blackPixMap = new HashMap<Integer,String>();




        int maxBlackPix = 0;
        String where_we_go = "";
        for (int i = 0; i < 8; i++) {
            
            Pair<String,SizeInt> curSide = sidesSize.get(i);

            int blackPix = 0;

            for( int x = curSide.second.x; x < curSide.second.x + curSide.second.w; x++ ){
                for( int y = curSide.second.y; y < curSide.second.y + curSide.second.h; y++ ){

                    MyColor color = new MyColor(image.getRGB( x, y ));
                    // image.setRGB(x, y, -321);

                    

                    if (color.b == 17 && color.r == 17 && color.b == 17){
                        // print(color.toString());
                        blackPix++;
                    }
    
                    // System.out.println( "blue = " + color.getBlue() + " green = " + color.getGreen()
                    //  + " red = " + color.getRed() );
                }
            }
            
            blackPixels.add(blackPix);
            blackPixMap.put(blackPix,curSide.first);
            
            if (maxBlackPix < blackPix){
                maxBlackPix = blackPix;
                where_we_go = curSide.first;
            }


 
            // print(curSide.first + " blacpix = " + blackPix );

        }
        
        // //Геном решает куда идти

        String sides[] = {"слево-сверху","сверху","сраво-сверху","справа","снизу-справа","снизу","снизу-слева","слева"};
        HashMap<String,Integer> sidesD = new HashMap<>();

        for (int i = 0; i < sides.length; i++) {
            sidesD.put(sides[i], i);
        }

        Collections.sort(blackPixels);
        

        // Integer[] blackPArr = {0,0,0,0,0,0,0,0};             

        // print(blackPixels);
        // Iterator<Integer> it =  blackPixels.keySet().iterator();
        // int ieiowqoiepqw = 0;
        // while (it.hasNext()) {
        //     int afasf = it.next();
        //     blackPArr[ieiowqoiepqw++] = afasf;
        //     print(afasf);
        // }

        // Arrays.sort(blackPArr);
        
        String curSituation = "";
        curSituation += sidesD.get(blackPixMap.get(blackPixels.get(0)));
        curSituation += sidesD.get(blackPixMap.get(blackPixels.get(3)));
        curSituation += sidesD.get(blackPixMap.get(blackPixels.get(4)));
        curSituation += sidesD.get(blackPixMap.get(blackPixels.get(7)));

        print(curSituation);
        // print(this.gen.geneticTable2);
        int choice = this.gen.makeAChoice(curSituation);

        
        if (choice == '8'){
            robot.keyPress(KeyEvent.VK_SPACE);
            Thread.sleep(300);
            robot.keyRelease(KeyEvent.VK_SPACE);
        }

        for (int i = 0; i < sides.length; i++) {
            if (i == choice){where_we_go = sides[i];}
        }


        print("we go -> " + where_we_go);

        
        switch (where_we_go){
            case "слево-сверху":
                additionalClasses.mouseMoveSlow(200, 200);
                break;
            case "сверху":
                additionalClasses.mouseMoveSlow(740, 100);
                break;
            case "сраво-сверху":
                additionalClasses.mouseMoveSlow(1200, 200);
                break;
            case "справа":
                additionalClasses.mouseMoveSlow(1200,400);
                break;
            case "снизу-справа":
                additionalClasses.mouseMoveSlow(1200,800);
                break;
            case "снизу":
                additionalClasses.mouseMoveSlow(740, 800);
                break;
            case "снизу-слева":
                additionalClasses.mouseMoveSlow(200, 800);
                break;
            case "слева":
                additionalClasses.mouseMoveSlow(200,400);
                break;
        
        }

        // switch (where_we_go){
        //     case "слево-сверху":
        //         robot.mouseMove(200, 200);
        //         break;
        //     case "сверху":
        //         robot.mouseMove(740, 100);
        //         break;
        //     case "сраво-сверху":
        //         robot.mouseMove(1200, 200);
        //         break;
        //     case "справа":
        //         robot.mouseMove(1200,400);
        //         break;
        //     case "снизу-справа":
        //         robot.mouseMove(1200,800);
        //         break;
        //     case "снизу":
        //         robot.mouseMove(740, 800);
        //         break;
        //     case "снизу-слева":
        //         robot.mouseMove(200, 800);
        //         break;
        //     case "слева":
        //         robot.mouseMove(200,400);
        //         break;
        
        // }

        
        // if ()

        while (RELOADIND_THE_TRY){
            print("jello");
            Thread.sleep(100);
        }

        // ImageIO.write(image, format, new File(name));

        
    }

    
    static <T> void print(T mes){
        System.out.println(String.valueOf(mes));
    }

}
