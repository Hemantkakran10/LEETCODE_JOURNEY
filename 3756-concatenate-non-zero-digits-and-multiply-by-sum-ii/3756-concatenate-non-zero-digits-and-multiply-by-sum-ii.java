class Solution {
    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        // Store only non-zero digits and their original indices.
        ArrayList<int[]> ind = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0')
                ind.add(new int[]{i, s.charAt(i) - '0'});
        }

        int m = ind.size();

        // last[i] = last non-zero digit index (in compressed array)
        int[] last = new int[n];
        int j = -1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0')
                j++;
            last[i] = j;
        }

        // nxt[i] = next non-zero digit index (in compressed array)
        int[] nxt = new int[n];
        j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) != '0')
                j--;
            nxt[i] = j + 1;
            if (nxt[i] >= m)
                nxt[i] = -1;
        }

        // Prefix sum of digits.
        long[] prefSum = new long[m];
        long sum = 0;
        for (int i = 0; i < m; i++) {
            sum += ind.get(i)[1];
            prefSum[i] = sum;
        }

        // Powers of 10 modulo MOD.
        long[] pow10 = new long[m + 1];
        pow10[0] = 1;
        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // Prefix number modulo MOD.
        long[] prefNum = new long[m];
        long cur = 0;
        for (int i = 0; i < m; i++) {
            cur = (cur * 10 + ind.get(i)[1]) % MOD;
            prefNum[i] = cur;
        }

        int[] ans = new int[queries.length];

        for (int k = 0; k < queries.length; k++) {
            int l = nxt[queries[k][0]];
            int r = last[queries[k][1]];

            if (l == -1 || r == -1 || l > r) {
                ans[k] = 0;
                continue;
            }

            long digitSum = prefSum[r] - (l > 0 ? prefSum[l - 1] : 0);

            long num = prefNum[r];
            if (l > 0) {
                num = (num - prefNum[l - 1] * pow10[r - l + 1]) % MOD;
                if (num < 0)
                    num += MOD;
            }

            ans[k] = (int)((num * digitSum) % MOD);
        }

        return ans;
    }
}