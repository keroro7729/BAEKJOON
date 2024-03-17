// https://www.acmicpc.net/problem/14502
// BOJ 14502 연구소

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m, map[][], max;
    static boolean[][] visited;
    static List<Integer> vpointX, vpointY;
    static int[] dx = {1,-1,0,0,1,1,-1,-1};
    static int[] dy = {0,0,1,-1,1,-1,1,-1};

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        vpointX = new ArrayList<>();
        vpointY = new ArrayList<>();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(scan.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) visited[i][j] = true;
                else if(map[i][j] == 2){
                    vpointX.add(i);
                    vpointY.add(j);
                }
            }
        }
        max = 0;
        makeWall(0);
        System.out.println(max);
        //System.out.println(System.currentTimeMillis()-startTime+"ms");
    }

    public static void makeWall(int depth){
        if(depth == 3){
            max = Math.max(max, countSaftyArea());
            return;
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] == 0){
                    map[i][j] = 1;
                    visited[i][j] = true;
                    makeWall(depth+1);
                    map[i][j] = 0;
                    visited[i][j] = false;
                }
            }
        }
    }

    public static int countSaftyArea(){
        boolean[][] reset = copy(visited);
        for(int i=0; i<vpointX.size(); i++){
            dfs(vpointX.get(i), vpointY.get(i));
        }
        int count = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!visited[i][j])
                    count++;
            }
        }
        visited = reset;
        return count;
    }
    public static boolean[][] copy(boolean[][] matrix){
        boolean[][] c = new boolean[n][m];
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++)
                c[i][j] = matrix[i][j];
        return c;
    }
    public static void dfs(int x, int y){
        Stack<Integer> stackX = new Stack<>();
        Stack<Integer> stackY = new Stack<>();
        stackX.add(x); stackY.add(y);
        
        while(!stackX.isEmpty()){
            int curx = stackX.pop(), cury = stackY.pop();
            visited[curx][cury] = true;
            for(int i=0; i<4; i++){
                int nextX = curx+dx[i], nextY = cury+dy[i];
                if(!check(nextX, nextY)) continue;
                stackX.add(nextX); stackY.add(nextY);
            }
        }
    }
    public static boolean check(int x, int y){
        if(x < 0 || n <= x || y < 0 || m <= y)
            return false;
        if(visited[x][y])
            return false;
        return true;
    }

    public static void printMap(){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++)
                System.out.print(map[i][j]+" ");
            System.out.println();
        }System.out.println();
    }
    public static void printVisited(){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++)
                if(visited[i][j]) System.out.print("1 ");
                else System.out.print("0 ");
            System.out.println();
        }System.out.println();
    }
}
