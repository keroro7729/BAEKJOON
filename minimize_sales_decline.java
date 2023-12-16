/*
코딩테스트 연습 > 2021 KAKAO BLIND RECRUITMENT > 매출 하락 최소화
https://school.programmers.co.kr/learn/courses/30/lessons/72416
  처음으로 lv4문제에 도전해서 정말 많이 고민하고 나름 답에도 근접했지만..
  결국 풀지못하고 해설을 보고 완성한 코드이다.
  후위순회하며 리프노드부터 계산해나가며 dynamic programing을 활용하는 것 까지는 잘했지만
  dp를 각 사원별로 참/불참 시의 매출정보를 모두 저장하며 전체를 계산해나가야 했다.
  
  난 graph자체를 팀별로 구성해 팀별 팀장의 참/불참시를 저장했는데
  이 경우 연속해서 팀장이 가는 것이 유리한 상황에서 이전 팀의 팀장이 빠지며
  이전 팀은 모두 불참하여 최소 한명은 참여해야하는 조건이 지켜지지 않게 되었다.
*/

import java.util.*;
import java.math.*;

class Solution{
	
	List<List<Integer>> graph;
	int[][] memo;
	int[] sales;

	public int solution(int[] sales, int[][] links) {
		this.sales = sales;
		// set graph
		graph = new ArrayList<>();
		for(int i=0; i<sales.length; i++)
			graph.add(new ArrayList<>());
		for(int i=0; i<links.length; i++) {
			int a = links[i][0]-1, b = links[i][1]-1;
			graph.get(a).add(b);
		}
		
		memo = new int[sales.length][2];
		dfs(0);
		return Math.min(memo[0][0], memo[0][1]);
	}
	
	public void dfs(int start) {
		Stack<Integer> s = new Stack<>();
		boolean[] visited = new boolean[sales.length];
		List<Integer> order = new ArrayList<>();

    // get postorder traverse order
		s.push(start);
		visited[start] = true;
		while(!s.isEmpty()) {
			int cur = s.peek();
			boolean isLeaf = true;
			for(int emp : graph.get(cur)) {
				if(!visited[emp]) {
					visited[emp] = true;
					s.push(emp);
					isLeaf = false;
					break;
				}
			}
			if(isLeaf) {
				order.add(cur);
				s.pop();
			}
		}
		
		for(int cur : order) {
			int sum = 0, min = Integer.MAX_VALUE;
			boolean is_no_one = true;
			for(int emp : graph.get(cur)) {
				if(memo[emp][0] >= memo[emp][1]) {
					is_no_one = false;
					sum += memo[emp][1];
				}
				else {
					sum += memo[emp][0];
					if(!is_no_one) continue;
					int tmp = memo[emp][1] - memo[emp][0];
					min = Math.min(tmp, min);
				}
			}
			
			if(min == Integer.MAX_VALUE) min = 0;
			memo[cur][1] = sum + sales[cur];
			if(!is_no_one) 
				memo[cur][0] = sum;
			else
				memo[cur][0] = sum + min;
			
			//System.out.println(cur+" : "+memo[cur][1]+", "+memo[cur][0]);
		}
	}
}
