// https://www.acmicpc.net/problem/2110
// 공유기 설치

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int n, c, arr[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n];
        for(int i=0; i<n; i++)
            arr[i] = Integer.parseInt(reader.readLine());
        Arrays.sort(arr);
        //print(arr);

        int start = 1, end = arr[n-1] - arr[0];
        int mid = -1, result = -1, answer = 0;
        while(start+1 < end){
            mid = start + (end - start)/2;
            result = install(mid);
            //System.out.println(mid+": "+result);
            if(result < answer)
                end = mid;
            else{
                answer = Math.max(answer, result);
                start = mid;
            }
        }
        answer = Math.max(answer, install(start));
        answer = Math.max(answer, install(end));
        System.out.println(answer);
    }

    // 최소 d이상의 거리를 벌리며 공유기를 설치
    private static int install(int d){
        int prev = 0; // 이전 공유기 인덱스
        int count = 1; // 설치된 공유기 갯수
        int min = Integer.MAX_VALUE;
        for(int i=1; i<n; i++){
            if(arr[i] - arr[prev] >= d){
                min = Math.min(min, arr[i]-arr[prev]);
                prev = i;
                count++;
            }
        }
        if(arr[n-1] - arr[prev] >= d){
            min = Math.min(min, arr[n-1]-arr[prev]);
            count++;
        }
        if(count < c) // d가 너무 큼
            return -1;
        else return min;
    }

    private static void print(int[] arr){
        for(int i=0; i<arr.length; i++)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
}
