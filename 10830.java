// https://www.acmicpc.net/problem/10830
// 행렬 제곱

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int n, arr[][], count = 0;
    private static long b;
    private static final int MASK = 1000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        b = Long.parseLong(st.nextToken());
        arr = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(reader.readLine());
            for(int j=0; j<n; j++)
                arr[i][j] = Integer.parseInt(st.nextToken()) % MASK;
        }

        //long stime = System.currentTimeMillis();
        int[][] answer = pPow(arr, b);
        print(answer);
        //System.out.println("iter: "+count+", "+(System.currentTimeMillis()-stime)+"ms");
    }

    private static int[][] pPow(int[][] arr, long p){
        //count++;
        if(p == 1) return arr;
        int[][] result = pPow(arr, p/2);
        result = pMul(result, result);
        if(p%2 == 1)
            return pMul(result, arr);
        else return result;
    }

    private static int[][] pMul(int[][] arr, int[][] brr){
        int[][] result = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                int element = 0;
                for(int k=0; k<n; k++)
                    element += arr[i][k] * brr[k][j];
                result[i][j] = element % MASK;
            }
        }
        return result;
    }
    
    private static void print(int[][] arr){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[0].length; j++){
                sb.append(arr[i][j]).append(' ');
            }sb.append('\n');
        }System.out.println(sb);
    }
}
