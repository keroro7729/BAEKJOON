/*
https://school.programmers.co.kr/learn/courses/30/lessons/43163
프로그래머스 > 깊이/너비 우선 탐색(DFS/BFS) > 단어 변환
  길이가 같은 문자열들이 주어진다. begin : 초기값, target : 목표값, words : 단어배열
  begin부터 시작해서 words에 있는 단어들 중 한글자만 다른 단어로 변환하며 최종적으로 target으로 변환 될 때 까지
  변환 최소횟수를 구하시오. target으로 변환 할 수 없는 경우 0을 리턴한다.
*/


import java.util.*;

class Solution {
    // x -> y로 변환 가능하다 == 한글자만 다르다
    // begin을 초기값으로, target에 도달할 때 까지
    // bfs방식으로 변환과정을 탐색
    // 최소 변환수는 탐색 depth
    int n;
    public int solution(String begin, String target, String[] words) {
        n = words.length+1;
        List<Integer>[] graph = new List[n];
        for(int i=0; i<n; i++){
            graph[i] = new ArrayList<>();
        }
        
        Queue<State> que = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        que.add(new State(0, begin));
        while(!que.isEmpty()){
            State cur = que.poll();
            //System.out.println("depth:"+cur.depth+", str:"+cur.str);
            visited.add(cur.str);
            if(cur.str.equals(target)) return cur.depth;
            for(String next : reachables(words, cur.str)){
                if(!visited.contains(next))
                    que.add(new State(cur.depth+1, next));
            }
        }
        return 0;
    }
    
    public List<String> reachables(String[] words, String begin){
        List<String> result = new ArrayList<>();
        int len = begin.length(), count;
        boolean reachable;
        for(String word : words){
            count = 0; reachable = true;
            for(int i=0; i<len; i++){
                if(word.charAt(i) != begin.charAt(i)) count++;
                if(count == 2) {
                    reachable = false;
                    break;
                }
            }
            if(reachable) result.add(word);
        }
        return result;
    }
}

class State{
    String str;
    int depth;
    public State(int depth, String str){
        this.depth = depth;
        this.str = str;
    }
}
