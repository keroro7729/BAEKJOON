// https://www.acmicpc.net/problem/31631
// :rightplant:

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, arr[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(scan.readLine());
        arr = new int[n];
        int val = n;
        for(int i=0; i<n/2; i++){
            if(i%2 == 0){
                arr[n-1-i] = val--;
                arr[i] = val--;
            }
            else{
                arr[i] = val--;
                arr[n-1-i] = val--;
            }
        }
        if(n%2 == 1)
            arr[n/2] = 1;

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            sb.append(arr[i]).append(' ');
        }
        System.out.println(sb);
    }
}
