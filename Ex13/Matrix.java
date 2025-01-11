
/**
 * This is a class of Matrix
 *
 * @author (Jason Sneag)
 * @version (2020a)
 */
public class Matrix
{
    // Matrix's fields:
    
    private int [][] _2darray;

    //Matrix's constructors:
    
    /**
     * Copy Constructor for objects of class Matrix.
     * @param array represents the array to be copied.
     */
    
    //assumes that array is at least 1 on 1.
    
    public Matrix(int[][] array)
    {
        _2darray=new int[array.length][array[0].length];
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                _2darray[i][j]=array[i][j];
            }
        }
    }
    
    /**
     * Constructor for objects of class Matrix.
     * @param size1 represents the amount of lines.
     * @param size2 represents the amount of collumns.
     */
    
    //assumes size1 and size2 are possitive.
    
    public Matrix(int size1,int size2)
    {
        _2darray=new int[size1][size2];
    }
    
    //methods of Matrix:

    /**
     * Creates a String that describes the Matrix.
     * @return  A String of the Matrix.
     */
    
    public String toString()
    {
        String str=new String();
        for(int i=0;i<_2darray.length;i++){
            for(int j=0;j<_2darray[i].length;j++){
                str=str+"\t"+_2darray[i][j];
            }
            str=str+"\n";
    }
    return str;
}

/**
 * Makes the Matrix negative.
 * @return The Matrix in its negative form.
 */

//explanation: the negative of all numbers is 255-(the number) just as said in the assignment page.

public Matrix makeNegative()
{
    Matrix mat=new Matrix(_2darray);
    for(int i=0;i<mat._2darray.length;i++){
        for(int j=0;j<mat._2darray[i].length;j++){
            mat._2darray[i][j]=255-mat._2darray[i][j];
        }
    }
    return mat;
}

/**
 * Makes th
 * @return The Matrix
 */

//explanation: the code checks if there are neighbours
//if i=0 then there is no left neighbour 
//if j=0 then there is no upper neighbour
//if i=array.length-1 then there is no lower neighbour
//if j=array[i].length-1 then there is no right neighbour
//to find the corners, both occurrences need to happen
//(for example there is no top left if and only if i=0 and j=0)
// sum will add the neighbours values if they are there(and the cube we're evaluating)
//counter will count the neighbours(including the cube we're evaluating)
//the average will be sum/counter ofcoarse

public Matrix imageFilterAverage()
{
    Matrix filmat=new Matrix(_2darray);
    int sum=0,counter=1;
    for(int i=0;i<_2darray.length;i++){
        for(int j=0;j<_2darray[i].length;j++){
            sum=sum+_2darray[i][j];
            if(i!=0){
                sum=sum+_2darray[i-1][j];
                counter++;}
            if(j!=0){
                sum=sum+_2darray[i][j-1];
                counter++;}
            if(i!=_2darray.length-1){
                sum=sum+_2darray[i+1][j];
                counter++;}
            if(j!=_2darray[i].length-1){
                sum=sum+_2darray[i][j+1];
                counter++;}
            if(i!=0&&j!=0){
                sum=sum+_2darray[i-1][j-1];
                counter++;}
            if(i!=0&&j!=_2darray[i].length-1){
                sum=sum+_2darray[i-1][j+1];
                counter++;}
            if(i!=_2darray.length-1&&j!=0){
                sum=sum+_2darray[i+1][j-1];
                counter++;}
            if(i!=_2darray.length-1&&j!=_2darray[i].length-1){
                sum=sum+_2darray[i+1][j+1];
                counter++;}
            filmat._2darray[i][j]=sum/counter;
            counter=1;
            sum=0;
        }
    }
    return filmat;
}

/**
 * Rotates the Matrix Clockwise.
 * @return The Matrix after the clockwise rotation.
 */

//explanation: when rotating a cube clockwise or flipping it over to the right
//the amount of collumns become the amount of lines and vice versa.
//also the number of the collumn that the value used to be in
//becomes the number of its line.
//and the new collumn is equal to the last line -(the previous line).

public Matrix rotateClockwise(){
    Matrix rotmat=new Matrix(_2darray[0].length,_2darray.length);
    for(int i=0;i<_2darray.length;i++){
        for(int j=0;j<_2darray[i].length;j++){
            rotmat._2darray[j][(_2darray.length-1)-i]=_2darray[i][j];
        }
    }
    return rotmat;
}

/**
 * Rotates the matrix counter clockwise.
 * @return The Matrix after the rotation.
 */

//explanation: when rotating a cube counter clockwise or flipping it over to the left
// the amount of collumns become the amount of lines and vice versa.
//also the number of the line becomes the the number of the collumn
//and the new line is equal to the last collumn-(the previous collumn).

public Matrix rotateCounterClockwise(){
    Matrix rotmat=new Matrix(_2darray[0].length,_2darray.length);
    for(int i=0;i<_2darray.length;i++){
        for(int j=0;j<_2darray[i].length;j++){
            rotmat._2darray[(_2darray[i].length-1)-j][i]=_2darray[i][j];
        }
    }
    return rotmat;
}

}
