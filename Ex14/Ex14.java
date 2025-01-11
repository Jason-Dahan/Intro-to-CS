
/**
 * This is a collection of questions in the fourth maman.
 *
 * @author (Jason Sneag)
 * @version (2020a)
 */
public class Ex14
{

    /**
     * Returns the numbers of substrings starting and ending with c
     * and has exactly 1 c in the middle.
     *
     * @param  s  the String that will be used.
     * @param  c  the char that will be searched.
     * @return    number of substrings starting and ending with c 
     *            and one in the middle
     */
    
    //explanation: Each inner c(not starting and ending) has exactly 1 substring
    //it can belong to thus that will be the amount in total.
    //time complexity: The for loop does n loops so O(n) time while all the others O(1)
    //space complexity: There is only 1 variable added so O(1)
    public static int subStrC(String s, char c)
    {
        int count=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==c){
                count++;
            }
        }
    if(count<2)
        return 0;
    else
        return count-2;
    }
    
    /**
     * Returns the numbers of substrings starting and ending with c
     * and has maximum k c's in the middle.
     *
     * @param  s  the String that will be used.
     * @param  c  the char that will be searched.
     * @param  k  the maximum amount of c's inbetween each substring
     * @return    number of substrings starting and ending with c 
     *            and at most k c's in the middle.
     */
    
    //explanation: Each inner j<=k c's(not starting and ending) that are together have 1 substring
    //it can belong to thus the amount in total will be going from 0-k and adding the amount of groups with that
    //many c's.
    //time complexity: The for loops do n loops so O(n) time while all the others O(1)
    //space complexity: There is only 3 variable added so O(1)
    public static int subStrMaxC(String s, char c, int k){
        int count=0;
        int min=k;
        int total=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==c){
                count++;
            }
        }
        if(count<min)
            min=count;
        for(int i=0;i<min;i++){
            total=total+(count-i+1);
        }
        return total;
    }
    
    /**
     * Changes the array so that values now represent their distance in indexes from the nearest 0.
     *
     * @param  a[] represents the array we need to change.
     * 
     */
    
    //explanation: We will check the distances of all non zeros to their closest zero from their left side 
    //then we will check the distances of all non zeros to their closest zero from their right and see what
    //is shorter.
    //time complexity: The for loops do n loops so O(n) time while all the others O(1)
    //space complexity: There is only 1 variable added so O(1)
    //Given: There is atleast 1 zero.
    public static void zeroDistance(int[] a){
        int distance=0;
        for(int i=0;i<a.length;i++){
            if(a[i]==0){
                distance=0;
            }
            else{
                a[i]=distance+1;
                distance++;
            }
        }
        distance=0;
        for(int i=a.length;i<=0;i--){
            if(a[i]==0){
                distance=0;
            }
            else{
                if(a[i]>distance){
                    a[i]=distance+1;
                    distance++;
                }
            }
        }
    }
    
    /**
     * Returns if t is a transformation of s.
     *
     * @param  s  The original string.
     * @param  t  The perhaps transformed string.
     * @return  Returns true if t is a transformation of s or false if not.
     * 
     */
    
    //explanation: We need to check first if the original string is longer than the transformation,if so
    //it's not possible that it is a transformation.If we reached the end of t then we reached the end of s
    //after the first check. If we haven't reached the end of t but we reached the end of s then we are
    //checking if an empty string is a transformation of a non-empty so its false.
    //We check first that the 2 first characters match,if so we either have in the next character of t
    //a duplicate or the same as the next character of s, if one is correct then its true and we continue
    //the recursion.
    public static boolean isTrans(String s, String t){
        if(s.length() > t.length())
            return false;
        if(t.length()==0)
            return true;
        if(s.length()==0)
            return false;
        return (s.charAt(0) == t.charAt(0) &&
            (isTrans(s, t.substring(1)) ||
            isTrans(s.substring(1), t.substring(1))));
    }
    /**
     * Returns the number of paths leading from the top left cubicle to the bottom right one. Movement is 
     * based on the value in each cubicle with the digits signalling a right movement or a down movement.
     *
     * @param  mat[][]  The matrix used
     * @return  Returns the number of paths usable to get to the bottom right cubicle from the top left.
     * 
     */
    //Given: The cubicles have numbers between 1-99
    public static int countPaths(int[][]mat){
        return countPaths(mat,0,0);
    }
    
    //explanation: We check first to see if the indexes are the bottom right corner if so add a route.
    //the second and third if statements check that the combined row indexes with the first or second digits
    //and the column with the second or first digits respectively dont exceed the matrix's indexes.
    //The reason for checking if the first and second digits aren't the same is because that move will be
    //considered twice in the recursion which isn't necessary so we only send it once in the third if statement.
    private static int countPaths(int[][] mat, int rowIndex, int colIndex) {
        if (mat.length-1 == rowIndex && mat[0].length-1 == colIndex) 
            return 1;
            
        int firstDigit = mat[rowIndex][colIndex] / 10;
        int secondDigit = mat[rowIndex][colIndex] % 10;
        int totalRoutes = 0;
        
        if (firstDigit != secondDigit && 
            mat.length > rowIndex+firstDigit &&
            mat[rowIndex].length > colIndex+secondDigit) {
            totalRoutes = totalRoutes + countPaths(mat, rowIndex+firstDigit, colIndex+secondDigit);
        }
        if (mat.length > rowIndex+secondDigit &&
            mat[rowIndex].length > colIndex+firstDigit) {
            totalRoutes = totalRoutes + countPaths(mat, rowIndex+secondDigit, colIndex+firstDigit);
        }
        return totalRoutes;
    }
}
