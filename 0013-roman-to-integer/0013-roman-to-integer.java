class Solution {
    public int getIntValue(char roman) {
        switch (roman) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            default: return 1000;
        }
    }


    public int romanToInt(String s) {
        char[] arr = s.toCharArray();
        int l = arr.length - 1;
        int prev = 0;
        int ans = 0;
        
        
        for (int idx = l; idx >= 0; idx--) {
            char romanChar = arr[idx];
            int intVal = getIntValue(romanChar);
            
            
            if (intVal >= prev)
                ans += intVal;
            else
                ans -= intVal;
            
            
            prev = intVal;
        }
        
        
        return ans;
    }
}