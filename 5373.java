// https://www.acmicpc.net/problem/5373
// 큐빙

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int cube[][] = new int[6][9], n;
    static final int WHITE = 0, YELLOW = 1, RED = 2, 
                     ORANGE = 3, GREEN = 4, BLUE = 5;
    static final int TOP = 0, BOTTOM = 1, FRONT = 2,
                     BACK = 3, LEFT = 4, RIGHT = 5;
    static final int[][] TURN = {{0, 1, 2}, {2, 5, 8},
                                {8, 7, 6}, {6, 3, 0}};
    static final int[] TURN_ROW = {0, 1, 2, 5, 8, 7, 6, 3};
    static final int[][][] ADJACENT = {
        {{BACK, 6, 7, 8}, {RIGHT, 2, 1, 0}, {FRONT, 2, 1, 0}, {LEFT, 2, 1, 0}},
        {{FRONT, 6, 7, 8}, {RIGHT, 6, 7, 8}, {BACK, 2, 1, 0}, {LEFT, 6, 7, 8}},
        {{TOP, 6, 7, 8}, {RIGHT, 0, 3, 6}, {BOTTOM, 2, 1, 0}, {LEFT, 8, 5, 2}},
        {{BOTTOM, 6, 7, 8}, {RIGHT, 8, 5, 2}, {TOP, 2, 1, 0}, {LEFT, 0, 3, 6}},
        {{TOP, 0, 3, 6}, {FRONT, 0, 3, 6}, {BOTTOM, 0, 3, 6}, {BACK, 0, 3, 6}},
        {{TOP, 8, 5, 2}, {BACK, 8, 5, 2}, {BOTTOM, 8, 5, 2}, {FRONT, 8, 5, 2}}
    };
    /*
        [T]         0 1 2
    [L] [F] [R]     3 4 5
        [B] Bottom  6 7 8
        [b] back    
    */
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(reader.readLine());
        for(int t=0; t<T; t++){
            n = Integer.parseInt(reader.readLine());
            initCube();
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for(int i=0; i<n; i++){
                String oper = st.nextToken();
                int side = getSide(oper.charAt(0));
                int dir = getDir(oper.charAt(1));
                turn(side, dir);
                //printCube();
            }
            sb.append(printTop());
            //printCube();
        }
        System.out.print(sb);
    }

    public static void turn(int side, int dir){
        // turn side surface
        int selfStd = (dir==1 ? TURN_ROW[0] : TURN_ROW[7]);
        for(int t=0; t<2; t++){
            for(int i=(dir==1 ? 1 : 6); (dir==1 ? i<8 : i>=0); i+=dir){
                swap(side, selfStd, TURN_ROW[i]);
            }
        }
        // turn ADJACENT line
        int[] adfStd = (dir==1 ? ADJACENT[side][0] : ADJACENT[side][3]);
        for(int i=(dir==1 ? 1 : 2); (dir==1 ? i<4 : i>=0); i+=dir){
            int[] target = ADJACENT[side][i];
            swap(adfStd, target);
        }
    }

    public static void swap(int[] a, int[] b){
        int tmp;
        for(int i=1; i<4; i++){
            tmp = cube[a[0]][a[i]];
            cube[a[0]][a[i]] = cube[b[0]][b[i]];
            cube[b[0]][b[i]] = tmp;
        }
    }
    public static void swap(int side, int a, int b){
        int tmp = cube[side][a];
        cube[side][a] = cube[side][b];
        cube[side][b] = tmp;
    }

    public static void initCube(){
        for(int i=0; i<6; i++){
            for(int j=0; j<9; j++){
                cube[i][j] = i;
            }
        }
    }

    public static int getSide(char c){
        switch(c){
            case 'U': return TOP;
            case 'D': return BOTTOM;
            case 'F': return FRONT;
            case 'B': return BACK;
            case 'L': return LEFT;
            case 'R': return RIGHT;
            default: return -1;
        }
    }

    public static int getDir(char c){
        switch(c){
            case '+': return 1;
            case '-': return -1;
            default: return 0;
        }
    }

    public static String printTop(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<9; i++){
            switch(cube[TOP][i]){
                case WHITE: sb.append('w'); break;
                case YELLOW: sb.append('y'); break;
                case RED: sb.append('r'); break;
                case ORANGE: sb.append('o'); break;
                case GREEN: sb.append('g'); break;
                case BLUE: sb.append('b'); break;
            }
            if(i%3 == 2) sb.append('\n');
        }
        return sb.toString();
    }

    public static void printCube(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<9; i++){
            if(i%3 == 0) sb.append("    ");
            sb.append(cube[TOP][i]);
            if(i%3 == 2) sb.append('\n');
        }sb.append('\n');
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++) sb.append(cube[LEFT][i*3+j]);
            sb.append(' ');
            for(int j=0; j<3; j++) sb.append(cube[FRONT][i*3+j]);
            sb.append(' ');
            for(int j=0; j<3; j++) sb.append(cube[RIGHT][i*3+j]);
            sb.append('\n');
        }sb.append('\n');
        for(int i=0; i<9; i++){
            if(i%3 == 0) sb.append("    ");
            sb.append(cube[BOTTOM][i]);
            if(i%3 == 2) sb.append('\n');
        }sb.append('\n');
        for(int i=0; i<9; i++){
            if(i%3 == 0) sb.append("    ");
            sb.append(cube[BACK][i]);
            if(i%3 == 2) sb.append('\n');
        }sb.append('\n');
        System.out.println(sb);
    }
}
