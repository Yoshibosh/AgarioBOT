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
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.management.relation.Relation;

import com.agario.additionalClasses.MyColor;
import com.agario.additionalClasses.Pair;
import com.agario.additionalClasses.SizeInt;


public class App 
{

    static boolean RELOADIND_THE_TRY = false;




    ///////////////////////////////////////////////////////////////////


    static protected Toolkit toolkit = Toolkit.getDefaultToolkit();
    static Dimension size = toolkit.getScreenSize();
    static int screenshot_x = (size.width - Double.valueOf(size.width / 1.5).intValue())/2;
    static int screenshot_y = (size.height - Double.valueOf(size.height / 1.5).intValue())/2;        
    static int screenshot_w = Double.valueOf(size.width / 1.5).intValue();
    static int screenshot_h = Double.valueOf(size.height / 1.5).intValue();

    
    static int side_width = screenshot_w/3;
    static int side_height = screenshot_h/3;

    
    // static HashMap<String,SizeInt> sidesSize = new HashMap<String,SizeInt>();
    
    static ArrayList<Pair<String,SizeInt>> sidesSize = new ArrayList<Pair<String,SizeInt>>();


    // Деление скриншота на 9 равных частей.
    


    static void init(){
        
        sidesSize.add(new Pair<String,SizeInt>("слево-сверху",new SizeInt(0,0, side_width,side_height )));
        sidesSize.add(new Pair<String,SizeInt>("сверху" ,new SizeInt(side_width,0, side_width,side_height )));
        sidesSize.add(new Pair<String,SizeInt>("сраво-сверху" ,new SizeInt(side_width * 2,0, side_width,side_height )));
        sidesSize.add(new Pair<String,SizeInt>("справа" ,new SizeInt(side_width*2,side_height, side_width,side_height )));
        sidesSize.add(new Pair<String,SizeInt>("снизу-справа" ,new SizeInt(side_width*2,side_height*2, side_width,side_height )));
        sidesSize.add(new Pair<String,SizeInt>("снизу" ,new SizeInt(side_width,side_height*2, side_width,side_height )));
        sidesSize.add(new Pair<String,SizeInt>("снизу-слева" ,new SizeInt(0,side_height * 2, side_width,side_height )));
        sidesSize.add(new Pair<String,SizeInt>("слева" ,new SizeInt(0,side_height , side_width,side_height )));
    }



    static void way_decide() throws IOException, AWTException, InterruptedException{


        // HashMap<String,Integer> blackPixels = new HashMap<String,Integer>();

        // String sides[] = {"слево-сверху","сверху","сраво-сверху",
        // "справа","снизу-справа","снизу","снизу-слева","слева"};

        // for (String string : sides) {
        //     blackPixels.put(string, 0);
        // }


        
        Robot robot = new Robot();
        String format = "png";
        String name = "assBitch" + "." +format;

        


        Rectangle rectagle = new Rectangle(screenshot_x, screenshot_y, screenshot_w, screenshot_h);
        BufferedImage image = robot.createScreenCapture(rectagle);


        int maxBlackPix = 0;
        String where_we_go = "";
        for (int i = 0; i < 8; i++) {
            
            Pair<String,SizeInt> curSide = sidesSize.get(i);

            int blackPix = 0;

            for( int x = curSide.second.x; x < curSide.second.x + curSide.second.w; x++ ){
                for( int y = curSide.second.y; y < curSide.second.y + curSide.second.h; y++ ){

                    MyColor color = new MyColor(image.getRGB( x, y ));
                    // image.setRGB(x, y, -321);

                    

                    if (color.b < 70 && color.r < 70 && color.b < 70){
                        // print(color.toString());
                        blackPix++;
                    }
    
                    // System.out.println( "blue = " + color.getBlue() + " green = " + color.getGreen()
                    //  + " red = " + color.getRed() );
                }
            }    
            
            if (maxBlackPix < blackPix){
                maxBlackPix = blackPix;
                where_we_go = curSide.first;
            }
 
            // print(curSide.first + " blacpix = " + blackPix );

        }

        
        
        print("we go -> " + where_we_go + "|-| maxBlackPix = " + maxBlackPix);

        

        switch (where_we_go){
            case "слево-сверху":
                robot.mouseMove(200, 200);
                break;
            case "сверху":
                robot.mouseMove(740, 100);
                break;
            case "сраво-сверху":
                robot.mouseMove(1200, 200);
                break;
            case "справа":
                robot.mouseMove(1200,400);
                break;
            case "снизу-справа":
                robot.mouseMove(1200,800);
                break;
            case "снизу":
                robot.mouseMove(740, 800);
                break;
            case "снизу-слева":
                robot.mouseMove(200, 800);
                break;
            case "слева":
                robot.mouseMove(200,400);
                break;
        
        }

        
        // if ()

        while (RELOADIND_THE_TRY){
            Thread.sleep(100);
        }

        // ImageIO.write(image, format, new File(name));

    
        
    }

    static void print(String mes){
        System.out.println(mes);
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
                    try {

                        reload();

                    } catch (AWTException e1) {
                        e1.printStackTrace();
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    
                    
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
           setTitle("This is our basic AWT example");   
               
           // no layout manager   
           setLayout(null);   
       
           // now frame will be visible, by default it is not visible    
           setVisible(true);  
        }
    }    

    static void reload() throws InterruptedException, AWTException{

        
        RELOADIND_THE_TRY = true;                   // !!!
                        

        Robot robot = new Robot();
                        
        robot.mouseMove(722, 584);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(400);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        
        Thread.sleep(1000);
        
        robot.mouseMove(602, 394);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(400);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);



        RELOADIND_THE_TRY = false;                  // !!!

    }
    
    public static void main( String[] args ) throws IOException, AWTException, InterruptedException
    {

        Thread.sleep(2000);
     
        Robot robot = new Robot();

        Rectangle rectagle = new Rectangle(580, 500, 300, 100);
        BufferedImage image;

        // ImageIO.write(image, "png", new File("endScreen.png"));

        
        BufferedImage example = ImageIO.read(new File("endScreen.png"));

        init();

        



        // print(  " x = " + MouseInfo.getPointerInfo().getLocation().getX() + " y = " + MouseInfo.getPointerInfo().getLocation().getY() );

        // System.exit(0);
        

        AWTExample1 f = new AWTExample1();  

        while(true){
            // Thread.sleep(500);

            for (int i = 0; i < 10; i++) {
                way_decide();
            }
            
            image = robot.createScreenCapture(rectagle);

            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    
                    if(image.getRGB(x, y) != example.getRGB(x, y)){
                        reload();
                    }
                
                }
            }

        }
        

    }

}
