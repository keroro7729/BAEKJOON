// https://www.acmicpc.net/problem/2457
// BOJ 2457 공주님의 정원

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		System.out.println(solution());
	}
	
	public static int solution() throws IOException {
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(scan.readLine());
		int memo[] = new int[275];
		memo[0] = -1;
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(scan.readLine());
			int start = date(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			int end = date(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			if(start >= 275 || end < 0) continue;
			if(start == -1) start = 0;
			memo[start] = Math.max(memo[start], end - 1);
		}
		
		if(memo[0] == -1) return 0;
		int checked = memo[0], next = checked, count = 1;
		for(int i=1; i<275; i++) {
			if(memo[i] > checked)
				next = Math.max(next, memo[i]);
			if(i > checked) {
				if(checked == next) {
					return 0;
				}
				checked = next;
				count++;
			}
		}
		return count;
	}
	
	public static int date(int month, int day) {
		int result = 0;
		//System.out.print(month+"월 "+day+"일 = ");
		switch(month) {
		case 1: 
		case 2: day = 0;
		case 3: result = 0; break;
		case 4: result = 31; break;
		case 5: result = 61; break;
		case 6: result = 92; break;
		case 7: result = 122; break;
		case 8: result = 153; break;
		case 9: result = 184; break;
		case 10: result = 214; break;
		case 11: result = 245; break;
		case 12: result = 275; break;
		}
		result += day - 1;
		//System.out.println(result);
		return result;
	}
	
}
