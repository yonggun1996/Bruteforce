package Avatar;
import java.io.*;
import java.util.*;

/*
야구 시뮬레이션 코드
각 타자들의 이닝별 컨디션이 주어질 때 각 이닝당 점수를 합해 최대점수를 출력하는 문제
브루트포스 알고리즘으로 타자들의 타순을 조정
타자들의 장타율을 토대로 점수 환산
이닝이 끝나면 다음 이닝의 컨디션으로 점수 환산
 */

public class BaseBall {

    static int[][] score_board;//각 타자들의 이닝별 장타력
    static boolean[] use;//해당 타자를 타순 배치를 했는지?
    static int[] hitter;//각 타자들의 타격 결과
    static int answer = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        score_board = new int[n][9];

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++){
                score_board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        hitter = new int[9];
        use = new boolean[9];
        Arrays.fill(hitter, -1);

        hitter[3] = 0;//4번타자 설정
        use[0] = true;

        // 1번타자 설정
        for(int j = 1; j < 9; j++){
            hitter[0] = j;
            use[j] = true;
            set_hitter(1, n);
            hitter[0] = -1;
            use[j] = false;
        }


        System.out.println(answer);
    }

    public static void set_hitter(int index, int n){
        //index + 1번 타자 설정
        if(index == 9){//모든 타순을 배치했으면 점수 시뮬레이션 함수 호출
            int score = get_score(n);
            answer = Math.max(answer, score);
        }else if(index == 3){//4번타자는 이미 설정됐기 때문에 5번타자로 설정
            set_hitter(index + 1, n);
        }else{
            for(int i = 1; i < 9; i++){
                if(!use[i]){//해당 타자를 배치하지 않았을 때만
                    hitter[index] = i;//타자 배치
                    use[i] = true;//타순 배치했음을 설정
                    set_hitter(index + 1, n);
                    hitter[index] = -1;//다시 원상태로
                    use[i] = false;
                }
            }
        }
    }

    public static int get_score(int n){
        int ining = 1;//이닝수
        int[] hit_arr = score_board[ining - 1];//각 타자들의 장타
        int score = 0;//점수
        int outcount = 0;//아웃카운트
        int index = 0;//타석에 들어설 타자
        int[] base = new int[3];//베이스를 표현
        Arrays.fill(base, -1);

        while(ining <= n){
            int hit = hit_arr[hitter[index]];//타자의 타격 결가
            if(hit == 0){//아웃인 경우 아웃카운트를  늘린다
                outcount++;
            }else if(hit == 1){//1루타
                if(base[2] != -1) {
                    score++;//3루주자 득점
                    base[2] = -1;
                }

                if(base[1] != -1){//2루주자 3루까지
                    base[2] = base[1];
                    base[1] = -1;
                }

                if(base[0] != -1){//1루주자 2루까지
                    base[1] = base[0];
                    base[0] = -1;
                }

                base[0] = index;//타자주자 1루
            }else if(hit == 2){//2루타
                if(base[2] != -1) {
                    score++;//3루주자 득점
                    base[2] = -1;
                }

                if(base[1] != -1) {
                    score++;//2루주자 득점
                    base[1] = -1;
                }

                if(base[0] != -1){//1루주자는 3루까지
                    base[2] = base[0];
                    base[0] = -1;
                }

                base[1] = index;
            }else if(hit == 3){
                if(base[2] != -1) {
                    score++;//3루주자 득점
                    base[2] = -1;
                }

                if(base[1] != -1) {
                    score++;//2루주자 득점
                    base[1] = -1;
                }

                if(base[0] != -1){//1루주자 득정
                    score++;
                    base[0] = -1;
                }

                base[2] = index;//타자는 3루까지
            }else if(hit == 4){//홈런
                for(int i = 0; i < 3; i++){//베이스에 있는 주자가 점수가 된다.
                    if(base[i] != -1){
                        score++;
                        base[i] = -1;
                    }
                }

                score++;//홈런은 타자도 점수가 된다.
            }
            index++;//다음 타순

            if(outcount == 3){//아웃카운트가 3개일 경우
                ining++;//다음 이닝으로
                outcount = 0;//다음 이닝은 아웃카운트가 다시 0
                Arrays.fill(base, -1);//베이스는 비워둠
                if(ining <= n){//다음이닝의 타자들 컨디션 교체
                    hit_arr = score_board[ining - 1];
                }
            }

            if(index == 9){//9번타자까지 했으면 다시 1번타자로
                index = 0;
            }
        }

        return score;
    }
}