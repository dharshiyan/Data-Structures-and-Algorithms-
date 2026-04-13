class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n <= 1) return s;

        // dp[i][j] will be true if substring s[i...j] is a palindrome
        boolean[][] dp = new boolean[n][n];
        int startIndex = 0;
        int maxLen = 1;

        // All substrings of length 1 are palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // Check for substrings of length 2
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                startIndex = i;
                maxLen = 2;
            }
        }

        // Check for lengths greater than 2
        // k is the current length of the substring we are checking
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                // Get the ending index of the substring from starting index i
                int j = i + len - 1;

                // Condition for s[i...j] to be a palindrome:
                // 1. Front and back characters must match
                // 2. The inner substring s[i+1...j-1] must also be a palindrome
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    
                    if (len > maxLen) {
                        startIndex = i;
                        maxLen = len;
                    }
                }
            }
        }

        return s.substring(startIndex, startIndex + maxLen);
    }
}