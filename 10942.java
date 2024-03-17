// https://www.acmicpc.net/problem/10942
// BOJ 10942 팰린드롬?

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, arr[], m, s, e, dp[][];

    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(scan.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(scan.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // solution
        long startTime = System.currentTimeMillis();
        dp = new int[n][n];
        for(int i=0; i<n; i++){
            dp[i][i] = 1;
            for(int j=1; check(i-j, i+j); j++){
                if(arr[i-j] == arr[i+j])
                    dp[i-j][i+j] = 1;
                else break;
            }
            if(i+1 < n && arr[i] == arr[i+1]){
                dp[i][i+1] = 1;
                for(int j=1; check(i-j, i+1+j); j++){
                    if(arr[i-j] == arr[i+1+j])
                        dp[i-j][i+1+j] = 1;
                    else break;
                }
            }
        }

        m = Integer.parseInt(scan.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(scan.readLine());
            s = Integer.parseInt(st.nextToken())-1;
            e = Integer.parseInt(st.nextToken())-1;
            sb.append(dp[s][e]).append('\n');
        }
        System.out.println(sb);
        //System.out.println(System.currentTimeMillis()-startTime+"ms");
    }

    public static boolean check(int a, int b){
        if(a < 0) return false;
        if(b >= n) return false;
        return true;
    }
}
