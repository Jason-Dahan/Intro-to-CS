
/**
 * This class represents the stock of food items.
 *
 * @author (Jason Sneag)
 * @version (2020a)
 */
public class Stock
{
    //Stock's fields:
    
    private FoodItem [] _stock;
    private int _noOfItems;
    
    //Constant variables:
    
    private final int MAX_SIZE = 100;

    //Stock's constructor:
    
    /**
     * Default constructor for objects of class Stock.
     */
    
    public Stock()
    {
        _stock = new FoodItem [ MAX_SIZE ];
        _noOfItems = 0;
    }

    //Stock's methods:
    
    /**
     * Returns the number of items in stock.
     * @return The number of items in the Stock.
     */
    
    public int getNumOfItems()
    {
        return _noOfItems;
    }
    
    /**
     * Adds an item to the Stock, if there are 
     * 100 items already in the Stock and the item isn't there,
     * then it doesn't add.
     * @param item Represents the food item that is to be added.
     * @return True if item was added, false if it wasn't.
     */
    
    //explaination:
    //part1: temp1 and temp2 will assist with pushing the items
    //in the array forward without deleting any of them.
    //the while function will search for the index of the item by his 
    //catalogue number(catNum),the search will stop in one of the following
    //states:1. catNum is found and index is the first item with it
    //       2. catNum isn't found and index is on a catalogue number
    //          higher than catNum.
    //       3. catNum isn't found and there isn't items with a higher cat.num
    //          and index is on the first null in the array.
    //       4. catNum isn't found and the array is full,index is on the length.
    //part2: If the catNum wasn't found, then the item has to be added
    //adds the item to the list in the place of index and pushes others forward.
    //this happens as long as the array isn't full.
    //part3: catNum was found,search all items of the same catNum if there
    //is one that is equal otherwise add him before the others.
    //assumes input isnt null and there isn't an item with the same number
    //and a different name.
    
    public boolean addItem(FoodItem item)
    {
        FoodItem temp1=null,temp2=null;
        boolean found=false;
        int index=0;
        long catNum=item.getCatalogueNumber();
        if(_noOfItems==MAX_SIZE)
            return false;
        while(index<_noOfItems&&
        !found&&
        _stock[index].getCatalogueNumber()<=catNum){
        if(_stock[index].getCatalogueNumber()==catNum)
            found=true;
        index++;
    }
    if(!found){
        temp2=new FoodItem(item);
        for(int i=index;i<_stock.length&&
        (_stock[i]!=null && temp2!=null);i++)
       {
          temp1=_stock[i];
          _stock[i]=temp2;
          temp2=temp1;
        }
        _noOfItems++;
        return true;
    }
    for(int i=index;i<_stock.length && _stock[i].getCatalogueNumber()==catNum;i++){
        if(!(item.getName().equals(_stock[i].getName())))
            return false;
    if(item.getName().equals(_stock[i].getName()) && 
        item.getProductionDate().equals(_stock[i].getProductionDate()) &&
        item.getExpiryDate().equals(_stock[i].getExpiryDate()) ){
                _stock[i].setQuantity(_stock[i].getQuantity()+1);
                return true;
        }
    }
    temp2=new FoodItem(item);
    for(int i=index;i<_stock.length||(_stock[i]!=null && temp2!=null);i++)
       {
          temp1=_stock[i];
          _stock[i]=temp2;
          temp2=temp1;
        }
    _noOfItems++;
    return true;
    }

/**
 * Creates a list of items that needs to be ordered.
 * @return String of items to be ordered.
 * @param amount Represents the minimum amount of the item before it needs
 * to get ordered.
 */

//explaination: All items are one after the other, if the number after is the same as the one
//before then adds all the quantities together,skips the index to the last one and checks
//if the sum is lesser than the amount,if so adds it to the list.
//sumOf-The amount of items with the same cat. num(sum of quantities of all)
//counter-Counts the amount of indexes passed until the cat.num changes.

public String order(int amount){
    int sumOf=0;
    int counter=0;
    String orderItems=new String();
    if(_noOfItems!=0){
    for(int i=0;i<_noOfItems;i++){
        sumOf=_stock[i].getQuantity();
        if(i!=_stock.length-1 && _stock[i+1].getCatalogueNumber()==_stock[i].getCatalogueNumber()){
            for(int j=i+1;j<_stock.length && _stock[j].getCatalogueNumber()==_stock[i].getCatalogueNumber();j++){
                sumOf=sumOf+_stock[j].getQuantity();
                counter++;
            }
            i=i+counter;
            counter=0;
        }
        if(sumOf<amount){
            orderItems=orderItems+", "+_stock[i].getName();
        }
        sumOf=0;
}
}
return orderItems;
}

/**
 * Calculates the amount of items that can be stored in a fridge with a given temperature.
 * @param temp Represents the temperature of a given fridge.
 * @return The amount of items that can .
 */

//explanation: Goes through the array and checks if temp is between the item's max and min 
//temperatures.If it is, counts the quality and adds to the amount.

public int howMany(int temp){
    int amount=0;
    if(_noOfItems!=0){
    for(int i=0;i<_noOfItems;i++){
        if(_stock[i].getMinTemperature()<=temp && _stock[i].getMaxTemperature()>=temp){
            amount=amount+_stock[i].getQuantity();
        }
    }
}
return amount;
}

/**
 * Checks which items in stock have expired on a specific date and deletes them.
 * @param d Represents the date to check which items have expired.
 */

//explanation: Checks if the items if their expiry date isn't after the date-meaning it's either before or the same
//then deletes it and pushes all the items after one place before.

public void removeAfterDate(Date d){
    for(int i=0;i<_noOfItems;i++){
        if(!(_stock[i].getExpiryDate().after(d))){
            if(i==_stock.length-1)
            _stock[i]=null;
            for(int j=i;j<_noOfItems;j++){
                _stock[j]=_stock[j+1];
            }
            i--;
            _noOfItems--;
        }
    }
}

/**
 * Finds the most expensive item in stock.
 * @return The item that is the most expensive.
 */

//explanation:picks the first item and checks if the other items are more expensive(index is cheaper) if so then switches index
//to the more expensive item.

public FoodItem mostExpensive(){
    FoodItem mostExp=null;
    int index=0;
    if(_noOfItems!=0){
    for(int i=1;i<_noOfItems;i++){
        if(_stock[index].isCheaper(_stock[i]))
        index=i;
    }
    mostExp=new FoodItem(_stock[index]);
}
return mostExp;
}

/**
 * Counts all the pieces of food in stock.
 * @return The amount of pieces in stock.
 */

//explanation: pretty simple,goes through the items and sums each quality 

public int howManyPieces(){
    int amount=0;
    if(_noOfItems!=0){
        for(int i=0;i<_noOfItems;i++){
            amount=_stock[i].getQuantity()+amount;
        }
    }
    return amount;
}

/**
 * Creates a string that describes the Stock.
 * @return The string that describes the stock.
 */

public String toString(){
    String str1=new String();
    for(int i=0;i<_noOfItems;i++){
        str1=str1+_stock[i].toString()+"\n";
    }
    return str1;
}

/**
 * Updates the Stock by removing all the items that were bought.
 * @param bought Represents an array of Strings that have all the names of the items bought.
 */

public void updateStock(String[] bought){
    int j =0;
    for(int i=0;i<bought.length;i++){
        while(!(_stock[j].getName().equals(bought[i]))){
            j++;}
                _stock[j].setQuantity(_stock[j].getQuantity()-1);
                if(_stock[j].getQuantity()==0){
                    for(int k=j+1;k<_noOfItems;k++){
                        _stock[k-1]=_stock[k];
                    }
                    _noOfItems--;
                }
                j=0;
    }
}

/**
 * Finds the minimum temperature of the fridge that can store all the items in stock.
 * @return The minimum temperature that all the items can be stored in.
 */

//explanation: the highest minimum temp and the lowest maximum temp will determine if it's possible.
//if minimum is higher than maximum then it isn't possible.

public int getTempOfStock(){
    int min,max;
    if(_noOfItems==0)
        return Integer.MAX_VALUE;
    min=_stock[0].getMinTemperature();
    max=_stock[0].getMaxTemperature();
    for(int i=1;i<_noOfItems;i++){
        if(min<_stock[i].getMinTemperature())
            min=_stock[i].getMinTemperature();
        if(max>_stock[i].getMaxTemperature())
            max=_stock[i].getMaxTemperature();
    }
    if(min>max)
        return Integer.MAX_VALUE;
    return min;
}

}

                
            
        
