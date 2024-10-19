// https://www.acmicpc.net/problem/6549
// 히스토그램에서 가장 큰 직사각형

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static final int ARR_SIZE = 100001, SEG_SIZE = 262144; //2^18
    private static int n, arr[], seg[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[ARR_SIZE];
        seg = new int[SEG_SIZE];
        StringBuilder sb = new StringBuilder();
        while(true){
            StringTokenizer st = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(st.nextToken());
            if(n == 0) break;
            for(int i=0; i<n; i++)
                arr[i] = Integer.parseInt(st.nextToken());

            Arrays.fill(seg, -1);
            init_seg(1, 0, n-1);
            //segPrint();
            sb.append(solution(0, n-1)).append('\n');
        }
        System.out.println(sb);
    }

    private static int init_seg(int idx, int start, int end){
        if(start == end)
            return seg[idx] = start;
        int mid = (start + end) / 2;
        int left = init_seg(left(idx), start, mid);
        int right = init_seg(right(idx), mid+1, end);
        return seg[idx] = arr[left] < arr[right] ? left : right;
    }

    private static int intervalMinIdx(int from, int to){
        return query(1, 0, n-1, from, to);
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
            return arr[left] < arr[right] ? left : right;
        }
        else return query(right(idx), mid+1, end, from, to);
    }

    private static long solution(int from, int to){
        if(from > to || from < 0 || n <= from || to < 0 || n <= to)
            return -1;
        else if(from == to)
            return (long)arr[from];
        int minIdx = intervalMinIdx(from, to);
        long result = (long)(to - from + 1) * arr[minIdx];
        result = Math.max(result, solution(from, minIdx-1));
        result = Math.max(result, solution(minIdx+1, to));
        return result;
    }

    private static int left(int idx){ return idx*2; }
    private static int right(int idx){ return idx*2 + 1; }
    private static void segPrint(){
        StringBuilder sb = new StringBuilder();
        int idx = 1, n = 1;
        while(seg[idx] != -1){
            for(int i=0; i<n; i++)
                sb.append(seg[idx++]).append(' ');
            sb.append('\n');
            n *= 2;
        }
        System.out.println(sb);
    }
}
