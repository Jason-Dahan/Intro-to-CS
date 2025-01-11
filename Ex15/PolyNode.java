
/**
 * This class represents a PolyNode
 *
 * @author (Jason Sneag)
 * @version (2020a)
 */
public class PolyNode
{
    // Fields of PolyNode:
    
    private int _power;
    private double _coefficient;
    private PolyNode _next;

    /**
     * Constructor for objects of class PolyNode.
     * @param power represents the power of the variable.
     * @param coefficient represents the coefficient of the variable.
     */
    
    public PolyNode(int power,double coefficient)
    {
        if(power<0)
        {
            _power=0;
            _coefficient=0;
        }
        else{
        _power=power;
        _coefficient=coefficient;
    }
        _next=null;
    }
    
    /**
     * Constructor for objects of class PolyNode.
     * @param power represents the power of the variable.
     * @param coefficient represents the coefficient of the variable.
     * @param next represents the next PolyNode in the Polynom.
     */
    
    public PolyNode(int power,double coefficient,PolyNode next)
    {
        if(power<0)
        {
            _power=0;
            _coefficient=0;
        }
        else{
            _power=power;
            _coefficient=coefficient;
        }
        _next=next;
    }
    
    /**
     * Copy Constructor for objects of class PolyNode.
     * @param p represents the PolyNode that will be copied.
     */
    
    public PolyNode(PolyNode p)
    {
        _power=p._power;
        _coefficient=p._coefficient;
        _next=p._next;
    }
    
    //Methods of PolyNode:

    /**
     * Returns the power of the varible.
     * Time Complexity-O(1)
     * Space Complexity-O(1)
     * @return    The power of the variable.
     */
    
    public int getPower()
    {
        return _power;
    }
    
    /**
     * Returns the coefficient of the varible.
     * Time Complexity-O(1)
     * Space Complexity-O(1)
     * @return    The coefficient of the variable.
     */
    
    public double getCoefficient()
    {
        return _coefficient;
    }
    
    /**
     * Returns the next PolyNode of the Polynom.
     * Time Complexity-O(1)
     * Space Complexity-O(1)
     * @return    The next PolyNode of the Polynom.
     */
    
    public PolyNode getNext()
    {
        return _next;
    }
    
    /**
     * Changes the value of the power of the variable.
     * Time Complexity-O(1)
     * Space Complexity-O(1)
     * @param power The power that the field will be changed to.
     */
    
    public void setPower(int power)
    {
        if(power>=0)
        {
            _power=power;
        }
    }
    
    /**
     * Changes the value of the coefficient of the variable.
     * Time Complexity-O(1)
     * Space Complexity-O(1)
     * @param coeficient   The coefficient that the field will be changed to.
     */
    
    public void setCoefficient(double coefficient)
    {
        _coefficient=coefficient;
    }
    
    /**
     * Changes the next PolyNode in the Polynom.
     * Time Complexity-O(1)
     * Space Complexity-O(1)
     * @param next   The next that the field will be changed to.
     */
    
    public void setNext(PolyNode next)
    {
        _next=next;
    }
    
    /**
     * Creates and returns a String that represents the object with all its fields.
     * Time Complexity-O(1)
     * Space Complexity-O(1)
     * @return    A string that represents the object with all its fields.
     */
    
    public String toString()
    {
        String str=new String();
        if(_coefficient!=0){
            if(_power==0) 
                str=str+_coefficient;
            else{
                if(_coefficient==-1)
                    str=str+"-";
                if(_coefficient!=1)
                    str=str+_coefficient;
            }
            if(_power!=0){
                str=str+"x";
                if(_power!=1){
                    str=str+"^"+_power;
                }
            }
           }
        return str;
    }
}
