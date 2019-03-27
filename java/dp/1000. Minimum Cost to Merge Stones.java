// There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.
// A move consists of merging exactly K consecutive piles into one pile, and the cost of this move is equal to the total number of stones in these K piles.
// Find the minimum cost to merge all piles of stones into one pile.  If it is impossible, return -1.
 
// Example 1:
// Input: stones = [3,2,4,1], K = 2
// Output: 20
// Explanation: 
// We start with [3, 2, 4, 1].
// We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
// We merge [4, 1] for a cost of 5, and we are left with [5, 5].
// We merge [5, 5] for a cost of 10, and we are left with [10].
// The total cost was 20, and this is the minimum possible.
// Example 2:
// Input: stones = [3,2,4,1], K = 3
// Output: -1
// Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.  So the task is impossible.
// Example 3:
// Input: stones = [3,5,1,2,6], K = 3
// Output: 25
// Explanation: 
// We start with [3, 5, 1, 2, 6].
// We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
// We merge [3, 8, 6] for a cost of 17, and we are left with [17].
// The total cost was 25, and this is the minimum possible.
 
// Note:
// 1 <= stones.length <= 30
// 2 <= K <= 30
// 1 <= stones[i] <= 100


// idea: dp[i][j][m] = minimum cost to merge stones from i-th to j-th to m piles.
// dp[i][j][1] = dp[i][j][k] + presum[j] - presum[i-1] (if j - i >= 1)
// dp[i][j][m] = min{dp[i][mid][1] + dp[mid+1][j][m-1]}


class Solution {
    public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        if((n-1) % (K-1) != 0) return -1;
        int[] presum = new int[n];
        int[][][] dp = new int[n][n][K+1];
        
        for(int i = 0; i < n; i++) 
            for(int j = 0; j < n; j++)
                for(int m = 0; m <= K; m++)
                    dp[i][j][m] = -1;
        for(int i = 0; i < n; i++) {
            presum[i] = ((i == 0) ?0:presum[i-1]) + stones[i];
            dp[i][i][1] = 0;
        }
        for(int diff = 1; diff <= n-1; diff++) {
            for(int i = 0; i < n-diff; i++) {
                int j = i + diff;
                for(int m = 2; m <= K; m++) {
                    if(m > (diff + 1) || (diff + 1 - m) % (K-1) != 0) {
                        dp[i][j][m] = -1;
                    } else {
                        dp[i][j][m] = Integer.MAX_VALUE;
                        for(int mid = i; mid < j; mid += (K-1)) {
                            int left = dp[i][mid][1];
                            int right = dp[mid+1][j][m-1];
                            if(left >= 0 && right >= 0)
                                dp[i][j][m] = Math.min(dp[i][j][m], left + right);
                        }
                        if(dp[i][j][m] == Integer.MAX_VALUE)
                            dp[i][j][m] = -1;
                    }
                }
                dp[i][j][1] = dp[i][j][K] + presum[j] - ((i==0)?0:presum [i-1]);
            }
        }
        return dp[0][n-1][1];
    }
    
}

