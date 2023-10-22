/*
백준 1753 최단경로 문제
https://www.acmicpc.net/problem/1753
방향 가중치 그래프와 시작점이 주어졌을 때
시작점으로 부터 모든점의 거리를 출력하는 문제

다익스트라 알고리즘을 구현해보는 문제이다.
인접 행렬은 메모리크기를 초과하기 때문에 인접 리스트로 구현
인접리스트에 가중치를 저장하기 위해 Node를 만들어 준다.
Node는 우선순위 큐에 넣어야 하기 때문에 Comparable 인터페이스를 impliments한다.

다익스트라 알고리즘
시작점부터 시작해서, 현재 노드에서 탐색할 수 있는 다음 노드를 큐에 넣고
큐중에 가장 가중치가 작은 노드를 먼저 방문하며 모든 접근가능한 노드를 탐색한다.
여기서 가중치는 (시작점부터 현재노드까지의 가중치 + 현재노드부터 다음노드까지의 가중치) 이다.
*/

import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	public static void main(String[] args) throws IOException{
    	BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));    	
    	StringBuilder sb = new StringBuilder();
    	StringTokenizer st = new StringTokenizer(scan.readLine());
    	
    	// input
    	int n = Integer.parseInt(st.nextToken());
    	int t = Integer.parseInt(st.nextToken());
    	int s = Integer.parseInt(scan.readLine())-1;
    	List[] graph = new List[n];
    	for(int i=0; i<n; i++) 
    		graph[i] = new ArrayList<Node>();
    	for(int i=0; i<t; i++) {
    		st = new StringTokenizer(scan.readLine());
    		int v1 = Integer.parseInt(st.nextToken()) -1;
    		int v2 = Integer.parseInt(st.nextToken()) -1;
    		int d = Integer.parseInt(st.nextToken());
    		graph[v1].add(new Node(v2, d));
    	}
    	
    	// solution dijkstra
      // 시작점부터 i 까지의 거리를 저장하는 배열
    	int distance[] = new int[n];
    	for(int i=0; i<n; i++)
    		distance[i] = Integer.MAX_VALUE; // 무한대로 초기화

      // 우선순위 큐에 시작점s를 넣고 s-s의 거리는 0으로 초기화
    	PriorityQueue<Node> que = new PriorityQueue<>();
    	Node cur = new Node(s, 0);
    	que.add(cur);
    	distance[s] = 0;
    
    	while(!que.isEmpty()) {
    		cur = que.poll();

        // 현재 노드에서 탐색가능한 다음 노드
    		for(Node next : (ArrayList<Node>)graph[cur.v]) {
          // 새로운 거리값이 더 작으면 업데이트
    			if(distance[next.v] > distance[cur.v] + next.d) {
    				distance[next.v] = distance[cur.v] + next.d;
    				que.add(new Node(next.v, distance[cur.v] + next.d)); // 그냥 next를 넣는것이 아님
            // 우선순위 큐에 들어가는 가중치는 distance배열의 거리값. (graph의 가중치 값이 아님)
            // s->cur 까지의 거리 + cur->next의 거리
    			}
    		}
    	}
    	
    	// output
    	for(int i=0; i<n; i++)
    		if(distance[i] == Integer.MAX_VALUE) sb.append("INF\n");
    		else sb.append(distance[i]).append('\n');
    	System.out.println(sb);
	}
}
class Node implements Comparable<Node>{
	int v;
	int d;
	public Node(int v, int d) {
		this.v = v;
		this.d = d;
	}
	@Override
	public int compareTo(Node other) {
		if(this.d > other.d) return 1;
		else if(this.d == other.d) return 0;
		else return -1;
	}
}
