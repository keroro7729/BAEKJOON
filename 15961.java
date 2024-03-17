// https://www.acmicpc.net/problem/15961
// BOJ 15961 회전 초밥

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());
        int n = Integer.parseInt(st.nextToken()); // 접시 갯수
        int d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰번호
        int belt[] = new int[n], count = 0;
        Map<Integer, Integer> cur = new HashMap<>();
        cur.put(c, 1);
        for(int i=0; i<n; i++){
            belt[i] = Integer.parseInt(scan.readLine());
            if(i >= k){
                if(cur.get(belt[i-k]) == 1)
                    cur.remove(belt[i-k]);
                else
                    cur.put(belt[i-k], cur.get(belt[i-k])-1);
            }
            if(cur.containsKey(belt[i]))
                cur.put(belt[i], cur.get(belt[i])+1);
            else
                cur.put(belt[i], 1);
            count = Math.max(count, cur.size());
        }
        for(int i=0; i<k; i++){
            if(cur.get(belt[n-k+i]) == 1)
                cur.remove(belt[n-k+i]);
            else
                cur.put(belt[n-k+i], cur.get(belt[n-k+i])-1);
            if(cur.containsKey(belt[i]))
                cur.put(belt[i], cur.get(belt[i])+1);
            else
                cur.put(belt[i], 1);
            count = Math.max(count, cur.size());
        }
        System.out.println(count);
    }
}
