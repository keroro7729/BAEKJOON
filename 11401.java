// https://www.acmicpc.net/problem/11401
// 이항 계수 3

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int n, k;
    private static final int MASK = 1000000007;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        System.out.println(solution(n, k));
    }

    private static int solution(int n, int k){
        // binomial: n! / (k! * (n-k)!)
        // mod operation distribution method of divide
        // (a / b) % m = ((a % m) * (b^(m-2) % m)) % m

        // (k+1 * ... * n / (n-k)! ) % m
        // maskedMul(k+1, n) * maskedPow(maskedMul(2, n-k), m-2)
        int a, b;
        if(k >= n-k){
            a = k;
            b = n-k;
        }else{
            a = n-k;
            b = k;
        }
        return (int)(((long)maskedMul(a+1, n) * maskedPow(maskedMul(2, b), MASK-2)) % MASK);
    }

    private static int maskedMul(int start, int end){
        long result = 1;
        for(int n=start; n<=end; n++){
            result *= n;
            result %= MASK;
        }
        return (int)result;
    }

    private static int maskedPow(int n, int m){
        // n^m % MASK
        if(m == 1) return n % MASK;
        int result = maskedPow(n, m/2);
        result = (int)(((long)result * result) % MASK);
        if(m%2 == 0) return result;
        else return (int)(((long)result * (n % MASK)) % MASK);
    }
}
