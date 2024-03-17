// https://www.acmicpc.net/problem/31498
// BOJ 31498 장난을 잘 치는 토카 양

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static long tocaStart, tocaSpeed, dolStart, dolSpeed, k;

    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(scan.readLine());
        tocaStart = Long.parseLong(st.nextToken());
        tocaSpeed = Long.parseLong(st.nextToken());
        st = new StringTokenizer(scan.readLine());
        dolStart = tocaStart + Long.parseLong(st.nextToken());
        dolSpeed = Long.parseLong(st.nextToken());
        k = Long.parseLong(scan.readLine());

        // solution
        long startTime = System.currentTimeMillis();
        System.out.println(binarySearch(tocaStart, tocaSpeed, dolStart, dolSpeed, k));
        //System.out.println(System.currentTimeMillis()-startTime+"ms");
    }
    
    // home = 0, moves to left(-)
    // home(0)___________<<-toca(tp)____<<-dol(dp)
    // tp = tocaPosition, ts = tocaSpeed, dp = dolPosition, ds = dolSpeed, k = tocaDecrement
    public static long binarySearch(long tp, long ts, long dp, long ds, long k){
        long start = 0, end, mid;
        long lastCheck = -1;
        if(k == 0) end = Long.parseLong("1000000000000"); // !!search size!!
        else end = (long)Math.floor(ts/(double)k + 1);

        while(true){
            if(lastCheck == -1) mid = (start+end)/2;
            else mid = lastCheck;
            long toca = tp - ts*mid + k*mid*(mid-1)/2;
            long dol = dp - ds*mid;
            //System.out.println(mid+" "+toca+" "+dol);
            if(lastCheck == -1 && end == start+1 || start >= end){
                lastCheck = start+1;
                continue;
            }
            
            if(dol <= 0){
                end = mid;
                if(lastCheck != -1) return -1;
            }
            else if(toca > 0){
                if(dol <= toca) return -1;
                start = mid;
                if(lastCheck != -1) return -1;
            }
            else{
                if(toca == 0) return mid;
                end = mid;
                if(lastCheck != -1) return mid;
            }
        }
    }
}
