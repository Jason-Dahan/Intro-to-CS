
/**
 * The Triangle class prints the area and perimeter of a triangle.
 */
import java.util.Scanner;
public class Triangle
{
    /** 
     * Receives the three sides of the triangle from the user,
     * checks that they are legal,
     * in case of illegal sides - prints appropriate notice,
     * otherwise - calculates the are and perimeter.
     */
    public static void main (String [] args)
    {
        Scanner scan = new Scanner (System.in);
        System.out.println("This program calculates the area"
                           + " and the perimeter of a given triangle");
        System.out.println("Please enter the three lengths"+
                           " of the triangle's sides");
        //Gets the three sides of the triangle and inserts
        //into three variables: a,b,c.
        //Assumes the three numbers are integers.
        //Creates three more variables,
        //permeter and area will contain the final values,
        //x1 will contain perimeter/2 for convenience,
        //and x2 will contain the value of Heron's Formula,
        //before applying the square root.
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        int perimeter = 0;
        double x1=0;
        double x2=0;
        double area = 0;
        //Checks legality of the three sides,
        //with use of the Triangle Inequality Theorem.
        //Also checks if they are natural numbers
        if ((a<=0)||(b<=0)||(c<=0)||(a+b<=c)||(a+c<=b)||(b+c<=a))
        System.out.println (a + " " + b + " and " + c + " aren't three legal"
                           +" sides of a triangle");
        else{
            //Calculates the perimeter of the legal triangle.
            perimeter = a + b + c;
            //Calculates the area of the legal triangle
            //with use of Heron's Formula.
            x1 = perimeter/2.0;
            x2 = ((x1) * (x1-a) * (x1-b) * (x1-c));
            area = Math.sqrt(x2);
            //Prints the area and perimeter of the triangle
            System.out.println("The perimeter of the triangle is: " + 
                               perimeter + " and the area of the triangle"+
                               " is: " + area);
            }//end of else statement
    }//end of method main
}//end of class Triangle
