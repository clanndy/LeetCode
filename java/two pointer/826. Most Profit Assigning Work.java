// We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i] is the profit of the ith job. 
// Now we have some workers. worker[i] is the ability of the ith worker, which means that this worker can only complete a job with difficulty at most worker[i]. 
// Every worker can be assigned at most one job, but one job can be completed multiple times.
// For example, if 3 people attempt the same job that pays $1, then the total profit will be $3.  If a worker cannot complete any job, his profit is $0.
// What is the most profit we can make?
// Example 1:
// Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
// Output: 100 
// Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get profit of [20,20,30,30] seperately.
// Notes:
// 1 <= difficulty.length = profit.length <= 10000
// 1 <= worker.length <= 10000
// difficulty[i], profit[i], worker[i]  are in range [1, 10^5]

// idea1: Sort jobs by increasing order of difficulty, and change the profit of any job to be the maximum profit among jobs with less or equaldifficulty.
//        This is because in the problem we are given a difficulty, and are required to compute the maximum profit under that difficulty.
//        Also sort workers by increasing order of difficulty.
//        Finally using two pointers, one pointing to jobs, the other pointer to workers, to compute maximum profit of workers
//        Time O(nlogn + mlogm)

// idea2: dp[i] = maximum profit of a job with difficulty of that job <= i.  (1 <= i <= 10^5)
//        Computing of dp[i] involves puting jobs to it and iterating it for making sure it is increasing. 
//        Time O(max difficulty of jobs and workers)

class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int[][] job = new int[n][2];
        for(int i = 0; i < n; i++) {
            job[i][0] = difficulty[i];
            job[i][1] = profit[i];
        }
        Arrays.sort(job, (a, b)->((a[0] == b[0])?(b[1]-a[1]):(a[0]-b[0])));
        for(int i = 1; i < n; i++) {
            if(job[i][1] < job[i-1][1])
                job[i][1] = job[i-1][1];
        }
        Arrays.sort(worker);
        
        
        int result = 0;
        int i = 0;
        for(int work : worker) {
            while(i < n && job[i][0] <= work) i++;
            if(i != 0) result += job[i-1][1];
        }
        
        return result;
    }
}