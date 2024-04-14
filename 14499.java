// https://www.acmicpc.net/problem/14499
// 주사위 굴리기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m, x, y, k, map[][], dice[] = {0, 0, 0, 0, 0, 0};
    static int[] DIRX = {0, 0, -1, 1}, DIRY = {1, -1, 0, 0};
    static int top = 0, east = 2, north = 4;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(reader.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(reader.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<k; i++){
            int dir = Integer.parseInt(st.nextToken()) - 1;
            //System.out.println(top+", "+east+", "+north);
            if(move(dir)){
                sb.append(dice[top]).append('\n');
            }
        }
        System.out.print(sb);
    }

    public static int getBottom(){
        return 5-top;
    }
    public static int getOpposite(int idx){
        return 5-idx;
    }

    public static boolean move(int dir){
        x += DIRX[dir]; y += DIRY[dir];
        if(!check(x, y)){
            x -= DIRX[dir]; y -= DIRY[dir];
            return false;
        }
        roll(dir);
        if(map[x][y] == 0){
            map[x][y] = dice[getBottom()];
        }
        else{
            dice[getBottom()] = map[x][y];
            map[x][y] = 0;
        }
        return true;
    }

    public static boolean check(int x, int y){
        if(x < 0 || n <= x || y < 0 || m <= y)
            return false;
        return true;
    }

    public static void roll(int dir){
        int tmp = top;
        switch(dir){
            case 0: top = getOpposite(east); east = tmp; break;
            case 1: top = east; east = getOpposite(tmp); break;
            case 2: top = getOpposite(north); north = tmp; break;
            case 3: top = north; north = getOpposite(tmp); break;
        }
    }
}
