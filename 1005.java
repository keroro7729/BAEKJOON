import java.io.*;
import java.util.*;

public class Main{

     public static void main(String []args) throws IOException{
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(scan.readLine());
        int t = Integer.parseInt(st.nextToken()), n, k, w, a, b;
        
        for(int T=0; T<t; T++){
            //input
            st = new StringTokenizer(scan.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            set_graph(n);
            st = new StringTokenizer(scan.readLine());
            for(int i=0; i<n; i++)
                time[i] = Integer.parseInt(st.nextToken());
            for(int i=0; i<k; i++){
                st = new StringTokenizer(scan.readLine());
                a = Integer.parseInt(st.nextToken())-1;
                b = Integer.parseInt(st.nextToken())-1;
                graph.get(b).add(a);
            }
            w = Integer.parseInt(scan.readLine())-1;
            
            sb.append(get(w)).append('\n');
        }
        System.out.println(sb);
     }
     
     static final int SIZE = 1000;
     static List<List<Integer>> graph;
	 
	 //init
     public static void set_graph(int n){
         graph = new ArrayList<>();
         for(int i=0; i<n; i++){
            graph.add(new ArrayList<>());
            solution[i] = nan;
         }
     }
	 
     static int time[] = new int[SIZE];
     static int solution[] = new int[SIZE];
     static final int nan = -999; //not a number
	 
	 //dynamic programing
     public static int get(int n){
         if(solution[n] != nan) return solution[n];
         if(graph.get(n).size()==0)
             return solution[n] = time[n];
         int max = 0, tmp;
         for(int i=0; i<graph.get(n).size(); i++){
             tmp = get(graph.get(n).get(i));
             if(tmp > max) max = tmp;
         }
         return solution[n] = max+time[n];
     }
}
