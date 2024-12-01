// https://www.acmicpc.net/problem/1197
// 최소 스패닝 트리

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int n, m, head[]; 
    private static PriorityQueue<Edge> edges = new PriorityQueue<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken()); // 정점의 갯수
        m = Integer.parseInt(st.nextToken()); // 간선의 갯수
        head = new int[n];
        for(int i=1; i<n; i++) head[i] = i;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }
    
        long answer = 0;
        while(!edges.isEmpty()){
            Edge cur = edges.poll();
            if(find(cur.x) != find(cur.y)){
                union(cur.x, cur.y);
                answer += cur.w;
            }
        }
        System.out.println(answer);
    }
    
    private static int find(int v){
        if(head[v] == v) return v;
        else return head[v] = find(head[v]);
    }
    
    private static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a < b) head[a] = b;
        else head[b] = a;
    }
}
class Edge implements Comparable<Edge> { 
    int x, y, w; 
    public Edge(int x, int y, int w){ 
        this.x = x; 
        this.y = y; 
        this.w = w; 
    } 
    public int compareTo(Edge o){ 
        return this.w - o.w; 
    } 
}
