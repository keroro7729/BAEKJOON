// https://www.acmicpc.net/problem/17471
// BOJ 17671 게리맨더링

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, population[], min, graph[][];
    static boolean[] comb;
    
    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(scan.readLine());
        population = new int[n];
        StringTokenizer st = new StringTokenizer(scan.readLine());
        for(int i=0; i<n; i++){
            population[i] = Integer.parseInt(st.nextToken());
        }
        graph = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(scan.readLine());
            int count = Integer.parseInt(st.nextToken());
            for(int j=0; j<count; j++){
                int nei = Integer.parseInt(st.nextToken())-1;
                graph[i][nei] = graph[nei][i] = 1;
            }
        }

        min = Integer.MAX_VALUE;
        comb = new boolean[n];
        for(int r=1; r<=n/2; r++)
            traceComb(0, n, r);
        if(min == Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }
        System.out.println(min);
    }

    public static void traceComb(int index, int n, int r){
        if(r == 0){
            // 두 선거구가 각각 연결되어있는지 확인
            if(ckeckConnection(comb)){
                min = Math.min(min, Math.abs(getSubstarct(comb)));
            }
            return;
        }
        if(index == n)
            return;

        comb[index] = true;
        traceComb(index+1, n, r-1);
        comb[index] = false;
        traceComb(index+1, n, r);
    }

    public static boolean ckeckConnection(boolean[] comb){
        int start1 = -1, start2 = -1;
        for(int i=0; i<n; i++){
            if(comb[i]) start1 = i;
            else start2 = i;
        }
        
        if(bfs(start1, true) && bfs(start2, false))
            return true;
        return false;
    }
    public static boolean bfs(int start, boolean TF){
        boolean[] visited = new boolean[n];
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        visited[start] = true;
        while(!que.isEmpty()){
            int cur = que.poll();
            for(int i=0; i<n; i++){
                if(graph[i][cur]==1 && comb[i]==TF && !visited[i]){
                    que.add(i);
                    visited[i] = true;
                }
            }
        }
        for(int i=0; i<n; i++){
            if(comb[i]==TF && !visited[i])
                return false;
        }
        return true;
    }

    public static int getSubstarct(boolean[] comb){
        int sum = 0;
        for(int i=0; i<n; i++){
            if(comb[i]) sum += population[i];
            else sum -= population[i];
        }
        return Math.abs(sum);
    }
}
