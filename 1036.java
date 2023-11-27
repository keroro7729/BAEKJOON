// https://www.acmicpc.net/problem/1036
import java.util.*;
import java.io.*;
/**
 * 사소한 오류로 디버깅에 굉장히 애먹었던 문제
 * 알고리즘은 아래 주석에 나와있 듯 간단하다
 * 구현도 많이 어렵지는 않았지만
 * String처리, 자릿수, 0, 36진수, 10진수 처리
 * 실수할 부분이 많고 디버깅 하기가 어려웠던 문제였던 것 같다.
 * 
 * 입력 받는 값에 대해서는 cut함수로 처리할 생각을 했는데
 * addition도 이상 값이 있을 수 있다는 생각을 하지 못하고
 * 한참을 디버깅하며 예외상황을 고민하느라 시간을 썼다.
 */
public class Base36 {
	// 입력값을 누적해서 sum을 저장
	// 36개의 숫자들 별로 Z로 바뀌었을 때 이득값을 addition배열에 저장
	// addition배열 내림차순 정렬 후 0~k-1의 값을 sum에 누적 -> 최대값
	public static void main(String[] args) throws IOException{
		BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
		
		// input & get sum, addition
		int n = Integer.parseInt(scan.readLine());
		String[] inputs = new String[n];
		String sum = "0";
		String[] addition = new String[36];
		for(int i=0; i<36; i++) addition[i] = "0";
		for(int i=0; i<n; i++) {
			inputs[i] = cut(scan.readLine());
			sum = add(sum, inputs[i]);
			set_addition(addition, inputs[i]);
		}
		int k = Integer.parseInt(scan.readLine());

		// output
		// !! addition에 0000000과 같은 이상 값이 들어있을 수 있다 !!
		for(int i=0; i<36; i++) addition[i] = cut(addition[i]);
		Arrays.sort(addition, (o1,o2)->{
			if(o1.length() != o2.length())
				return o2.length() - o1.length();
			else
				return o2.compareTo(o1);
		});
		//for(int i=0; i<36; i++) System.out.println(addition[i]);
		for(int i=0; i<k; i++) {
			sum = add(sum, addition[i]);
		}
		System.out.println(sum);
	}
	
	static String N = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static String add(String num_a, String num_b) {
		boolean isa = true, isb = true, carry = false;
		int index = 0, val_a, val_b, value;
		char a, b;
		StringBuilder sb = new StringBuilder();
		while(true) {
			if(!isa && !isb) {
				if(carry) sb.insert(0, '1');
				break;
			}
			
			value = 0;
			if(isa) {
				a = num_a.charAt(num_a.length()-index-1);
				val_a = N.indexOf(a);
				value += val_a;
			}
			if(isb) {
				b = num_b.charAt(num_b.length()-index-1);
				val_b = N.indexOf(b);
				value += val_b;
			}
			
			if(carry) {
				carry = false;
				value += 1;
			}
			if(value >= 36) {
				carry = true;
				value -= 36;
			}
			sb.insert(0, N.charAt(value));
			
			index++;
			if(index == num_a.length()) isa = false;
			if(index == num_b.length()) isb = false;
		}
		return sb.toString();
	}
	
	public static void set_addition(String[] addition, String input) {
		int in, Z = 35, digit = 1;
		for(int i=input.length()-1; i>=0; i--) {
			char tmp = input.charAt(i);
			in = N.indexOf(tmp);
			
			StringBuilder gain = new StringBuilder();
			gain.append(N.charAt(Z-in));
			for(int j=0; j<digit-1; j++) gain.append('0');
			addition[in] = add(addition[in], gain.toString());
			digit++;
		}
	}
	
	// input string 이상값을 잘라주는 함수 (앞자리가 0으로 시작하거나, 36진수 숫자가 아닌값, 0에대한 처리)
	public static String cut(String input) {
		boolean start = true, isZero = true;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<input.length(); i++) {
			char c = input.charAt(i);
			if(c != '0') isZero = false;
			if(N.indexOf(c) == -1) break;
			if(start && c == '0') continue;
			start = false;
			sb.append(c);
		}
		if(isZero) return "0";
		return sb.toString();
	}
}
