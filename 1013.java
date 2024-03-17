// https://www.acmicpc.net/problem/1013
// BOJ 1013 Contact

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
    		input = scan.readLine();
    		if(hasPattern(input)) sb.append("YES").append('\n');
    		else sb.append("NO").append('\n');
    	}
    	System.out.println(sb);
    }
	
	public static boolean hasPattern(String s) {
		if(s.length() == 0) return false;
		if(s.charAt(s.length()-1) == '0') return false;
		
		int tmp;
		while(s.length() != 0) {
			if(s.indexOf("01") == 0) s = s.substring(2);
			else if(s.indexOf("100") == 0) {
				try {
				s = s.substring(3);
				s = s.substring(s.indexOf('1'));
				
				tmp = s.indexOf('0');
				if(tmp == -1) return true;
				if(s.charAt(tmp+1) == '0') {
					if(s.charAt(tmp-2) != '1') return false;
					s = s.substring(tmp-1);
				}
				else
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
