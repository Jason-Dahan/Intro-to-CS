
/**
 * This class represents dates.
 *
 * @author (Jason Sneag)
 * @version (2020a)
 */
public class Date
{
    //Date's fields:
    
    private int _day;
    private int _month;
    private int _year;
    
    //Date's constants:
    
    private final int LOWEST_YEAR = 1000;
    private final int HIGHEST_YEAR = 9999;
    private final int LOWEST_MONTH = 1;
    private final int HIGHEST_MONTH = 12;
    private final int LOWEST_DAY = 1;
    private final int HIGHEST_DAY = 31;
    private final int SECOND_HIGHEST_DAY =30;
    private final int JANUARY = 1;
    private final int FEBRUARY = 2;
    private final int MARCH = 3;
    private final int APRIL = 4;
    private final int MAY = 5;
    private final int JUNE= 6;
    private final int JULY= 7;
    private final int AUGUST= 8;
    private final int SEPTEMBER= 9;
    private final int OCTOBER= 10;
    private final int NOVEMBER= 11;
    private final int DECEMBER= 12;
    private final int NON_LEAP_DAY = 28;
    private final int LEAP_DAY = 29;
    private final int DEFAULT_YEAR = 2000;
    private final int DEFAULT_MONTH = 1;
    private final int DEFAULT_DAY = 1;
    private final int ZERO = 0;
    private final int HIGHEST_SINGLE_DIGIT = 9;
    private final int LOWEST_DOUBLE_DIGIT = 10;
    private final int HUNDRED = 100;
    private final int TWENTY_SIX = 26;
    private final int TEN = 10;
    private final int FOUR = 4;
    private final int TWO = 2;
    private final int SEVEN = 7;
    private final int ONE = 1;
    
    /**
     * Constructs and initializes a date by the specified day,month and year.
     * @param day represents day in the month(1-31)
     * @param month represents month in the year(1-12)
     * @param year represents the year(4 digits)
     * @return An object representing a date.
     */
    
    //The constuctor also makes sure that the if the year isn't a leap year
    // February can't have 29 days.
    // Also in both situations for February makes sure the days are no more
    // than 29 days.
    
    public Date(int day, int month, int year)
    {
        if( (year<LOWEST_YEAR) || (year>HIGHEST_YEAR) 
           || (month<LOWEST_MONTH) || (month>HIGHEST_MONTH) 
           || (day<LOWEST_DAY) || (day>HIGHEST_DAY) || 
           ( (month==FEBRUARY) && (day>LEAP_DAY) ) ||
                ((!(isLeap(year))&&(month==FEBRUARY)&&(day==LEAP_DAY) ) ) )
           {
               _day = DEFAULT_DAY;
               _month = DEFAULT_MONTH;
               _year = DEFAULT_YEAR;
            }
            else{
           _day = day;
           _month = month;
           _year = year;
        }
    }
    
    /**
     * Creates and initializes a date on the same date as another.
     * @param other represents the date to be copied.
     * @return An object representing a Date.
     */
    
    public Date(Date other)
    {
        _day = other._day;
        _month = other._month;
        _year = other._year;
    }

    //Dates methods:
    
    /**
     * Returns this date's day.
     * @return The day of the date.
     * 
     */
    
    public int getDay()
    {
        return _day;
    }
    
    /**
     * Returns this date's month.
     * @return The month of the date.
     */
    
    public int getMonth()
    {
        return _month;
    }
    
    /**
     * Returns this date's year.
     * @return The year of the date.
     */
    
    public int getYear()
    {
        return _year;
    }
    
    /**
     * Changes the day of the date.
     * The value needs to be between 1 and the current month's last day (including).
     * @param dayToSet represents the day that will replace the current one.
     */
    
    public void setDay(int dayToSet)
    {
        if ( maxDays(_month,_year)>=dayToSet && dayToSet>=LOWEST_DAY )
           _day = dayToSet;
        }
        
    /**
     * Changes the month of the date.
     * The value needs to be between 1 and 12 (including) and the month added needs to include the current day.
     * @param monthToSet represents the month that will replace the current one.
     */
    
    public void setMonth(int monthToSet)
    {
        if ((!( (monthToSet<LOWEST_MONTH) || (monthToSet>HIGHEST_MONTH)))
                && (maxDays(monthToSet,_year)>=_day))
        _month = monthToSet;
    }
    
    /**
     * Changes the year of the date.
     * The value needs to be between 1000 and 9999 (including) and if the month is February and the day is 29,the new year
     * needs to be a leap year.
     * @param yearToSet represents the year that will replace the current one.
     */
    
    public void setYear(int yearToSet)
    {
        if ((!( (yearToSet<LOWEST_YEAR) || (yearToSet>HIGHEST_YEAR))))
            if((_month!=FEBRUARY || _day!=LEAP_DAY)||((isLeap(yearToSet))))
        _year = yearToSet;
    }
    
    /**
     * Checks if the current date and the other date are the same.
     * @param other represents the other date.
     * @return If the dates are the same.
     */
    
    public boolean equals(Date other)
    {
        if( (_day==other._day) && (_month==other._month) 
        && (_year==other._year))
        return true;
        return false;
    }
    
    /**
     * Checks if the current date is before the other date.
     * If they are the same then it isn't before.
     * @param other represents the other date.
     * @return If this date is before the other date.
     */
    
    public boolean before(Date other)
    {
        if( (_year<other._year) || ( (_year==other._year) 
        && (_month<other._month) ) || ( (_year==other._year) 
        && (_month==other._month) && (_day<other._day) ) )
        return true;
        return false;
    }
    
    /**
     * Checks if the current date is after the other date.
     * If they are the same then it isn't after.
     * @param other represents the other date.
     * @return If this date is after the other date.
     */
    
    public boolean after(Date other)
    {
        if(other.before(this))
        return true;
        return false;
    }
    
    /**
     * Calculates the difference, in days, between the current date and the other date.
     * @param other represents the other date.
     * @return The amount of days between the two dates.
     */
    
    public int difference(Date other)
    {
        return Math.abs(this.calculateDate(_day,_month,_year)
        -other.calculateDate(other._day,other._month,other._year));
    }
    
    /**
     * Creates a String to represent the Date with all it's values.
     * The date is presented : dd/mm/yyyy.
     * @return A string representing the date.
     */
    
    public String toString()
    {
        if(_day<LOWEST_DOUBLE_DIGIT && _month>HIGHEST_SINGLE_DIGIT)
        return ""+ZERO + _day + "/" + _month + "/" + _year;
        if(_day>HIGHEST_SINGLE_DIGIT && _month<LOWEST_DOUBLE_DIGIT)
        return _day + "/" + ZERO + _month + "/" + _year;
        if(_day<LOWEST_DOUBLE_DIGIT && _month<LOWEST_DOUBLE_DIGIT)
        return ""+ZERO + _day + "/"+ ZERO + _month + "/" + _year;
        return _day + "/" + _month + "/" + _year;
    }
    
    /**
     * Returns the date of tomorrow.
     * @return The date of tomorrow.
     */
    
    //Returns the next day of this Date,needs to check which month
    //-in order to calculate if its the end of the month.
    //If it's December then the year needs to be changed and the month set
    //back to 1.Also if it's February needs to check if its a leap year.
    //Otherwise if it's the end of the month,sets day to 1 and month to next/
    //If not,then just change the day to the next day:
    
    public Date tomorrow()
    {
        Date nextDay = new Date(this);
        if((nextDay._year==HIGHEST_YEAR)
                &&(nextDay._month==HIGHEST_MONTH)&&(nextDay._day==HIGHEST_DAY)) {
            nextDay._day = DEFAULT_DAY;
            nextDay._month = DEFAULT_MONTH;
            nextDay._year = DEFAULT_YEAR;
        }
        else {
            if ((maxDays(nextDay._month, nextDay._year) == HIGHEST_DAY)
                    && ((nextDay._day == HIGHEST_DAY))) {
                if (nextDay._month == DECEMBER) {
                    nextDay.setDay(LOWEST_DAY);
                    nextDay.setMonth(JANUARY);
                    nextDay.setYear(nextDay.getYear() + ONE);
                } else {
                    nextDay.setDay(LOWEST_DAY);
                    nextDay.setMonth(nextDay.getMonth() + ONE);
                }
            } else {
                if ((maxDays(nextDay._month, nextDay._year) == SECOND_HIGHEST_DAY)
                        && ((nextDay._day == SECOND_HIGHEST_DAY))) {
                    nextDay.setDay(LOWEST_DAY);
                    nextDay.setMonth(nextDay.getMonth() + ONE);
                } else {
                    if ((((nextDay._day == NON_LEAP_DAY) &&
                            (!(isLeap(nextDay._year)))) ||
                            ((nextDay._day == LEAP_DAY) &&
                                    (isLeap(nextDay._year))))) {
                        nextDay.setDay(LOWEST_DAY);
                        nextDay.setMonth(nextDay._month + ONE);
                    } else {
                        nextDay.setDay(nextDay._day + ONE);
                    }
                }
            }
        }
return nextDay;
}
   
/**
     * Returns on which day of the week the current date is.
     * 1-6 represents Sunday - Friday.
     * 0 represents Saturday.
     * @return A number representing the day of the week.
     */ 
    
//Calculates which day of the week it is,by using the formula given.
//If its January or February the formula requires to add 12 to change them
//to 13 or 14.The answer can be negative which is why we use Math.floodMod
//in that case:

    public int dayInWeek()
    {
        int M;
        int Y;
        int C;
        if(_month<MARCH){
            M = _month+HIGHEST_MONTH;
            Y = (_year-ONE)%HUNDRED;
            C = (_year-ONE)/HUNDRED;}
            else{
                M = _month;
                Y = _year%HUNDRED;
                C = _year/HUNDRED;}
        int ans = (_day+(TWENTY_SIX*(M+1))/TEN+Y+Y/FOUR+C/FOUR-TWO*C)%SEVEN;
        if(ans<ZERO)
        return Math.floorMod(ans,SEVEN);
        return ans;
    }
    
    //Given Method to help with calculations.
    //Allowed to use numbers:
    
    private int calculateDate(int day,int month,int year)
    {
        if(month<3){
            year--;
            month=month+12;
        }
        return 365*year+year/4-year/100+year/400+((month+1)*306)/10+(day-62);
    }

    //Helping Method that returns if the given year is a leap year

    private boolean isLeap(int year)
    {
        if(year%FOUR!=ZERO)
            return false;
        else{
            if(year%HUNDRED!=ZERO)
                return true;
            else{
                if(year%(FOUR*HUNDRED)!=ZERO)
                    return false;
                else
                    return true;
            }
        }
    }

    //Helping method that returns the amount of days in that month of that year

    private int maxDays(int month,int year)
    {
        if((month==JANUARY)||(month==MARCH)||(month==MAY)||(month==JULY)||(month==AUGUST)
            ||(month==OCTOBER)||(month==DECEMBER))
            return HIGHEST_DAY;
        if((month==APRIL)||(month==JUNE)||(month==SEPTEMBER)||(month==NOVEMBER))
            return SECOND_HIGHEST_DAY;
        if(isLeap(year))
            return LEAP_DAY;
            return NON_LEAP_DAY;
    }
}
