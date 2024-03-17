// https://www.acmicpc.net/problem/1759
// BOJ 1759 암호 만들기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static List<String> list;
    static int l, c;
    static StringBuilder sb;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(scan.readLine());
        list = new ArrayList<>();
        for(int i=0; i<c; i++){
            list.add(st.nextToken());
        }
        Collections.sort(list);
        sb = new StringBuilder();
        visited = new boolean[c];
        //System.out.println(list);
        dfs();
    }

    public static void dfs(){
        if(sb.length() == l){
            if(check(sb.toString()))
                System.out.println(sb);
            return;
        }
        for(int i=0; i<c; i++){
            if(sb.length() != 0 && 
                sb.charAt(sb.length()-1) > list.get(i).charAt(0)) 
                    continue;
            if(visited[i]) continue;
            visited[i] = true;
            sb.append(list.get(i));
            dfs();
            visited[i] = false;
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public static boolean check(String str){
        String moem = "aeiou";
        int count = 0;
        for(int i=0; i<str.length(); i++){
            if(moem.indexOf(str.charAt(i)) != -1)
                count++;
        }
        if(count >= 1 && str.length() - count >= 2)
            return true;
        else return false;
    }
}
