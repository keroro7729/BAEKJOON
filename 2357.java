// https://www.acmicpc.net/problem/2357
// 최소값과 최대값

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m, arr[];
    static int segTree[][], size, scope[][];
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i] = Integer.parseInt(reader.readLine());

        size = getSize(n);
        segTree = new int[2][size+1];
        scope = new int[size+1][2];
        for(int i=0; i<=size; i++)
            scope[i][0] = scope[i][1] = -1;
        initSegTree(0, n-1, 1);
        //printSegTree();

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            sb.append(getSeg(a, b, 0, 1)).append(' ')
                .append(getSeg(a, b, 1, 1)).append('\n');
        }
        System.out.println(sb);
    }

    public static int getSize(int n){
        int size = 1;
        while(size < n) size *= 2;
        return size * 2 - 1;
    }

    public static void initSegTree(int start, int end, int idx){
        if(start == end){
            segTree[0][idx] = segTree[1][idx]  = arr[start];
            scope[idx][0] = start; scope[idx][1] = end;
            return;
        }
        int mid = start + (end - start) / 2;
        initSegTree(start, mid, idx*2);
        initSegTree(mid+1, end, idx*2+1);
        segTree[0][idx] = Math.min(segTree[0][idx*2], segTree[0][idx*2+1]);
        segTree[1][idx] = Math.max(segTree[1][idx*2], segTree[1][idx*2+1]);
        scope[idx][0] = start; scope[idx][1] = end;
    }

    public static int getSeg(int a, int b, int fidx, int idx){
        if(idx > size){
            System.out.println("error: getSeg("+a+", "+b+", "+fidx+", "+idx+")");
            return -1;
        }
        int start = scope[idx][0], end = scope[idx][1];
        if(a == start && b == end) return segTree[fidx][idx];
        int mid = start + (end - start) / 2;
        if(a <= mid && b <= mid){
            return getSeg(a, b, fidx, idx*2);
        }
        else if(a <= mid && b > mid){
            int left = getSeg(a, mid, fidx, idx*2);
            int right = getSeg(mid+1, b, fidx, idx*2+1);
            if(fidx == 0) return Math.min(left, right);
            else if(fidx == 1) return Math.max(left, right);
        }
        else{ // if(a > mid && b > mid)
            return getSeg(a, b, fidx, idx*2+1);
        }
        System.out.println("error: getSeg("+a+", "+b+", "+fidx+", "+idx+")");
        return -1;
    }

    public static void printSegTree(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<=1; i++){
            for(int j=1; j<=size; j++){
                int[] se = scope[j];
                sb.append(se[0]).append('~').append(se[1])
                    .append(':').append(' ').append(segTree[i][j]).append('\n');
            }
            sb.append('\n');
        }System.out.println(sb);
    }
}
