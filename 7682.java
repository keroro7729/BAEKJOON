// https://www.acmicpc.net/problem/7682
// 틱택토

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static final int X = 1, O = 2, EMPTY = 0;
    static int[] map;
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        List<Integer> xlist = new ArrayList<>();
        List<Integer> olist = new ArrayList<>();
        while(true){
            String line = reader.readLine();
            if(line.equals("end")) break;
            xlist.clear(); olist.clear();
            map = new int[9];
            for(int i=0; i<9; i++){
                switch(line.charAt(i)){
                    case 'X': xlist.add(i); map[i] = X; break;
                    case 'O': olist.add(i); map[i] = O; break;
                }
            }
            if(valid(xlist, olist)) sb.append("valid\n");
            else sb.append("invalid\n");
        }
        System.out.println(sb);
    }

    public static boolean valid(List<Integer> xlist, List<Integer> olist){
        if((xlist.size() == 5 && olist.size() == 4) && !isEnd())
            return true;
        else if((xlist.size() != 5 || olist.size() != 4) && !isEnd())
            return false;
        else if(xlist.size() == olist.size()+1){
            for(int i=0; i<xlist.size(); i++){
                int idx = xlist.get(i);
                map[idx] = 0;
                if(!isEnd()) return true;
                map[idx] = X;
            }
        }
        else if(xlist.size() == olist.size()){
            for(int i=0; i<olist.size(); i++){
                int idx = olist.get(i);
                map[idx] = 0;
                if(!isEnd()) return true;
                map[idx] = O;
            }
        }
        return false;
    }

    public static boolean isEnd(){
        for(int i=0; i<3; i++){
            if(map[i*3] != EMPTY && map[i*3] == map[i*3+1] && map[i*3] == map[i*3+2])
                return true;
            if(map[i] != EMPTY && map[i] == map[i+3] && map[i] == map[i+6])
                return true;
        }
        if(map[0] != EMPTY && map[0] == map[4] && map[4] == map[8])
            return true;
        if(map[2] != EMPTY && map[2] == map[4] && map[4] == map[6])
            return true;
        return false;
    }
}
