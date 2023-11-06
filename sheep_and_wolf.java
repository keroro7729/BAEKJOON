/*
https://school.programmers.co.kr/learn/courses/30/lessons/92343
프로그래머스 > 2022 KAKAO BLIND RECRUITMENT > 양과 늑대
  info : index번의 노드가 양인지, 늑대인지 (양이면 0 늑대는 1)
  edges : 노드의 간선정보 (n-1 * 2)크기의 배열 ( [[0,1], [1,2] ....] 0번 1번 연결, 1번 2번 연결, ...)
  이진 트리구조에 양과 늑대가 있을 때, 노드를 탐색하며 최대한 많은 양을 모으려고 한다.
  탐색은 연결된 지점으로 자유롭게 할 수 있다.
  탐색 과정에서 늑대수가 양수보다 크거나 같아지면 늑대가 모든 양을 잡아먹는다.
  모을 수 있는 양의 최대값을 리턴하는 solution함수를 작성하시오.

  노드의 최대값이 17이고, 탐색중의 현재 상태를 계속해서 저장해야할 필요가 있기 때문에 비트마스크를 활용하였다.
  메인 솔루션의 동작을 보면, 그래프를 구성하고(인접행렬), 큐에 초기상태(0번만 방문한 상태 = 비트마스크1)를 넣어주고,
  큐가 빌 때 까지 최대양수를 업데이트 하면서, 현재 상태에 따른 다음 상태들을 큐에 넣으며 탐색을 반복한다.
  get_nextStates()메소드를 보면 현재 여유분(margin)을 계산하고, 현상태의 방문한 노드들, 
  다음 방문할 노드들(graph를 검사, margin이 1이라면 늑대는 패스)을 계산.
  다음 방문할 노드들은 순차적으로 추가되면서 방문한 노드들을 포함한다. 계산완료후 next_nodes - visited_nodes
*/

import java.util.*;
import java.math.*;

class Solution {
    // 현재 상태에서 방문 할 수 있는 노드의 조건
    // 1. 연결이 되어있는가
    // 2. 늑대라면 양-늑 > 1
    
    // bfs와 비슷하게 que에 탐색할 노드를 add해서 탐색해보자
    // 비트마스크 활용 visited 정보를 탐색상황마다 저장
    // 탐색 하면서 양의 최대값을 저장
    
    int n;
    public int solution(int[] info, int[][] edges) {
        n = info.length;
        int answer = 0;
        int[][] graph = new int[n][n];
        for(int i=0; i<n-1; i++){
            int a = edges[i][0], b = edges[i][1];
            graph[a][b] = graph[b][a] = 1;
        }
        
        Queue<Integer> que = new LinkedList<>();
        que.add(1); // bit mask
        while(!que.isEmpty()){
            int state = que.poll();
            answer = Math.max(answer, get_count(info, state)[0]);
            for(int next : get_nextStates(info, graph, state)){
                que.add(next);
            }
        }
        return answer;
    }
    
    public int[] get_count(int[] info, int state){
        int[] count = new int[2];
        for(int i=0; i<n; i++){
            if((state & 1) == 1){
                if(info[i] == 0) count[0]++;
                else count[1]++;
            }
            state >>= 1;
        }
        return count;
    }
    
    public int[] get_nextStates(int[] info, int[][] graph, int state){
        int tmp = state;
        int[] count = get_count(info, state);
        int margin = count[0] - count[1];
        Set<Integer> visited_nodes = new HashSet<>();
        Set<Integer> next_nodes = new HashSet<>();
        
        for(int i=0; i<n; i++){
            if((tmp & 1) == 1) {
                visited_nodes.add(i);
                for(int j=0; j<n; j++){
                    if(margin <= 1 && info[j] == 1) continue;
                    if(graph[i][j] == 1){
                        next_nodes.add(j);
                    }
                }
            }
            tmp >>= 1;
        }
        next_nodes.removeAll(visited_nodes);
        int[] result = new int[next_nodes.size()];
        int index = 0;
        for(int next : next_nodes){
            result[index++] = (state | (1 << next));
        }
        return result;
    }
}
