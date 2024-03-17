// https://www.acmicpc.net/problem/31404
// BOJ 31404 아리스, 청소합니다! (Easy)

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(scan.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int[][] A = new int[n][m];
        int[][] B = new int[n][m];
        boolean[][] clean = new boolean[n][m];
        for(int i=0; i<n; i++){
            String line = scan.readLine();
            for(int j=0; j<m; j++){
                A[i][j] = (int)(line.charAt(j) - '0');
            }
        }
        for(int i=0; i<n; i++){
            String line = scan.readLine();
            for(int j=0; j<m; j++){
                B[i][j] = (int)(line.charAt(j) - '0');
            }
        }

        // 먼지가 없는 길을 다닐 때 시작지점을 저장한다
        // 밖으로 나가거나
        // 처음왔던곳 같은 방향으로 진입하면
        // 종료하고 기록된 경로의 이동횟수를 답에서 뺀다

        int dx[] = {-1, 0, 1, 0};
        int dy[] = {0, 1, 0, -1};
        //String dir[] = {"위", "오른", "아래", "왼"};
        int answer = 0;
        while(true){
            if(clean[x][y]){
                if(tracking == 0){
                    ex = x;
                    ey = y;
                    ed = d;
                }
                tracking++;
                d += B[x][y];
            }
            else{
                tracking = 0;
                clean[x][y] = true;
                d += A[x][y];
            }
            d %= 4;
            x += dx[d];
            y += dy[d];
            //System.out.println(dir[d]);
            
            if(tracking != 0 &&
              ((ex == x && ey == y && ed == d) || 
               (x<0 || n<=x || y<0 || m<=y))){
                answer -= tracking-1;
                break;
            }
            answer++;
            if(x<0 || n<=x || y<0 || m<=y){
                break;
            }
        }
        System.out.println(answer);
    }

    static int ex = -1, ey = -1, ed = -1, tracking = -1;
}
