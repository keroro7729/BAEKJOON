// https://www.acmicpc.net/problem/16236
// 아기상어

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, map[][], x, y;
    static int size = 2, eat = 0, answer = 0;
    static final int[] DIRX = {-1, 0, 0, 1}, DIRY = {0, -1, 1, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        map = new int[n][n];
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    x = i; y = j;
                    map[i][j] = 0;
                }
            }
        }

        long startTime = System.currentTimeMillis();
        while(findAndEat(x, y)){
            //print(map, x, y);
        }
        System.out.println(answer);
        //System.out.println(System.currentTimeMillis()-startTime+"ms");
    }

    public static boolean findAndEat(int startx, int starty){
        PriorityQueue<Node> que = new PriorityQueue<>();
        boolean visited[][] = new boolean[n][n];
        que.add(new Node(startx, starty, 0));
        visited[startx][starty] = true;

        while(!que.isEmpty()){
            Node cur = que.poll();
            //System.out.println(cur);
            if(map[cur.x][cur.y] != 0 && map[cur.x][cur.y] < size){
                x = cur.x; y = cur.y;
                map[x][y] = 0;
                eat++;
                if(eat == size){
                    eat = 0;
                    size++;
                }
                answer += cur.depth;
                return true;
            }
            for(int i=0; i<4; i++){
                int x = cur.x + DIRX[i], y = cur.y + DIRY[i];
                if(!check(x, y)) continue;
                if(visited[x][y]) continue;
                que.add(new Node(x, y, cur.depth+1));
                visited[x][y] = true;
            }
        }
        return false;
    }

    public static boolean check(int x, int y){
        if(x < 0 || n <= x || y < 0 || n <= y)
            return false;
        if(map[x][y] > size)
            return false;
        return true;
    }

    public static void print(int[][] map, int x, int y){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i == x && j == y) sb.append('*');
                else sb.append(map[i][j]);
                sb.append(' ');
            }sb.append('\n');
        }System.out.println(sb);
    }
}

class Node implements Comparable<Node> {
    int x, y, depth;
    public Node(int x, int y, int depth){
        this.x = x;
        this.y = y;
        this.depth = depth;
    }
    @Override
    public String toString(){
        return x+", "+y+" : "+depth;
    }
    @Override
    public int compareTo(Node o){
        if(this.depth < o.depth) return -1;
        else if(this.depth > o.depth) return 1;
        else if(this.x < o.x) return -1;
        else if(this.x > o.x) return 1;
        else if(this.y < o.y) return -1;
        else if(this.y > o.y) return 1;
        else return 0;
    }
}
