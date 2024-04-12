// https://www.acmicpc.net/problem/12094
// 2048 (Hard)

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, map[][], answer = 0, MAX = 0;
    static final int[] DIRX = {-1, 0, 1, 0}, DIRY = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(scan.readLine());
        map = new int[n][n];
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(scan.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                answer = Math.max(answer, map[i][j]);
                MAX += map[i][j];
            }
        }
        dfs(0);
        System.out.println(answer);
    }

    public static void dfs(int depth){
        //print("cur depth: "+depth , map, answer);
        int curMax = getMax(map);
        answer = Math.max(answer, curMax);
        if(depth == 10) return;
        if(curMax * Math.pow(2, 10-depth) <= answer) return; // too small stop
        if(curMax * 2 > MAX) return; // reach max stop
        
        int[][] reset = map;
        for(int i=0; i<4; i++){
            map = getMovedMap(map, i);
            if(map != null) {
                dfs(depth + 1);
            }
            map = reset;
        }
    }

    public static int[][] clone(int[][] arr){
        int[][] result = new int[n][n];
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                result[i][j] = arr[i][j];
        return result;
    }

    // return new move result(changed map), if not changed return null
    public static int[][] getMovedMap(int[][] MAP, int dir){
        int[][] map = clone(MAP);
        boolean changed = false;

        if(dir == 0 || dir == 3){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(map[i][j] == 0) continue;
                    if(move(map, i, j, dir))
                        changed = true;
                }
            }
        }
        else{ // if(dir == 1 || dir == 2)
            for(int i=n-1; i>=0; i--){
                for(int j=n-1; j>=0; j--){
                    if(map[i][j] == 0) continue;
                    if(move(map, i, j, dir))
                        changed = true;
                }
            }
        }
        
        if(!changed) return null;
        return map;
    }

    public static boolean move(int[][] map, int X, int Y, int dir){
        int x = X, y = Y;
        boolean changed = false;
        
        // find thre same number to combine
        while(true){
            x -= DIRX[dir]; y -= DIRY[dir];
            if(!check(x, y)) break;
            if(map[x][y] == 0) continue;
            else if(map[x][y] == map[X][Y]){
                changed = true;
                map[x][y] = 0;
                map[X][Y] *= 2;
                break;
            }
            else break;
        }

        x = X; y = Y;
        int locX = x, locY = y;
        // move map[X][Y] to right location
        while(true){
            x += DIRX[dir]; y += DIRY[dir];
            if(!check(x, y)) break;
            if(map[x][y] == 0){
                locX = x; locY = y;
                continue;
            }
            else break;
        }
        if(locX == X && locY == Y) return changed;
        map[locX][locY] = map[X][Y];
        map[X][Y] = 0;
        return true;
    }

    public static boolean check(int x, int y){
        if(x < 0 || n <= x || y < 0 || n <= y)
            return false;
        return true;
    }

    public static int getMax(int[][] map){
        int max = 0;
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                if(max < map[i][j])
                    max = map[i][j];
        return max;
    }
    
    public static void print(String str, int[][] map, int answer){
        StringBuilder sb = new StringBuilder();
        sb.append(str).append('\n');
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                sb.append(map[i][j]).append(' ');
            }sb.append('\n');
        }sb.append(answer).append('\n');
        System.out.println(sb);
    }
}
