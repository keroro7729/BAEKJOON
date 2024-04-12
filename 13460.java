// https://www.acmicpc.net/problem/13460
// 구슬 탈출 2

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m, map[][];
    static final int EMPTY = 0, BLOCKED = 1, END = 2;
    static int red, blue;
    static final int[] DIR_X = {-1, 0, 1, 0}, DIR_Y = {0, 1, 0, -1};
    static List<Integer> visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());
        n = Integer.parseInt(st.nextToken())-2;
        m = Integer.parseInt(st.nextToken())-2;
        map = new int[n][m];
        scan.readLine(); // drop
        for(int i=0; i<n; i++){
            String line = scan.readLine();
            for(int j=0; j<m; j++){
                switch(line.charAt(j+1)){
                    case '.': map[i][j] = EMPTY; break;
                    case '#': map[i][j] = BLOCKED; break;
                    case 'O': map[i][j] = END; break;
                    case 'R': red = getPos(i, j); break;
                    case 'B': blue = getPos(i, j); break;
                }
            }
        }

        Queue<Node> que = new LinkedList<>();
        visited = new ArrayList<>();
        visit(red, blue);
        que.add(new Node(red, blue, 0));
        while(!que.isEmpty()){
            Node cur = que.poll();
            //print(cur.red, cur.blue);
            if(isEnd(cur.blue)) continue;
            if(isEnd(cur.red)){
                System.out.println(cur.depth);
                return;
            }
            if(cur.depth == 10) continue;

            for(int i=0; i<4; i++){
                int[] result = move(cur.red, cur.blue, i);
                if(!isVisited(result[0], result[1])){
                    visit(result[0], result[1]);
                    que.add(new Node(result[0], result[1], cur.depth+1));
                }
            }
        }
        System.out.println(-1);
    }

    public static int[] move(int red, int blue, int dir){
        boolean redStop = false, blueStop = false;
        int rx = getX(red), ry = getY(red), bx = getX(blue), by = getY(blue);
        while(true){
            if(redStop && blueStop)
                return new int[]{getPos(rx, ry), getPos(bx, by)};
            if(!redStop){
                rx += DIR_X[dir]; ry += DIR_Y[dir];
                if(!check(rx, ry)){
                    rx -= DIR_X[dir]; ry -= DIR_Y[dir];
                    redStop = true;
                }
            }
            if(!blueStop){
                bx += DIR_X[dir]; by += DIR_Y[dir];
                if(!check(bx, by)){
                    bx -= DIR_X[dir]; by -= DIR_Y[dir];
                    blueStop = true;
                }
            }

            if(rx == bx && ry == by){
                if(map[rx][ry] == END){
                    redStop = true;
                    blueStop = true;
                    continue;
                }
                if(blueStop){
                    rx -= DIR_X[dir]; ry -= DIR_Y[dir];
                    redStop = true;
                }
                else{ //if(redStop)
                    bx -= DIR_X[dir]; by -= DIR_Y[dir];
                    blueStop = true;
                }
            }

            if(map[rx][ry] == END) redStop = true;
            if(map[bx][by] == END) blueStop = true;
        }
    }

    public static boolean check(int x, int y){
        if(x < 0 || n <= x || y < 0 || m <= y)
            return false;
        if(map[x][y] == BLOCKED)
            return false;
        return true;
    }

    public static void visit(int red, int blue){
        visited.add(red * 64 + blue);
    }
    public static boolean isVisited(int red, int blue){
        return visited.contains(red * 64 + blue);
    }

    public static boolean isEnd(int pos){
        return map[getX(pos)][getY(pos)] == END;
    }
    public static int getPos(int x, int y){
        return x * m + y;
    }
    public static int getX(int pos){
        return pos / m;
    }
    public static int getY(int pos){
        return pos % m;
    }

    public static void print(int red, int blue){
        System.out.println("red=("+getX(red)+","+getY(red)+") / blue=("+getX(blue)+", "+getY(blue)+")");
    }
}
class Node{
    int red, blue, depth;
    public Node(int red, int blue, int depth){
        this.red = red;
        this.blue = blue;
        this.depth = depth;
    }
}
