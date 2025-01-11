
/**
 * This is a class that represents a Polynom.
 *
 * @author (Jason Sneag)
 * @version (2020a)
 */
public class Polynom
{
    // Fields of Polynom:
    private PolyNode _first;

    /**
     * Empty Constructor for objects of class Polynom.
     * Time Complexity-O(1)
     * Space Complexity-O(1)
     */
    public Polynom()
    {
        _first=null;
    }

    /**
     * Constructor for objects of class Polynom.
     * Time Complexity-O(1)
     * Space Complexity-O(1)
     * @param p represents the PolyNode with the highest power of the Polynom.
     */
    public Polynom(PolyNode p){
        _first=p;
    }
    
    //Methods of Polynom:
    
    /**
     * Returns the PolyNode with the highest power of the Polynom.
     * Time Complexity-O(1)
     * Space Complexity-O(1)
     * @return    The PolyNode with the highest power of the Polynom.
     */
    public PolyNode getHead(){
        return _first;
    }
    
    /**
     * Changes the head of the Polynom.
     * Time Complexity-O(1)
     * Space Complexity-O(1)
     * @param p represents the PolyNode that will be added to the Polynom.
     */
    
    public void setHead(PolyNode p)
    {
        _first=p;
        
    }
    
    /**
     * Adds a PolyNode to the Polynom in the correct position.
     * Time Complexity-O(n)
     * Space Complexity-O(1)
     * @param p represents the PolyNode that will be added
     * @return    The Polynom after the insertion of p
     */
    
    public Polynom addNode(PolyNode p) {
        if (p == null || (p.getCoefficient() == 0)) 
            return this;
        PolyNode currNode = this._first;
        if (currNode == null) {
            this._first = p;
        }
        else {
            boolean done = false;
            while (!done) {
                done = true;
                if (currNode.getPower() < p.getPower()) {
                    this._first = p;
                    p.setNext(currNode);
                    done = true;
                }
                else if (currNode.getPower() == p.getPower()) {
                    currNode.setCoefficient(currNode.getCoefficient() + p.getCoefficient());
                }
                else if (currNode.getNext() != null) {
                    if (currNode.getNext().getPower() < p.getPower()) {
                        p.setNext(currNode.getNext());
                        currNode.setNext(p);
                    }
                    else done = false;
                }
                else {
                    currNode.setNext(p);
                }
                currNode = currNode.getNext();
            }
        }
        return this;
    }

/**
     * Multiplies the Polynom with a whole number.
     * Time Complexity-O(n)
     * Space Complexity-O(1)
     * @param num represents the whole number that will be multiplied with the Polynom.
     * @return    The Polynom after the calculation.
     */

public Polynom multByScalar(int num)
{
    if(_first!=null && num!=1){
        PolyNode current=_first;
        while(current!=null){
            current.setCoefficient(current.getCoefficient()*num);
            current=current.getNext();
        }
    }
    return this;
}

/**
     * Adds another Polynom to the current Polynom.
     * Time Complexity-O(n)
     * Space Complexity-O(1)
     * @param other represents the Polynom that will be added to the current Polynom.
     * @return    The Polynom after it has been added with the other Polynom.
     */
    
public Polynom addPol(Polynom other) {
        if (other == null) 
            return this;
        PolyNode currOtherNode = other.getHead();
        PolyNode currNode = this._first;
        PolyNode prevNode = null;
        while (currNode != null && currOtherNode != null) {
            if (currOtherNode.getCoefficient() == 0)
                currOtherNode = currOtherNode.getNext();
            else if (currOtherNode.getPower() == currNode.getPower()) {
                currNode.setCoefficient(currOtherNode.getCoefficient() + currNode.getCoefficient());
                prevNode = currNode;
                currNode = currNode.getNext();
                currOtherNode = currOtherNode.getNext();
            }
            else if (currOtherNode.getPower() > currNode.getPower())
            {
                PolyNode newNode = new PolyNode(currOtherNode);
                if (prevNode != null)
                {
                    prevNode.setNext(newNode);
                    newNode.setNext(currNode);
                    prevNode = newNode;
                }
                else {
                    newNode.setNext(currNode);
                    this._first = newNode;
                    prevNode = newNode;
                }
                currOtherNode = currOtherNode.getNext();
            }
            else if (currOtherNode.getPower() < currNode.getPower() &&
                    (currNode.getNext() == null ||
                            (currNode.getNext() != null &&
                            currNode.getNext().getPower() < currOtherNode.getPower()))) {
                PolyNode newNode = new PolyNode(currOtherNode);
                newNode.setNext(currNode.getNext());
                currNode.setNext(newNode);
                prevNode = currNode;
                currNode = newNode;
                currOtherNode = currOtherNode.getNext();
            }
            else {
                currNode = currNode.getNext();
            }
        }
        while (currOtherNode != null) {
            PolyNode newNode = new PolyNode(currOtherNode);
            if (prevNode != null) {
                prevNode.setNext(newNode);
                prevNode = newNode;
            }
            else {
                this._first = newNode;
                prevNode = newNode;
            }
            currOtherNode = currOtherNode.getNext();
        }
        return this;
    }

/**
     * Multiplies another Polynom with the current Polynom.
     * Time Complexity-O(n)
     * Space Complexity-O(1)
     * @param other represents the other Polynom that will be multiplied with the current Polynom.
     * @return    The Polynom after the calculation.
     */
    
 public Polynom multPol(Polynom other) {
        if (other == null || other.toString().equals("")) 
            return this;
        Polynom tempPolynom = new Polynom();
        PolyNode currNode = this._first;
        while (currNode != null) {
            PolyNode otherNode = other.getHead();
            while (otherNode != null) {
                if (otherNode.getCoefficient() != 0) {
                    int power = otherNode.getPower() + currNode.getPower();
                    double coefficient = otherNode.getCoefficient() * currNode.getCoefficient();
                    PolyNode nodeToAdd = new PolyNode(power, coefficient);
                    tempPolynom.addNode(nodeToAdd);
                }
                otherNode = otherNode.getNext();
            }
            currNode = currNode.getNext();
        }
        this._first = tempPolynom.getHead();
        return this;
    }

/**
     * Calculates the differential of the Polynom.
     * Time Complexity-O(n)
     * Space Complexity-O(1)
     * @return   The differential of the Polynom
     */
    
public Polynom differential() {
        PolyNode currNode = this._first;
        while (currNode != null) {
            double newCoefficient = currNode.getCoefficient() * currNode.getPower();
            currNode.setCoefficient(newCoefficient);
            int newPower = currNode.getPower() - 1;
            currNode.setPower(newPower);
            if (currNode.getNext() != null && currNode.getNext().getPower() == 0) {
                currNode.setNext(null);
                currNode = null;
            }
            else {
                currNode = currNode.getNext();
            }
        }
        return this;
    }

/**
     * Creates a String that represents the object Polynom.
     * Time Complexity-O(n)
     * Space Complexity-O(1)
     * @return   A String that represents the Polynom.
     */
    
public String toString() {
        String result = "";
        PolyNode currNode = this._first;
        while (currNode != null) {
            String nodeString = currNode.toString();
            if (!nodeString.equals("") && nodeString.charAt(0) != '-' && !result.equals("")) {
                result += "+";
            }
            result += nodeString;
            currNode = currNode.getNext();
        }
        return result;
    }

}
