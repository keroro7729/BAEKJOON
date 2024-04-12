// https://www.acmicpc.net/problem/5639
// 이진 검색 트리

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        MyTree myTree = new MyTree();
        String input = scan.readLine();
        while(input != null){
            myTree.add(Integer.parseInt(input));
            input = scan.readLine();
        }
        System.out.println(myTree.toString());
    }
}
class MyTree{
    Node head;
    public MyTree(){
        head = null;
    }
    public void add(int n){
        if(head == null){
            head = new Node(n);
            return;
        }
        Node cur = head;
        while(true){
            if(cur.value > n){
                if(cur.left == null){
                    cur.setLeft(n);
                    return;
                }
                else cur = cur.left;
            }
            else if(cur.value < n){
                if(cur.right == null){
                    cur.setRight(n);
                    return;
                }
                else cur = cur.right;
            }
        }
    }

    StringBuilder sb;
    public String toString(){
        sb = new StringBuilder();
        dfs(head);
        return sb.toString();
    }
    public void dfs(Node cur){
        if(cur == null) return;
        if(cur.left == null && cur.right == null){
            sb.append(cur.value).append('\n');
            return;
        }
        dfs(cur.left);
        dfs(cur.right);
        sb.append(cur.value).append('\n');
    }
}
class Node{
    int value;
    Node left, right;
    public Node(int value){
        this.value = value;
        left = right = null;
    }
    public void setLeft(int n){
        left = new Node(n);
    }
    public void setRight(int n){
        right = new Node(n);
    }
}
