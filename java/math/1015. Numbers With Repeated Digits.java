// Given a positive integer N, return the number of positive integers less than or equal to N that have at least 1 repeated digit.
 
// Example 1:
// Input: 20
// Output: 1
// Explanation: The only positive number (<= 20) with at least 1 repeated digit is 11.
// Example 2:
// Input: 100
// Output: 10
// Explanation: The positive numbers (<= 100) with atleast 1 repeated digit are 11, 22, 33, 44, 55, 66, 77, 88, 99, and 100.
// Example 3:
// Input: 1000
// Output: 262
 
// Note:
// 1 <= N <= 10^9


//idea: Count res the Number Without Repeated Digit, then the number with repeated digits = N - res
// if N = 8765, L = [8,7,6,6],
// the number without repeated digit can the the following format:
// XXX
// XX
// X
// 1XXX ~ 7XXX
// 80XX ~ 86XX
// 870X ~ 875X
// 8760 ~ 8765



class Solution {
    public int numDupDigitsAtMostN(int N) {
        int count = 0;
        N++;
        int[] digits = toDigitsArray(N);
        int n = digits.length;
        for(int i = 0; i < n-1; i++) {
            int cur = 9;
            for(int j = 0; j < i; j++) {
                cur *= 9-j;
            }
            count += cur;
        }
        
        
        boolean[] visited = new boolean[10];
        for(int i = 0; i < n; i++) {
            //0,1,..,i-1 number = digits[0], digits[1],...digits[i-1]
            //i-th number < digits[i], and i+1, i+2, ..., n-1 number can be arbitrary value different from 0-i-th
            int start = 0;
            if(i != 0) {
                for(int j = 0; j < digits[i]; j++)
                    if(!visited[j]) start++;
            }
            else {
                for(int j = 1; j < digits[i]; j++)
                    if(!visited[j]) start++;
            }
            for(int j = i+1; j < n; j++) 
                start *= 10-j;
            count += start;
            if(visited[digits[i]]) break;
            visited[digits[i]] = true;
        }
        return N-1-count;
    }
    
    private int[] toDigitsArray(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        while(n > 0) {
            list.add(n % 10);
            n /= 10;
        }
        
        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(list.size() - i - 1);
        }
        return res;
    }
}


