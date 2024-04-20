// https://www.acmicpc.net/problem/1799
// 비숍

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n;
    static List<Node> black, white;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        black = new ArrayList<>();
        white = new ArrayList<>();
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for(int j=0; j<n; j++){
                if(st.nextToken().charAt(0) == '1'){
                    if(isBlack(i, j)) black.add(new Node(i, j));
                    else white.add(new Node(i, j));
                }
            }
        }

        long startTime = System.currentTimeMillis();
        System.out.println(solution(black) + solution(white));
        //System.out.println(System.currentTimeMillis()-startTime+"ms");
    }

    static int max, end;
    static List<Node> positions, cases;
    public static int solution(List<Node> list){
        max = 0;
        end = list.size();
        positions = new ArrayList<>();
        cases = list;
        dfs(0);
        return max;
    }
    public static void dfs(int idx){
        if(idx == end) return;
        if(check(positions, cases.get(idx))){
            positions.add(cases.get(idx));
            max = Math.max(max, positions.size());
            dfs(idx+1);
            positions.remove(cases.get(idx));
        }
        dfs(idx+1);
    }

    public static boolean check(List<Node> list, Node cur){
        for(Node node : list)
            if(cur.isConfront(node))
                return false;
        return true;
    }

    public static boolean isBlack(int x, int y){ return x%2 == y%2; }
}
class Node{
    int x, y;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean isConfront(Node o){
        return Math.abs(this.x - o.x) == Math.abs(this.y - o.y);
    }
    
    @Override
    public String toString(){
        return "("+x+", "+y+")";
    }
}
