// https://www.acmicpc.net/problem/4179
// 불!

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int r, c, map[][];
    static boolean visited[][];
    static final int BLOCK = 1, EMPTY = 0, FIRE = 2;
    static final int[] DIRX = {1, -1, 0, 0}, DIRY = {0, 0, 1, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        map = new int[r][c];
        visited = new boolean[r][c];
        Queue<Node> que = new LinkedList<>();
        Set<Integer> fires = new HashSet<>();
        for(int i=0; i<r; i++){
            String line = reader.readLine();
            for(int j=0; j<c; j++){
                switch(line.charAt(j)){
                    case '#': map[i][j] = BLOCK; break;
                    case 'J': que.add(new Node(i, j, 0)); visited[i][j] = true; break;
                    case 'F': fires.add(getCoor(i, j)); break;
                }
            }
        }

        int prevDepth = -1;
        while(!que.isEmpty()){
            Node cur = que.poll();
            if(isEdge(cur.x, cur.y)){
                System.out.println(cur.depth+1);
                return;
            }
            if(cur.depth != prevDepth){
                prevDepth = cur.depth;
                updateFire(fires);
                //print(map);
            }
            for(int i=0; i<4; i++){
                int x = cur.x+DIRX[i], y = cur.y+DIRY[i];
                if(!check(x, y, map)) continue;
                if(fires.contains(getCoor(x, y))) continue;
                que.add(new Node(x, y, cur.depth+1));
                visited[x][y] = true;
            }
        }
        System.out.println("IMPOSSIBLE");
    }

    public static void updateFire(Set<Integer> fires){
        Set<Integer> next = new HashSet<>();
        for(int coor : fires) map[getX(coor)][getY(coor)] = FIRE;
        for(int coor : fires){
            for(int i=0; i<4; i++){
                int x = getX(coor)+DIRX[i], y = getY(coor)+DIRY[i];
                if(x < 0 || r <= x || y < 0 || c <= y) continue;
                if(map[x][y] != EMPTY) continue;
                next.add(getCoor(x, y));
            }
        }
        fires.clear();
        fires.addAll(next);
    }

    public static boolean isEdge(int x, int y){
        return (x == 0 || y == 0 || x == r-1 || y == c-1);
    }

    public static boolean check(int x, int y, int map[][]){
        if(x < 0 || r <= x || y < 0 || c <= y) return false;
        if(visited[x][y]) return false;
        if(map[x][y] != EMPTY) return false;
        return true;
    }

    public static int[][] clone(int[][] arr){
        int[][] result = new int[r][c];
        for(int i=0; i<r; i++)
            for(int j=0; j<c; j++)
                result[i][j] = arr[i][j];
        return result;
    }

    public static void print(int[][] arr){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                switch(arr[i][j]){
                    case 0: sb.append(' '); break;
                    case 1: sb.append('#'); break;
                    case 2: sb.append('F'); break;
                }
            }sb.append('\n');
        }System.out.println(sb);
    }

    public static int getCoor(int x, int y){ return x*c+y; }
    public static int getX(int coor){ return coor/c; }
    public static int getY(int coor){ return coor%c; }
}
class Node{
    int x, y, depth;
    public Node(int x, int y, int depth){
        this.x = x;
        this.y = y;
        this.depth = depth;
    }
    @Override
    public String toString(){
        return "("+x+", "+y+", "+depth+")";
    }
}
