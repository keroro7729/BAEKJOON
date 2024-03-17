// https://www.acmicpc.net/problem/2239
// BOJ 2239 스도쿠

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int board[][];
    static List<Integer> listx, listy;
    static boolean end = false;

    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        board = new int[9][9];
        listx = new ArrayList<>();
        listy = new ArrayList<>();
        for(int i=0; i<9; i++){
            String line = scan.readLine();
            for(int j=0; j<9; j++){
                board[i][j] = (int)(line.charAt(j)-'0');
                if(board[i][j] == 0){
                    listx.add(i);
                    listy.add(j);
                }
            }
        }
        long startTime = System.currentTimeMillis();
        solution(0);
        printBoard();
        //System.out.println(System.currentTimeMillis()-startTime+"ms");
    }

    public static void solution(int index){
        if(index == listx.size()){
            end = true;
            return;
        }
        //printBoard();
        int x = listx.get(index), y = listy.get(index);
        for(int i=1; i<=9; i++){
            board[x][y] = i;
            if(check(x, y)){
                solution(index+1);
                if(end) return;
            }
            board[x][y] = 0;
        }
    }

    public static boolean check(int x, int y){
        for(int i=0; i<9; i++){
            if(i != y && board[x][i] == board[x][y])
                return false;
            if(i != x && board[i][y] == board[x][y])
                return false;
        }
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                int dx = (x/3)*3+i, dy = (y/3)*3+j;
                if(dx == x && dy == y) continue;
                if(board[dx][dy] == board[x][y])
                    return false;
            }
        }
        return true;
    }

    public static void printBoard(){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                System.out.print(board[i][j]);
            }System.out.println();
        }System.out.println();
    }
}
