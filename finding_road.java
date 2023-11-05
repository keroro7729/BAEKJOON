/*
https://school.programmers.co.kr/learn/courses/30/lessons/42892
프로그래머스 > 2019 KAKAO BLIND RECRUITMENT > 길 찾기 게임
  노드들의 정보가 들어있는 nodeinfo (n*2 크기의 2차원배열)이 주어진다.
  nodeinfo : 인덱스+1 번 노드의 x좌표, y좌표가 들어있다.
  각 노드들의 정보에 따라 y값은 level, x값은 범위 정보가 되어 트리구조를 이루게된다. (링크의 그림을 확인해보는 것을 추천한다.)
  구성된 트리구조를 전위순회 후위순회한 결과를 배열로 리턴하는 solution을 완성하시오.

  문제의 조건을 이해하고 구현하는 것이 중요한 문제였다.
  주요 목표는 1. 그래프 구성하기 2. 구성된 그래프를 전위, 후위 순회하기
  그래프 구성은 y값을 기준으로 정렬하여 큰값부터 연결될 다음 노드를 구한다.
  자신보다 작은 y값 중 가장 큰값을 갖는 노드들 중 자신의 범위조건을 만족하는 노드가 연결될 다음 노드이다.
  이 과정에서 다음 노드는 부모노드(현재노드)의 x값을 기준으로 범위조건을 가지게 된다.
  전위 후위순회는 dfs로 간단히구현 가능하지만 순회 과정에서도 x값이 작은 노드부터 순차적으로 순회해야한다.
*/

class Solution{
	int n, first_index, last_index;
	List<List<Integer>> graph = new ArrayList<>();
	boolean[] visited;
	int[][] answer;
	
	public int[][] solution(int[][] nodeinfo){
		// init, sort sorted_index
		n = nodeinfo.length;
		int[][] sorted_index = new int[n][2];
		for(int i=0; i<n; i++) {
			graph.add(new ArrayList<>());
			sorted_index[i] = new int[] {i, nodeinfo[i][1]};
		}
		Arrays.sort(sorted_index, (o1,o2) -> o2[1] - o1[1]);
		
		// set graph
		int tmp, min, mid, max, top = 0;
		List<Integer> nextNodes = new ArrayList<>();
		for(int i=0; i<n; i++) {
			// bring cur condition
			int cur = sorted_index[i][0];
			if(i == 0) {
				top = cur;
				graph.get(cur).add(-1);
				graph.get(cur).add(Integer.MAX_VALUE);
			}
			min = graph.get(cur).get(0);
			mid = nodeinfo[cur][0];
			max = graph.get(cur).get(1);
			graph.get(cur).clear();
			
			// get nextNodes
			int height = nodeinfo[cur][1], next_height = -1;
			for(int j=i+1; ; j++) {
				if(j >= n) break;
				if(sorted_index[j][1] == height) continue;
				if(next_height == -1) 
					next_height = sorted_index[j][1];
				if(next_height != sorted_index[j][1]) break;
				int next = sorted_index[j][0];
				if(min < nodeinfo[next][0] && nodeinfo[next][0] < max)
					nextNodes.add(next);
			}
			
			// add cur->next, and set next condition
			for(int next : nextNodes) {
				graph.get(cur).add(next);
				int next_min, next_max;
				try {
					next_min = graph.get(next).get(0);
					next_max = graph.get(next).get(1);
					graph.get(next).clear();
				}catch(IndexOutOfBoundsException e) {
					next_min = -1;
					next_max = Integer.MAX_VALUE;
				}
				if(nodeinfo[next][0] < mid) {
					graph.get(next).add(Integer.max(next_min, min));
					graph.get(next).add(Integer.min(next_max, mid));
				}
				else {
					graph.get(next).add(Integer.max(next_min, mid));
					graph.get(next).add(Integer.min(next_max, max));
				}
			}
			nextNodes.clear();
		}
		System.out.println(graph);
		
		// set output
		answer = new int[2][n];
		visited = new boolean[n];
		first_index = last_index = 0;
		answer[0][first_index++] = top+1;
		dfs(top, nodeinfo);
		answer[1][last_index++] = top+1;
		return answer;
	}
	public void dfs(int start, int[][] nodeinfo) {
		visited[start] = true;
		for(int next : sort_by_x(graph.get(start), nodeinfo)) {
			if(!visited[next]) {
				answer[0][first_index++] = next+1;
				dfs(next, nodeinfo);
				answer[1][last_index++] = next+1;
			}
		}
	}
	public int[] sort_by_x(List<Integer> nodes, int[][] nodeinfo) {
		int n = nodes.size();
		int[][] ix = new int[n][2];
		for(int i=0; i<n; i++) {
			int cur = nodes.get(i);
			ix[i] = new int[] {cur, nodeinfo[cur][0]};
		}
		Arrays.sort(ix, (o1,o2) -> o1[1] - o2[1]);
		int[] result = new int[n];
		for(int i=0; i<n; i++) {
			result[i] = ix[i][0];
		}
		return result;
	}
}
