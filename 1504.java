// https://www.acmicpc.net/problem/1504
// 특정한 최단 경로

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static int n, e, graph[][], v1, v2;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                if(i != j)
                    graph[i][j] = INF;
        for(int i=0; i<e; i++){
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            graph[a][b] = graph[b][a] = c;
        }
        st = new StringTokenizer(reader.readLine());
        v1 = Integer.parseInt(st.nextToken()) - 1;
        v2 = Integer.parseInt(st.nextToken()) - 1;

        int[] start = dijkstra(0);
        int[] mid1 = dijkstra(v1);
        int[] mid2 = dijkstra(v2);

        long ans1 = (long)start[v1] + mid1[v2] + mid2[n-1];
        long ans2 = (long)start[v2] + mid2[v1] + mid1[n-1];
        if(ans1 >= INF && ans2 >= INF)
            System.out.println(-1);
        else if(ans1 >= INF)
            System.out.println(ans2);
        else if(ans2 >= INF)
            System.out.println(ans1);
        else System.out.println(Math.min(ans1, ans2));
    }

    private static int[] dijkstra(int s){
        PriorityQueue<Node> que = new PriorityQueue<>();
        int[] distance = new int[n];
        Arrays.fill(distance, INF);
        distance[s] = 0;
        que.add(new Node(s, 0));
        while(!que.isEmpty()){
            Node cur = que.poll();
            if(cur.v > distance[cur.x]) continue;
            for(int i=0; i<n; i++){
                if(graph[cur.x][i] == INF) continue;
                int v = cur.v + graph[cur.x][i];
                if(distance[i] > v){
                    distance[i] = v;
                    que.add(new Node(i, v));
                }
            }
        }
        return distance;
    }
}

class Node implements Comparable<Node> {
    int x, v;
    public Node(int x, int v){
        this.x = x;
        this.v = v;
    }
    public int compareTo(Node o){
        return this.v - o.v;
    }
}
