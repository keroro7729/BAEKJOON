// https://www.acmicpc.net/problem/1520
// 내리막 길

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // dfs & count arrive times => timeout? yes
    // 높이순으로 탐색을 하면 균등하게 탐색을 할 수 있겠는데?
    // visited가 0이 아니라면 +1하고 해당 노드는 삭제
    // 우선순위 큐를 사용한 bfs탐색 => timeout?

    
    private static int n, m, arr[][];
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(reader.readLine());
            for(int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();
    }

    private static void solution(){
        final int[] DIRX = {0, 0, 1, -1}, DIRY = {1, -1, 0, 0};
        int[][] visited = new int[n][m];
        PriorityQueue<Node> que = new PriorityQueue<>();
        que.add(new Node(0, 0, arr[0][0]));
        visited[0][0] = 1;

        while(!que.isEmpty()){
            Node cur = que.poll();
            if(cur.x == n-1 && cur.y == m-1) continue;
            if(cur.h <= arr[n-1][m-1]) continue;
            //System.out.println(cur);
            
            for(int i=0; i<4; i++){
                int x = cur.x+DIRX[i], y = cur.y+DIRY[i];
                if(indexOut(x, y)) continue;
                if(cur.h <= arr[x][y]) continue;

                if(visited[x][y] == 0)
                    que.add(new Node(x, y, arr[x][y]));
                visited[x][y] += visited[cur.x][cur.y];
            }
        }

        //print(visited);
        System.out.println(visited[n-1][m-1]);
    }

    private static boolean indexOut(int x, int y){
        return (x < 0 || n <= x || y < 0 || m <= y);        
    }

    private static void print(int[][] arr){
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[0].length; j++){
                System.out.print(arr[i][j]+" ");
            }System.out.println();
        }System.out.println();
    }
}

class Node implements Comparable<Node> {
    int h, x, y;

    public Node(int x, int y, int h){
        this.x = x;
        this.y = y;
        this.h = h;
    }
    
    @Override
    public int compareTo(Node o){
        return o.h - this.h;
    }

    @Override
    public String toString(){
        return x+","+y+","+h;
    }
}
