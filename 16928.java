// https://www.acmicpc.net/problem/16928
// 뱀과 사다리 게임

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static final int SIZE = 100, INIT = -1;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] up = new int[SIZE], down = new int[SIZE];
        Arrays.fill(up, INIT);
        Arrays.fill(down, INIT);
        for(int i=0; i<n; i++){
            st = new StringTokenizer(reader.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            up[s] = e;
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(reader.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            down[s] = e;
        }
    
        Queue<Node> que = new LinkedList<>();
        boolean[] visited = new boolean[SIZE];
        que.add(new Node(0, 0));
        visited[0] = true;
        while(!que.isEmpty()){
            Node cur = que.poll();
            //System.out.println(cur.depth+" "+cur.x);
            if(cur.x == 99){
                System.out.println(cur.depth);
                return;
            }
            for(int i=1; i<=6; i++){
                int next = cur.x+i;
                if(next >= SIZE) continue;
                if(up[next] != INIT)
                    next = up[next];
                else if(down[next] != INIT)
                    next = down[next];
                if(visited[next]) continue;
                que.add(new Node(next, cur.depth+1));
                visited[next] = true;
            }
        }
    }
}
class Node{
    int x, depth;
    public Node(int x, int depth){
        this.x = x;
        this.depth = depth;
    }
}
