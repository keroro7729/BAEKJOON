/*
https://school.programmers.co.kr/learn/courses/30/lessons/42628
프로그래머스 > 힙(Heap) > 이중우선순위큐
  명령어 리스트가 주어졌을 때, 명령어를 순서대로 실행할 후, 큐에 남아있는 원소의 최대값, 최소값을 리턴하시오.
  명령어는 "I 넘버" (insert number), "D -1" (최소값 delete), "D 1" (최대값 delete) 세가지 이다.
*/

import java.util.*;

class Solution {
    // 정렬상태를 유지하는 LinkedList에 값들을 추가, 삭제하며 시뮬
    
    public int[] solution(String[] operations) {
        int n = operations.length;
        List<Integer> sorted_list = new LinkedList<>();
        for(int i=0; i<n; i++){
            String oper = operations[i];
            if(oper.charAt(0) == 'I'){
                int value = Integer.parseInt(oper.substring(2));
                insert(sorted_list, value);
            }
            else if(oper.equals("D -1")){
                if(sorted_list.size() != 0){
                    sorted_list.remove(0);
                } 
            }
            else if(oper.equals("D 1")){
                if(sorted_list.size() != 0){
                    sorted_list.remove(sorted_list.size()-1);
                } 
            }
        }
        
        int min = 0, max = 0;
        if(sorted_list.size() > 0){
            min = sorted_list.get(0);
            max = sorted_list.get(sorted_list.size()-1);
        }
        int[] answer = {max, min};
        return answer;
    }
    
    public void insert(List<Integer> list, int value){
        for(int i=0; i<list.size(); i++){
            if(list.get(i) < value) continue;
            else {
                list.add(i, value);
                return;
            }
        }
        list.add(value);
        return;
    }
}
