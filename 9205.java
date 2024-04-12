// https://www.acmicpc.net/problem/9205
// 맥주 마시면서 걸어가기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n;
    static Coordinate start, end, store[];
    static boolean visited[], isHappy;
    
    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(scan.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<T; t++){
            n = Integer.parseInt(scan.readLine());
            start = new Coordinate(scan.readLine());
            store = new Coordinate[n];
            for(int i=0; i<n; i++){
                store[i] = new Coordinate(scan.readLine());
            }
            end = new Coordinate(scan.readLine());
            sb.append(solution()).append('\n');
        }
        System.out.println(sb);
    }

    public static String solution(){
        visited = new boolean[n];
        Queue<Coordinate> que = new LinkedList<>();
        que.add(start);
        while(!que.isEmpty()){
            Coordinate cur = que.poll();
            if(cur.distance(end) <= 1000)
                return "happy";
            for(int i=0; i<n; i++){
                if(visited[i]) continue;
                if(cur.distance(store[i]) > 1000) continue;
                visited[i] = true;
                que.add(store[i]);
            }
        }
        return "sad";
    }
}

class Coordinate{
    int x, y;
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Coordinate(String input){
        StringTokenizer st = new StringTokenizer(input);
        this.x = Integer.parseInt(st.nextToken());
        this.y = Integer.parseInt(st.nextToken());
    }
    public int distance(Coordinate o){
        return Math.abs(this.x - o.x) + Math.abs(this.y - o.y);
    }
    @Override
    public String toString(){
        return "("+x+", "+y+")";
    }
}
