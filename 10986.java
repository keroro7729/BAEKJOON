// https://www.acmicpc.net/problem/10986
// 나머지 합

/*
  1~n의 구간합 = S[n]
  i~j의 구간합 = S[j] - S[i-1]
  구간합을 m으로 나눈 나머지가 0인 (i, j)의 갯수를 구해야한다.
  => (S[j] - S[i-1])%m == 0
  => S[j]%m == S[i-1]%m 
  => 위 조건을 만족하는 (i, j)의 갯수 
  
  (i == 1) => S[j] == 0의 갯수
  (i >= 2) => 나머지 값이 같은 것의 갯수 c 에대해 (c-1) * c / 2
*/

import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

class Main {
    static int n, m, sum[], count[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        long adder = 0;
        sum = new int[n+1];
        count = new int[m];
        st = new StringTokenizer(reader.readLine());
        for(int i=1; i<=n; i++){
            sum[i] = (Integer.parseInt(st.nextToken()) + sum[i-1]) % m;
            count[sum[i]]++;
            if(sum[i] == 0) adder++;
        }
        
        BigInteger answer = BigInteger.valueOf(adder);
        for(int i=0; i<m; i++){
            if(count[i]%2 == 0)
                adder = (long)count[i] / 2 * (count[i] - 1);
            else
                adder = (long)(count[i] - 1) / 2 * count[i];
            answer = answer.add(BigInteger.valueOf(adder));
        }
        System.out.println(answer);
    }
}
