class Solution {
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length()-1;
        while (i<(s.length()) && j>0 ){
            if (Character.isLetterOrDigit(s.charAt(i))){
                if(Character.isLetterOrDigit(s.charAt(j))){
                   if(Character.toLowerCase(s.charAt(i))!=Character.toLowerCase(s.charAt(j))){
                    return false;
                    }
                    i++;
                    j--; 
                }
                else {
                    j--;
                }
                
            }
            else{
                i++;
            }
        }
        return true;
    }
}