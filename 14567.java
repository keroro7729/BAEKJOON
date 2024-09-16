// https://www.acmicpc.net/problem/14567
// 선수과목 (Prerequisite)

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

        // initialize
        int[] answer = new int[n];
        Queue<QNode> que = new LinkedList<>();
        for(int i : graph.getNoEntryNodes())
            que.add(new QNode(i, 1));

        // solution
        while(!que.isEmpty()){
            QNode cur = que.poll();
            answer[cur.index] = cur.term;
            
            graph.remove(cur.index);
            for(int i : graph.getNoEntryNodes())
                que.add(new QNode(i, cur.term+1));
        }

        // output
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++)
            sb.append(answer[i]).append(' ');
        System.out.println(sb);
    }
}
class Graph{
    private List<Integer>[] graph;
    private int[] entry, visited;
    private int size;

    public Graph(int n){
        graph = new List[n];
        for(int i=0; i<n; i++)
            graph[i] = new ArrayList<>();
        entry = new int[n];
        visited = new int[n];
        size = n;
    }

    public void add(int a, int b){
        graph[a].add(b);
        entry[b]++;
    }

    public void remove(int node){
        for(int i : graph[node])
            entry[i]--;
    }

    public List<Integer> getNoEntryNodes(){
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<size; i++){
            if(visited[i] == 1) continue;
            if(entry[i] == 0){
                result.add(i);
                visited[i] = 1;
            }
        }
        return result;
    }
}
class QNode{
    int index, term;
    public QNode(int i, int t){
        index = i;
        term = t;
    }
}
