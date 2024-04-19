// https://www.acmicpc.net/problem/1253
// 좋다

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, arr[], answer = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(reader.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        for(int i=0; i<n; i++){
            if(isGood(i))
                answer++;
        }
        
        System.out.println(answer);
    }

    public static boolean isGood(int index){
        for(int i=0; i<n; i++){
            if(i == index) continue;
            if(contains(arr[index] - arr[i], Math.min(index, i), Math.max(index, i))){
                //System.out.println(arr[index]+" is good: "+arr[i]);
                return true;
            }
        }
        return false;
    }

    public static boolean contains(int value, int ex1, int ex2){
        int start = -1, middle = -1, end = -1;
        if(value == arr[ex1]){
            int i = ex1;
            while(true){
                i++;
                if(i == ex2) continue;
                if(i >= n) break;
                if(arr[i] == value) return true;
                else break;
            }
            i = ex1;
            while(true){
                i--;
                if(i == ex2) continue;
                if(i < 0) break;
                if(arr[i] == value) return true;
                else break;
            }
            return false;
        }
        else if(value == arr[ex2]){
            int i = ex2;
            while(true){
                i++;
                if(i == ex1) continue;
                if(i >= n) break;
                if(arr[i] == value) return true;
                else break;
            }
            i = ex2;
            while(true){
                i--;
                if(i == ex1) continue;
                if(i < 0) break;
                if(arr[i] == value) return true;
                else break;
            }
            return false;
        }
        else if(value < arr[ex1]){
            start = 0;
            end = ex1 - 1;
            if(end < 0) return false;
        }
        else if(arr[ex1] < value && value < arr[ex2]){
            start = ex1 + 1;
            end = ex2 - 1;
            if(start >= n || end < 0) return false;
        }
        else if(arr[ex2] < value){
            start = ex2 + 1;
            end = n - 1;
            if(start >= n) return false;
        }
        
        while(start <= end){
            middle = start + (end - start) / 2;            
            if(arr[middle] > value){
                end = middle - 1;
            }
            else if(arr[middle] < value){
                start = middle + 1;
            }
            else return true;
        }
        return false;
    }

    public static void print(int[] arr){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<arr.length; i++)
            sb.append(arr[i]).append(' ');
        System.out.println(sb);
    }
}
