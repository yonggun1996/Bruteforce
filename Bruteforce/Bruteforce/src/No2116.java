package Avatar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
브루트포스 알고리즘으로 해결한 문제
주사위 입력값을 HasMap에 담고 table에는 각 옆면에 있는 수를 담는다
table을 세팅할 때 마다 정렬을 해 가장 큰 값을 누적해 가장 큰 값이 답이 된다.
 */

public class No2116 {
    static int n;//입력받을 주사위 수
    static int answer = 0;//정답이 될 정수
    static int[][] table;
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();//입력받은 주사위 숫자

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        table = new int[n][4];//상하좌우순으로 담은 배열

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            ArrayList<Integer> list = new ArrayList<>();
            for(int j = 0; j < 6; j++){
                list.add(Integer.parseInt(st.nextToken()));
            }
            map.put(i, list);
        }

        //a ~ f번 중 하나를 아래로 선택한 후 모든 경우를 다 살펴본다
        for(int i = 0; i < 6; i++){
            set_Table(i, 0);
            switch (i) {
                //a번이 아래로 가능 경우
                case 0:
                    search(5, 1);
                    break;

                //b번이 아래로 가능 경우
                case 1:
                    search(3, 1);
                    break;

                //c번이 아래로 가능 경우
                case 2:
                    search(4, 1);
                    break;

                //d번이 아래로 가능 경우
                case 3:
                    search(1, 1);
                    break;

                //d번이 아래로 가능 경우
                case 4:
                    search(2, 1);
                    break;

                //e번이 아래로 가능 경우
                case 5:
                    search(0, 1);
                    break;
            }
        }

        System.out.println(answer);
    }

    static void search(int top, int level){
        if(level == n){
            int result = 0;
            for(int i = 0; i < table.length; i++){
                result += table[i][3];//정렬된 배열 중 가장 큰 값만 더한다
            }

            answer = Math.max(answer, result);
        }else{
            int num =  map.get(level - 1).get(top);//아래 주사위의 윗 부분
            int idx = map.get(level).indexOf(num);//윗 주사위의 아랫 부분은 몇 번째 인가
            set_Table(idx, level);
            switch (idx) {
                //a번이 아래로 가능 경우
                case 0:
                    search(5, level + 1);
                    break;

                //b번이 아래로 가능 경우
                case 1:
                    search(3, level + 1);
                    break;

                //c번이 아래로 가능 경우
                case 2:
                    search(4, level + 1);
                    break;

                //d번이 아래로 가능 경우
                case 3:
                    search(1, level + 1);
                    break;

                //e번이 아래로 가능 경우
                case 4:
                    search(2, level + 1);
                    break;

                //f번이 아래로 가능 경우
                case 5:
                    search(0, level + 1);
                    break;
            }
        }
    }

    //상하좌우 세팅하는 메소드
    static void set_Table(int num, int level){
        ArrayList<Integer> dice_list = map.get(level);

        if(num == 0 || num == 5){//a와 f가 마주보므로 0번과 5번을 제외한 숫자를 삽입한다
            table[level][0] = dice_list.get(1);
            table[level][1] = dice_list.get(2);
            table[level][2] = dice_list.get(3);
            table[level][3] = dice_list.get(4);
        }else if(num == 1 || num == 3){//b와 d가 마주보므로 1번과 3번을 제외한 숫자를 삽입한다
            table[level][0] = dice_list.get(0);
            table[level][1] = dice_list.get(2);
            table[level][2] = dice_list.get(4);
            table[level][3] = dice_list.get(5);
        }else{//c와 e가 마주보므로 2번과 4번을 제외한 숫자를 삽입한다
            table[level][0] = dice_list.get(0);
            table[level][1] = dice_list.get(1);
            table[level][2] = dice_list.get(3);
            table[level][3] = dice_list.get(5);
        }

        Arrays.sort(table[level]);//측면의 숫자들을 세팅할 때 마다 정렬한다
    }
}
