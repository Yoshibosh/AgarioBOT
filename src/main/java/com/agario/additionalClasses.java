package com.agario;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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

        Random random = new Random();

        Genome(){
            for (int i = 0; i < sides.length; i++) {
                Pair<String,String> p = new Pair<String,String>(sides[i], sides[random.nextInt(8)]);
                geneticTable.add(p);
            }
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

        
        private void parse(String s){

            char[] inners = {'[',']',' ',','};
            boolean check = false;
            char[] str = s.toCharArray();
            String newStr = "";
            boolean isSecondWord = false;


            geneticTable = new ArrayList<Pair<String,String>>();

            // print(s);
            
           Pair<String,String> p = new Pair<String,String>("ass","sas");

           for (int i = 0; i < str.length; i++){


                if (str[i] == ',' && !isSecondWord){
                    p.first = newStr;
                    isSecondWord = true;
                    newStr = "";

                }

                if (str[i] == ']' && isSecondWord){

                    p.second = newStr;
                    newStr = "";

                    geneticTable.add(new Pair<String,String>(p.first,p.second));
                }   

                if (str[i] == '['){isSecondWord = false;}
                
                check = false;
                
                for (char d : inners) {
                    if (d == str[i]){check = true;}
                }if (!check){newStr += str[i];}
            }

            // print(geneticTable);

        }


        void save(String url){
            // File file = new File(url);

            try (FileWriter file = new FileWriter(url)){
                file.write(String.valueOf(geneticTable));                
            } catch (IOException e) {
                e.printStackTrace();
            }

            
        }

        void mutation(){
            Random r = new Random();

            int rand = r.nextInt(8);
            geneticTable.set(rand, new Pair<String,String>(geneticTable.get(rand).first, sides[r.nextInt(8)]));
        }

    }

    // public static void main(String[] args) {
    //     additionalClasses a = new additionalClasses();

    //     Genome g = a.new Genome();

    //     print(g.geneticTable);

    //     g.save("genom.txt");

    //     g = a.new Genome("genom.txt");


    // }

}
