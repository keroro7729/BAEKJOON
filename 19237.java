// https://www.acmicpc.net/problem/19237
// 어른 상어

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    private static final int[] DIRX = {-1, 1, 0, 0};
    private static final int[] DIRY = {0, 0, -1, 1};
    private static int n, m, k, mapId[][], mapTime[][];
    private static Set<Shark> sharkSet;
    
    public static void main(String[] args) throws IOException {
        // input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken()); //map size
        m = Integer.parseInt(st.nextToken()); //sharks num
        k = Integer.parseInt(st.nextToken()); //life time

        Shark[] sharks = new Shark[m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(reader.readLine());
            for(int j=0; j<n; j++){
                int id = Integer.parseInt(st.nextToken());
                if(id != 0)
                    sharks[id-1] = new Shark(id, i, j);
            }
        }

        st = new StringTokenizer(reader.readLine());
        for(int i=0; i<m; i++)
            sharks[i].setD(Integer.parseInt(st.nextToken()) - 1);

        for(int i=0; i<m; i++){
            for(int d=0; d<4; d++){
                sharks[i].setPriority(d, reader.readLine());
            }
        }

        sharkSet = new HashSet<>(Arrays.asList(sharks));
        mapId = new int[n][n];
        mapTime = new int[n][n];
        Shark.setN(n);
        debug("initial state");

        // solution
        int answer = 0;
        while(true){
            sprayScent();
            moveAll();
            answer++;
            decreaseScent();
            handleCollision();
            debug(answer+" sec state");

            if(sharkSet.size() == 1) break;
            else if(answer >= 1000){
                answer = -1;
                break;
            }
        }
        System.out.println(answer);
    }

    // solution functions
    private static void sprayScent(){
        for(Shark s : sharkSet){
            int x = s.x, y = s.y;
            mapId[x][y] = s.id;
            mapTime[x][y] = k;
        }
    }

    private static void moveAll(){
        for(Shark s : sharkSet){
            int[] arroundInfo = new int[4];
            for(int i=0; i<4; i++){
                int x = s.x + DIRX[i];
                int y = s.y + DIRY[i];
                if(indexOut(x, y))
                    arroundInfo[i] = -1;
                else arroundInfo[i] = mapId[x][y];
            }
            s.move(arroundInfo);
        }
    }

    private static void decreaseScent(){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(mapTime[i][j] == 1){
                    mapTime[i][j] = 0;
                    mapId[i][j] = 0;
                }
                else if(mapTime[i][j] > 1)
                    mapTime[i][j]--;
            }
        }
    }

    private static void handleCollision(){
        // put sharks by position
        Map<Integer, Set<Shark>> sets = new HashMap<>();
        for(Shark s : sharkSet){
            int pos = s.getPosition();
            if(!sets.containsKey(pos))
                sets.put(pos, new HashSet<>());
            sets.get(pos).add(s);
        }

        // remove all sharks in collision set (except min shark)
        for(int key : sets.keySet()){
            Set<Shark> set = sets.get(key);
            if(set.size() <= 1) continue;

            Iterator<Shark> iter = set.iterator();
            Shark min = iter.next();
            while(iter.hasNext()){
                Shark s = iter.next();
                if(s.id < min.id)
                    min = s;
            }
            set.remove(min);
            sharkSet.removeAll(set);
        }
    }

    private static boolean indexOut(int x, int y){
        return x < 0 || n <= x || y < 0 || n <= y;
    }

    // debug printing
    private static int debugMode = 0;
    private static void debug(String header){
        if(debugMode <= 0) return;
        System.out.println(header);
        Iterator<Shark> iter = sharkSet.iterator();
        while(iter.hasNext()){
            Shark s = iter.next();
            if(debugMode == 1)
                System.out.println(s);
            else if(debugMode == 2)
                System.out.println(s.getInfo());
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(debugMode == 1)
                    System.out.print(mapId[i][j]+" ");
                else if(debugMode == 2)
                    System.out.print(mapId[i][j]+","+mapTime[i][j]+" ");
            }System.out.println();
        }System.out.println();
    }
}

class Shark{
    // settings
    static int n;
    int id, x, y, d;
    int[][] priority;
    public Shark(int id, int x, int y){
        priority = new int[4][4];
        this.id = id;
        this.x = x;
        this.y = y;
    }
    public static void setN(int num){ n = num; }
    public void setD(int d){ this.d = d; }
    public void setPriority(int d, String line){
        StringTokenizer st = new StringTokenizer(line);
        for(int i=0; i<4; i++)
            priority[d][i] = Integer.parseInt(st.nextToken()) - 1;
    }

    // move functions
    private static final int[] DIRX = {-1, 1, 0, 0};
    private static final int[] DIRY = {0, 0, -1, 1};

    public void move(int[] arroundInfo){
        Set<Integer> moveAble = new HashSet<>();
        for(int i=0; i<4; i++)
            if(arroundInfo[i] == 0)
                moveAble.add(i);

        if(moveAble.isEmpty())
            for(int i=0; i<4; i++)
                if(arroundInfo[i] == id)
                    moveAble.add(i);

        for(int dir : priority[d]){
            if(moveAble.contains(dir) && !indexOut(x+DIRX[dir], y+DIRY[dir])){
                d = dir;
                x += DIRX[d];
                y += DIRY[d];
                return;
            }
        }
    }

    public int getPosition(){
        return x * n + y;
    }

    private boolean indexOut(int x, int y){
        return x < 0 || n <= x || y < 0 || n <= y;
    }
    

    // printings
    @Override
    public String toString(){
        return id+" : ("+x+","+y+") "+d;
    }
    public String getInfo(){
        StringBuilder sb = new StringBuilder(this.toString());
        sb.append('\n');
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                sb.append(priority[i][j]).append(' ');
            }sb.append('\n');
        }
        return sb.toString();
    }
}
