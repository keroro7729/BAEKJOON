// https://www.acmicpc.net/problem/3190
// 뱀

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, k, appleX[], appleY[], l, sec, dir, answer;
    static final int[] DIRX = {-1, 0, 1, 0}, DIRY = {0, 1, 0, -1};
    static List<Integer> X, Y;
    
    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(scan.readLine());
        k = Integer.parseInt(scan.readLine());
        appleX = new int[k];
        appleY = new int[k];
        for(int i=0; i<k; i++){
            StringTokenizer st = new StringTokenizer(scan.readLine());
            appleX[i] = Integer.parseInt(st.nextToken()) - 1;
            appleY[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        answer = -1;
        sec = 0;
        dir =1;
        X = new LinkedList<>();
        Y = new LinkedList<>();
        push(0, 0);
        l = Integer.parseInt(scan.readLine());
        for(int i=0; i<l; i++){
            StringTokenizer st = new StringTokenizer(scan.readLine());
            int next = Integer.parseInt(st.nextToken());
            if(!go(next - sec)){
                System.out.println(answer);
                return;
            }
            sec = next;
            switch(st.nextToken()){
                case "L": if(dir == 0) dir = 3; else dir--; break;
                case "D": if(dir == 3) dir = 0; else dir++; break;
            }
        }
        if(!go(n))
            System.out.println(answer);
    }

    public static boolean go(int n){
        for(int i=0; i<n; i++){
            int x = X.get(0)+DIRX[dir], y = Y.get(0)+DIRY[dir];
            if(!check(x, y)){
                answer = sec + i + 1;
                return false;
            }
            push(x, y);
            if(!checkApple(x, y))
                pop();
        }
        return true;
    }

    public static boolean check(int x, int y){
        if(x < 0 || n <= x || y < 0 || n <= y)
            return false;
        for(int i=0; i<X.size(); i++){
            if(X.get(i) == x && Y.get(i) == y)
                return false;
        }
        return true;
    }

    public static boolean checkApple(int x, int y){
        for(int i=0; i<k; i++){
            if(appleX[i] == x && appleY[i] == y){
                appleX[i] = -1; appleY[i] = -1;
                return true;
            }
        }
        return false;
    }
    
    public static void push(int x, int y){
        X.add(0, x); Y.add(0, y);
    }
    public static void pop(){
        X.remove(X.size()-1); Y.remove(Y.size()-1);
    }
}
