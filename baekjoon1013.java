/*
https://www.acmicpc.net/problem/1013
백준 1013 contact문제
입력으로 주어지는 String이 특정 패턴을 만족하는지 String 패턴을 검사하는 문제이다. 
  + 는 앞의 문자(문자열)이 반복될 수 있다는 의미
  | 는 or의 의미로
  제시한 패턴 (100+1+ | 01)+ 은 01 또는 10(0...0)(1...1)이 반복된다는 의미이다. (괄호 안의 숫자는 최소 한번 이상 반복되야 한다.)
  (편의 상 띄어쓰기를 추가한) 예시) 01 01 1001 01 1001, 100000111111 100000011111 01 10001

String의 indexOf(), substring()을 이용하여 앞부분 부터 패턴을 만족하는 부분을 자르며
StringIndexOutOfBounds를 catch해서 패턴을 벗어난 문자열을 만나면 false를 리턴한다.
  */


import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	public static void main(String[] args) throws IOException{
    	BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));    	
    	StringBuilder sb = new StringBuilder();
    	int t = Integer.parseInt(scan.readLine());
    	String input;
    	for(int T=0; T<t; T++) {
        
        //입출력 부분
    		input = scan.readLine();
    		if(hasPattern(input)) sb.append("YES").append('\n');
    		else sb.append("NO").append('\n');
        
    	}
    	System.out.println(sb);
    }

  // Solution
	public static boolean hasPattern(String s) {
		if(s.length() == 0) return false;
		if(s.charAt(s.length()-1) == '0') return false;
		
		int tmp;
		while(s.length() != 0) {
			if(s.indexOf("01") == 0) s = s.substring(2); // 01 패턴
			else if(s.indexOf("100") == 0) { // 100+1+패턴
				try {
				  s = s.substring(3);

          //반복되는 0을 자른다
				  s = s.substring(s.indexOf('1'));
				
				  tmp = s.indexOf('0');
          
          // 0이 없는경우 == 100+1+패턴으로 문자열이 종료된 경우
				  if(tmp == -1) return true;

          // 100+1+ 패턴의 경우 자르기 전에 다음 문자를 확인해야한다.
				  if(s.charAt(tmp+1) == '0') { // 00 패턴 => 100+1+패턴
          
          // 1이 두개이상 있으면 1하나 남겨두고 자른다.
					if(s.charAt(tmp-2) != '1') return false;
					s = s.substring(tmp-1);
				}
				else // 01 패턴
					s = s.substring(tmp+2);
				}catch(StringIndexOutOfBoundsException e) {
					return false;
				}
			}
			else return false;
		}
		return true;
	}
}
