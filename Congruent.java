
/**
 * The Congruent class prints the size of the two triangles' sides,
 * and states if the two triangles are congruent.
 */
import java.util.Scanner;
public class Congruent
{
    /**
     * Receives from the user six pairs of real numbers,
     * each pair is a corner of two triangles.
     * Assumes all pairs are coordinates of the triangles.
     */
    public static void main (String [] args)
    {
       Scanner scan = new Scanner (System.in);
       System.out.println("This program calculates the sides"
                           + " of 2 given triangles by their corners'"+
                           " coordinates");
        System.out.println("Please enter the six pairs of coordinates"+
                           " of the triangles");
       //Gets the six pairs and inserts them into variables.
       //a-f are the sides of the triangles that will be calculated.
       //a,b,c are the first triangle's sides.
       //d,e,f are the second triangle's sides.
       //isCongruent will determine if the triangles are congruent.
       double x11 = scan.nextDouble();
       double y11 = scan.nextDouble();
       double x12 = scan.nextDouble();
       double y12 = scan.nextDouble();
       double x13 = scan.nextDouble();
       double y13 = scan.nextDouble();
       double x21 = scan.nextDouble();
       double y21 = scan.nextDouble();
       double x22 = scan.nextDouble();
       double y22 = scan.nextDouble();
       double x23 = scan.nextDouble();
       double y23 = scan.nextDouble();
       double a = 0;
       double b = 0;
       double c = 0;
       double d = 0;
       double e = 0;
       double f = 0;
       boolean isCongruent=false;
       //Calculates the sides of the triangles,
       //by using the Distance Formula.
       a = Math.sqrt((Math.pow((x12-x11),2) + Math.pow((y12-y11),2)));
       b = Math.sqrt((Math.pow((x13-x12),2) + Math.pow((y13-y12),2)));
       c = Math.sqrt((Math.pow((x11-x13),2) + Math.pow((y11-y13),2)));
       d = Math.sqrt((Math.pow((x22-x21),2) + Math.pow((y22-y21),2)));
       e = Math.sqrt((Math.pow((x23-x22),2) + Math.pow((y23-y22),2)));
       f = Math.sqrt((Math.pow((x21-x23),2) + Math.pow((y21-y23),2)));
       //Calculates the relations between the sides,
       //and checks if they are the same.
       if( a==d){
           if(((b==e) && (c==f)) || ((b==f) && (c==e)))
           isCongruent=true;
        }
       if(a==e){
           if(((b==d) && (c==f)) || ((b==f) && (c==d)))
           isCongruent=true;
        }
       if(a==f){
           if(((b==d) && (c==e)) || ((b==e) && (c==d)))
           isCongruent=true;
    }
    //Prints the sizes of the sides and if the triangles are congruent.
    System.out.println("The first triangle is (" + x11 + ", " + y11 +
                       ") (" + x12 + ", " + y12 +") (" + x13 + ", " +
                       y13 + ") .");
    System.out.println("Its lengths are " + a + ", " + b + ", " + c + ".");
    System.out.println("The second triangle is (" + x21 + ", " + y21 +
                       ") (" + x22 + ", " + y22 +") (" + x23 + ", " +
                       y23 + ") .");
    System.out.println("Its lengths are " + d + ", " + e + ", " + f + ".");
    if ( isCongruent)
      System.out.println("The triangles are congruent.");
      else
      System.out.println("The triangles are not congruent.");
    }
}
