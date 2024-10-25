// https://www.acmicpc.net/problem/11066
// 파일 합치기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int n, arr[], memo[][];
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        for(int T=0; T<t; T++){
            n = Integer.parseInt(reader.readLine());
            arr = new int[n];
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for(int i=0; i<n; i++)
                arr[i] = Integer.parseInt(st.nextToken());
            for(int i=1; i<n; i++)
                arr[i] += arr[i-1];
            
            memo = new int[n][n];
            for(int i=n-1; i>=0; i--){
                for(int j=i; j<n; j++){
                    if(j == i) memo[i][j] = 0;
                    else if(j == i+1) memo[i][j] = getSum(i, j);
                    else{
                        int min = Integer.MAX_VALUE;
                        for(int k=i; k<j; k++)
                            min = Math.min(min, memo[i][k]+memo[k+1][j]);
                        memo[i][j] = min + getSum(i, j);
                    }
                }
            }
            //debug();
            sb.append(memo[0][n-1]).append('\n');
        }
        System.out.println(sb);
    }

    private static int getSum(int from, int to){
        if(from == 0) return arr[to];
        else return arr[to] - arr[from-1];
    }

    private static void debug(){
        for(int i=0; i<n; i++)
            System.out.print(arr[i]+" ");
        System.out.println();
        for(int i=0; i<memo.length; i++){
            for(int j=0; j<memo[0].length; j++)
                System.out.print(memo[i][j]+" ");
            System.out.println();
        }System.out.println();
    }
}
