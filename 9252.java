// https://www.acmicpc.net/problem/9252
// BOJ 9252 LCS 2

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static String s1, s2;
    static int n, m, dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        s1 = scan.readLine();
        s2 = scan.readLine();
        n = s1.length();
        m = s2.length();
        
        // solution
        long startTime = System.currentTimeMillis();
        dp = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(j != 0 && dp[i][j-1] == i+1) // already max
                    dp[i][j] = dp[i][j-1];
                else if(s2.charAt(j) == s1.charAt(i))
                    dp[i][j] = (j==0 || i==0 ? 0 : dp[i-1][j-1]) + 1;
                else
                    dp[i][j] = Math.max((j==0 ? 0 : dp[i][j-1]), 
                                        (i==0 ? 0 : dp[i-1][j]));
            }
        }
        //printLCS();
        System.out.println(dp[n-1][m-1]);
        if(dp[n-1][m-1] == 0) return;
        System.out.println(getLCS());
        //System.out.println(System.currentTimeMillis()-startTime+"ms");
    }

    public static String getLCS(){
        StringBuilder sb = new StringBuilder();
        int x = n-1, y = m-1;
        while(x >= 0 && y >= 0){
            if(y == 0){
                if(dp[x][y] == 1){
                    sb.insert(0, s2.charAt(y));
                    return sb.toString();
                }
                return sb.toString();
            }
            else if(dp[x][y] == dp[x][y-1]){
                y--;
            }
            else{
                sb.insert(0, s2.charAt(y));
                int i;
                for(i=x; ; i--){
                    if(i == 0 || s1.charAt(i) == s2.charAt(y))
                        break;
                }
                x = i-1;
                y--;
            }
        }
        return sb.toString();
    }

    public static void printLCS(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                sb.append(s1.substring(0, i+1)).append('+').append(s2.substring(0, j+1))
                .append('=').append(dp[i][j]).append('\n');
            }
        }
        System.out.println(sb);
    }
}
