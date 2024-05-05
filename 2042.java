// https://www.acmicpc.net/problem/2042
// 구간 합 구하기

import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

class Main {
    static int n, m, k;
    static long arr[];
    static BigInteger segTree[];
    static int size, scope[][];
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new long[n];
        for(int i=0; i<n; i++){
            arr[i] = Long.parseLong(reader.readLine());
        }
        init();

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m+k; i++){
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if(a == 1){
                update(b-1, c);
            }
            else if(a == 2){
                sb.append(get(b-1, (int)c-1, 1)).append('\n');
            }
        }
        System.out.println(sb);
    }

    public static void init(){
        size = 1;
        while(size < n) size *= 2;
        size = size * 2;
        segTree = new BigInteger[size];
        scope = new int[size][3];
        initSegTree(0, n-1, 1);
    }
    public static BigInteger initSegTree(int start, int end, int idx){
        int mid = start + (end - start) / 2;
        scope[idx][0] = start;
        scope[idx][1] = mid;
        scope[idx][2] = end;
        if(start == end)
            return segTree[idx] = BigInteger.valueOf(arr[start]);
        return segTree[idx] = 
            initSegTree(start, mid, idx*2).add(
            initSegTree(mid+1, end, idx*2+1));
    }

    public static void update(int idx, long value){
        long changes = value - arr[idx];
        arr[idx] = value;
        addSegTree(idx, changes, 1);
    }
    public static void addSegTree(int targetIdx, long changes, int idx){
        segTree[idx] = segTree[idx].add(BigInteger.valueOf(changes));
        if(scope[idx][0] == scope[idx][2]) return;
        if(targetIdx <= scope[idx][1])
            addSegTree(targetIdx, changes, idx*2);
        else
            addSegTree(targetIdx, changes, idx*2+1);
    }

    public static BigInteger get(int start, int end, int idx){
        if(start == scope[idx][0] && end == scope[idx][2])
            return segTree[idx];
        if(start <= scope[idx][1] && end <= scope[idx][1]){
            return get(start, end, idx*2);
        }
        else if(start > scope[idx][1] && end > scope[idx][1]){
            return get(start, end, idx*2+1);
        }
        else{
            return get(start, scope[idx][1], idx*2).add(
                   get(scope[idx][1]+1, end, idx*2+1));
        }
    }
}
