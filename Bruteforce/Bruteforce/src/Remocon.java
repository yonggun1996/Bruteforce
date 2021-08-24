package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Remocon {

    static ArrayList<Integer> result_list;
    static HashSet<Integer> result_set = new HashSet<>();//조합을 찾으면서 중복된 값이 올 수 있기 때문에 HashSet를 사용한다
    static ArrayList<Integer> list = new ArrayList<>();//사용할 수 있는 수들

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 10; i++){
            list.add(i);
        }

        String n_str = br.readLine();
        int n = Integer.parseInt(n_str);

        int m = Integer.parseInt(br.readLine());

        if(m == 0){//고장난 버튼이 없을 경우 그냥 누르는 방법, 100번에서 +,-만 눌렀을 때의 방법 중 작은 수를 출력한다
            int answer = Math.min(n_str.length(), Math.abs(Integer.parseInt(n_str) - 100));
            System.out.println(answer);
        }else{
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()){
                int num = Integer.parseInt(st.nextToken());
                int idx = list.indexOf(num);
                list.remove(idx);
            }

            for(int i = 0; i < list.size(); i++){//사용할 수 있는 수를 토대로 모든 조합들을 찾아보는 for문
                StringBuilder sb = new StringBuilder();
                sb.append(list.get(i));
                set_result(sb, n_str.length());
            }

            result_list = new ArrayList<>(result_set);
            Collections.sort(result_list);
            int[] index_arr = binary_search(n);
            int start = index_arr[0];
            int end = index_arr[1];

            int answer = 0;
            if(list.isEmpty()){//모두 고장이나 리스트가 빈 경우 100번에서 채널을 하나하나 돌린다
                answer = Math.abs(n - 100);
            }else if(end == -1){//조합중 제일 낮은 번호보다 낮으면 start는 0이 된다.
                //가까운 조합을 뺀 결과와 100번에서 채널 하나하나 돌리는 값중 작은 값이 정답
                String start_str = "" + result_list.get(start);
                answer = Math.min(Math.abs(n - result_list.get(start)) + start_str.length(), Math.abs(n - 100));
            }else if(start >= result_list.size()){//조합중 제일 높은 번호보다 높으면 end 마지막 값을 가리킨다
                //제일 큰 값과 100번에서 채널 하나하나 돌리는 값중 작은 값이 정답
                String end_str = "" + result_list.get(end);
                answer = Math.min(Math.abs(n - result_list.get(end)) + end_str.length(), Math.abs(n - 100));
            }else{//그 외에는 양 옆중 채널과 가까운 값과 하나하나 돌려서 나오는 값중 작은 값이 정답이 된다.
                String start_str = "" + result_list.get(start);
                String end_str = "" + result_list.get(end);
                int num1 = Math.abs(n - result_list.get(start)) + start_str.length();
                int num2 = Math.abs(n - result_list.get(end)) + end_str.length();
                int min = Math.min(num1, num2);
                answer = Math.min(min, Math.abs(n - 100));
            }

            System.out.println(answer);
        }

    }

    //모든 조합과 채널 번호를 토대로 이진탐색을 해 시작지점과 끝지점을 배열로 담는다
    public static int[] binary_search(int num){
        int[] arr = new int[2];
        int start = 0;
        int end = result_list.size() - 1;

        while(start <= end){
            int mid = (start + end) / 2;

            if(result_list.get(mid) <= num){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }

        arr[0] = start; arr[1] = end;
        return arr;
    }

    //조합들을 찾는 메서드
    public static void set_result(StringBuilder sb, int lenght){
        if(sb.length() <= lenght + 1){
            int result_num = Integer.parseInt(sb.toString());
            result_set.add(result_num);

            for(int i = 0; i < list.size(); i++){
                sb.append(list.get(i));
                set_result(sb, lenght);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
