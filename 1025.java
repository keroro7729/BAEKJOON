/*
https://www.acmicpc.net/problem/1025
백준 1025번 제곱수 찾기
  n, m과 n*m크기의 한자리 숫자 map이 주어진다.
  등차수열을 만족하는 i와 j로 이루어진 수열로 숫자를 만들때
  만들수 있는 가장 큰 제곱수를 출력하는 문제
  9 9          
  257240281            1
  197510846          8
  014345401        4
  035562575      5
  974935632    9
  865865933    
  684684987    
  768934659    
  287493867    95481 = 309^2

map의 최대 크기가 9*9로 81개 내의 데이터를 처리하기 때문에
모든 경우를 확실히 탐색하도록 구현하는 것이 중요한 문제이다.
+ 등차수열의 조건 : 3이상의 등차수열은 An - An-1 == An+1 - An 을 만족한다.

map데이터와, 숫자별로 각 숫자가 어떤 인덱스에 저장되어 있는지 list에 저장하여 활용한다.
for문에서 제곱수들을 하나씩 반복하며 그 제곱수의 첫번째, 두번째 숫자의 인덱스로
등차수열을 구하여 나머지 (세번째 ~ 끝까지) 숫자도 일치하는지 확인하도록 구현하였다.
*/

import java.util.*;
import java.io.*;
import java.math.*;

public class ReversedTriangle {
	public static void main(String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(scan.readLine());
		StringBuilder sb = new StringBuilder();
		
		// input
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		String[] map = new String[n];
    // 숫자의 인덱스(좌표)정보를 저장하는 list
		List<Coor>[] num_coor_list = new List[10];
		for(int i=0; i<10; i++) num_coor_list[i] = new ArrayList<>();
		for(int i=0; i<n; i++) {
			map[i] = scan.readLine();
			for(int j=0; j<m; j++) {
				num_coor_list[(int)(map[i].charAt(j) - '0')].add(new Coor(i, j));
			}
		}
		
		// solution
		int answer = -1;
		for(int i=0; i <= Math.sqrt(Integer.MAX_VALUE); i++) {
			String num = String.valueOf(i*i); // 제곱수
			if(num.length() > n && num.length() > m) break; //탐색 종료

      //길이가 1, 2인 경우는 있는지만 확인
			if(num.length() == 1) {
				if(!num_coor_list[(int)(num.charAt(0) - '0')].isEmpty())
					answer = Integer.parseInt(num);
			}
			else if(num.length() == 2) {
				if(!num_coor_list[(int)(num.charAt(0) - '0')].isEmpty() &&
					!num_coor_list[(int)(num.charAt(1) - '0')].isEmpty())
					answer = Integer.parseInt(num);
			}
      // 길이 3이상인 경우 a1과 a2를 비교하여 등차수열의 간격(차)을 구하고
      // 나머지 an에 대해서 등차수열을 만족하는 index의 map값이 제곱수와 일치하는지 확인
			else {
				for(Coor a1 : num_coor_list[(int)(num.charAt(0) - '0')]) {
					boolean found = true;
					for( Coor a2 : num_coor_list[(int)(num.charAt(1) - '0')]) {
						int dx = a1.x - a2.x, dy = a1.y - a2.y;
						int tmpx = a2.x, tmpy = a2.y;
						found = true;
						for(int index=2; index<num.length(); index++) {
							tmpx -= dx; tmpy -= dy;
              // 범위를 벗어나거나, map[tmpx][tmpy]의 값이 제곱수의 해당 자리값과 다르다면 break
							if(tmpx < 0 || n <= tmpx || tmpy < 0 || m <= tmpy) {
								found = false; break;
							}
							if(map[tmpx].charAt(tmpy) != num.charAt(index)) {
								found = false; break;
							}
						}
						if(found) {
							answer = Integer.parseInt(num);
							break;
						}
					}
					if(found) break;
				}
			}
		}
		System.out.println(answer);
	}
}
class Coor{
	int x;
	int y;
	public Coor(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
