// https://www.acmicpc.net/problem/1600
// BOJ 1600 말이 되고픈 원숭이

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int k, n, m, map[][];
    static int walkx[] = {1,-1,0,0};
    static int walky[] = {0,0,1,-1};
    static int horsex[] = {-2,-1,1,2,2,1,-1,-2};
    static int horsey[] = {1,2,2,1,-1,-2,-2,-1};
    static int dp[][][]; // dp[jump][x][y] = count

    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(scan.readLine());
        StringTokenizer st = new StringTokenizer(scan.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(scan.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // solution
        dp = new int[k+1][n][m];
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(0, 0, 0, 0));
        while(!que.isEmpty()){
            Node cur = que.poll();
            if(cur.x == n-1 && cur.y == m-1){
                System.out.println(cur.count);
                //printDp();
                return;
            }
            for(int i=0; i<4; i++){
                Node next = new Node(cur.jump, cur.x+walkx[i], cur.y+walky[i], cur.count+1);
                if(!check(next)) continue;
                que.add(next);
                dp[next.jump][next.x][next.y] = next.count;
            }
            if(cur.jump == k) continue;
            for(int i=0; i<8; i++){
                Node next = new Node(cur.jump+1, cur.x+horsex[i], cur.y+horsey[i], cur.count+1);
                if(!check(next)) continue;
                que.add(next);
                dp[next.jump][next.x][next.y] = next.count;
            }
        }
        System.out.println(-1);
        //printDp();
    }
    
    public static boolean check(Node node){
        if(node.x < 0 || n <= node.x || node.y < 0 || m <= node.y)
            return false;
        if(map[node.x][node.y] == 1)
            return false;
        if(dp[node.jump][node.x][node.y] != 0 && 
           dp[node.jump][node.x][node.y] <= node.count)
            return false;
        return true;
    }

    public static void printDp(){
        for(int jump=0; jump<=k; jump++){
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    System.out.print(dp[jump][i][j]+" ");
                }System.out.println();
            }System.out.println("\n");
        }System.out.println("\n");
    }
}

class Node{
    int jump, x, y, count;
    public Node(int jump, int x, int y, int count){
        this.jump = jump;
        this.x = x;
        this.y = y;
        this.count = count;
    }
}
