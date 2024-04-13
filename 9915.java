// https://www.acmicpc.net/problem/9935
// 문자열 폭발

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        String bomb = reader.readLine();
        
        if(bomb.length() == 1)
            System.out.println(solution3(input, bomb));
        else
            System.out.println(solution2(input, bomb));
    }

    public static String solution1(String input, String bomb){
        StringBuilder sb = new StringBuilder(input);
        while(true){
            int idx = sb.indexOf(bomb);
            if(idx == -1) break;
            sb.delete(idx, idx+bomb.length());
        }
        if(sb.length() == 0) return "FRULA";
        else return sb.toString();
    }

    public static String solution2(String input, String bomb){
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<input.length(); i++){
            sb.append(input.charAt(i));
            if(input.charAt(i) == bomb.charAt(0)){
                stack.push(1);
            }
            else if(!stack.isEmpty() && input.charAt(i) == bomb.charAt(stack.peek())){
                int idx = stack.pop()+1;
                if(idx == bomb.length()){
                    sb.delete(sb.length()-bomb.length(), sb.length());
                }
                else stack.push(idx);
            }
            else stack.clear();
        }
        if(sb.length() == 0) return "FRULA";
        else return sb.toString();
    }

    public static String solution3(String input, String bomb){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<input.length(); i++){
            if(input.charAt(i) == bomb.charAt(0)) continue;
            sb.append(input.charAt(i));
        }
        if(sb.length() == 0) return "FRULA";
        else return sb.toString();
    }
}
