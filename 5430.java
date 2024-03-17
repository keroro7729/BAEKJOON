// https://www.acmicpc.net/problem/5430\
// BOJ 5430 AC

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int[] array;
    static int n, front, rear;
    static boolean reversed = false;
    
    public static void main(String[] args) throws IOException{
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(scan.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t=0; t<T; t++){
            String command = scan.readLine();
            n = Integer.parseInt(scan.readLine());
            setArray(scan.readLine());
            reversed = false;
            front = 0; rear = 0;
            sb.append(solution(command)).append('\n');
        }
        System.out.println(sb);
    }

    public static void setArray(String input){
        input = input.substring(1, input.length()-1);
        array = new int[n];
        StringTokenizer st = new StringTokenizer(input, ",");
        for(int i=0; i<n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }
    }
    public static String solution(String command){
        for(int i=0; i<command.length(); i++){
            if(command.charAt(i) == 'D'){
                if(n-front-rear <= 0)
                    return "error";
                if(reversed) rear++;
                else front++;
            }
            else{
                reversed = !reversed;
            }
        }

        if(n-front-rear <= 0)
            return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if(reversed){
            for(int i=array.length-1-rear; i>=front; i--){
                sb.append(array[i]).append(',');
            }
        }
        else{
            for(int i=front; i<array.length-rear; i++){
                sb.append(array[i]).append(',');
            }
        }
        sb.setCharAt(sb.length()-1, ']');
        return sb.toString();
    }
}
