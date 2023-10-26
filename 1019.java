/*
  https://www.acmicpc.net/problem/1019
  백준 1019 책 페이지
  정수n이 주어졌을 때 1부터 n까지의 모든 숫자에서
  0, 1, 2, ... , 8, 9 각 숫자들이 몇번 등장하는지를 출력하는 문제

solution함수는 1~9까지의 숫자들의 횟수를 재귀적으로 풀었다.
숫자 n을 맨앞자리 숫자 하나와 나머지로 정리하여 (n => d, n`  |  5673 => 5, 673)

맨 앞자리 0 ~ d-1 까지는 각 앞자리숫자가 10^(n의자릿수)번 등장하고
나머지 0 ~ 99...99에서 각 숫자들은 n`의 자릿수에 따라 1, 20, 300, 4000, 50000.... 번씩 등장한다.
( 0, 000~999 | 1, 000~999 | 2, 000~999 | 3, 000~999 | 4, 000~999 )

맨 앞자리가 d인 경우 d는 n`+1번 등장 하고 
나머지 숫자는 n`을 다시 재귀적으로 계산하며 구해간다.
( 5, 000~673 )

0의 경우 다른 숫자들과 다르게 앞자리가 0이면 생략되기 때문에 다른 솔루션을 적용하여 풀었다.
n이하의 숫자 중에 각 자리에 0이 존재하는 경우의 수를 곱셈으로 구하는 방식으로 0의 갯수를 구하였다.
5673에서 1000의 자리에는 0이 들어갈수 없다.
100의 자리에 0이 들어가는 경우의 수는 5 * 100
(만약 n의 100자리가 0이라면 5*100이 아닌 5 * (n`+1) 이 된다.)
10의 자리에 0이 들어가는 경우의 수는 56 * 10 ......


코딩 구현보다는 수학적으로 어떻게 정의할 수 있을지 고민을 많이 했던 문제였다. 
정리하며 생각해 보니 1~9의 숫자도 재귀적 방식보다 경우의 수로 쉽게 구할 수 있을 것 같다.

*/

import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	public static void main(String[] args) throws IOException{
    	BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));    	
    	
    	int n = Integer.parseInt(scan.readLine());
    	solution(n);
    	solution_for_zero(n);
		
    	for(int i=0; i<10; i++)
    		System.out.print(answer[i]+" ");
    	System.out.println();
	}
	
	static int[] answer = new int[10];
	
	public static void solution(int n) {
		if(n == 0) return;
		if(n < 10) {
			for(int i=0; i<=n; i++) answer[i]++;
			return;
		}
		int ten = 1000000000, d;
		while(true) {
			d = n/ten;
			if(d != 0) {
				n %= ten;
				break;
			}
			ten /= 10;
		}
		for(int i=0; i<d; i++)
			answer[i] += ten;
		
		int tt = 1, count = 0;
		while(true) {
			if(tt*10 == ten) break;
			tt *= 10;
			count++;
		}
		for(int i=0; i<10; i++)
			answer[i] += tt*(count+1)*d;
		answer[d] += n+1;
		solution(n);
	}
	public static void solution_for_zero(int n) {
		answer[0] = 0;
		String str = String.valueOf(n);
		for(int i=1; i<str.length(); i++) {
			if(i == str.length()-1) {
				answer[0] += Integer.parseInt(str.substring(0, i));
				break;
			}
			int d = Integer.parseInt(str.substring(0, i));
			if((int)(str.charAt(i)-'0') == 0) {
				answer[0] += (d-1) * (Math.pow(10, str.length()-1-i));
				answer[0] += Integer.parseInt(str.substring(i+1, str.length())) + 1;
			}
			else {
				answer[0] += d * (Math.pow(10, str.length()-1-i));
			}
		}
	}
}
