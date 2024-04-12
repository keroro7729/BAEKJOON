// https://www.acmicpc.net/problem/4485
// 녹색 옷 입은 애가 젤다지?

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, map[][], dp[][];
    static final int[] DIR_X = {0, 1, 0, -1}, DIR_Y = {1, 0, -1, 0};
    
    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        int num = 1;
        StringBuilder sb = new StringBuilder();
        while(true){
            n = Integer.parseInt(scan.readLine());
            if(n == 0) break;
            map = new int[n][n];
            for(int i=0; i<n; i++){
                StringTokenizer st = new StringTokenizer(scan.readLine());
                for(int j=0; j<n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append("Problem ").append(num++).append(": ").append(solution()).append('\n');
        }
        System.out.println(sb);
    }

    public static int solution(){
        dp = new int[n][n];
        for(int i=0; i<n; i++) for(int j=0; j<n; j++) dp[i][j] = Integer.MAX_VALUE;
        Queue<Node> que = new LinkedList<>();
        dp[0][0] = map[0][0];
        que.add(new Node(0, 0, dp[0][0]));
        while(!que.isEmpty()){
            Node cur = que.poll();
            if(cur.value > dp[cur.x][cur.y]) continue;
            
            for(int dir=0; dir<4; dir++){
                int x = cur.x+DIR_X[dir], y = cur.y+DIR_Y[dir];
                if(!check(x, y, cur.value)) continue;
                dp[x][y] = cur.value + map[x][y];
                que.add(new Node(x, y, dp[x][y]));
            }
        }
        return dp[n-1][n-1];
    }

    public static boolean check(int x, int y, int value){
        if(x < 0 || n <= x || y < 0 || n <= y) return false;
        if(dp[x][y] <= value+map[x][y]) return false;
        return true;
    }
}
class Node{
    int x, y, value;
    public Node(int x, int y, int value){
        this.x = x;
        this.y = y;
        this.value = value;
    }
}
