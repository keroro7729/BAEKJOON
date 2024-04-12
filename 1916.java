// https://www.acmicpc.net/problem/1916
// 최소비용 구하기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m, start, end, graph[][];
    static int answer[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(scan.readLine());
        m = Integer.parseInt(scan.readLine());
        graph = new int[n][n];
        for(int i=0; i<n; i++) for(int j=0; j<n; j++) 
            if(i == j) graph[i][j] = 0;
            else graph[i][j] = Integer.MAX_VALUE;
        for(int i=0; i<m; i++){
            StringTokenizer st = new StringTokenizer(scan.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            int price = Math.min(graph[s][e], Integer.parseInt(st.nextToken()));
            graph[s][e] = price;
        }
        StringTokenizer st = new StringTokenizer(scan.readLine());
        start = Integer.parseInt(st.nextToken())-1;
        end = Integer.parseInt(st.nextToken())-1;

        PriorityQueue<Node> que = new PriorityQueue<>();
        answer = new int[n];
        for(int i=0; i<n; i++) answer[i] = Integer.MAX_VALUE;
        que.add(new Node(start, 0));
        answer[start] = 0;
        while(!que.isEmpty()){
            Node cur = que.poll();
            //System.out.println(cur);
            if(cur.point == end){
                System.out.println(answer[end]);
                return;
            }
            if(answer[cur.point] < cur.fare) continue;
            
            for(int i=0; i<n; i++){
                if(graph[cur.point][i] == Integer.MAX_VALUE) continue;
                if(cur.fare + graph[cur.point][i] < answer[i]){
                    que.add(new Node(i, cur.fare + graph[cur.point][i]));
                    answer[i] = cur.fare + graph[cur.point][i];
                }
            }
        }
        System.out.println(-1);
    }
}

class Node implements Comparable<Node>{
    int point, fare;
    public Node(int point, int fare){
        this.point = point;
        this.fare = fare;
    }
    public int compareTo(Node o){
        return this.fare - o.fare;
    }
    @Override
    public String toString(){
        return point+", "+fare;
    }
}
