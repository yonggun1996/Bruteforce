package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Breaket {

    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] formual = br.readLine().toCharArray();
        LinkedList<Integer> number = new LinkedList<>();
        LinkedList<Character> operation = new LinkedList<>();

        for(int i = 0; i < n; i++){
            if(i % 2 == 0){
                number.add(formual[i] - '0');
            }else{
                operation.add(formual[i]);
            }
        }

        if(n == 1){//연산자가 없는 경우 숫자 하나만 출력한다
            System.out.println(number.get(0));
        }else if(n == 3){//연산자가 하나만 있는 경우 계산을 한 후 출력한다
            operation(number, operation);
            System.out.println(answer);
        }else{//그 외 다수의 연산자가 있는 경우
            for(int i = 0; i < operation.size(); i++){
                calculate(number, operation, i);
            }
            System.out.println(answer);
        }
    }

    public static void calculate(LinkedList<Integer> number, LinkedList<Character> operation, int index){
        //받아온 연산자 리스트와 숫자 리스트를 복사한다
        //뒤에 계산할 수 있는 연산이 있으면 해야하기 때문
        LinkedList<Integer> num_list = new LinkedList(number);
        LinkedList<Character> op_list = new LinkedList(operation);

        operation(num_list, op_list);//현재 수식을 계산

        int num1 = num_list.get(index);
        int num2 = num_list.get(index + 1);
        int result = 0;
        char op = op_list.get(index);

        //연산자 종류에 따라 연산
        switch (op){
            case '+' :
                result = num1 + num2;
                break;

            case '*' :
                result = num1 * num2;
                break;

            case '-' :
                result = num1 - num2;
                break;
        }

        //새로 계산된 수를 인덱스에 담는 과정
        num_list.remove(index);
        num_list.remove(index);
        op_list.remove(index);
        num_list.add(index, result);

        //바로 앞에 있는 연산이 되면 규칙상 안된다.
        //index에 1을 더해 다음 연산을 계산한다
        if(index + 1 >= op_list.size()){
            operation(num_list, op_list);
        }else{
            for(int i = index + 1; i < op_list.size(); i++){
                calculate(num_list, op_list, i);
            }
        }
    }

    public static void operation(LinkedList<Integer> number, LinkedList<Character> operation){
        int num = 0;
        //초기값 설정
        if(operation.get(0) == '+'){
            num = number.get(0) + number.get(1);
        }else if(operation.get(0) == '*'){
            num = number.get(0) * number.get(1);
        }else{
            num = number.get(0) - number.get(1);
        }

        //남아있는 연산 실행
        for(int i = 1; i < operation.size(); i++){
            if(operation.get(i) == '+'){
                num += number.get(i + 1);
            }else if(operation.get(i) == '*'){
                num *= number.get(i + 1);
            }else{
                num -= number.get(i + 1);
            }
        }

        answer = Math.max(answer, num);
    }

}
