// https://www.acmicpc.net/problem/1300
// K번째 수

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int n, k;
    private static final int MAX = 1000000000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        k = Integer.parseInt(reader.readLine());

        int start = 1, end = k;
        int mid = -1, index, answer = k;
        while(start+1 < end){
            mid = start + (end - start)/2;
            index = getMaxIndexOf(mid);
            if(index < k){ // cant be answer, setup start
                start = mid;
            }
            else if(index >= k){ // can be answer, but should search min
                answer = Math.min(answer, mid);
                if(end == mid) break;
                end = mid;
            }
        }
        if(getMaxIndexOf(start) >= k)
            answer = Math.min(answer, start);
        if(getMaxIndexOf(end) >= k)
            answer = Math.min(answer, end);
        System.out.println(answer);
    }

    private static int getMaxIndexOf(int value){
        int result = 0;
        for(int i=Math.min(n, value); i>=1; i--)
            result += Math.min(n, value/i);
        return result;
    }
}
