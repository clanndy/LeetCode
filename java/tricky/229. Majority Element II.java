// https://leetcode.com/problems/majority-element-ii/
// Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
// Note: The algorithm should run in linear time and in O(1) space.
// Example 1:
// Input: [3,2,3]
// Output: [3]
// Example 2:
// Input: [1,1,1,3,3,2,2,2]
// Output: [1,2]


//idea: Boyer-Moore Majority Vote algorithm


class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int a = -1, b = -1, counta = 0, countb = 0;
        for(int i : nums) {
            if(a == i) {
                counta++;
            } else if(b == i)
                countb++;
            else if(counta == 0) {
                a = i;
                counta++;
            } else if(countb == 0) {
                b = i;
                countb++;
            } else {
                counta--;
                countb--;
            }
        }
        counta = 0;
        countb = 0;
        for(int i : nums) {
            if(a == i)
                counta++;
            else if(b == i)
                countb++;
        }
        List<Integer> result = new ArrayList<>();
        if(counta > nums.length/3) result.add(a);
        if(countb > nums.length/3) result.add(b);
        return result;
    }
}