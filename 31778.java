// https://www.acmicpc.net/problem/31778
// PPC 만들기

import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

class Main {
    static int n, k, arr[];
    static final int P = 1, C = 2;
    
    public static void main(String[] args) throws IOException {
        for(int i=0; i<1; i++){input();
        solution();}
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void input() throws IOException {
        st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        String input = reader.readLine();
        arr = new int[n];
        for(int i=0; i<n; i++){
            if(input.charAt(i) == 'P') arr[i] = P;
            else if(input.charAt(i) == 'C') arr[i] = C;
        }
    }

    public static void solution(){
        // operation k times
        int start = 0, end = n-1;
        while(k > 0){
            while(start < n && arr[start] == P) start++;
            while(end >= 0 && arr[end] == C) end--;
            if(start >= n || end < 0 || start >= end) break;
            arr[start] = P;
            arr[end] = C;
            k--;
        }
        //print(arr);

        // count PPC substring
        BigInteger answer = BigInteger.ZERO;
        int dp[] = new int[n];
        if(arr[0] == P) dp[0] = 1;
        for(int i=1; i<n; i++){
            if(arr[i] == P) dp[i] = dp[i-1] + 1;
            else if(arr[i] == C){
                dp[i] = dp[i-1];
                BigInteger adder = BigInteger.valueOf((long)dp[i] * (dp[i] - 1) / 2);
                answer = answer.add(adder);
            }
        }
        System.out.println(answer);
    }

    public static void print(int[] arr){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<arr.length; i++){
            if(arr[i] == P) sb.append('P');
            else if(arr[i] == C) sb.append('C');
        }System.out.println(sb);
    }
}
