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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicComboBoxUI.FocusHandler;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import com.agario.additionalClasses.Genome;
import com.agario.additionalClasses.MyColor;
import com.agario.additionalClasses.Pair;
import com.agario.additionalClasses.SizeInt;

import com.agario.Bot;


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
                    
                    // gen.mutation();
                    
                    // try {

                    //     reload();

                    // } catch (AWTException e1) {
                    //     e1.printStackTrace();
                    // } catch (InterruptedException e1) {
                    //     e1.printStackTrace();
                    // }
                    
                    
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
                        
        robot.mouseMove(722, 584);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(400);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        
        Thread.sleep(500);
        
        robot.mouseMove(602, 394);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(400);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);



        Bot.RELOADIND_THE_TRY = false;                  // !!!

    }

    
    public static void main( String[] args ) throws IOException, AWTException, InterruptedException
    {



        AWTExample1 f = new AWTExample1();  
        
        long[] liveTime = new long[10];
        
        String lastGenUrl = "2_287152_.txt";
        additionalClasses a = new additionalClasses();

        while(true){
            for (int i = 0; i < 10; i++){
                Bot bot = new Bot(i);
                bot.gen = a.new Genome(lastGenUrl);
    
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
    
            lastGenUrl = (maxIndex + "_" + max + "_" + ".txt");


        }
        


    }

}
