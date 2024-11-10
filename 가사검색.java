// https://school.programmers.co.kr/learn/courses/30/lessons/60060
// 코딩테스트 연습 > 2020 KAKAO BLIND RECRUITMENT > 가사 검색

import java.util.*;

class Solution {
    public int[] solution(String[] words, String[] queries) {
        // split by key(length), get reversed words
        Map<Integer, List<String>> forward = new HashMap<>();
        Map<Integer, List<String>> reverse = new HashMap<>();
        for(String str : words){
            if(!forward.containsKey(str.length())){
                forward.put(str.length(), new ArrayList<>());
                reverse.put(str.length(), new ArrayList<>());
            }
            forward.get(str.length()).add(str);
            reverse.get(str.length()).add(reverse(str));
        }
        // sort all
        for(int k : forward.keySet()){
            Collections.sort(forward.get(k));
            Collections.sort(reverse.get(k));
        }
        
        // get answer of queries
        int answer[] = new int[queries.length];
        for(int i=0; i<queries.length; i++){
            
            // exception
            if(isStar(queries[i])){
                answer[i] = forward.get(queries[i].length()) != null ?
                    forward.get(queries[i].length()).size() : 0;
                continue;
            }
            
            // get prefix keyword and list
            String keyword;
            List<String> list;
            if(isPrefix(queries[i])){
                keyword = queries[i];
                list = forward.get(keyword.length());
            }
            else{
                keyword = reverse(queries[i]);
                list = reverse.get(keyword.length());
            }
            
            // get answer
            if(list == null)
                answer[i] = 0;
            else{
                int start = lowerBound(list, fill(keyword, 'a'));
                int end = upperBound(list, fill(keyword, 'z'));
                answer[i] = end - start;
            }
        }
        return answer;
    }
    
    // binary search lower bound
    private int lowerBound(List<String> list, String keyword){
        int start = 0, end = list.size(), mid;
        while(start < end){
            mid = (start + end) / 2;
            if(list.get(mid).compareTo(keyword) >= 0)
                end = mid;
            else start = mid + 1;
        }
        return end;
    }
    
    // binary search upper bound
    private int upperBound(List<String> list, String keyword){
        int start = 0, end = list.size(), mid;
        while(start < end){
            mid = (start + end) / 2;
            if(list.get(mid).compareTo(keyword) <= 0)
                start = mid + 1;
            else end = mid;
        }
        return end;
    }
    
    // return reversed str
    private String reverse(String str){
        StringBuilder sb = new StringBuilder();
        for(int i=str.length()-1; i>=0; i--)
            sb.append(str.charAt(i));
        return sb.toString();
    }
    
    // fill keyword's '?' to c
    private String fill(String keyword, char c){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<keyword.length(); i++)
            if(keyword.charAt(i) == '?')
                sb.append(c);
            else sb.append(keyword.charAt(i));
        return sb.toString();
    }
    
    // is keyword prefix? return true false
    private boolean isPrefix(String keyword){
        return keyword.charAt(0) != '?';
    }
    
    // is keyword is '*'(all '?', "????")
    private boolean isStar(String keyword){
        for(int i=0; i<keyword.length(); i++)
            if(keyword.charAt(i) != '?')
                return false;
        return true;
    }
}
