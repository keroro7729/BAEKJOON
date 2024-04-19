// https://www.acmicpc.net/problem/7569
// 토마토

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m, h, map[][][], count = 0;
    static boolean visited[][][];
    static final int RIPEN = 1, UNRIPEN = 0, EMPTY = -1;
    static final int[] DIRX = {1, -1, 0, 0, 0, 0}, 
                       DIRY = {0, 0, 1, -1, 0, 0}, 
                       DIRZ = {0, 0, 0, 0, 1, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h][n][m];
        Queue<Node> que = new LinkedList<>();
        visited = new boolean[h][n][m];
        for(int i=0; i<h; i++){
            for(int j=0; j<n; j++){
                st = new StringTokenizer(reader.readLine());
                for(int k=0; k<m; k++){
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if(map[i][j][k] == RIPEN){
                        que.add(new Node(j, k, i, 0));
                        visited[i][j][k] = true;
                    }
                }
            }
        }

        while(!que.isEmpty()){
            Node cur = que.poll();
            count = cur.depth;
            for(int i=0; i<6; i++){
                int x = cur.x+DIRX[i], y = cur.y+DIRY[i], z = cur.z+DIRZ[i];
                if(!check(x, y, z)) continue;
                que.add(new Node(x, y, z, cur.depth+1));
                visited[z][x][y] = true;
            }
        }
        if(isAllRipen())
            System.out.println(count);
        else System.out.println(-1);
    }

    public static boolean check(int x, int y, int z){
        if(x < 0 || n <= x || y < 0 || m <= y || z < 0 || h <= z)
            return false;
        if(visited[z][x][y]) return false;
        if(map[z][x][y] != UNRIPEN) return false;
        return true;
    }

    public static boolean isAllRipen(){
        for(int i=0; i<h; i++){
            for(int j=0; j<n; j++){
                for(int k=0; k<m; k++){
                    if(visited[i][j][k]) continue;
                    if(map[i][j][k] == EMPTY) continue;
                    return false;
                }
            }
        }
        return true;
    }
}
class Node{
    int x, y, z, depth;
    public Node(int x, int y, int z, int depth){
        this.x = x;
        this.y = y;
        this.z = z;
        this.depth = depth;
    }
}
