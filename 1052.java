// https://www.acmicpc.net/problem/1052
// BOJ 1052 물병

import java.util.*;
import java.io.*;
import java.math.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] hand = new int[k];
        for(int i=0; i<k; i++){
            int l = get_biggist(n);
            hand[i] = l;
            n -= l;
            if(n <= 0){
                System.out.println(0);
                return;
            }
        }
        int answer = hand[k-1] - n;
        System.out.println(answer);
    }
  
    public static int get_biggist(int n){
        int pow = 1;
        while(pow < n){
            pow *= 2;
        }
        if(pow == n) return pow;
        else return pow/2;
    }
}
