// https://www.acmicpc.net/problem/2629
// 양팔저울

import java.util.*; 
import java.lang.*; 
import java.io.*;

class Main { 
    private static int n, m, weight[]; 
    private static Set<Integer> rem;
            
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        rem = new HashSet<>();
    
        n = Integer.parseInt(reader.readLine());
        weight = new int[n];
        StringTokenizer st = new StringTokenizer(reader.readLine());
        for(int i=0; i<n; i++){
            weight[i] = Integer.parseInt(st.nextToken());
            Set<Integer> tmp = new HashSet<>();
            if(rem.contains(weight[i])){
                for(int v : rem)
                    tmp.add(v + weight[i]);
            }
            else{
                tmp.add(weight[i]);
                for(int v : rem){
                    tmp.add(v + weight[i]);
                    tmp.add(v - weight[i]);
                    tmp.add(weight[i] - v);
                }
            }
            rem.addAll(tmp);
        }

        m = Integer.parseInt(reader.readLine());
        st = new StringTokenizer(reader.readLine());
        for(int i=0; i<m; i++){
            int b = Integer.parseInt(st.nextToken());
            if(rem.contains(b)) sb.append('Y');
            else sb.append('N');
            sb.append(' ');
        }
        System.out.println(sb);
        //System.out.println(rem);
    }
}
