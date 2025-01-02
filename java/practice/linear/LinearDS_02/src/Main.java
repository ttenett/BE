// 선형 자료구조 - 배열

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

//      1차원 배열
        System.out.println("== 1차원 배열 ==");
        int[] arr = {1, 2, 3, 4, 5};
        // for each문으로 출력하기. 꺼낼 자료타입: 배열이름
        for (int item: arr) {
            System.out.println("item = " + item);
        }

        // 특정 위치의 배열 수정하기
        arr[1] = 100;
        System.out.println("arr = " + Arrays.toString(arr));


//      2차원 배열
        System.out.println("== 2차원 배열 ==");
        int[][] arr2 = {{1, 2, 3}, {4, 5, 6}};
        System.out.println(arr2[0][1]);

        for(int[] row: arr2) {
            for(int item: row) {
                System.out.println("item = " + item);
            }
        }


//      ArrayList - 1차원, 2차원
        System.out.println("== ArrayList ==");
        // 생성시에 데이터를 바로 만듦
        ArrayList list1 = new ArrayList(Arrays.asList(1, 2, 3));
        System.out.println("list1 = " + list1);
        // add로 하나식 추가
        list1.add(4);
        list1.add(5);
        System.out.println("list1 = " + list1);
        // 인덱스로 잡혀서 지워짐.
        list1.remove(2);
        System.out.println("list1 = " + list1);
        // 실제 값으로 지우기
        list1.remove(Integer.valueOf(2));
        System.out.println("list1 = " + list1);

        ArrayList list2d = new ArrayList();
        ArrayList list1d1 = new ArrayList(Arrays.asList(1, 2, 3));
        ArrayList list1d2 = new ArrayList(Arrays.asList(4, 5, 6));
        list2d.add(list1d1);
        list2d.add(list1d2);
        System.out.println("list1d1 = " + list1d1);
        System.out.println("list1d2 = " + list1d2);
        System.out.println("list2d = " + list2d);
    }
}
