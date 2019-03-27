// Given a binary string S (a string consisting only of '0' and '1's) and a positive integer N, return true if and only if for every integer X from 1 to N, the binary representation of X is a substring of S.
 
// Example 1:
// Input: S = "0110", N = 3
// Output: true
// Example 2:
// Input: S = "0110", N = 4
// Output: false
 
// Note:
// 1 <= S.length <= 1000
// 1 <= N <= 10^9






//idea 1:  check if S contains N, then check if S contains N-1,  and so on.
// (1) Because S.length <= 1000 is too short,  S contains maximumly 1000 distinct consecutive digits with length 10.
//     That is to say, N should be less than 2024, otherwise we should return false directly.
// (2)  For every i < N/2, the binary string of 2*i will contain binary string of i. Thus we don't need to check for i < N/2

public boolean queryString(String S, int N) {
    for (int i = N; i > N / 2; --i)
        if (!S.contains(Integer.toBinaryString(i)))
            return false;
    return true;
}

//idea 2: S contains S.length * S.length substrings, if N is greater than S.length * S.length we can return false directly.
//  Then check numbers from 1 to S.length * S.length.

class Solution {
    public boolean queryString(String S, int N) {
        int n = S.length();
        int max = n * n;
        if(N > max) return false;
        boolean[] valid = new boolean[max+1];
        for(int i = 0; i < n; i++) {
            int cur = 0;
            for(int j = i; j < n && j < i+30; j++) {
                cur = (cur << 1) + (int)(S.charAt(j) - '0');
                if(cur <= max)
                    valid[cur] = true;
                else
                    break;
            }
        }
        for(int i = 1; i <= N; i++)
            if(!valid[i])
                return false;
        return true;
    }
}