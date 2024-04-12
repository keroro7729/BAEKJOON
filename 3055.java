// https://www.acmicpc.net/problem/3055
// 탈출

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m, startx, starty;;
    static List<int[][]> map;
    static final int EMPTY = 0, WATER = 1, BLOCKED = 2, END = 3;
    static final int DX[] = {0,0,1,-1}, DY[] = {1,-1,0,0};
    static boolean visited[][];
    
    public static void main(String[] args) throws IOException {
        // input
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new ArrayList<>();
        map.add(new int[n][m]);
        for(int i=0; i<n; i++){
            String line = scan.readLine();
            for(int j=0; j<m; j++){
                switch(line.charAt(j)){
                    case '.': map.get(0)[i][j] = EMPTY; break;
                    case '*': map.get(0)[i][j] = WATER; break;
                    case 'X': map.get(0)[i][j] = BLOCKED; break;
                    case 'D': map.get(0)[i][j] = END; break;
                    case 'S': startx = i; starty = j; break;
                }
            }
        }

        // set maps
        while(true){
            int[][] cur = map.get(map.size()-1);
            int[][] next = deepcopy(cur);
            boolean noChanges = true;
            //printMap(cur);
            for(int i=0; i<n; i++)
                for(int j=0; j<m; j++)
                    if(cur[i][j] == WATER)
                        if(flood(i, j, next))
                            noChanges = false;
            if(noChanges) break;
            map.add(next);
        }

        // bfs solution
        Queue<Node> que = new LinkedList<>();
        visited = new boolean[n][m];
        que.add(new Node(startx, starty, 0));
        visited[startx][starty] = true;
        while(!que.isEmpty()){
            Node cur = que.poll();
            //System.out.println(cur);
            if(map.get(0)[cur.x][cur.y] == END){
                System.out.println(cur.depth);
                return;
            }
            for(int i=0; i<4; i++){
                int x = cur.x+DX[i], y = cur.y+DY[i];
                if(!check(x, y, cur.depth)) continue;
                que.add(new Node(x, y, cur.depth+1));
                visited[x][y] = true;
            }
        }
        System.out.println("KAKTUS");
    }

    public static boolean check(int x, int y, int depth){
        if(x < 0 || n <= x || y < 0 || m <= y) return false;
        if(visited[x][y]) return false;
        if(depth >= map.size()) depth = map.size()-1;
        if(map.get(depth)[x][y] == WATER || 
           map.get(depth)[x][y] == BLOCKED)
            return false;
        if(depth+1 < map.size() && map.get(depth+1)[x][y] == WATER)
            return false;
        return true;
    }

    // water in [x][y] floods and changes next, if somthing change return true
    public static boolean flood(int X, int Y, int[][] next){
        boolean result = false;
        for(int i=0; i<4; i++){
            int x = X+DX[i], y = Y+DY[i];
            if(x < 0 || n <= x || y < 0 || m <= y) continue;
            //System.out.println(x+", "+y+": "+next[x][y]);
            if(next[x][y] == EMPTY){
                next[x][y] = WATER;
                result = true;
            }
        }
        return result;
    }

    public static int[][] deepcopy(int[][] arr){
        int[][] result = new int[n][m];
        for(int i=0; i<n; i++) result[i] = arr[i].clone();
        return result;
    }

    public static void printMap(int[][] map){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                switch(map[i][j]){
                    case EMPTY: sb.append('.'); break;
                    case WATER: sb.append('W'); break;
                    case BLOCKED: sb.append('B'); break;
                    case END: sb.append('E'); break;
                }sb.append(' ');
            }sb.append('\n');
        }
        System.out.println(sb);
    }
}

class Node{
    int x, y, depth;
    public Node(int x, int y, int depth){
        this.x = x;
        this.y = y;
        this.depth = depth;
    }
    @Override
    public String toString(){
        return "("+x+", "+y+" : "+depth+")";
    }
}
