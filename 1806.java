// https://www.acmicpc.net/problem/1806
// 부분합

import java.util.*;
import java.util.stream.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, s, arr[], dp[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        dp = new int[n];
        st = new StringTokenizer(reader.readLine());
        dp[0] = Integer.parseInt(st.nextToken());
        for(int i=1; i<n; i++){
            dp[i] = dp[i-1] + Integer.parseInt(st.nextToken());
        }

        int start = 0, end = 0, min = Integer.MAX_VALUE;
        while(true){
            int val = get(start, end);
            //System.out.println(start+", "+end+": "+val);
            if(val == -1) break;
            if(val < s) end++;
            else{
                min = Math.min(min, end - start + 1);
                start++;
            }
        }
        if(min == Integer.MAX_VALUE)
            System.out.println(0);
        else System.out.println(min);
    }

    public static int get(int start, int end){
        if(end >= n || start >= n || start > end)
            return -1;
        if(start == 0) return dp[end];
        else return dp[end] - dp[start-1];
    }
}
