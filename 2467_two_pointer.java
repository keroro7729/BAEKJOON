// https://www.acmicpc.net/problem/2467
// 용액

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int n, arr[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine());
        arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int start = 0, end = n-1, min = Integer.MAX_VALUE, ans1 = -1, ans2 = -1;
        while(true){
            if(start >= end) break;
            int val = arr[start]+arr[end];
            if(Math.abs(val) < min){
                min = Math.abs(val);
                ans1 = arr[start];
                ans2 = arr[end];
            }
            if(val < 0) start++;
            else end--;
        }
        System.out.println(ans1+" "+ans2);
    }
}
