// https://www.acmicpc.net/problem/11444
// 피보나치 수 6

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static long n;
    private static final int mask = 1000000007;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(reader.readLine());
        if(n == 1) System.out.println(1);
        else System.out.println(fibo(n));
    }

    private static int fibo(long n){
        int matrix[][] = {{1,1},{1,0}};
        return matPow(matrix, n-1)[0][0];
    }

    // matrix pow function
    private static int[][] matPow(int[][] m, long n){
        if(n == 1) return m;
        int[][] result = matPow(m, n/2);
        result = dot(result, result);
        if(n%2 != 0)
            return dot(result, m);
        else return result;
    }

    // matix multipy(dot) : matrix size(2,2)
    private static int[][] dot(int[][] m1, int[][] m2){
        int[][] dotResult = new int[2][2];
        long sum, v;
        for(int i=0; i<2; i++){
            for(int j=0; j<2; j++){
                sum = 0;
                for(int k=0; k<2; k++){
                    v = m1[i][k];
                    v *= m2[k][j];
                    sum += v;
                    sum %= mask;
                }
                dotResult[i][j] = (int)sum;
            }
        }
        return dotResult;
    }
}
/*
피보나치 수열 
점화식: F(n) = F(n-1) + F(n-2)
일반항: F(n) = sigma(0, (n+1)/2, combination(n, 2*k+1)*(5^k)) / 2^(n-1)

나머지 연산 분배법칙
+, -, *의 경우 : (A + B) % P = ((A%P) + (B%P)) % P
/연산의 경우 P가 소수일 때 : (A / B) % P = ((A%P) * (B^(p-2)%P)) % p

피보나치 수열 행렬제곱을 활용한 알고리즘
M =1, 1    M^n = F(n+1), F(n)
   1, 0          F(n),   F(n-1)

https://namu.wiki/w/%ED%94%BC%EB%B3%B4%EB%82%98%EC%B9%98%20%EC%88%98%EC%97%B4
*/
