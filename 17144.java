// https://www.acmicpc.net/problem/17144
// 미세먼지 안녕!

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m, t, map[][];
    static int cleaner;
    
    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(scan.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1) cleaner = i;
            }
        }

        for(int i=0; i<t; i++){
            //printMap();
            dustFlow();
            runCleaner();
        }

        int answer = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] != -1)
                    answer += map[i][j];
            }
        }
        System.out.println(answer);
    }

    static int[] DIR_X = {0,-1,0,1}, DIR_Y = {1,0,-1,0};
    public static void dustFlow(){
        int[][] next = new int[n][m];
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++)
                next[i][j] = map[i][j];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] <= 4) continue;
                int val = map[i][j]/5;
                int count = 0;
                for(int dir=0; dir<4; dir++){
                    int x = i+DIR_X[dir], y = j+DIR_Y[dir];
                    if(!check(x, y)) continue;
                    next[x][y] += val;
                    count++;
                }
                next[i][j] -= val*count;
            }
        }
        map = next;
    }

    public static boolean check(int x, int y){
        if(x < 0 || n <= x || y < 0 || m <= y)
            return false;
        if(map[x][y] == -1) return false;
        return true;
    }

    public static void runCleaner(){
        int x, y, val, dir, tmp;
        x = cleaner-1; y = 0; val = 0; dir = 0;
        while(dir < 4){
            x += DIR_X[dir]; y += DIR_Y[dir];
            if(!check(x, y)){
                x -= DIR_X[dir]; y -= DIR_Y[dir];
                dir++;
                continue;
            }
            tmp = map[x][y];
            map[x][y] = val;
            val = tmp;
        }
        x = cleaner; y = 0; val = 0; dir = 0;
        while(dir < 4){
            if(dir%2==0){
                x += DIR_X[dir]; y += DIR_Y[dir];
            }
            else{
                x -= DIR_X[dir]; y -= DIR_Y[dir];
            }
            if(!check(x, y)){
                if(dir%2==0){
                    x -= DIR_X[dir]; y -= DIR_Y[dir];
                }
                else{
                    x += DIR_X[dir]; y += DIR_Y[dir];
                }
                dir++;
                continue;
            }
            tmp = map[x][y];
            map[x][y] = val;
            val = tmp;
        }
    }

    public static void printMap(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                sb.append(map[i][j]).append(' ');
            }sb.append('\n');
        }System.out.println(sb);
    }
}
