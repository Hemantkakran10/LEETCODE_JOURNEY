class Solution {
    public int mirrorDistance(int n) {
        int res = 0;
        int x=n;
        while (x > 0) {
            res = res*10+ x % 10;
            x /= 10;
        }

        return Math.abs(n-res);
    }
}