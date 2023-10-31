/*
https://www.acmicpc.net/problem/1030
백준 1030 프렉탈 평면
  문제 설명이 좀 난해하다. s n k r1 r2 c1 c2가 주어질 때 s n k로 나타나는 패턴의 r1 r2 c1 c2 영역을 출력하는 문제
  https://www.acmicpc.net/problem/2447 백준 2447번 별찍기 문제를 참고하면 이해가 편하다.
  s, n, k에 대해서 1단위시간에는 n*n크기의 정사각형에 0을 채우고, 가운데 k*k크기의 정사각형에 1을 채운다.
  2단위시간에는 n^2 * n^2 크기의 정사각형에 1단위시간의 패턴을 채우고, 가운데 (k*n) * (k*n) 크기의 정사각형에 1을 채운다.
  s = 3, n = 3, k = 1 인 경우의 패턴 예시
000000000000000000000000000
010010010010010010010010010
000000000000000000000000000
000111000000111000000111000
010111010010111010010111010
000111000000111000000111000
000000000000000000000000000
010010010010010010010010010
000000000000000000000000000
000000000111111111000000000
010010010111111111010010010
000000000111111111000000000
000111000111111111000111000
010111010111111111010111010
000111000111111111000111000
000000000111111111000000000
010010010111111111010010010
000000000111111111000000000
000000000000000000000000000
010010010010010010010010010
000000000000000000000000000
000111000000111000000111000
010111010010111010010111010
000111000000111000000111000
000000000000000000000000000
010010010010010010010010010
000000000000000000000000000

  행번호 i와 열번호 j가 주어졌을 때 1단위시간 때는 (i%n == 가운데좌표 && j%n == 가운데좌표)를 만족하면 1이다.
  2단위시간 때는 (i/n%n == 가운데 && j/n%n == 가운데), 3단위시간 때는 (i/(n^2)%n == 가운데 && j/(n^2)%n == 가운데)
  n의 제곱수로 나누어 단위시간마다 제곱으로 커지는 영역을 잡는다고 생각하면 될거 같다.
  추가로 (가운데 좌표)라고 표현한 부분은 k에 따라 여러개가 될 수 있다. 한 단위시간 동안 i, j 모두 조건을 한번은 만족해야 한다.
*/

import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	public static void main(String[] args) throws IOException
    {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());
        StringBuilder sb = new StringBuilder();

        // input
        int s = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int r1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        // solution
        int[] middle = get_middle(n, k); // 가운데 부분의 좌표(나누었을 때 나머지 값)을 저장하는 배열
        int end_pow = (int)Math.pow(n, s-1);
        for(int i=r1; i<=r2; i++){
            for(int j=c1; j<=c2; j++){

                boolean ib = false, jb = false;
                int ccvi = 0, ccvj = 0;
                for(int pow=1; pow<=end_pow; pow*=n){ // 단위시간에 따른 n의 제곱값 pow
                    ib = false; jb = false;
                    ccvi = i/pow%n, ccvj = j/pow%n; // 확인해야할 값 : (좌표) / (n^s) % (n)
                    for(int index=0; index<k; index++){ // middle에 저장된 가운데 좌표를 모두 확인
                        if(!ib && ccvi == middle[index]){
                            ib = true;
                        }
                        if(!jb && ccvj == middle[index]){
                            jb = true;
                        }
                        if(ib && jb) break;
                    }
                    if(ib && jb) break;
                }
                if(ib && jb) sb.append('1');
                else sb.append('0');
                
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
    public static int[] get_middle(int n, int k){
        int[] result = new int[k];
        for(int i=0; i<k; i++){
            result[i] = (n-k)/2 + i;
        }
        return result;
    }
}
