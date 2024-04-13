// https://www.acmicpc.net/problem/14500
// 테트로미노

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m, map[][];
    static int[] stick = {0, 1, 0, 1, 0, 1}, square = {0, 1, 1, 0, 0, -1};
    static int[] Lbrick1 = {1, 0, 1, 0, 0, 1}, Lbrick2 = {1, 0, 1, 0, 0, -1};
    static int[] spark1 = {1, 0, 0, 1, 1, 0}, spark2 = {1, 0, 0, -1, 1, 0};
    static int[] Tbrick = {0, 1, 0, 1, 1, -1};
    static int[][] twoRot = {stick, spark1, spark2};
    static int[][] fourRot = {Lbrick1, Lbrick2, Tbrick};
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(reader.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long startTime = System.currentTimeMillis();
        int max = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                max = Math.max(max, getVal(i, j, square, 0));
                for(int[] block : twoRot){
                    max = Math.max(max, getVal(i, j, block, 0));
                    max = Math.max(max, getVal(i, j, block, 1));
                }
                for(int[] block : fourRot){
                    max = Math.max(max, getVal(i, j, block, 0));
                    max = Math.max(max, getVal(i, j, block, 1));
                    max = Math.max(max, getVal(i, j, block, 2));
                    max = Math.max(max, getVal(i, j, block, 3));
                }
            }
        }
        System.out.println(max);
        //System.out.println(System.currentTimeMillis()-startTime+"ms");
    }

    public static int getVal(int x, int y, int[] block, int rotation){
        int idx = 0, val = map[x][y];
        for(int i=0; i<3; i++){
            switch(rotation){
                case 0: x += block[idx++]; y += block[idx++]; break;
                case 1: y += block[idx++]; x -= block[idx++]; break;
                case 2: x -= block[idx++]; y -= block[idx++]; break;
                case 3: y -= block[idx++]; x += block[idx++]; break;
            }
            if(x < 0 || n <= x || y < 0 || m <= y)
                return -1;
            val += map[x][y];
        }
        return val;
    }
}
