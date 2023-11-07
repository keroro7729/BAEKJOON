/*
https://school.programmers.co.kr/learn/courses/30/lessons/12938
프로그래머스 > 연습문제 > 최고의 집합
  집합의 원소갯수 n과, 집합의 모든 원소의합 s가 주어졌을 때
  중복을 허용하는 집합(set)중 n과 s를 만족하면서 모든 원소의 곱이 가장 큰 최고의 집합을 오름차순 정렬상태로 리턴하시오.
  만족하는 집합이 없는경우 {-1}를 리턴한다.
*/

class Solution {
    // n개의 원소의 합이 s가 되면서
    // n개의 원소의 곱이 최대가 되는 집합을 찾는 문제
    
    // 합s를 만족하는 자연수 집합?
  
    // 가장 작은 집합 {1,1,1, ... ,1}
    // if (n > s) return {-1};

    // 모든 원소의 곱이 가장 크다 == 모든 원소의 평균이 가장 크다
    // 합s에 가장 근접하게 큰 집합의 모든 원소는 s/n
    // s%n이 남음 얘네 뒤쪽부터 1씩 더해주면 됨
  
    public int[] solution(int n, int s) {
        int[] answer = {-1};
        if(n > s) return answer;
        
        answer = new int[n];
        int initial_value = s/n;
        int margin = s%n;
        for(int i=0; i<n; i++){
            if(i > n-1-margin) answer[i] = initial_value+1;
            else answer[i] = initial_value;
        }
        
        return answer;
    }
}
