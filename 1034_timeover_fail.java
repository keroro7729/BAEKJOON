/*
https://www.acmicpc.net/problem/1034
백준 1034번 램프 문제 시간초과 코드

스위치를 작동시키는 열을 고르는 모든 경우를 탐색하는 방식으로 작성된 코드
원본 초기의 테이블 상태origin과 스위치가 작동된 (반전된) switched를 저장하고
몇번 열의 스위치를 켰는지 여부를 저장한 switch_info를 정의하여
dfs로 모든 경우를 탐색했다.
k값이 홀수라면 켤 수 있는 열의 수는 1, 3, 5... , 짝수라면 0, 2, 4... 이다.

스위치를 작동 시킬때 마다 하나의 열이 바뀌며 모든 행에 영향을 주기 때문에
모든 경우를 다 따져봐야한다고 생각했던 것이 문제였다.
문제를 더 간단하게 생각하려면 한 행을 키는것을 생각해봐야했다.
*/
import java.util.*;
import java.io.*;

public class 램프_시간초과 {
		// n, m은 50 작은 값, k는 1000까지 들어옴
		// 백트래킹? 전탐색? 너무 오래걸릴듯 50 ^ 1000
		
		// 중간 과정은 필요없음. k번 수행 후 결과가 최대값이여야함.
		// 열에 스위칭이 적용된다, 행이 켜져있는지만 확인한다.
		
		// 모든 경우의 수는 2^m
		// 각 열들의 2가지 상태를 조합해 모든 경우를 확인
		// 소요되는 스위칭 횟수가 k내에 있는 경우만
		// k번은 꼭 실행 되어야 함. 홀수라면 한번은 꼭 스위칭됨
		
		// 홀수인 경우를 잘 고려해야함
		// 예제3번 두열을 스위칭하면 한행을 켤수있지만
		// 홀수번 스위칭을 동작 시킬수 있기 때문에
	public static void main(String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(scan.readLine());
		
		// input
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		origin = new boolean[n][m];
		switched = new boolean[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(scan.readLine());
			String line = st.nextToken();
			for(int j=0; j<m; j++) {
				if(line.charAt(j) == '0') {
					origin[i][j] = false;
					switched[i][j] = true;
				}
				else if(line.charAt(j) == '1') {
					origin[i][j] = true;
					switched[i][j] = false;
				}
			}
		}
		k = Integer.parseInt(scan.readLine());
		
		switch_info = new boolean[m];
		int ccc;
		if(k%2 == 0) ccc = 0;
		else ccc = 1;
		for(; ccc<=m; ccc+=2) {
			end_point = ccc;
			dfs(0, 0);
		}
		System.out.println(max);
	}
	
	static int n, m, k, max = 0, end_point;
	static boolean origin[][], switched[][], switch_info[];
	public static void dfs(int j, int switch_count) {
		if(switch_count == end_point) {
			max = Math.max(max, count_table());
			return;
		}
		if(j == m) return;
		switch_info[j] = true;
		dfs(j+1, switch_count+1);
		switch_info[j] = false;
		dfs(j+1, switch_count);
	}
	
	public static int count_table() {
		int count = 0;
		for(int i=0; i<n; i++) {
			boolean is_on = true;
			for(int j=0; j<m; j++) {
				if((!switch_info[j] && !origin[i][j]) ||
						(switch_info[j] && !switched[i][j])) {
					is_on = false;
					break;
				}
			}
			if(is_on) count++;
		}
		
		/*for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(switch_info[j]) {
					if(switched[i][j]) System.out.print(1);
					else System.out.print(0);
				}else {
					if(origin[i][j]) System.out.print(1);
					else System.out.print(0);
				}
			}System.out.println();
		}System.out.println(count);
		System.out.println();*/
		return count;
	}
}
