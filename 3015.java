// https://www.acmicpc.net/problem/3015
// 오아시스 재결합

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        Stack<Group> stack = new Stack<>();
        long answer = 0;
        for(int i=0; i<n; i++){
            long height = Long.parseLong(reader.readLine());

            while(!stack.isEmpty() && stack.peek().height < height)
                answer += stack.pop().count;

            if(stack.isEmpty())
                stack.push(new Group(height, 1));
            else if(stack.peek().height == height){
                answer += stack.peek().count++;
                if(stack.size() > 1)
                    answer++;
            }
            else if(stack.peek().height > height){
                answer++;
                stack.push(new Group(height, 1));
            }
        }
        System.out.println(answer);
    }
}
class Group{
    long height, count;
    public Group(long h, long c){
        height = h;
        count = c;
    }
}
