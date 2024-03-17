// https://www.acmicpc.net/problem/17298
// BOJ 17298 오큰수

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, arr[], answer[];

    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(scan.readLine());
        StringTokenizer st = new StringTokenizer(scan.readLine());
        arr = new int[n];
        answer = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long startTime = System.currentTimeMillis();
        answer = new int[n];
        answer[n-1] = -1;
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(arr[n-1]);
        for(int i=n-2; i>=0; i--){
            if(arr[i] >= treeSet.last()){
                treeSet.clear();
                treeSet.add(arr[i]);
                answer[i] = -1;
            }
            else{
                cutTree(treeSet, arr[i]);
                answer[i] = treeSet.first();
                treeSet.add(arr[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            sb.append(answer[i]).append(' ');
        }System.out.println(sb);
        //System.out.println(System.currentTimeMillis()-startTime+"ms");
        
        /*sb = new StringBuilder();
        for(int i=8414; i<=10000; i++) sb.append(i).append(' ');
        System.out.println(sb);*/
    }

    public static void cutTree(TreeSet<Integer> tset, int min){
        Set<Integer> tmp = new HashSet<>();
        for(int val : tset){
            if(val <= min)
                tmp.add(val);
            else break;
        }
        tset.removeAll(tmp);
    }
}
