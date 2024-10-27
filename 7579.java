// https://www.acmicpc.net/problem/7579
// 앱

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static final int MAX_SIZE = 10001;
    private static int n, m, mem[], cost[], dp[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        mem = new int[n];
        cost = new int[n];
        StringTokenizer st1 = new StringTokenizer(reader.readLine());
        StringTokenizer st2 = new StringTokenizer(reader.readLine());
        int sum = 0;
        for(int i=0; i<n; i++){
            mem[i] = Integer.parseInt(st1.nextToken());
            cost[i] = Integer.parseInt(st2.nextToken());
            sum += cost[i];
        }

        dp = new int[MAX_SIZE];
        for(int i=0; i<n; i++){
            for(int c=sum; c>=cost[i]; c--){
                dp[c] = Math.max(dp[c], dp[c-cost[i]]+mem[i]);
            }
        }
        //print(dp);

        int start = 0, end = sum, mid;
        while(start+1 < end){
            mid = (start + end) / 2;
            if(dp[mid] < m)
                start = mid;
            else end = mid;
        }
        int answer = Integer.MAX_VALUE;
        if(dp[start] >= m) answer = Math.min(answer, start);
        if(dp[end] >= m) answer = Math.min(answer, end);
        System.out.println(answer);
    }

    private static void print(int[] arr){
        for(int i=0; i<arr.length; i++)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
}
