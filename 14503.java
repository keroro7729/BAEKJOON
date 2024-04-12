// https://www.acmicpc.net/problem/14503
// 로봇 청소기

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m, map[][], answer;
    static Robot robot;
    static final int[] DIR_X = {-1, 0, 1, 0}, DIR_Y = {0, 1, 0, -1};
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(scan.readLine());
        robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        map = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(scan.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;
        visited = new boolean[n][m];
        while(true){
            if(!visited[robot.x][robot.y]){
                visited[robot.x][robot.y] = true;
                answer++;
            }
            if(!checkArround(robot)){
                robot.back();
                if(!check(robot.x, robot.y)) break;
            }
            else{
                robot.turn();
                robot.front();
                if(!check(robot.x, robot.y) || visited[robot.x][robot.y])
                    robot.back();
            }
        }
        System.out.println(answer);
    }

    public static boolean check(int x, int y){
        if(x < 0 || n <= x || y < 0 || m <= y)
            return false;
        if(map[x][y] == 1)
            return false;
        return true;
    }

    public static boolean checkArround(Robot robot){
        for(int i=0; i<4; i++){
            int x = robot.x + DIR_X[i], y = robot.y + DIR_Y[i];
            if(!check(x, y)) continue;
            if(!visited[x][y]) return true;
        }
        return false;
    }
}

class Robot{
    int x, y, dir;
    final int[] DIR_X = {-1, 0, 1, 0}, DIR_Y = {0, 1, 0, -1};

    public Robot(int x, int y, int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    public void back(){
        x -= DIR_X[dir];
        y -= DIR_Y[dir];
    }
    public void front(){
        x += DIR_X[dir];
        y += DIR_Y[dir];
    }
    public void turn(){
        if(dir == 0) dir = 3;
        else dir--;
    }
}
