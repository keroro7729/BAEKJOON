/*
https://www.acmicpc.net/problem/1033
백준 1033번 캌테일
  재료의 종류 n과, 두 재료의 비율이 (a b p q) (a번재료:b번재료 = p:q 비율)로 n-1개 주어졌을 때
  칵테일을 만드는데 필요한 n가지 재료의 정수 최소값을 출력하는 문제

  필요한 비율들을 곱해서 마지막에 모든 재료들의 최대 공약수로 나누면 된다.
  n개의 노드가 n-1개의 엣지로 모두 연결되어 있다면 그 그래프는 순환이 없는 트리구조이다.
  엣지 하나를 선택해서 a : b = p : q 일때 a의 하위노드에는 q를 곱하고 b의 하위 노드에는 p를 곱하며
  모든 엣지를 순회하면 된다. (a의 하위노드를 탐색할 때 b는 제외한다, b도 마찬가지)

  최대공약수를 구하는 유클리드 호제법: 
  1. 두 수의 나머지를 구해 나머지가 0인지 확인한다.
  2. 두 수중 작은 값과 계산한 나머지로 1의 과정을 반복한다.
*/

import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	public static void main(String[] args) throws IOException
    {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());

        // input
        n = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(scan.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int max_div = find_max_div(p, q);
            graph[a][b] = p / max_div;
            graph[b][a] = q / max_div;
        }

        // init answer
        answer = new int[n];
        for(int i=0; i<n; i++) answer[i] = 1;

        // solution
        que = new LinkedList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                if(graph[i][j] == 0) continue;
                bfs(i, j, graph[i][j]);
                bfs(j, i, graph[j][i]);
            }
        }

        // output
        int tmp = answer[0];
        for(int i=1; i<n; i++){
            tmp = find_max_div(tmp, answer[i]);
        }
        for(int i=0; i<n; i++){
            answer[i] /= tmp;
            System.out.print(answer[i]+" ");
        }
        System.out.println();
    }

    static int n;
    static int[] answer;
    static int[][] graph;
    static Queue<Integer> que;
    static boolean[] visit;

    // bfs
    public static void bfs(int start, int block, int value){
        if(value == 1) return;
        que.add(start);
        visit = new boolean[n];
        visit[block] = true;
        while(!que.isEmpty()){
            int cur = que.poll();
            visit[cur] = true;
            answer[cur] *= value;
            for(int i=0; i<n; i++){
                if(!visit[i] && graph[cur][i] != 0)
                    que.add(i);
            }
        }
    }
    
    // 최대공약수를 리턴하는 함수
    public static int find_max_div(int a, int b){
        int tmp;
        if(a < b){
            tmp = a; a = b; b = tmp;
        }
        while((tmp = a%b) != 0){
            a = b;
            b = tmp;
        }
        return b;
    }
}
