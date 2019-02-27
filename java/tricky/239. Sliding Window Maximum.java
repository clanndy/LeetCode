// Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
//You can only see the k numbers in the window. Each time the sliding window moves right by one position.
// Return the max sliding window.
// Example:
// Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
// Output: [3,3,5,5,6,7] 
// Explanation: 

// Window position                Max
// ---------------               -----
// [1  3  -1] -3  5  3  6  7       3
//  1 [3  -1  -3] 5  3  6  7       3
//  1  3 [-1  -3  5] 3  6  7       5
//  1  3  -1 [-3  5  3] 6  7       5
//  1  3  -1  -3 [5  3  6] 7       6
//  1  3  -1  -3  5 [3  6  7]      7


// result[i] = max(nums[i-k+1],..., nums[i])


//idea1: Use a treemap to store k numbers in a window. When the window moves, remove the oldest number and add the new number in the treemap
//       Time  O(n * logk)

//idea2: Break the array in to n/k blocks with each block containing k numbers.
//       For each number, compute maximum number to its right (including itself) in the same block.  Get array right[n].
//       Similarily, for each number, compute maximum number to its right (including itself) in the same block. Get array left[n].
//       result[i] = max(right[i], left[i-k+1])
//       Time O(n)

//idea3: Observe that there is no need to memorize the whole window.
//       Because if i < j and nums[i] < nums[j], and now the window moves to nums[j], we can just ignore nums[i] 
//          since nums[j] is always "better" and we will never choose nums[i] later.
//       So the window maintained is a decreasing array, and the maximum number in the window is always the first one.
//       Time O(n)



//implementation of idea1
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        if(n == 0 || k == 0) return new int[0];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        for(int i = 0; i < k; i++)
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        result[0] = map.lastKey();
        for(int i = k; i < n; i++) {
            int count = map.get(nums[i-k]);
            if(count == 1)
                map.remove(nums[i-k]);
            else
                map.put(nums[i-k], count - 1);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            result[i-k+1] = map.lastKey();
        }
        return result;
    }
}