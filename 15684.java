// https://www.acmicpc.net/problem/15684
// 사다리 조작

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m, h;
    static int[][] links;
    static List<Integer> xnodes, ynodes;
    static int min, start[];
    static final int MIN_ANSWER = 3;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        links = new int[h][n-1];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(reader.readLine());
            int level = Integer.parseInt(st.nextToken())-1;
            int point = Integer.parseInt(st.nextToken())-1;
            links[level][point] = 1;
        }

        // when answer is 0
        start = new int[n];
        if(allPass()){
            System.out.println(0);
            return;
        }

        // get search nodes
        xnodes = new ArrayList<>();
        ynodes = new ArrayList<>();
        for(int i=0; i<h; i++){
            for(int j=0; j<n-1; j++){
                if(isLinkable(i, j)){
                    xnodes.add(i);
                    ynodes.add(j);
                }
            }
        }

        min = -1;
        dfs(0, 0);
        System.out.println(min);
    }

    public static void dfs(int idx, int count){
        if(min == -1 && count >= MIN_ANSWER) return;
        if(min != -1 && count >= min) return;
        if(idx == xnodes.size()) return;
        int x = xnodes.get(idx), y = ynodes.get(idx);
        
        if(isLinkable(x, y)){
            links[x][y] = 1;
            if(allPass()){
                min = count+1;
                links[x][y] = 0;
                return;
            }
            else{
                dfs(idx+1, count+1);
                links[x][y] = 0;
            }
        }
        dfs(idx+1, count);
    }

    public static boolean allPass(){
        int tmp;
        for(int i=0; i<n; i++) start[i] = i;
        for(int i=0; i<h; i++){
            for(int j=0; j<n-1; j++){
                if(links[i][j] == 1){
                    tmp = start[j];
                    start[j] = start[j+1];
                    start[j+1] = tmp;
                }
            }
        }
        for(int i=0; i<n; i++)
            if(start[i] != i) return false;
        return true;
    }

    public static boolean isLinkable(int i, int j){
        if(links[i][j] == 1) return false;
        if(j-1 >= 0 && links[i][j-1] == 1) return false;
        if(j+1 < n-1 && links[i][j+1] == 1) return false;
        return true;
    }
}
