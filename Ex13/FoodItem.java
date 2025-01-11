
/**
 * This class represent FoodItem.
 *
 * @author (Jason Sneag)
 * @version (2020a)
 */
public class FoodItem
{
    // FoodItem's fields:
    private String name;
    private long catalogueNumber;
    private int quantity;
    private Date productionDate;
    private Date expiryDate;
    private int minTemperature;
    private int maxTemperature;
    private int price;

    //Constant Variables:
    //The default for a number that is higher or lower than four digits
    //is the HIGHEST_FOUR_DIGIT.
    
    private final int ZERO = 0;
    private final int DEFAULT_PRICE = 1;
    private final int LOWEST_FOUR_DIGIT = 1000;
    private final int HIGHEST_FOUR_DIGIT = 9999;
    
    /**
     * Constructs a food item by the specified name,catalogue number,quantity,production date,
     * expiry date,minimum temperature,maximum temperature and price of the item.
     * @param name represents the FoodItem's name(if empty called item)
     * @param catalogueNumber represents the item's catalogue number
     * (four digits and positive)
     * @param quantity represents how many item's there are of the item.
     * (positive)
     * @param productionDate represents the date the product was created.
     * @param expiryDate represents the date the product expires.
     * (expiryDate>productionDate)
     * @param minTemperature represents the item's minimum temperature.
     * @param maxTemperature represents the item's maximum temperature.
     * (minTemperature>maxTemperature)
     * @param price represents the item's price.(non-negative number)
     * @return An object representing a food item.
     */
    public FoodItem(String name,long catalogueNumber,int quantity,
                    Date productionDate,Date expiryDate,int minTemperature,
                    int maxTemperature,int price)
    {
        if(name.equals(""))
        this.name="item";
        else
        this.name=name;
        if((catalogueNumber<LOWEST_FOUR_DIGIT)
        ||(catalogueNumber>HIGHEST_FOUR_DIGIT))
        this.catalogueNumber=HIGHEST_FOUR_DIGIT;
        else
        this.catalogueNumber=catalogueNumber;
        if(quantity<ZERO)
        this.quantity=ZERO;
        else
        this.quantity=quantity;
        if(expiryDate.before(productionDate))
        this.expiryDate=new Date(productionDate.tomorrow());
        else
            this.expiryDate=new Date(expiryDate);
            this.productionDate=new Date(productionDate);
        if(minTemperature>maxTemperature){
            this.minTemperature=maxTemperature;
            this.maxTemperature=minTemperature;
        }
        else{
            this.minTemperature=minTemperature;
            this.maxTemperature=maxTemperature;
        }
        if(price<=ZERO)
        this.price=DEFAULT_PRICE;
        else
        this.price=price;
    }
    
    /**
     * Constructs a food item with another item's qualities.
     * @param other represents the item to be copied.
     * @return An object representing a food item.
     */
    
    //Copy constructor:
    
    public FoodItem(FoodItem other)
    {
    this.name=other.name;
    this.catalogueNumber=other.catalogueNumber;
    this.quantity=other.quantity;
    this.productionDate=new Date(other.productionDate);
    this.expiryDate=new Date(other.expiryDate);
    this.minTemperature=other.minTemperature;
    this.maxTemperature=other.maxTemperature;
    this.price=other.price;
}

    //FoodItem's methods:
    
    /**
     * Returns the catalogue number of the item.
     * @return Returns the catalogue number of the item.
     */
    
    public long getCatalogueNumber()
    {
        return this.catalogueNumber;
    }
    
    /**
     * Returns the name of the item.
     * @return Returns the name of the item.
     */
    
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Returns how many items are left.
     * @return Returns the quantity of the item.
     */
    
    public int getQuantity()
    {
        return this.quantity;
    }
    
    /**
     * Returns the production date of the item.
     * @return Returns the production date of the item.
     */
    
    public Date getProductionDate()
    {
        return new Date(this.productionDate);
    }
    
    /**
     * Returns the expiry date of the item.
     * @return Returns the expiry date of the item.
     */
    
    public Date getExpiryDate()
    {
        return new Date(this.expiryDate);
    }
    
    /**
     * @return Returns the minimum temperature of the item.
     */
    
    public int getMinTemperature()
    {
        return this.minTemperature;
    }
    
    /**
     * @return Returns the maximum of the item.
     */
    
    public int getMaxTemperature()
    {
        return this.maxTemperature;
    }
    
    /**
     * @return Returns the price of the item.
     */
    
    public int getPrice()
    {
        return this.price;
    }
    
    /**
     * Changes the quantity of the item.
     * Quantity must be positive.
     * @param n represents the quantity that will switch the current one.
     */
    
    public void setQuantity(int n)
    {
        if(!(n<ZERO))
        this.quantity=n;
    }
    
    /**
     * Changes the production date of the item.
     * Production date must be before the expiry date.
     * @param d represents the date that will switch the current one.
     */
    
    public void setProductionDate(Date d)
    {
        if(!(d.after(this.expiryDate)))
        this.productionDate=new Date(d);
    }
    
    /**
     * Changes the expiry date of the item.
     * Expiry date must be after the production date.
     * @param d represents the date that will switch the current one.
     */
    
    public void setExpiryDate(Date d)
    {
        if(!(d.before(this.productionDate)))
        this.expiryDate=new Date(d);
    }
    
    /**
     * Changes the price of the item.
     * Price must be non-negative.
     * @param n represents the price that will switch the old one.
     */
    
    public void setPrice(int n)
    {
        if(n>ZERO)
        this.price=n;
    }
    
    /**
     * Checks if the current item and the other item are the same.
     * Two items are the same if all of the fields are the same except for quantity.
     * @param other represents the other item that will be checked
     * @return True if they are, false if they aren't
     */
    
    public boolean equals(FoodItem other)
    {
        if((this.name.equals(other.name))&&
        (this.catalogueNumber==other.catalogueNumber)&&
        (this.productionDate.equals(other.productionDate))&& 
        (this.expiryDate.equals(other.expiryDate))&&
        (this.minTemperature==other.minTemperature)&&
        (this.maxTemperature==other.maxTemperature)&&(this.price==other.price))
        return true;
        return false;
    }
    
    /**
     * Checks if the item is still fresh on the date d.
     * @param d represents the date of that will be evaluated.
     * @return True if it is still fresh, false if it isn't.
     */
    
    //The item's production date mustn't be after d and it's expiry date
    //mustn't be before d.That way if the d is the same date as 
    //expiry or production it's still fresh.
    
    public boolean isFresh(Date d)
    {
        if((!(d.before(this.productionDate)))&&(!(d.after(this.expiryDate))))
        return true;
        return false;
    }
    
    /**
     * Creates a String that represents all the fields of the item and its values.
     * @return String of the item that shows all its values
     */
    
    public String toString()
    {
        return "FoodItem: "+this.name+"\t"+"CatalogueNumber: "
        +this.catalogueNumber+"\t"+"ProductionDate: "
        +this.productionDate.toString()+"\t"+"ExpiryDate: "
        +this.expiryDate.toString()+"\t"+"Quantity: "+this.quantity;
    }
    
    /**
     * Checks to see if the item is older than the other one.
     * @return True if it is, false if not.
     */
    
    public boolean olderFoodItem(FoodItem other)
    {
        if(other.productionDate.after(this.productionDate))
        return true;
        return false;
    }
    
    /**
     * Calculates how many of the current item the customer can buy with an amount of money.
     * @return The number of items he can buy
     */
    
    //If the quantity is larger than the amount he can buy
    //then he can buy as much as he can afford, if not then he can buy
    //all in stock.
    
    public int howManyItems(int amount)
    {
        return Math.min(this.quantity,amount/this.price);
    }
    
    /**
     * Checks to see if the item is cheaper than another item.
     * @return True if it is, false if it isn't.
     */
    
    public boolean isCheaper(FoodItem other)
    {
        return (this.price<other.price);
    }
        
}
