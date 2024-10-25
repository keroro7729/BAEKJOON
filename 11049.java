// https://www.acmicpc.net/problem/11049
// 행렬 곱셈 순서

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int n, memo[][];
    private static Matrix[] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        arr = new Matrix[n];
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[i] = new Matrix(r, c);
        }

        memo = new int[n][n];
        for(int i=n-1; i>=0; i--){
            for(int j=i; j<n; j++){
                if(j == i) 
                    memo[i][j] = 0;
                else if(j == i+1) 
                    memo[i][j] = arr[i].r * arr[i].c * arr[j].c;
                else{
                    memo[i][j] = Integer.MAX_VALUE;
                    for(int k=i; k<j; k++){
                        memo[i][j] = Math.min(memo[i][j],
                            memo[i][k] + memo[k+1][j] +
                            arr[i].r * arr[k].c * arr[j].c
                        );
                    }
                }
            }
        }
        System.out.println(memo[0][n-1]);
    }
}

class Matrix{
    int r, c;
    public Matrix(int r, int c){
        this.r = r;
        this.c = c;
    }
}
