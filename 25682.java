// https://www.acmicpc.net/problem/25682
// 체스판 다시 칠하기 2

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int n, m, k;
    private static int[][] bwMemo, wbMemo;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        bwMemo = new int[n+1][m+1];
        wbMemo = new int[n+1][m+1];
        for(int i=1; i<=n; i++){
            String line = reader.readLine();
            for(int j=1; j<=m; j++){
                bwMemo[i][j] = bwMemo[i-1][j] + bwMemo[i][j-1] - bwMemo[i-1][j-1];
                wbMemo[i][j] = wbMemo[i-1][j] + wbMemo[i][j-1] - wbMemo[i-1][j-1];
                if(i%2 == j%2){
                    bwMemo[i][j] += line.charAt(j-1) == 'B' ? 0 : 1;
                    wbMemo[i][j] += line.charAt(j-1) == 'W' ? 0 : 1;
                }
                else{
                    bwMemo[i][j] += line.charAt(j-1) == 'W' ? 0 : 1;
                    wbMemo[i][j] += line.charAt(j-1) == 'B' ? 0 : 1;
                }
            }
        }
        //print(bwMemo);
        //print(wbMemo);

        int min = Integer.MAX_VALUE;
        for(int i=1; i<=n-k+1; i++){
            for(int j=1; j<=m-k+1; j++){
                min = Math.min(getBWsquare(i, j, i+k-1, j+k-1), min);
                min = Math.min(getWBsquare(i, j, i+k-1, j+k-1), min);
            }
        }
        System.out.println(min);
    }

    private static int getSquare(int[][] arr, int starti, int startj, int endi, int endj){
        return arr[endi][endj] - arr[starti-1][endj] - arr[endi][startj-1] + arr[starti-1][startj-1];
    }
    private static int getBWsquare(int a, int b, int c, int d){
        return getSquare(bwMemo, a, b, c, d);
    }
    private static int getWBsquare(int a, int b, int c, int d){
        return getSquare(wbMemo, a, b, c, d);
    }

    private static void print(int[][] arr){
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[0].length; j++){
                System.out.print(arr[i][j]+" ");
            }System.out.println();
        }System.out.println();
    }
}
