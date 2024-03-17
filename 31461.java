// https://www.acmicpc.net/problem/31461
// BOJ 31461 최고의 초콜릿 성지순례

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, store[][], sx, sy, ex, ey;
    
    public static void main(String[] args) throws IOException{
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(scan.readLine());
        for(int t=0; t<T; t++){
            n = Integer.parseInt(scan.readLine());
            store = new int[2][n];
            StringTokenizer st = new StringTokenizer(scan.readLine());
            for(int j=0; j<n; j++)
                store[0][j] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(scan.readLine());
            for(int j=0; j<n; j++)
                store[1][j] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(scan.readLine());
            sy = Integer.parseInt(st.nextToken())-1;
            sx = Integer.parseInt(st.nextToken())-1;
            ey = Integer.parseInt(st.nextToken())-1;
            ex = Integer.parseInt(st.nextToken())-1;
            sb.append(solution()).append('\n');
        }
        System.out.println(sb);
    }

    public static int solution(){
        if(sy == ey){
            return store[sx][sy] + store[ex][ey] + 
            Math.max(getMaxRoundTripScore(sy-1, 0), getMaxRoundTripScore(ey+1, n-1));
        }
        if(sy > ey){
            int tmp = sy; sy = ey; ey = tmp;
            tmp = sx; sx = ex; ex = tmp;
        }

        int result = store[sx][sy];

        int point = store[(sx==0 ? 1 : 0)][sy];
        int left = getMaxRoundTripScore(sy-1, 0);
        result += getMax(left+point, point, 0);
        
        for(int i=sy+1; i<ey; i++){
            int a = store[0][i], b = store[1][i];
            if(b > a){ int tmp = a; a = b; b = tmp; }
            result += a;
            if(b > 0) result += b;
        }

        point = store[(ex==0 ? 1 : 0)][ey];
        int right = getMaxRoundTripScore(ey+1, n-1);
        result += getMax(right+point, point, 0);

        result += store[ex][ey];
        return result;
    }
    
    public static int getMaxRoundTripScore(int start, int end){
        if(start < 0 || n <= start || end < 0 || n <= end) return 0;
        int sum = 0, max = 0, i=start;
        while(true){
            int a = store[0][i], b = store[1][i];
            max = getMax(max, sum+a, sum+b);
            sum += a+b;
            max = Math.max(max, sum);
            if(i == end)
                return max;
            if(start < end) i++;
            else i--;
        }
    }

    public static int getMax(int a, int b, int c){
        if(a >= b && a >= c) return a;
        else if(b >= a && b >= c) return b;
        else return c;
    }
}
