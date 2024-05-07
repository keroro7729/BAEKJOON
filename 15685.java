// https://www.acmicpc.net/problem/15685
// 드래곤 커브

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, map[][];
    static final int[] DIRX = {1, 0, -1, 0}, DIRY = {0, -1, 0, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        map = new int[101][101];
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            setDragonCurve(x, y, d, g);
        }

        int count = 0;
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                if(isPartOf(i, j))
                    count++;
            }
        }
        System.out.println(count);
        //printMap();
    }

    static List<Integer> listx = new ArrayList<>();
    static List<Integer> listy = new ArrayList<>();
    
    public static void setDragonCurve(int x, int y, int d, int g){
        int endx =x+DIRX[d], endy = y+DIRY[d];
        map[y][x] = 1; map[endy][endx] = 1;
        listx.clear(); listy.clear();

        for(int i=1; i<=g; i++){
            int[] nextEnd = rotate(x, y, endx, endy);
            int size = listx.size();
            for(int idx=0; idx<size; idx++){
                int[] next = rotate(listx.get(idx), listy.get(idx), endx, endy);
                listx.add(next[0]);
                listy.add(next[1]);
                map[next[1]][next[0]] = 1;
            }
            listx.add(endx);
            listy.add(endy);
            map[endy][endx] = 1;
            endx = nextEnd[0];
            endy = nextEnd[1];
            map[endy][endx] = 1;
            //printMap();
        }
    }

    public static int[] rotate(int targetx, int targety, int stdx, int stdy){
        int dx = targetx - stdx, dy = targety - stdy;
        int[] result = new int[]{stdx-dy, stdy+dx};
        return result;
    }

    public static boolean isPartOf(int x, int y){
        if(map[x][y] == 1 && map[x+1][y] == 1 &&
           map[x][y+1] == 1 && map[x+1][y+1] == 1)
            return true;
        return false;
    }

    public static void printMap(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<=10; i++){
            sb.append(i);
            for(int j=0; j<=10; j++){
                if(map[i][j] == 0) sb.append(' ');
                else sb.append('.');
            }sb.append('\n');
        }sb.append('\n');
        System.out.println(sb);
    }
}
