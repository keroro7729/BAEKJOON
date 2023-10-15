/****************
* 반만 맞은 실패한 풀이이다. 75/100점
  다이나믹 프로그래밍과 BFS를 활용해 문제를 풀어보려고 했지만
  BFS의 경우 depth가 더 깊지만 가중치는 더 적은 경우를 탐지하지 못한다.
  가중치 그래프 탐색 알고리즘인 다익스트라 알고리즘을 공부해 다시 풀어볼 생각이다.
**************************************************************************/


import java.util.*;
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        //그래프 구성
        s--; a--; b--;
        graph = new int[n][n];
        int s1, s2;
        for(int i=0; i<fares.length; i++){
            s1 = fares[i][0] - 1;
            s2 = fares[i][1] - 1;
            graph[s1][s2] = graph[s2][s1] = fares[i][2];
        }
        
        //그래프에는 항상 두지점의 최소요금이 들어있어야 함! a->b보다 a->i->b가 더싼 경우!!
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(graph[i][j] == 0) continue;
                for(int k=0; k<n; k++){
                    if(graph[i][k] == 0) continue;
                    if(graph[j][k] == 0) continue;
                    if(graph[i][j] > graph[i][k]+graph[k][j]) {
                        graph[i][j] = graph[j][i] = graph[i][k]+graph[k][j];
                    }
                }
            }
        }
        
        //요금 최소값 탐색
        update_graph(s);
        update_graph(a);
        update_graph(b);
        int min = graph[s][a] + graph[s][b]; //각자 타고가는 경우
        int val;
        for(int i=0; i<n; i++) {
            if(i != s && graph[s][i] == 0) continue; // i가 s or a or b에 도달할수 없는 경우
            if(i != a && graph[i][a] == 0) continue;
            if(i != b && graph[i][b] == 0) continue;
            
            if(i == a) val = graph[s][i] + graph[i][b]; // s->a->b의 경우
            else if(i == b) val = graph[s][i] + graph[i][a]; // s->b->a의 경우
        	else val = graph[s][i] + graph[i][a] + graph[i][b]; // s->i, i->a, i->b의 경우
            
        	if(val < min) min = val;
        }
        return min;
    }
    
    //다이나믹 프로그래밍, BFS활용
    int[][] graph;
    Queue<Integer> que = new LinkedList<>();
    int[] visit;
    // (s -> 모든지점)의 최소요금을 구한다.
    public void update_graph(int s) {
    	visit = new int[graph.length];
    	que.clear();
    	for(int i=0; i<graph.length; i++)
    		if(graph[s][i] != 0) que.add(i);
    	visit[s] = 1;
        
    	int cur, tmp;
    	while(!que.isEmpty()) {
    		cur = que.poll();
    		if(visit[cur] == 1) continue;
    		visit[cur] = 1;
            
    		for(int next=0; next<graph.length; next++) {
    			if(graph[cur][next] == 0) continue;
                que.add(next);
                tmp = graph[s][cur]+graph[cur][next];
    			if(graph[s][next] != 0) {
    				if(tmp < graph[s][next]) {
    					graph[s][next] = graph[next][s] = tmp;
    				}
    			}
    			else {
    				graph[s][next] = graph[next][s] = tmp;
    			}
    		}
    	}
    }
}
