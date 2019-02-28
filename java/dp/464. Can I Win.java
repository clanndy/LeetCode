// In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins. 
// What if we change the game so that players cannot re-use integers? 
// For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.
// Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally. 
// You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300. 
// Example 
// Input:
// maxChoosableInteger = 10
// desiredTotal = 11

// Output:
// false

// Explanation:
// No matter which integer the first player choose, the first player will lose.
// The first player can choose an integer from 1 up to 10.
// If the first player choose 1, the second player can only choose integers from 2 up to 10.
// The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
// Same with other integers chosen by the first player, the second player will always win.



//idea: exausively visit every subproblem, where each subproblem is that "given a set of integers, determine if the first player can win".
//      The set of integers can be stored using only an 20-bit integer, and the results of all subproblems can be stored in a hashmap.
//      Time  O(2 ^ maxChoosableInteger)



class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
        Map<Integer, Boolean> map = new HashMap<>();
        map.put(0, false);
        return dfs(maxChoosableInteger, (1 << maxChoosableInteger) - 1, desiredTotal, map);
    }
    
    private boolean dfs(int maxChoosableInteger, int state, int desiredTotal, Map<Integer, Boolean> map) {
        if(map.containsKey(state)) return map.get(state);
        Boolean result = false;
        for(int i = maxChoosableInteger; i >= 1; i--) {
            if((state & (1 << (i - 1))) == 0) continue;
            if(i >= desiredTotal || !dfs(maxChoosableInteger, state & (~(1 << (i-1))), desiredTotal - i, map)) {
                result = true;
                break;
            }
        }
        map.put(state, result);
        return result;
    }
}