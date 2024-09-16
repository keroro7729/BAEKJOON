// https://www.acmicpc.net/problem/2252
// 줄 세우기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int n, m;
    private static Graph graph;
    
    public static void main(String[] args) throws IOException {
        // input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new Graph(n);
        for(int i=0; i<m; i++){
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph.add(a, b);
        }

        //long startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        Queue<Integer> que = new LinkedList<>();
        for(int node : graph.getNoEntryNodes())
            que.add(node);
        while(!que.isEmpty()){
            int cur = que.poll();
            sb.append(cur+1).append(' ');
            graph.remove(cur);
            for(int node : graph.getNoEntryNodes())
                que.add(node);
        }
        System.out.println(sb);
        //System.out.println(System.currentTimeMillis() - startTime + "ms");
    }
}

class Graph{
    private List<Integer>[] graph;
    private int[] entry; // count entry
    private boolean[] visited;

    public Graph(int n){
        graph = new List[n];
        for(int i=0; i<n; i++)
            graph[i] = new LinkedList<>();
        entry = new int[n];
        visited = new boolean[n];
    }

    public void add(int a, int b){
        graph[a].add(b);
        entry[b]++;
    }

    public List<Integer> getNoEntryNodes(){
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<entry.length; i++){
            if(visited[i]) continue;
            if(entry[i] == 0){
                result.add(i);
                visited[i] = true;
            }
        }
        return result;
    }

    public void remove(int rn){
        for(int cn : graph[rn])
            entry[cn]--;
        //graph[rn].clear();
    }
}
