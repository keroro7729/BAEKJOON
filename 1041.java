// https://www.acmicpc.net/problem/1041
// BOH 1041 주사위

import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(scan.readLine());
        StringTokenizer st = new StringTokenizer(scan.readLine());
        int[] dice = new int[6];
        int max = 0, sum = 0;
        for(int i=0; i<6; i++){
            dice[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, dice[i]);
            sum += dice[i];
        }
        if(n == 1){
            System.out.println(sum-max);
            return;
        }

        int min1 = get_min1(dice);
        int min2 = get_min2(dice);
        int min3 = get_min3(dice);
        //System.out.println(min1+", "+min2+", "+min3);//

        //long result = (5*n*n-16*n+12)*min1 + (8*n-12)*min2 + 4*min3;
        BigInteger result = BigInteger.valueOf(n);
        result = result.multiply(BigInteger.valueOf(n));
        result = result.multiply(new BigInteger("5"));
        result = result.add(BigInteger.valueOf(n*-16));
        result = result.add(new BigInteger("12"));
        result = result.multiply(BigInteger.valueOf((long)min1));
        result = result.add(BigInteger.valueOf((8*n-12)*min2 + 4*min3));
        
        System.out.println(result);
    }

    public static int get_min1(int[] dice){
        int min = Integer.MAX_VALUE;
        for(int i=0; i<6; i++)
            min = Math.min(min, dice[i]);
        return min;
    }

    static int[][] graph = {
        {0,1,1,1,1,0},
        {1,0,1,1,0,1},
        {1,1,0,0,1,1},
        {1,1,0,0,1,1},
        {1,0,1,1,0,1},
        {0,1,1,1,1,0}
    };
    public static int get_min2(int[] dice){
        int min = Integer.MAX_VALUE;
        for(int i=0; i<6; i++){
            for(int j=i+1; j<6; j++){
                if(graph[i][j] == 1)
                    min = Math.min(min, dice[i]+dice[j]);
            }
        }
        return min;
    }
    public static int get_min3(int[] dice){
        int min = Integer.MAX_VALUE;
        for(int i=0; i<6; i++){
            for(int j=i+1; j<6; j++){
                for(int k=j+1; k<6; k++){
                    if(graph[i][j]==1 && graph[j][k]==1 && graph[i][k]==1)
                        min = Math.min(min, dice[i]+dice[j]+dice[k]);
                }
            }
        }
        return min;
    }
}
