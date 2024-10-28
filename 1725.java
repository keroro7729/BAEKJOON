// https://www.acmicpc.net/problem/1725
// 히스토그램 

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static final int MAX_SIZE = 262144; //2^18
    private static int n, arr[], seg[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i] = Integer.parseInt(reader.readLine());

        seg = new int[MAX_SIZE+1];
        initSeg(1, 0, n-1);
        //printSeg();
        System.out.println(solution(0, n-1));
    }

    private static int solution(int from, int to){
        if(from < 0 || from > to || to < 0 || to > MAX_SIZE) return -1;
        else if(from == to) return arr[from];
        int minidx = query(1, 0, n-1, from, to);
        int result = (to - from + 1) * arr[minidx];
        result = Math.max(result, solution(from, minidx-1));
        result = Math.max(result, solution(minidx+1, to));
        //System.out.println(from+"~"+to+" = "+result);
        return result;
    }

    private static int initSeg(int idx, int from, int to){
        if(from == to)
            return seg[idx] = from;
        int mid = (from + to) / 2;
        int left = initSeg(left(idx), from, mid);
        int right = initSeg(right(idx), mid+1, to);
        return seg[idx] = arr[left] <= arr[right] ? left : right;
    }

    private static int query(int idx, int start, int end, int from, int to){
        if(start == from && end == to)
            return seg[idx];
        int mid = (start + end) / 2;
        if(to <= mid)
            return query(left(idx), start, mid, from, to);
        else if(from <= mid){
            int left = query(left(idx), start, mid, from, mid);
            int right = query(right(idx), mid+1, end, mid+1, to);
            return arr[left] <= arr[right] ? left : right;
        }
        else return query(right(idx), mid+1, end, from, to);
    }


    private static void printSeg(){
        StringBuilder sb = new StringBuilder();
        int idx = 1;
        for(int i=1; i<=n; i*=2){
            for(int j=0; j<i; j++)
                sb.append(seg[idx++]).append(' ');
            sb.append('\n');
        }
        System.out.println(sb);
    }
    private static int left(int idx){ return idx*2; }
    private static int right(int idx){ return idx*2 + 1; }
    private static int parent(int idx){ return idx/2; }
}
