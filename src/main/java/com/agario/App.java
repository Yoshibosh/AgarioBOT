package com.agario;
import java.awt.AWTException;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.CacheRequest;
import java.awt.MouseInfo;
import com.agario.additionalClasses;

import javax.imageio.ImageIO;

import org.w3c.dom.events.MouseEvent;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;


// import org.jnativehook.GlobalScreen;
// import org.jnativehook.NativeHookException;
// import org.jnativehook.keyboard.NativeKeyEvent;
// import org.jnativehook.keyboard.NativeKeyListener;


public class App 
{
    

    static <T> void print(T mes){
        System.out.println(String.valueOf(mes));
    }


    public static class AWTExample1 extends Frame {    
  
        
        // initializing using constructor  
        AWTExample1() {  
       
           // creating a button   
           Button b = new Button("Click Me!!");  
       
           // setting button position on screen  
           b.setBounds(30,100,80,30);  
            

           b.addKeyListener(new KeyListener() {
 
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    System.exit(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_I){
                    
                    Bot.RELOADIND_THE_TRY = !Bot.RELOADIND_THE_TRY; 
                    
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
             
        });
       
           // adding button into frame    
           add(b);  

           // frame size 300 width and 300 height    
           setSize(300,300);  
       
           // setting the title of Frame  
           setTitle("DOOM");   
               
           // no layout manager   
           setLayout(null);   
       
           // now frame will be visible, by default it is not visible    
           setVisible(true);  
        }
    }    

    static void reload() throws InterruptedException, AWTException{

        
        Bot.RELOADIND_THE_TRY = true;                   // !!!
                        

        Robot robot = new Robot();
                        
        robot.mouseMove(690, 388);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(400);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        Thread.sleep(1500);
        
        
        robot.mouseMove(1009, 294);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(400);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        Thread.sleep(1000);
        
        robot.mouseMove(716, 318);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(400);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);



        Bot.RELOADIND_THE_TRY = false;                  // !!!

    }

    
    public static void main( String[] args ) throws IOException, AWTException, InterruptedException
    {


        // ImageIO.write(image,"png",new File("endScreen2.png"));
        int thisSessionBotsGeneration = 0;

        AWTExample1 f = new AWTExample1();  
        
        long[] liveTime = new long[10];
        
        String lastGenUrl = "";
        additionalClasses a = new additionalClasses();

        boolean firstTime = false;


        while(true){

            

            for (int i = 0; i < 10; i++){
                
                Bot bot = new Bot(i);       
                if (lastGenUrl != "" && firstTime){bot.gen = a.new Genome(lastGenUrl);}

                firstTime = false;

                // bot.gen = a.new Genome();
    
                bot.start();
                // Thread.currentThread().join();
                // print("HELLO!");
                bot.gen.mutation();
                
                liveTime[i] = bot.howLongItLive;

                reload();
            }
    
            long max = 0;
            int maxIndex = 0;
            for (int i = 0; i < liveTime.length; i++) {
                if (max < liveTime[i]){
                    max = liveTime[i];
                    maxIndex = i;
                }
            }
    
            thisSessionBotsGeneration++;


            lastGenUrl = (maxIndex + ".txt");

            try(FileWriter fw = new FileWriter("g - " + thisSessionBotsGeneration + "_" + "LiveTime=" + max +"_"+ lastGenUrl)){
                try (FileReader fr = new FileReader(lastGenUrl)){
                    
                    int c;
                    String s = "";
                    while( (c=fr.read()) != -1){
                        
                        s += (char)c;

                    }
    
    
                    fw.write(s);

                }
            }catch (Exception e){
                e.printStackTrace();
            }

            firstTime = true;

        }
        


    }

}
