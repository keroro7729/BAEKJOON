// 백준 1039번 교환
// https://www.acmicpc.net/problem/1039

/*
  주어진 숫자를 변경해서 얻을 수 있는 최대값을 MAX_NUM으로 저장
  MAX_NUM과 비교하며 앞자리부터 MAX_NUM과 같아질 수 있는 모든 경우를 dfs로 순회
  종료 시점을 잘 볼필요가 있다. 같은 문자가 있어 변경 횟수 k에 영향을 받지 않거나
  k와 현재 변경횟수의 홀짝 여부가 같다면 만들 수 있는 숫자다.
  그 중에서도 k만큼 변경을 완료한 경우, 이미 MAX_NUM과 같아져서 더이상 탐색할 필요가 없을 때 종료된다.
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(scan.readLine());

        // input
        String num = st.nextToken();
        k = Integer.parseInt(st.nextToken());

        // except case
        if(num.length() == 1 ||
          (num.length() == 2 && num.indexOf('0') != -1)){
            System.out.println(-1);
            return;
        }

        // maximum number string
        MAX_NUM = get_max_num(num);
        // if num num has same char(token) its moveable
        moveable = has_same_token(num);

        dfs(num, 0);
        System.out.println(max_result);
    }

    static int k = -1, max_result = 0;
    static String MAX_NUM;
    static boolean moveable = false;

    public static void dfs(String num, int depth){
        if(moveable || depth%2 == k%2){
            //System.out.println(num);
            max_result = Math.max(max_result, Integer.parseInt(num));
            if(depth == k || num.equals(MAX_NUM)) return;
        }

        // compare to MAX_NUM, select target index, and source indexes
        int target = -1;
        List<Integer> idxes = new ArrayList<>();
        for(int i=0; i<num.length(); i++){
            if(MAX_NUM.charAt(i) == num.charAt(i)) continue;
            target = i;
            char target_char = MAX_NUM.charAt(i);
            for(int j=i+1; j<num.length(); j++){
                if(num.charAt(j) == target_char)
                    idxes.add(j);
            }
            break;
        }

        // (num == MAX_NUM) but should swap
        if(target == -1){
            dfs(swap(num, num.length()-2, num.length()-1), depth+1);
            return;
        }

        for(int idx : idxes){
            dfs(swap(num, target, idx), depth+1);
        }
    }

    public static String get_max_num(String num){
        int[] count_nums = new int[10];
        for(int i=0; i<num.length(); i++){
            int idx = (int)(num.charAt(i) - '0');
            count_nums[idx]++;
        }

        StringBuilder sb = new StringBuilder();
        for(int c=9; c>=0; c--){
            for(int i=0; i<count_nums[c]; i++){
                sb.append((char)(c+'0'));
            }
        }
        return sb.toString();
    }

    public static boolean has_same_token(String str){
        Set<Character> set = new HashSet<>();
        for(int i=0; i<str.length(); i++){
            if(set.contains(str.charAt(i))) return true;
            else set.add(str.charAt(i));
        }
        return false;
    }

    public static String swap(String str, int i, int j){
        StringBuilder sb = new StringBuilder();
        for(int k=0; k<str.length(); k++){
            if(k == i) sb.append(str.charAt(j));
            else if(k == j) sb.append(str.charAt(i));
            else sb.append(str.charAt(k));
        }
        return sb.toString();
    }
}
