// https://www.acmicpc.net/problem/17299
// 오등큰수

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int n, arr[], found[], answer[];
    private static final int MAX_INPUT = 1000000;
    
    public static void main(String[] args) throws IOException {
        // input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        arr = new int[n];
        found = new int[MAX_INPUT+1]; // count array
        StringTokenizer st = new StringTokenizer(reader.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            found[arr[i]]++;
        }

        // initialize
        answer = new int[n];
        answer[n-1] = -1;
        Stack<Integer> stack = new Stack<>();
        stack.add(n-1);
        int flag;

        // solution
        for(int i=n-2; i>=0; i--){
            flag = 0;
            //System.out.println("cur index: "+i+", stack: "+stack);
            while(!stack.isEmpty()){
                //print(i, stack.peek());
                if(found[arr[i]] < found[arr[stack.peek()]]){
                    answer[i] = arr[stack.peek()];
                    stack.add(i);
                    flag = 1;
                    break;
                }
                else{
                    stack.pop();
                }
            }
            if(flag == 0){
                answer[i] = -1;
                stack.add(i);
            }
        }

        // output
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++)
            sb.append(answer[i]).append(' ');
        System.out.println(sb);
    }

    private static void print(int a, int b){
        System.out.println("  F("+a+")="+found[arr[a]]+", F("+b+")="+found[arr[b]]);
    }
}
