// https://www.acmicpc.net/problem/14003
// 가장 긴 증가하는 부분 수열 5

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static int n, arr[], indexs[];
    private static List<Integer> list = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        arr = new int[n];
        indexs = new int[n];
        Arrays.fill(indexs, -1);
        StringTokenizer st = new StringTokenizer(reader.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            indexs[i] = addToList(arr[i]);
            //debug("iter"+(i+1)+": input="+arr[i]);
        }

        // output
        int size = list.size();
        System.out.println(size);
        List<Integer> answer = new LinkedList<>();
        for(int i=n-1; i>=0; i--){
            if(indexs[i] == size-1){
                answer.add(0, arr[i]);
                size--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int a : answer) sb.append(a).append(' ');
        System.out.println(sb);
    }

    // lower bound, return inserted index
    private static int addToList(int value){
        if(list.isEmpty() || list.get(list.size()-1) < value){
            list.add(value);
            return list.size()-1;
        }
        
        int start = 0, end = list.size() - 1, mid;
        while(start < end){
            mid = (end + start) / 2;
            if(list.get(mid) < value)
                start = mid + 1;
            else if(list.get(mid) == value)
                return -1;
            else
                end = mid - 1;
        }

        if(list.get(start) < value) start++;
        list.set(start, value);
        return start;
    }

    private static void debug(String header){
        System.out.println(header);
        System.out.println(list);
        for(int i : indexs)
            System.out.print(i+" ");
        System.out.println("\n");
    }
}
