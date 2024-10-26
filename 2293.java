// https://www.acmicpc.net/problem/2293
// 동전 1

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int n, k, arr[], memo[][];
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];
        memo = new int[n][k+1];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(reader.readLine());
            Arrays.fill(memo[i], -1);
        }
        System.out.println(getMemo(n-1, k));
    }

    private static int getMemo(int idx, int val){
        if(val < 0) return 0;
        else if(memo[idx][val] != -1) return memo[idx][val];
        else if(val == 0) return memo[idx][val] = 1;
        else if(idx == 0){
            if(val % arr[idx] == 0)
                return memo[idx][val] = 1;
            else return memo[idx][val] = 0;
        }

        memo[idx][val] = getMemo(idx-1, val) + getMemo(idx, val-arr[idx]);
        return memo[idx][val];
    }
}
/*
k원을 만드는 경우의 수 = 
x를 사용하지 않고 k원을 만드는 경우의 수 +
k-x원을 만드는 경우의 수

memo[n][m] = 0~n-1까지의 동전을 사용해서 m원을 만드는 경우의 수
memo[n][m] = memo[n-1][m] + memo[n][m-arr[n]]
*/
