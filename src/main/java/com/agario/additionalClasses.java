package com.agario;

public class additionalClasses {
    
    
    static public class Pair<A,B>{
        A first;
        B second;

        Pair(A first, B second){
            this.first = first;
            this.second = second;
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

}
