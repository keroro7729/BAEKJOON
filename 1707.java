// https://www.acmicpc.net/problem/1707
// 이분 그래프

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int v, e, color[];
    private static List<Integer>[] graph;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testcase = Integer.parseInt(reader.readLine());
        for(int T=0; T<testcase; T++){
            StringTokenizer st = new StringTokenizer(reader.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            init();
            for(int i=0; i<e; i++){
                st = new StringTokenizer(reader.readLine());
                int v1 = Integer.parseInt(st.nextToken()) - 1;
                int v2 = Integer.parseInt(st.nextToken()) - 1;
                graph[v1].add(v2);
                graph[v2].add(v1);
            }
            if(isBinaryGraph())
                sb.append("YES");
            else sb.append("NO");
            sb.append('\n');
        }
        System.out.println(sb);
    }
    
    private static boolean isBinaryGraph(){
        for(int i=0; i<v; i++){
            if(color[i] != 0) continue;
            else if(!bfs(i)) return false;
        }
        return true;
    }
    
    private static boolean bfs(int start){
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        color[start] = 1;
        while(!que.isEmpty()){
            int cur = que.poll();
            int nextColor = color[cur] == 1 ? 2 : 1;
            for(int next : graph[cur]){
                if(color[next] == 0){
                    que.add(next);
                    color[next] = nextColor;
                }
                else if(color[next] != nextColor)
                    return false;
            }
        }
        return true;
    }
    
    private static void init(){
        graph = new List[v];
        for(int i=0; i<v; i++)
            graph[i] = new ArrayList<>();
        color = new int[v];
    }
}
