// https://www.acmicpc.net/problem/15686
// BOJ 15686 치킨 배달

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m, min;
    static List<Coordinate> home, store, select;

    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        home = new ArrayList<>();
        store = new ArrayList<>();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(scan.readLine());
            for(int j=0; j<n; j++){
                switch(Integer.parseInt(st.nextToken())){
                    case 1: home.add(new Coordinate(i, j)); break;
                    case 2: store.add(new Coordinate(i, j)); break;
                }
            }
        }

        min = Integer.MAX_VALUE;
        select = new ArrayList<>();
        dfs(0);
        System.out.println(min);
    }

    public static void dfs(int index){
        if(select.size() == m){
            min = Math.min(min, getChickenDistance());
            //System.out.println("\n"+select+"\n"+min);
            return;
        }
        if(index >= store.size()) return;

        select.add(store.get(index));
        dfs(index+1);
        select.remove(select.size()-1);
        dfs(index+1);
    }

    public static int getChickenDistance(){
        int result = 0, min;
        for(Coordinate start : home){
            min = Integer.MAX_VALUE;
            for(Coordinate end : select)
                min = Math.min(min, getDistance(start, end));
            result += min;
        }
        return result;
    }
    public static int getDistance(Coordinate start, Coordinate end){
        return Math.abs(start.x-end.x) + Math.abs(start.y-end.y);
    }
}

class Coordinate{
    int x, y;
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString(){
        return "("+x+", "+y+")";
    }
}
