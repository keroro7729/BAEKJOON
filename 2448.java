// https://www.acmicpc.net/problem/2448
// BOJ 2448 별 찍기 - 11

import java.util.*;
import java.lang.*;
import java.io.*;
@SuppressWarnings("unchecked")

class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(scan.readLine());
        int n = N/3;
        
        // solution
        List<Integer>[] blueprints = getBluePrints(n);
        for(int i=0; i<n; i++){
            printTriangle((n-i-1)*3, blueprints[i]);
            //System.out.println(blueprints[i]);
        }
        System.out.println(sb);
    }
    
    public static void printTriangle(int blank, List<Integer> blueprint){
        for(int i=0; i<3; i++){
            append(' ', blank);
            for(int j=0; j<blueprint.size(); j++){
                if(blueprint.get(j) == 0)
                    append(' ', 6);
                else if(i == 0){
                    sb.append(' ').append(' ').append('*').append(' ').append(' ').append(' ');
                }
                else if(i == 1){
                    sb.append(' ').append('*').append(' ').append('*').append(' ').append(' ');
                }
                else{
                    sb.append('*').append('*').append('*').append('*').append('*').append(' ');
                }
            }
            append(' ', N*2 - blank - blueprint.size()*6);
            sb.append('\n');
        }
    }

    public static List<Integer>[] getBluePrints(int n){
        List<Integer>[] blueprints = new List[n];
        for(int i=0; i<n; i++){
            blueprints[i] = new ArrayList<Integer>();
        }
        blueprints[0].add(1);
        if(n == 1) return blueprints;
        blueprints[1].add(1);
        blueprints[1].add(1);
        
        int complete = 2;
        while(complete < n){
            for(int i=0; i<complete; i++){
                List<Integer> prev = blueprints[i];
                int cur = complete+i;
                blueprints[cur].addAll(prev);
                for(int j=0; j<cur+1 - 2*prev.size(); j++)
                    blueprints[cur].add(0);
                blueprints[cur].addAll(prev);
            }
            complete *= 2;
        }
        return blueprints;
    }
    
    public static void append(char c, int count){
        for(int i=0; i<count; i++)
            sb.append(c);
    }
}
