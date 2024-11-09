// https://www.acmicpc.net/problem/1865
// 웜홀 

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static int n, m, w;
    private static List<Edge> edges = new ArrayList<>();
    private static List<Integer> list = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testsize = Integer.parseInt(reader.readLine());
        for(int T=0; T<testsize; T++){
            StringTokenizer st = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            init();
            int s, e, t, answer = 0;
            for(int i=0; i<m; i++){
                st = new StringTokenizer(reader.readLine());
                s = Integer.parseInt(st.nextToken()) - 1;
                e = Integer.parseInt(st.nextToken()) - 1;
                t = Integer.parseInt(st.nextToken());
                edges.add(new Edge(s, e, t));
                edges.add(new Edge(e, s, t));
            }
            for(int i=0; i<w; i++){
                st = new StringTokenizer(reader.readLine());
                s = Integer.parseInt(st.nextToken()) - 1;
                e = Integer.parseInt(st.nextToken()) - 1;
                t = Integer.parseInt(st.nextToken()) * -1;
                edges.add(new Edge(s, e, t));
                list.add(s);
            }

            for(int start : list){
                if(bellmanFord(start)){
                    answer = 1;
                    break;
                }
            }
            if(answer == 1)
                sb.append("YES");
            else sb.append("NO");
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static int[] distance;
    private static boolean bellmanFord(int start){
        Arrays.fill(distance, INF);
        distance[start] = 0;
        for(int i=0; i<n; i++){
            for(Edge edge : edges){
                if(distance[edge.s] != INF && distance[edge.e] > distance[edge.s] + edge.t){
                    distance[edge.e] = distance[edge.s] + edge.t;
                    if(i == n-1) return true;
                }
            }
        }
        return false;
    }

    private static void init(){
        edges.clear();
        list.clear();
        distance = new int[n];
    }
}
class Edge{
    int s, e, t;
    public Edge(int s, int e, int t){
        this.s = s;
        this.e = e;
        this.t = t;
    }
}
