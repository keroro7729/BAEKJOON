/*
https://www.acmicpc.net/problem/1024
백준 1024번 수열의 합
n, l이 주어졌을 때
길이 l 이상 100이하의 연속된 정수 리스트 중 리스트의 합이 n이 되는
가장 짧은 리스트를 구하는 문제. (음이 아닌 정수의 리스트이다. 0포함됨)
길이 100 이내에 만족하는 리스트가 없는 경우 -1을 출력
예) (18, 3) = 5 + 6 + 7  |  (18, 4) = 3 + 4 + 5 + 6  |  (18, 5) = -1

  길이가 k인 연속된 수열중 가장 작은 수열은 0, 1, ... , k-1 이다.
  그 다음으로 큰 수열은 1, 2, ... , k 으로 총합은 길이인 k만큼 증가한다.
  n과 가장 작은 수열 총합의 차가 k로 나누어 떨어진다면
  길이가 k 일 때 조건(sum == n)을 만족하는 수열이 있다.
*/

import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(scan.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());

    // solution
		long start = 0, end, sum;
		for(int len=l; len<=100; len++) {
			sum = (len-1)*len/2; // 0~n-1까지의 총합
			if(sum > n) break;
			if((n-sum)%len != 0) continue;

      // n-sum이 len으로 나누어 떨어지는 경우 == 해당 길이의 조건을 만족하는 수열이 존재
			start = (n-sum)/len; end = start+len-1;
			for(int i=(int)start; i<=end; i++)
				sb.append(i).append(' ');
			System.out.println(sb);
			return;
		}
		System.out.println(-1);
	}
}
