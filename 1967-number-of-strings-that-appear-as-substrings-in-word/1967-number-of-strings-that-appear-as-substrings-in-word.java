class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int ans=0;
        for(String val:patterns){
            if(word.contains(val))ans++;
        }
        return ans;
    }
}