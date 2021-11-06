package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No15684 {

    static boolean checkanswer = false;//같은 위치로 떨어지는지 확인하는 변수
    static Horizental[][] ladder;//사다리를 표현한 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        ladder = new Horizental[H + 1][N + 1];
        for(int i = 0; i <= H; i++){
            for(int j = 0; j <= N; j++){
                //사다리 처음 설정을 왼쪽 오른쪽으로 갈 수 없음으로 설정한다
                ladder[i][j] = new Horizental(false, false);
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ladder[a][b].right = true;//해당 세로줄에서 오른쪽으로 갈 수 있게 설정
            ladder[a][b + 1].left = true;//해당 세로줄에서 왼쪽으로 갈 수 있게 설정
        }

        if(M == 0){
            System.out.println(0);
        }else{
            //처음부터 세로줄 번호에 맞게 도착하는 경우가 있기 때문에 0부터 시작
            for(int i = 0; i <= 3; i++){
                search(N,H,i, 0);
                if(checkanswer) {//만약 찾았으면 i를 출력한 후에 반복문을 벗어난다
                    System.out.println(i);
                    break;
                }
            }

            if(!checkanswer){//3개의 길을 더 두고도 못찾았으면 -1을 출력한다
                System.out.println(-1);
            }
        }
    }

    public static void search(int N, int H, int count, int depth){
        if(count == depth){//count개 만큼 경로를 추가했으면 세로줄 번호에 맞게 들어가는지 확인
            if(simulation()) {
                checkanswer = true;
            }
        }else{
            for(int i = 1; i <= H; i++){
                for(int j = 1; j < N; j++){
                    //핸제 세로줄의 왼족 오른쪽, 오른쪽 세로줄의 왼쪽, 오른쪽 경로가 없어야 한다
                    if(!ladder[i][j].left && !ladder[i][j].right && !ladder[i][j + 1].left && !ladder[i][j + 1].right){
                        //경로 설정
                        ladder[i][j].right = true;
                        ladder[i][j + 1].left = true;
                        search(N,H,count,depth + 1);
                        //재귀함수를 호출한 후 경로를 다시 막아둔다
                        ladder[i][j].right = false;
                        ladder[i][j + 1].left = false;
                    }
                }
            }
        }
    }

    public static boolean simulation(){
        for(int i = 1; i < ladder[0].length; i++){//세로선
            int n = i;
            for(int j = 1; j < ladder.length; j++){//가로선
                if(ladder[j][n].left){
                    n--;
                }else if(ladder[j][n].right){
                    n++;
                }
            }

            if(n != i){//시작한 세로선이 도착한 세로번호와 다르면 false 반환
                return false;
            }
        }

        return true;//모든 세로선의 도착지점이 세로선 번호와 같으면 true 반환
    }
}

class Horizental{
    boolean left;
    boolean right;

    public Horizental(boolean left, boolean right){
        this.left = left;
        this.right = right;
    }
}
