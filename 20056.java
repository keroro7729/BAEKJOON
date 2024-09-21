// https://www.acmicpc.net/problem/20056
// 마법사 상어와 파이어볼

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int n, m, k;
    private static List<FireBall> balls;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        FireBall.setN(n);
        balls = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(reader.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            balls.add(new FireBall(r, c, m, s, d));
        }
        print("initial input");

        for(int i=0; i<k; i++){
            moveAllFireBalls();
            print("after move "+(i+1));
            combine();
            print("after combine "+(i+1));
        }

        int answer = 0;
        for(FireBall ball : balls)
            answer += ball.m;
        System.out.println(answer);
    }

    private static final int[] DIRX = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] DIRY = {0, 1, 1, 1, 0, -1, -1, -1};
    private static void moveAllFireBalls(){
        for(FireBall ball : balls){
            ball.x += DIRX[ball.d]*ball.s;
            ball.y += DIRY[ball.d]*ball.s;
            ball.correctCoor();
        }
    }

    private static boolean initialized = false;
    private static List<FireBall>[] map;
    private static int mapSize;
    private static void combine(){
        // initialize
        if(!initialized){
            mapSize = n*n;
            map = new List[mapSize];
            for(int i=0; i<mapSize; i++)
                map[i] = new ArrayList<>();
            initialized = true;
        }
        else{
            for(int i=0; i<mapSize; i++)
                map[i].clear();
        }

        for(FireBall ball : balls)
            map[ball.getCoor()].add(ball);
        for(int i=0; i<mapSize; i++){
            if(map[i].size() >= 2){
                // set r, c, m, s, d
                int r = map[i].get(0).x;
                int c = map[i].get(0).y;
                int m = 0, s = 0, D[] = {};
                int flag = isOdd(map[i].get(0).d);
                for(FireBall ball : map[i]){
                    m += ball.m;
                    s += ball.s;
                    if(flag != -1 && flag != isOdd(ball.d)){
                        flag = -1;
                        int[] d = {1, 3, 5, 7};
                        D = d;
                    }
                }
                if(flag != -1){
                    int[] d = {0, 2, 4, 6};
                    D = d;
                }
                m /= 5;
                s /= map[i].size();

                // remove combined balls ans add new 4 balls
                balls.removeAll(map[i]);
                if(m <= 0) continue;
                for(int j=0; j<4; j++)
                    balls.add(new FireBall(r, c, m, s, D[j]));
            }
        }
    }

    private static int isOdd(int n){
        if(n%2 == 0)
            return 0;
        else return 1;
    }

    private static boolean debug = false;
    private static void print(String header){
        if(!debug) return;
        System.out.println(header);
        for(FireBall ball : balls)
            System.out.println(ball);
        System.out.println();
    }
}

class FireBall{
    static int n;
    public static void setN(int v){
        n = v;
    }
    
    int x, y, m, s, d;
    public FireBall(int r, int c, int m, int s, int d){
        x = r; y = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }

    public void correctCoor(){
        if(x < 0){
            x = n + x%n;
            if(x == n)
                x = 0;
        }
        else if(x >= n)
            x = x%n;
        if(y < 0){
            y = n + y%n;
            if(y == n)
                y = 0;
        }
        else if(y >= n)
            y = y%n;
    }
    public int getCoor(){
        return x*n + y;
    }
    
    @Override
    public String toString(){
        return "["+x+","+y+"]: m="+m+", s="+s+", d="+d;
    }
}
