// https://www.acmicpc.net/problem/31791
// 바이러스 공격

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m, Tg, Tb, x, b, map[][];
    static final int[] DIRX = {1, -1, 0, 0}, DIRY = {0, 0, 1, -1};
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(reader.readLine());
        Tg = Integer.parseInt(st.nextToken());
        Tb = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        
        map = new int[n][m];
        visited = new boolean[n][m];
        PriorityQueue<Node> que = new PriorityQueue<>();
        for(int i=0; i<n; i++){
            String line = reader.readLine();
            for(int j=0; j<m; j++){
                switch(line.charAt(j)){
                    case '*': 
                        que.add(new Node(i, j, 0));
                        visited[i][j] = true;
                        break;
                    case '#': map[i][j] = -1; break;
                }
            }
        }

        // bfs solution
        while(!que.isEmpty()){
            Node cur = que.poll();
            if(cur.time >= Tg) continue;
            for(int i=0; i<4; i++){
                int x = cur.x+DIRX[i], y = cur.y+DIRY[i];
                if(!check(x, y)) continue;
                if(map[x][y] != 0){ // building
                    map[x][y] = cur.time+1; // Time of infection
                    que.add(new Node(x, y, cur.time+Tb+1));
                    visited[x][y] = true;
                }
                else{
                    que.add(new Node(x, y, cur.time+1));
                    visited[x][y] = true;
                }
            }
        }

        // output
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!visited[i][j] || (map[i][j] != 0 && Tg-Tb+1 <= map[i][j]))
                    sb.append(i+1).append(' ').append(j+1).append('\n');
            }
        }
        if(sb.length() == 0)
            System.out.println(-1);
        else System.out.print(sb);
    }

    public static boolean check(int x, int y){
        if(x < 0 || n <= x || y < 0 || m <= y)
            return false;
        if(visited[x][y])
            return false;
        return true;
    }

    public static void printMap(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!visited[i][j] || (map[i][j] != 0 && Tg-Tb+1 <= map[i][j]))
                    sb.append('.');
                else
                    sb.append('*');
            }sb.append('\n');
        }
        System.out.println(sb);
    }
}

class Node implements Comparable<Node> {
    int x, y, time;
    public Node(int x, int y, int time){
        this.x = x;
        this.y = y;
        this.time = time;
    }
    @Override
    public int compareTo(Node o){
        return this.time - o.time;
    }
}
