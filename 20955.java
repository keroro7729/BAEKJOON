// https://www.acmicpc.net/problem/20955
// 민서의 응급 수술

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m, count;
    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = initGraph(n);
        for(int i=0; i<m; i++){
            st = new StringTokenizer(scan.readLine());
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            graph[u].add(v);
            graph[v].add(u);
        }

        // solution
        // 순환이 감지되면 자르고
        // 다돌았는데 방문하지 않은 노드와 연결
        Queue<Integer> que = new LinkedList<>();
        visited = new boolean[n];
        count = 0;
        int prevStart = -1;
        while(true){
            int start = getUnvisited();
            if(start == -1) break;
            if(prevStart != -1){
                graph[prevStart].add(start);
                count++;
            }
            que.add(start);
            visited[start] = true;
            prevStart = start;
            while(!que.isEmpty()){
                int cur = que.poll();
                List<Integer> nextNodes = graph[cur];
                for(int i=nextNodes.size()-1; i>=0; i--){
                    int next = nextNodes.get(i);
                    graph[next].remove((Integer)cur); // 단방향으로 변환
                    if(visited[next]){
                        graph[cur].remove((Integer)next); // 순환을 제거
                        count++;
                    }
                    else{
                        visited[next] = true;
                        que.add(next);
                    }
                }
            }
            //printState();
        }
        
        System.out.println(count);
    }

    public static int getUnvisited(){
        for(int i=0; i<n; i++)
            if(!visited[i]) return i;
        return -1;
    }

    public static List<Integer>[] initGraph(int n){
        List<Integer>[] result = new List[n];
        for(int i=0; i<n; i++)
            result[i] = new ArrayList<>();
        return result;
    }

    public static void printState(){
        for(int i=0; i<n; i++)
            System.out.println(i+" : "+graph[i]);
        for(int i=0; i<n; i++)
            System.out.print((visited[i] ? 1 : 0)+" ");
        System.out.println("\n\n");
    }
}
