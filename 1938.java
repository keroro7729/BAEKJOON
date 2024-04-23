// https://www.acmicpc.net/problem/1938
// 통나무 옮기기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, map[][];
    static final int END = -2;
    static boolean visited[][][];
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        map = new int[n][n];
        visited = new boolean[2][n][n];
        int bi = -1, bj = -1;
        Queue<Wood> que = new LinkedList<>();
        for(int i=0; i<n; i++){
            String line = reader.readLine();
            for(int j=0; j<n; j++){
                switch(line.charAt(j)){
                    //case '0': map[i][j] = 0; break;
                    case '1': map[i][j] = 1; break;
                    case 'B':
                        if(bi == -1 || bj == -1){
                            bi = i; bj = j; break;
                        } 
                        else if(bi == END || bj == END) break;
                        else{
                            Wood start = new Wood(i, j, 0, bj == j);
                            //System.out.println("start: "+start);
                            que.add(start);
                            visit(start);
                            bi = END; bj = END;
                            break;
                        }
                    case 'E': map[i][j] = END;
                }
            }
        }

        while(!que.isEmpty()){
            Wood cur = que.poll();
            if(isEnd(cur)){
                System.out.println(cur.depth);
                return;
            }
            for(int i=0; i<5; i++){
                Wood next = null;
                switch(i){
                    case 0: next = roll(cur, true); break;
                    case 1: next = roll(cur, false); break;
                    case 2: next = push(cur, true); break;
                    case 3: next = push(cur, false); break;
                    case 4: next = turn(cur); break;
                }
                if(next != null && !isVisited(next)){
                    que.add(next);
                    visit(next);
                }
            }
        }
        System.out.println(0);
    }

    public static Wood roll(Wood cur, boolean plus){
        int adder = plus ? 1 : -1;
        Wood wood;
        if(cur.vertical) wood = new Wood(cur.x, cur.y+adder, cur.depth+1, cur.vertical);
        else wood = new Wood(cur.x+adder, cur.y, cur.depth+1, cur.vertical);
        int[] positions = wood.getPositions();
        if(!check(positions)) return null;
        return wood;
    }

    public static Wood push(Wood cur, boolean plus){
        int adder = plus ? 1 : -1;
        Wood wood;
        if(cur.vertical) wood = new Wood(cur.x+adder, cur.y, cur.depth+1, cur.vertical);
        else wood = new Wood(cur.x, cur.y+adder, cur.depth+1, cur.vertical);
        int[] positions = wood.getPositions();
        if(!check(positions)) return null;
        return wood;
    }

    public static Wood turn(Wood cur){
        Wood w1 = roll(cur, true), w2 = roll(cur, false);
        if(w1 != null && w2 != null)
            return new Wood(cur.x, cur.y, cur.depth+1, !cur.vertical);
        else return null;
    }

    public static boolean check(int[] positions){
        for(int i=0; i<=4; i+=2){
            int x = positions[i], y = positions[i+1];
            if(x < 0 || n <= x || y < 0 || n <= y) return false;
            if(map[x][y] == 1) return false;
        }
        return true;
    }

    public static boolean isEnd(Wood cur){
        int[] positions = cur.getPositions();
        for(int i=0; i<=4; i+=2){
            int x = positions[i], y = positions[i+1];
            if(x < 0 || n <= x || y < 0 || n <= y) return false;
            if(map[x][y] != END) return false;
        }
        return true;
    }

    public static boolean isVisited(Wood cur){
        if(cur.vertical) return visited[1][cur.x][cur.y];
        else return visited[0][cur.x][cur.y];
    }

    public static void visit(Wood cur){
        if(cur.vertical) visited[1][cur.x][cur.y] = true;
        else visited[0][cur.x][cur.y] = true;
    }
}
class Wood{
    int x, y, depth;
    boolean vertical;
    
    public Wood(int x, int y, int depth, boolean vertical){
        this.x = x;
        this.y = y;
        this.depth = depth;
        this.vertical = vertical;
    }

    public int[] getPositions(){
        int[] result = new int[6];
        if(vertical){
            result[0] = x-1; result[1] = y;
            result[2] = x; result[3] = y;
            result[4] = x+1; result[5] = y;
        }
        else{
            result[0] = x; result[1] = y-1;
            result[2] = x; result[3] = y;
            result[4] = x; result[5] = y+1;
        }
        return result;
    }

    public String toString(){
        return "("+x+", "+y+", "+(vertical ? "V" : "H")+")";
    }
}
