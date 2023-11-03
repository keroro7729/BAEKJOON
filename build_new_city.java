/*
https://school.programmers.co.kr/learn/courses/30/lessons/86053
프로그래머스 > 월간 코드 챌린지 시즌3 > 금과 은 운반하기
  새로운 도시를 지으려고 할때 금과 은이 a, b만큼 필요하고, 각 도시마다 금,은 보유량, 마차의 수송 용량, 건설지점까지의 편도 시간이 주어진다.   
  새 도시를 짓는데 필요한 금의 양 a, 은의 양 b, 각 도시들의 금 보유량 g[], 은 보유량 s[], 한번에 수송 가능한 무게(용량)w [], 건설지점까지 가는데 걸리는 시간 t[](편도)
  새 도시를 짓는데 걸리는 최소 시간을 구하시오.

  처음 이문제를 접했을 때 당연히 시간별로 정렬한 후 문제를 풀고 싶었지만, 메모리, 시간이 부족해서 정렬을 할 수 없는 문제였다.
  시간이 주어졌을 때 운반량을 계산하는 것은 가능하기 때문에, ** 시간을 기준으로 이진탐색! ** 을 해서 문제를 풀어보았다.
  시간이 주어졌을 때 각 자원의 최소 수송량과, 둘 중 어떤것이든 수송할 수 있는 수송량을 계산해 a, b를 충족시킬 수 있는지 확인한다.
  수송량이 부족하면 start = mid, mid = (start+end)/2 로, 수송량이 충족(초과)되면 end = mid, mid = (start+end)/2로 조정 후 반복.
  
  도시 수가 많아지면 아무리 이진탐색이여도 시간이 좀 걸리지 않을까 걱정했는데 시간성능은 굉장히 준수했다.
  end의 초기값에 따라 너무 크면 오버플로우가 발생할 수 있고, 너무 적으면 정답값까지 도달하지 못하는 경우가 생긴다.
  최악의 경우를 가정하면 t 시간은 1인데 w 거리는 최대값(10^9)일 때 오버플로우가 발생하지 않으려면 end 초기값은 Long.MAX_VALUE / 500000000 -1 이여야 한다.
  테스트 케이스가 이런 최악의 경우는 없고, 정답값이 큰 경우가 있는거 같다. Long.MAX_VALUE/5000로 초기화해서 모든 테스트케이스를 통과했다.
  만약 오버플로우까지 고려해야하는 테스트케이스가 있었다면 tmp, gold, silver, either 변수를 BigInteger로 수정해야했을거 같다.
*/

import java.util.*;
import java.math.*;

class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        int n = t.length;
      // 시간을 기준으로 이진탐색!!
        long start = 0, end = Long.MAX_VALUE/5000, m_time = (end+start) / 2;
        long tmp=0, gold=0, silver=0, either=0;
      // gold, silver는 금,은의 최소 수송량, either은 금과 은 둘다 가능한 수송량
        boolean stop = false, lastCheck = false;
        while(true){
            if(start >= end) return m_time;
            gold = 0; silver = 0; either = 0; stop = false;
            for(int i=0; i<n; i++){
                tmp = m_time / t[i];
                if(tmp == 0) continue;
                tmp = (tmp+1) / 2 * w[i]; // i번 도시의 m_time동안의 수송량
                if(g[i] > tmp && s[i] > tmp) // 금,은 각각의 보유량 모두 수송량보다 충분하다면
                  either += tmp;
                else if(g[i] + s[i] <= tmp){ // 금,은 보유총량이 수송량보다 적다면
                    gold += g[i];
                    silver += s[i];
                }
                else{
                	if(g[i] > tmp) { // 금만 충분한 경우
                		gold += tmp - s[i];
                		either += s[i];
                	}
                	else if(s[i] > tmp) { // 은만 충분한 경우
                		silver += tmp - g[i];
                		either += g[i];
                	}
                	else { // 둘다 부족한 경우
                		gold += tmp - s[i];
                    silver += tmp - g[i];
                    either += s[i] + g[i] - tmp;
                	}
                }
                //early stop
                if(!lastCheck && check(a,b,gold,silver,either)){
                    end = m_time;
                    m_time = (end+start) / 2;
                    if(end-start <= 2) lastCheck = true;
                    stop = true;
                    break;
                }
            }
            if(stop) continue;
          // 모든 도시의 수송량을 합쳐도 자원이 부족함 -> start = mid
            if(!check(a,b,gold,silver,either)){
                if(lastCheck) return end;
                start = m_time;
                m_time = (end+start) / 2;
                if(end-start <= 2) lastCheck = true;
            }
          // 총수송량이 필요 자원보다 많거나 같음 -> end = mid
            else if(check(a,b,gold,silver,either)){
                if(lastCheck) return m_time;
                end = m_time;
                m_time = (end+start) / 2;
                if(end-start <= 2) lastCheck = true;
            }
        }
    }
    public boolean check(int a, int b, long gold, long silver, long either) {
		a -= gold;
		if(a < 0) a = 0;
		b -= silver;
		if(b < 0) b = 0;
		if(a+b > either) return false;
		else return true;
	}
}
