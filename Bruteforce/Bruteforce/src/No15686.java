package Avatar;
import java.io.*;
import java.util.*;

/*
치킨집 일부를 폐쇠한 후 각 집마다 가까운 치킨집을 구해 거리에 담는다.
처음에는 조합을 재귀함수 호출 후 0번째 집부터 해서 시간초곽 났었다.
 */

public class No15686 {
    static int min_dist = Integer.MAX_VALUE;//답이 될 도시의 가장 작은 치킨거리
    static int[][] map;//입력받는 맵
    static ArrayList<Chicken> home_list = new ArrayList<>();//집이 있는 좌표
    static ArrayList<Chicken> chicken_list = new ArrayList<>();//치킨집 좌표
    static boolean[] check;//폐업할 치킨집을 체크하는 배열
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) home_list.add(new Chicken(i,j));//집은 home_list에 삽입
                if(map[i][j] == 2) chicken_list.add(new Chicken(i,j));//치킨집은 chicken_list에 삽입
            }
        }

        check = new boolean[chicken_list.size()];
        setting_chicken(0,chicken_list.size() - m, -1);
        System.out.println(min_dist);
    }

    public static void setting_chicken(int count, int limit, int index){
        if(count == limit){
            int dist = 0;
            for(int i = 0; i < home_list.size(); i++){
                int min = Integer.MAX_VALUE;//해당 집과 각 치킨집의 거리
                for(int j = 0; j < check.length; j++){
                    if(!check[j]){
                        min = Math.min(min, Math.abs(home_list.get(i).x - chicken_list.get(j).x) + Math.abs(home_list.get(i).y - chicken_list.get(j).y));
                    }
                }
                dist += min;//총 거리에 누적한다.
            }

            min_dist = Math.min(dist, min_dist);//기존에 구했던 누적값과 비교해 작으면 값을 바꾼다
        }else{
            for(int i = index + 1; i < check.length; i++){//폐업하는 치킨집의 조합을 구하는 for문
                if(!check[i]){//폐업을 안한 집인 경우
                    check[i] = true;
                    setting_chicken(count + 1, limit, i);//재귀함수로 다른 집을 폐업해본다
                    check[i] = false;
                }
            }
        }
    }
}

class Chicken{
    int x;
    int y;

    public Chicken(int x, int y){
        this.x = x;
        this.y = y;
    }
}
