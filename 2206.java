// https://www.acmicpc.net/problem/2206
// BOJ 2206 벽 부수고 이동하기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i=0; i<n; i++){
            String line = scan.readLine();
            for(int j=0; j<m; j++){
                map[i][j] = (int)(line.charAt(j) - '0');
            }
        }

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<Node> que = new LinkedList<>();
        visited = new boolean[n][m][2];
        que.add(new Node(0, 0, 0, 1));
        visited[0][0][0] = true;
        while(!que.isEmpty()){
            Node cur = que.poll();
            if(cur.x == n-1 && cur.y == m-1){
                System.out.println(cur.depth);
                return;
            }
            for(int i=0; i<4; i++){
                int x = cur.x + dx[i], y = cur.y + dy[i];
                if(!check(x, y)) continue;
                if(map[x][y] == 0){
                    if(visited[x][y][cur.jumped]) continue;
                    que.add(new Node(x, y, cur.jumped, cur.depth+1));
                    visited[x][y][cur.jumped] = true;
                }
                else{
                    if(cur.jumped == 1)
                        continue;
                    else{
                        que.add(new Node(x, y, 1, cur.depth+1));
                        visited[x][y][1] = true;
                    }
                }
            }
            
        }
        System.out.println(-1);
    }

    static int n, m;
    static boolean[][][] visited;
    static int[][] map;
    
    public static boolean check(int x, int y){
        if(x < 0 || n <= x || y < 0 || m <= y)
            return false;
        return true;
    }
}

class Node{
    int x, y, jumped, depth;
    
    public Node(int x, int y, int jumped, int depth){
        this.x = x;
        this.y = y;
        this.jumped = jumped;
        this.depth = depth;
    }
}
