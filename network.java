/*
https://school.programmers.co.kr/learn/courses/30/lessons/43162
프로그래머스 > 깊이/너비 우선 탐색(DFS/BFS) > 네트워크
  컴퓨터의 갯수 n과, 컴퓨터들의 연결정보를 나타내는 인접행렬 (1이면 해당 열번호, 행번호의 컴퓨터가 연결되어있다) 이 주어졌을 때
  그룹의 갯수를 리턴하는 문제. 서로 연결되어 있는 컴퓨터들은 하나의 그룹으로 생각한다.
*/

import java.util.*;

class Solution {
    // 인접 행렬이 주어졌을 때, 그룹의 갯수를 출력하는 문제
    
    // 노드하나를 고르고 탐색
    // 탐색이 끝나면 count++
    // 아직 visit하지 않은 노드중 하나 고르고 반복
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        Queue<Integer> que = new LinkedList<>();
        
        for(int i=0; i<n; i++){
            if(visited[i]) continue;
            int start = i;
            que.add(start);
            while(!que.isEmpty()){
                int cur = que.poll();
                visited[cur] = true;
                for(int j=0; j<n; j++){
                    if(!visited[j] && computers[cur][j] == 1)
                        que.add(j);
                }
            }
            answer++;
        }
        return answer;
    }
}
