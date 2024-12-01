// https://www.acmicpc.net/problem/15681
// 트리와 쿼리

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int n, r, q, memo[];
    private static List<Integer>[] graph;
    private static Node root;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken()); // 정점의 갯수
        r = Integer.parseInt(st.nextToken()) - 1; // 루트 번호
        q = Integer.parseInt(st.nextToken()); // 쿼리 갯수
        memo = new int[n];
        graph = new List[n];
        for(int i=0; i<n; i++) graph[i] = new ArrayList<>();
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            graph[u].add(v);
            graph[v].add(u);
        }
        root = init(r, null);
        for(int i=0; i<q; i++){
            int v = Integer.parseInt(reader.readLine()) - 1;
            sb.append(memo[v]).append('\n');
        }
        System.out.println(sb);
    }

    private static Node init(int id, Node parent){
        Node node = new Node(id, parent);
        memo[id] = 1;
        for(int childId : graph[id]){
            if(parent != null && childId == parent.id) continue;
            node.children.add(init(childId, node));
            memo[id] += memo[childId];
        }
        return node;
    }
}

class Node{
    int id;
    Node parent;
    List<Node> children;
    public Node(int id, Node parent){
        this.id = id;
        this.parent = parent;
        children = new ArrayList<>();
    }
}
