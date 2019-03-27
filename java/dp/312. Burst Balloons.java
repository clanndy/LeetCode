// Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
// Find the maximum coins you can collect by bursting the balloons wisely.
// Note:
// You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
// 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
// Example:
// Input: [3,1,5,8]
// Output: 167 
// Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
//              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167



//idea: dp[i][j] = maximum coins if only bursting balloons from i-th to j-th 
// then consider the index of last bursted balloons, mid, with i <= mid <= j, we have
// dp[i][j] = max_mid{dp[i][mid-1] + dp[mid+1][j] + nums[i-1]*nums[mid]*nums[j+1]}


class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;  
        
        int[][] dp = new int[n][n];
        for(int diff = 0; diff < n; diff++) {
            for(int i = 0; i < n-diff; i++) {
                int j = i + diff;
                for(int mid = i; mid <= j; mid++) {
                    int cur = (i!=0?nums[i-1]:1) * nums[mid] * (j!=(n-1)?nums[j+1]:1);
                    if(mid != i)
                        cur += dp[i][mid-1];
                    if(mid != j)
                        cur += dp[mid+1][j];
                    dp[i][j] = Math.max(dp[i][j], cur);
                }  
            }
        }
        return dp[0][n-1];
    }
}