/*
https://www.acmicpc.net/submit/1027/68560215
백준 1027 고층 건물
  빌딩의 갯수 n과 각 빌딩의 높이가 배열로 주어졌을 때 (빌딩은 거리를 따라 일자로 지어져있다.)
  옥상에서 볼 수 있는 빌딩의 최대 갯수를 출력하는 문제

  빌딩a에서 b빌딩이 보이려면 둘 사이의 건물들은 두 빌딩을 잇는 선분을 넘지 않아야 한다.
  n은 최대 50개 이다. 반복문을 사용해서 각 건물을 확인하면 된다. 
  선분을 정의하고 (ax + b) 두빌딩 사이의 모든 건물에대해 height < ax + b를 만족하는지 확인 
*/

import java.util.*;
import java.io.*;
import java.math.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());
        StringBuilder sb = new StringBuilder();

        // input
        int n = Integer.parseInt(st.nextToken());
        int[] building = new int[n];
        st = new StringTokenizer(scan.readLine());
        for(int i=0; i<n; i++){
            building[i] = Integer.parseInt(st.nextToken());
        }

        // solution
        int[] count = new int[n];
        double a, b; // y = ax + b
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(j == i) continue;
                a = (double)(building[i] - building[j]) / (i - j);
                b = building[i] - a*i;
                boolean visable = true;
                int k = (i < j ? i : j);
                int end = (i > j ? i : j);
                while(k != end-1){
                    k++;
                    if(building[k] >= a*k+b){
                        visable = false;
                        break;
                    }
                }
                if(visable) {
                    count[i]++;
                    
                }
            }
        }

        // output
        int max = count[0];
        for(int i=1; i<n; i++)
            if(count[i] > max) max = count[i];
        System.out.println(max);
    }
}
