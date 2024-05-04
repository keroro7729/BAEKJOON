// https://www.acmicpc.net/problem/14890
// 경사로

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, l, map[][];
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(reader.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for(int i=0; i<n; i++){
            if(checkLine(true, i)) answer++;
            if(checkLine(false, i)) answer++;
        }
        System.out.println(answer);
    }

    public static boolean checkLine(boolean vertical, int i){
        int prev = vertical ? map[i][0] : map[0][i];
        int count = 1, cur = -1;
        for(int j=1; j<n; j++){
            cur = vertical ? map[i][j] : map[j][i];
            if(cur == prev+1){
                if(count >= l){
                    prev++;
                    count = 1;
                }
                else return false;
            }
            else if(cur == prev-1){
                if(j+l > n) return false;
                for(int k=j+1; k<j+l; k++){
                    if((vertical ? map[i][k] : map[k][i]) != cur)
                        return false;
                }
                prev--;
                count = 0;
                j += l-1;
            }
            else if(cur == prev){
                count++;
            }
            else return false;
        }
        return true;
    }
}
