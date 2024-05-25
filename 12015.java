// https://www.acmicpc.net/problem/12015
// 가장 긴 증가하는 부분 수열 2

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, arr[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        arr = new int[n];
        List<Integer> list = new ArrayList<>();
        
        StringTokenizer st = new StringTokenizer(reader.readLine());
        arr[0] = Integer.parseInt(st.nextToken());
        list.add(arr[0]);
        for(int i=1; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            if(arr[i] > list.get(list.size()-1)) list.add(arr[i]);
            else lowerBound(list, arr[i]);
        }
        System.out.println(list.size());
    }

    public static void lowerBound(List<Integer> list, int val){
        int start = 0, end = list.size()-1, mid;
        while(true){
            mid = start + (end - start) / 2;
            if(end <= start) break;
            if(list.get(mid) > val){
                end = mid-1;
            }
            else if(list.get(mid) < val){
                start = mid+1;
            }
            else{
                return;
            }
        }
        //System.out.println(list);
        //System.out.println(start+", "+mid+", "+end+": "+val);
        if(end == -1) end = 0;
        if(list.get(end) < val) list.set(end+1, val);
        else list.set(end, val);
    }
}
