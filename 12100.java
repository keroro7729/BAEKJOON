// https://www.acmicpc.net/problem/12100
// BOJ 12100 2048(Easy)

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, origin[][], max;

    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(scan.readLine());
        origin = new int[n][n];
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(scan.readLine());
            for(int j=0; j<n; j++){
                origin[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //printBoard(moveBoard(origin, 3));
        
        max = 0;
        long startTime = System.currentTimeMillis();
        dfs(origin, 0);
        System.out.println(max);
        //System.out.println(System.currentTimeMillis()-startTime+"ms");
    }

    public static void dfs(int[][] board, int depth){
        max = Math.max(max, getMax(board));
        if(depth == 5) return;

        for(int i=0; i<4; i++){
            int[][] nextBoard = moveBoard(board, i);
            if(equals(board, nextBoard)) continue;
            dfs(nextBoard, depth+1);
        }
    }
    
    public static int[][] moveBoard(int[][] BOARD, int dir){
        int[][] board = new int[n][n];
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                board[i][j] = BOARD[i][j];
        switch(dir){
            case 0: 
                for(int i=0; i<n; i++){
                    for(int j=0; j<n; j++){
                        if(board[i][j] != 0)
                            move(board, i, j, dir);
                    }
                } break;
            case 1: 
                for(int j=n-1; j>=0; j--){
                    for(int i=0; i<n; i++){
                        if(board[i][j] != 0)
                            move(board, i, j, dir);
                    }
                } break;
            case 2: 
                for(int i=n-1; i>=0; i--){
                    for(int j=0; j<n; j++){
                        if(board[i][j] != 0)
                            move(board, i, j, dir);
                    }
                } break;
            case 3: 
                for(int j=0; j<n; j++){
                    for(int i=0; i<n; i++){
                        if(board[i][j] != 0)
                            move(board, i, j, dir);
                    }
                } break;
        }
        return board;
    }
    
    static int[] dx = {-1,0,1,0}, dy = {0, 1, 0, -1};
    public static void move(int[][] board, int x, int y, int dir){
        int i= x, j = y;
        while(true){
            i -= dx[dir]; j -= dy[dir];
            if(!check(i, j)) break;
            if(board[i][j] == 0) continue;
            else if(board[i][j] == board[x][y]){
                board[i][j] = 0;
                board[x][y] *= 2;
                break;
            }
            else break;
        }
        i = x; j = y;
        int px = -1, py = -1;
        while(true){
            i += dx[dir]; j += dy[dir];
            if(!check(i, j)) break;
            if(board[i][j] == 0){
                px = i; py = j;
                continue;
            }
            else break;
        }
        if(px == -1 || py == -1) return;
        board[px][py] = board[x][y];
        board[x][y] = 0;
    }

    public static boolean check(int x, int y){
        if(x < 0 || n <= x || y < 0 || n <= y)
            return false;
        return true;
    }
    
    public static int getMax(int[][] board){
        int max = 0;
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                max = Math.max(max, board[i][j]);
        return max;
    }

    public static boolean equals(int[][] a, int[][] b){
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                if(a[i][j] != b[i][j])
                    return false;
        return true;
    }
    
    public static void printBoard(int[][] board){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.print(board[i][j]+" ");
            }System.out.println();
        }System.out.println();
    }
}
