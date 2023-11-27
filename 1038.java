// https://www.acmicpc.net/problem/1038
/*
  현재수를 기준으로 다음 감소하는 수를 리턴하는 함수를 구현하고
  반복문으로 n번째 감소하는 수를 구할 수 있다.
  가장 큰(마지막) 감소하는 수 "9876543210"일 때는 null을 리턴한다.
*/

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(scan.readLine());
		String num = "0";
		for(int i=0; i<n; i++) {
			num = get_next_decending_number(num);
			if(num == null) {
				System.out.println(-1);
				return;
			}
			//System.out.println(num);
		}
		System.out.println(num);
		return;
	}
	
	// 현재 감소하는 수를 입력했을 때 다음 감소하는 수를 리턴하는 함수
	public static String get_next_decending_number(String num) {
		if(num.equals("9876543210")) return null;
		
		int index = num.length()-1;
		StringBuilder sb = new StringBuilder();
		while(true) {
			if(index == 0) {
				if(num.charAt(index) == '9') {
					for(int i=num.length(); i>=0; i--) {
						sb.append((char)(i+'0'));
					}
					return sb.toString();
				}
				
				sb.append((char)(num.charAt(index)+1));
				for(int i=num.length()-2; i>=0; i--) {
					sb.append((char)(i+'0'));
				}
				return sb.toString();
			}
			
			if(num.charAt(index-1) - num.charAt(index) == 1) {
				index--;
				continue;
			}
			else {
				sb.append(num.substring(0, index))
				.append((char)(num.charAt(index)+1));
				for(int i=num.length()-index-2; i>=0; i--) {
					sb.append((char)(i+'0'));
				}
				return sb.toString();
			}
		}
	}
}
