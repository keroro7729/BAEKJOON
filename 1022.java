/*
https://www.acmicpc.net/problem/1022
백준 1022번 소용돌이 예쁘게 출력하기

    -3 -2 -1  0  1  2  3
    --------------------
-3 |37 36 35 34 33 32 31
-2 |38 17 16 15 14 13 30
-1 |39 18  5  4  3 12 29
 0 |40 19  6  1  2 11 28
 1 |41 20  7  8  9 10 27
 2 |42 21 22 23 24 25 26
 3 |43 44 45 46 47 48 49

위와 같이 중앙에서부터 반시계방향으로 1씩 증가하는 2차원 배열에서
가장 왼쪽 위 인덱스 r1, c1과 가장 오른쪽 아래 인덱스 r2,c2가 주어졌을 때
해당 영역만 글자수를 맞춰서 출력하는 문제

그냥 반복문으로 소용돌이 패턴을 만드는 것은 쉽지만 전체를 만드려고 하면 메모리가 초과된다.
인덱스가 주어졌을 때 해당 칸이 어떤 숫자일지 구하는 solution함수로 출력할 부분의 2차원 배열만 구하고
format을 지정하여 형식에 맞게 출력해주었다.

solution함수는 소용돌이의 범위(반지름?) range와 x-y의 값을 활용해 해당 좌표의 값을 구했다.
range == 3인 경우의 x-y값 패턴

0 -1 -2 -3 -4 -5 -6
1		             -5
2		             -4
3	            	 -3
4	            	 -2
5		             -1
6  5  4  3  2  1  0

*/

import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(scan.readLine());
		StringBuilder sb = new StringBuilder();
		
		// input
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());
		
		// set circle
		int row_size = r2-r1+1;
		int col_size = c2-c1+1;
		int[][] circle = new int[row_size][col_size];
		for(int i=r1; i<=r2; i++) {
			for(int j=c1; j<=c2; j++) {
				int x = i - r1, y = j - c1;
				circle[x][y] = solution(i, j);
			}
		}

		// get output max length
		int max_length = String.valueOf(circle[0][0]).length();
		int tmp = String.valueOf(circle[0][col_size-1]).length();
		if(max_length < tmp) max_length = tmp;
		tmp = String.valueOf(circle[row_size-1][0]).length();
		if(max_length < tmp) max_length = tmp;
		tmp = String.valueOf(circle[row_size-1][col_size-1]).length();
		if(max_length < tmp) max_length = tmp;
		
		// set format output
		String format = "%"+max_length+"d ";
		for(int i=0; i<row_size; i++) {
			for(int j=0; j<col_size; j++) {
				sb.append(String.format(format, circle[i][j]));
			}sb.append('\n');
		}
		System.out.println(sb);
	}

  // solution
	public static int solution(int x, int y) {
		if(x == 0 && y == 0) return 1;
		int range;
		if(Math.abs(x) > Math.abs(y)) range = Math.abs(x);
		else range = Math.abs(y);
		int min = (2*range-1)*(2*range-1);
		if(y == range && x-y < 0) return min + y-x;
		if(x == -1*range && x-y <= 0) return min + 4*range + x-y;
		if(y == -1*range && x-y > 0) return min + 4*range + x-y;
		if(x == range && x-y >= 0) return min + 8*range + y-x;
		else return -1;
	}
}
