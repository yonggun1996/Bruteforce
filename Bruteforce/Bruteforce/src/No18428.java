package Avatar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
선생님들이 학생을 찾지 못하도록 장애물을 설치하는 문제
장애물은 3개씩 설치가 가능하며 선생님들은 상하좌우 움직이면서 학생들을 찾아낸다, 단 장애물을 만날경우 탐색을 중단한다
주어진 N이 많아봐야 6이기 때문에 완전탐색 알고리즘을 선택
선생님들의 위치를 리스트에 담아 조금이라도 더 빠른 탐색을 했다.
재귀함수를 최대 3번씩 호출해 장애물의 위치를 잡으면 선생님들을 상하좌우로 움직여서 학생들을 찾아낸다
학생들을 찾아내면 No을 반환하고 못찾아내면 YES를 반환한다.
만약 YES가 나올경우 더이상의 탐색을 중단하고 YES를 출력한다
 */

public class No18428 {
    static ArrayList<Teacher> teacher_List = new ArrayList<>();//선생님들의 위치를 담은 리스트
    static String[][] arr;//입력받을 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new String[n][n];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = st.nextToken();
                if(arr[i][j].equals("T")){//선생인 구역을 리스트에 추가
                    teacher_List.add(new Teacher(i,j));
                }
            }
        }

        String answer = "NO";
        out : for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(arr[i][j].equals("X")){
                    arr[i][j] = "O";//장애물 설치
                    answer = set_obstacle(1);
                    arr[i][j] = "X";//for문을 다 돌면 장애물 해제

                    if(answer.equals("YES")){//YES로 바뀔 경우 탐색을 멈춘다
                        break out;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static String set_obstacle(int count){
        if(count == 3){
            if(search()){//학생을 찾았을 경우는 NO 반환
                return "NO";
            }else{//학생을 못찾을 경우 YES 반환
                return "YES";
            }
        }else{
            String s = "NO";
            for(int i = 0; i < arr.length; i++){
                for(int j = 0; j < arr.length; j++){
                    if(arr[i][j].equals("X")){
                        arr[i][j] = "O";
                        s = set_obstacle(count + 1);
                        arr[i][j] = "X";
                        if(s.equals("YES")){//학생을 못찾은 경우 YES 반환
                            return s;
                        }
                    }
                }
            }

            return s;//학생을 계속해서 찾은 경우는 No 반환
        }
    }

    static boolean search(){
        for(int i = 0; i < teacher_List.size(); i++){
            Teacher t = teacher_List.get(i);
            int x = t.x;//x축 고정
            int y = t.y;//y축 고정

            //위로 확인
            for(int j = x - 1; j >= 0; j--){
                if(arr[j][y].equals("S")){
                    return true;
                }

                if(arr[j][y].equals("O")){
                    break;
                }
            }

            //아래로 확인
            for(int j = x + 1; j < arr.length; j++){
                if(arr[j][y].equals("S")){
                    return true;
                }

                if(arr[j][y].equals("O")){
                    break;
                }
            }

            //왼쪽확인
            for(int j = y - 1; j >= 0; j--){
                if(arr[x][j].equals("S")){
                    return true;
                }

                if(arr[x][j].equals("O")){
                    break;
                }
            }

            //오른쪽확인
            for(int j = y + 1; j < arr.length; j++){
                if(arr[x][j].equals("S")){
                    return true;
                }

                if(arr[x][j].equals("O")){
                    break;
                }
            }
        }

        return false;
    }
}

class Teacher{
    int x;
    int y;

    public Teacher(int x, int y){
        this.x = x;
        this.y = y;
    }
}
