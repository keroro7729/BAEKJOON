// https://www.acmicpc.net/problem/15683
// BOJ 15683 감시

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m, map[][], max;
    static boolean[][] visited;
    static List<Coordinate> cctv;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        cctv = new ArrayList<>();
        int count = 0;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(scan.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) count++;
                if(0 < map[i][j] && map[i][j] < 6)
                    cctv.add(new Coordinate(i,j));
            }
        }

        max = 0;
        dfs(0);
        System.out.println(count - max);
    }

    public static void dfs(int index){
        if(index == cctv.size()){
            //printVisited();
            max = Math.max(max, countTrue());
            return;
        }

        Coordinate cur = cctv.get(index);
        boolean[][] reset = copy(visited);
        switch(map[cur.x][cur.y]){
        case 1:
            for(int i=0; i<4; i++){
                setVisited(cur, i);
                dfs(index+1);
                visited = copy(reset);
            } break;
        case 2:
            for(int i=0; i<2; i++){
                setVisited(cur, i);
                setVisited(cur, i+2);
                dfs(index+1);
                visited = copy(reset);
            } break;
        case 3:
            for(int i=0; i<4; i++){
                setVisited(cur, i);
                setVisited(cur, getDir(i+1));
                dfs(index+1);
                visited = copy(reset);
            } break;
        case 4:
            for(int i=0; i<4; i++){
                setVisited(cur, i);
                setVisited(cur, getDir(i+1));
                setVisited(cur, getDir(i+2));
                dfs(index+1);
                visited = copy(reset);
            } break;
        case 5:
            setVisited(cur, 0);
            setVisited(cur, 1);
            setVisited(cur, 2);
            setVisited(cur, 3);
            dfs(index+1);
            visited = copy(reset);
        }
    }

    public static void setVisited(Coordinate start, int dir){
        int x = start.x, y = start.y;
        while(true){
            x += dx[dir]; y += dy[dir];
            if(x < 0 || n <= x || y < 0 || m <= y)
                return;
            if(map[x][y] == 6)
                return;
            if(map[x][y] == 0)
                visited[x][y] = true;
        }
    }

    public static int getDir(int index){
        if(index < 0) index += 4;
        else if(index >= 4) index -= 4;
        return index;
    }
    
    public static boolean[][] copy(boolean[][] matrix){
        boolean[][] copy = new boolean[n][m];
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++)
                copy[i][j] = matrix[i][j];
        return copy;
    }

    public static int countTrue(){
        int count = 0;
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++)
                if(visited[i][j])
                    count++;
        return count;
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
class Coordinate{
    int x, y;
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
}
