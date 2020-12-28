package com.example.legange;

public class Rand {

    public static int randInt(int min, int max) {

    return min + (int) (Math.random() * ((max - min) + 1));
}

 public static String randColorString()
 {
     int rand = 0 + (int)(Math.random() * ((3 - 0) + 1));

     switch(rand) {
         case 0: return "rouge";

         case 1 : return "bleu";

         case 2 : return  "noir";

         case 3 : return "blanc";

     }
     return "blanc";
 }

}
