class Solution {

    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public int largestPrime(int n) {
        if (n < 2) return 0;

        int ans = 2, sum = 2, m = 3;

        while (sum + m <= n) {
            if (isPrime(m)) {
                sum += m;
                if (isPrime(sum)) ans = sum;
            }
            m += 2;
        }
        return ans;
    }
}