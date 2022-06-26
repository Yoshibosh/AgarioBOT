package com.agario;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;


import javax.swing.text.StyledEditorKit.BoldAction;

public class additionalClasses {
    
    static <T> void print(T mes){
        System.out.println(String.valueOf(mes));
    }

    static public class Pair<A,B>{
        A first;
        B second;

        Pair(A first, B second){
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return " [" + first + ", " + second + "] ";
        }
    }
    
    static public class SizeInt{
        int x;
        int y;
        int w;
        int h;

        SizeInt(int x,int y,int w,int h){
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
    }

    static public class MyColor{
        int r,g,b;

        MyColor(int value){
            this.r = ( value >>  0 ) & 255;
            this.g = ( value >>  8 ) & 255;
            this.b = ( value >> 16 ) & 255;
        }

        @Override
        public String toString() {
            return "r = " + this.r + " g = " + this.g + " b = " + this.b;
        }
    }



    String sides[] = {"слево-сверху","сверху","сраво-сверху","справа","снизу-справа","снизу","снизу-слева","слева"};

    public class Genome{

        ArrayList<Pair<String,String>> geneticTable = new ArrayList<>();
        HashMap<String,Integer> geneticTable2 = new HashMap<String,Integer>();

        Random random = new Random();

        Genome(){
            
            for (int i = 0; i < 8888; i++) {
                String s = String.valueOf(i);
                
                Random r = new Random();
                // ArrayList<Integer> inSAlready = new ArrayList<>();


                // while (s.length() < 4){
                //     // int newInt = r.nextInt(1,8);
                //     // if (inSAlready.contains(newInt)){continue;}
                //     // inSAlready.add(newInt);
                //     s += newInt;
                // }

                if (s.length() < 4){
                    for (int j = 0; j < 4 - s.length(); j++) {
                        s = "0" + s;
                    }
                }

                geneticTable2.put(s,r.nextInt(1,9));
            }
            // print(geneticTable2);
        }

        Genome(String genomeUrl){

            String genomeInString = "";

            try (FileReader file = new FileReader(genomeUrl)){
                
                int c;
                while( (c=file.read()) != -1){
                    
                    genomeInString += (char)c;

                } 

                parse(genomeInString);

                

            } catch (IOException e) {
                
                new Genome();
            }

        }

        private void parse(String string){
            this.geneticTable2.clear();
            
            String combination = ""; 
            char[] s = string.toCharArray();
            char c;

            for (int i = 0; i < s.length; i++) {
                c = s[i];

                if (c == '{' || c == ' ' || c == '}' || c == ','){continue;}
                if (c == '='){
                    this.geneticTable2.put(combination, Integer.parseInt(String.valueOf(s[i+1])));
                    i++;
                    combination = "";
                    continue;
                }

                combination += c;
            }
        }
        
        // private void parse(String s){

        //     char[] inners = {'[',']',' ',','};
        //     boolean check = false;
        //     char[] str = s.toCharArray();
        //     String newStr = "";
        //     boolean isSecondWord = false;


        //     geneticTable = new ArrayList<Pair<String,String>>();

        //     // print(s);
            
        //    Pair<String,String> p = new Pair<String,String>("1","2");

        //    for (int i = 0; i < str.length; i++){

        //         if (str[i] == ',' && !isSecondWord){
        //             p.first = newStr;
        //             isSecondWord = true;
        //             newStr = "";

        //         }

        //         if (str[i] == ']' && isSecondWord){

        //             p.second = newStr;
        //             newStr = "";

        //             geneticTable.add(new Pair<String,String>(p.first,p.second));
        //         }   

        //         if (str[i] == '['){isSecondWord = false;}
                
        //         check = false;
                
        //         for (char d : inners) {
        //             if (d == str[i]){check = true;}
        //         }if (!check){newStr += str[i];}
        //     }

        //     // print(geneticTable);

        // }


        void save(String url){
            // File file = new File(url);

            try (FileWriter file = new FileWriter(url)){
                file.write(String.valueOf(geneticTable2));                
            } catch (IOException e) {
                e.printStackTrace();
            }

            
        }

        void mutation(){
            Random r = new Random();

            int start = r.nextInt(8000);
            int end = start + 887;
            
            for (int i = start; i < end; i++) {
                geneticTable2.replace(String.valueOf(i), r.nextInt(8));
            }
            

            // geneticTable.set(rand, new Pair<String,String>(geneticTable.get(rand).first, sides[r.nextInt(8)]));
        }

        int makeAChoice(String way){
            if (geneticTable2.get(way) == null){System.exit(0);}
            return geneticTable2.get(way);
        }

    }

    public static void mouseMoveSlow(int endX,int endY) throws InterruptedException, AWTException{

        Robot r = new Robot();

        int startX = MouseInfo.getPointerInfo().getLocation().x;
        int startY = MouseInfo.getPointerInfo().getLocation().y;
        
        for (int i=0; i<100; i++){
            Thread.sleep(5);
            int x = ((endX * i)/100) + (startX*(100-i)/100);
            int y = ((endY * i)/100) + (startY*(100-i)/100);
          r.mouseMove(x,y);
        }

        // Point curPos = MouseInfo.getPointerInfo().getLocation();
        // Point range = new Point();
        // range.x = posX - curPos.x;
        // range.y = posY - curPos.y;

        // boolean poX = false;

        // poX = Math.abs(range.x) < Math.abs(range.y);
        

        // if (poX){
        //     while (posX != curPos.x) {
        //         Thread.sleep(timeInMillis/ Math.abs(range.x));
                
        //         if (range.x < 0){curPos.x -= 1;}
        //         else{curPos.x += 1;}
                
        //         curPos.y += range.y/range.x;

        //         r.mouseMove(curPos.x, curPos.y);
        //     }
        // }else{
        //     while (posY != curPos.y) {
        //         Thread.sleep(timeInMillis/ Math.abs(range.y));
                
        //         if (range.y < 0){curPos.y -= 1;}
        //         else{curPos.y += 1;}

        //         curPos.x += range.x/range.y;

        //         r.mouseMove(curPos.x, curPos.y);
        //     }
        // }

    }

    public static void main(String[] args) throws InterruptedException, AWTException {
        additionalClasses a = new additionalClasses();

        mouseMoveSlow(500, 500);

        // Genome g = a.new Genome();

        // print(g.geneticTable2);

        // g.save("genom.txt");

        // g = a.new Genome("genom.txt");
    }

}
