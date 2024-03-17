// https://www.acmicpc.net/problem/1043
// BOJ 1043 거짓말

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());

        // input
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(scan.readLine());
        int count = Integer.parseInt(st.nextToken());
        Set<Integer> tset = new HashSet<>();
        for(int i=0; i<count; i++){
            tset.add(Integer.parseInt(st.nextToken()));
        }
        Set<Integer>[] partys = new Set[m];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(scan.readLine());
            partys[i] = new HashSet<>();
            count = Integer.parseInt(st.nextToken());
            for(int j=0; j<count; j++){
                partys[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        // solution
        boolean[] trueParty = new boolean[m];
        boolean end = false;
        while(!end){
            end = true;
            Set<Integer> tmp;
            for(int i=0; i<m; i++){
                if(trueParty[i]) continue;
                tmp = new HashSet<>(tset);
                tmp.retainAll(partys[i]);
                if(!tmp.isEmpty()){ // 교집합이 존재한다면
                    trueParty[i] = true;
                    tset.addAll(partys[i]);
                    end = false;
                }
            }
        }

        // output
        count = 0;
        for(int i=0; i<m; i++){
            if(!trueParty[i]) count++;
        }
        System.out.println(count);
    }

}
