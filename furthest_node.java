/*
https://school.programmers.co.kr/learn/courses/30/lessons/49189
프로그래머스 > 그래프 > 가장 먼 노드
  노드의 갯수 n과 노드들의 연결정보 edge가 주어졌을 때
  0 번노드에서 가장 멀리 떨어진 노드들의 갯수를 리턴하는 문제
*/

import java.util.*;
import java.math.*;

class Solution {
    // 노드갯수 n와 엣지가 주어졌을 때
    // 0번 노드에서 가장 멀리 떨어진 노드의 갯수를 리턴
    
    // 1반복 -> 0과 연결된 노드들 추가
    // 2반복 -> 위의 집합과 연결된 노드들 추가
    // 
    // 현재 노드집합에 0번노드 추가
    // 현재 노드집합에서 탐색 할 수 있는 노드들 nextNodes에 저장
    // 현재노드집합 += nextNodes
    // 반복 중 현재 노드집합 크기가 n이되면(탐색 완료) 해당시점의 nextNodes의 갯수를 리턴
    
    public int solution(int n, int[][] edge) {
        List<Integer>[] graph = new List[n];
        for(int i=0; i<n; i++) graph[i] = new ArrayList<>();
        for(int i=0; i<edge.length; i++){
            int a = edge[i][0]-1, b = edge[i][1]-1;
            graph[a].add(b); graph[b].add(a);
        }
        
        Set<Integer> curNodes = new HashSet<>();
        Set<Integer> nextNodes = new HashSet<>();
        curNodes.add(0); nextNodes.add(0);
        while(true){
            nextNodes = get_nextNodes(graph, nextNodes, curNodes);
            curNodes.addAll(nextNodes);
            if(curNodes.size() == n){
                return nextNodes.size();
            }
        }
    }
    
    public Set<Integer> get_nextNodes(List<Integer>[] graph, Set<Integer> nextNodes, Set<Integer> prevNodes){
        Set<Integer> result = new HashSet<>();
        for(int next : nextNodes){
            for(int addition : graph[next]){
                if(prevNodes.contains(addition)) continue;
                result.add(addition);
            }
        }
        return result;
    }
}
