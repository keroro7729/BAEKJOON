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

        int min = Integer.MAX_VALUE;
        int ans1 = -1, ans2 = -1;
        for(int i=0; i<n-1; i++){
            int idx = bsearch(i);
            int val = Math.abs(arr[i]+arr[idx]);
            if(val < min){
                min = val;
                ans1 = arr[i];
                ans2 = arr[idx];
            }
        }
        System.out.println(ans1+" "+ans2);
    }

    private static int bsearch(int std){
        int start = std+1, end = n-1, mid, val = arr[std] * -1;
        while(start+1 < end){
            mid = (start + end) / 2;
            if(arr[mid] < val)
                start = mid;
            else end = mid;
        }
        int val1 = Math.abs(arr[std]+arr[start]);
        int val2 = Math.abs(arr[std]+arr[end]);
        return val1 < val2 ? start : end;
    }
}
