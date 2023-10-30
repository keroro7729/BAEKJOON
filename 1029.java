/*
https://www.acmicpc.net/problem/1029
백준 1029 그림교환
  n명의 예술가들이 그림을 거래할 때 다음과 같은 조건 내에서 거래하려고 한다.
  1. 그림을 팔 때, 그림을 산 가격보다 크거나 같은 가격으로 팔아야 한다.
  2. 같은 그림을 두 번 이상 사는 것은 불가능하다.
  n*n짜리 (i -> j)판매 가격 인접행렬 그래프가 주어지고
  1번 예술가가 0원에 그림을 입수해 시장에 가져왔을 때
  그림이 최대한 많은 예술가의 손을 지나는 경우의 거처간 예술가의 최대수를 구하시오.
  (그림을 소유했던 예술가 수의 최대값, 탐색 최대 depth)

  처음에 n이 최대 15밖에 안돼서 dfs로 모든 노드를 탐색하며 최대 depth를 구하려고 했었다.
  하지만 이 문제의 그래프는 모든 노드가 서로 연결되어 있는 전연결 그래프?로 15!의 경우의 수가 나온다.
  문제의 특징을 살펴 보면 a가 b에게 그림을 팔 때의 가격은 고정되어 있고
  탐색에서의 중요한 요소는 현재 그림 소유자, 소유자의 그림 구매가격(입찰 가격), 사람들의 구매 여부 이다.
  사람들의 구매 여부를 매 순간마다 저장해야 하는데 비트 마스크로 4바이트 int변수 하나에 구매여부를 기록할 수 있다.
  비트 마스크에서 비트연산과 비트 마스크 활용법 까지 연습할 수 있었던 문제였다.

  다이나믹 프로그래밍 문제라는데..
  다이나믹 프로그래밍 보다는 dfs의 visited같이 느꼈다. (더 세세한 경우까지 따지는 visited 느낌?)
  처음에는 소유자, 입찰가격, 구매여부로 탐색을 할 필요가 있을지 판단하고
  탐색한다면 dp에 기록하려고 했었는데 너무 어렵게 접근했던 거였다.
  판매가격이 정해져 있다보니 중복되는 경우가 굉장히 많고 위의 세가지 요소만 비교해 visit여부만 기록해도 충분했다.
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
        dp = new int[n][10][(int)Math.pow(2,n)];
        for(int i=0; i<n; i++){
            String input = scan.readLine();
            for(int j=0; j<n; j++){
                graph[i][j] = (int)(input.charAt(j) - '0');
            }
        }

        dp[0][0][1] = 1;
        dfs(0, 0, 1);
        System.out.println(max_count);
        //System.out.println(dfs_count);
    }

    static int max_count = 0, n;
    static int[][] graph;
    static int[][][] dp; // dp[소유자][입찰가격][구매여부]로 해당 경우를 탐색 했는지 표시
    //static int dfs_count = 0;
  
  // *********** solution ***************
    public static void dfs(int cur, int price, int record){
        //dfs_count++;
        count_record(record);
        for(int i=0; i<n; i++){
            if((record & (1 << i)) != 0) continue; // 이미 구매했던 사람
            if(graph[cur][i] < price) continue; // 판매 가격이 입찰가격 보다 낮은 사람
            if(dp[i][graph[cur][i]][record | (1 << i)] == 1) continue; // 이미 탐색했던 경우
            dp[i][graph[cur][i]][record | (1 << i)] = 1;
            dfs(i, graph[cur][i], record | (1 << i));
            if(max_count == n) return; // early stop;
        }
    }
  
  // record를 통해 구매했던 사람의 수를 리턴 + max_count값 업데이트 하는 함수
    public static int count_record(int record){
        int count = 0;
        while(record != 0){
            if((record & 1) == 1) count++;
            record >>= 1;
        }
        if(max_count < count) max_count = count;
        return count;
    }
}
