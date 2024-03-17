// https://www.acmicpc.net/problem/7576
// BOJ 7576 토마토

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        state = new int[n][m];
        Queue<Node> nextQ = new LinkedList<>();
        visited = new boolean[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(scan.readLine());
            for(int j=0; j<m; j++){
                state[i][j] = Integer.parseInt(st.nextToken());
                if(state[i][j] == 1){
                    nextQ.add(new Node(i, j));
                    visited[i][j] = true;
                }
            }
        }

        // bfs
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int answer = 0;
        while(true){
            Queue<Node> curQ = nextQ;
            nextQ = new LinkedList<>();
            while(!curQ.isEmpty()){
                Node cur = curQ.poll();
                for(int i=0; i<4; i++){
                    int x = cur.x+dx[i], y = cur.y+dy[i];
                    if(!check(x, y)) continue;
                    nextQ.add(new Node(x, y));
                    visited[x][y] = true;
                }
            }
            if(nextQ.isEmpty())
                break;
            answer++;
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(state[i][j] != -1 && !visited[i][j]){
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(answer);
    }

    static int n, m;
    static boolean[][] visited;
    static int[][] state;
    
    public static boolean check(int x, int y){
        if(x < 0 || n <= x || y < 0 || m <= y)
            return false;
        if(visited[x][y])
            return false;
        if(state[x][y] != 0)
            return false;
        return true;
    }
}

class Node{
    int x, y;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
