import java.util.Scanner;

public class Practice2 {
    public static void solution() {
        Scanner sc = new Scanner(System.in);
        System.out.print("알파벳 입력: ");
        char input = sc.nextLine().charAt(0);
        int output = 0;

        // 대소문자 뒤집어주기 간격 계산

    }

    public static void reference() {
        int a = (int)'a';
        System.out.println("a = " + a);
        int z = (int)'z';
        System.out.println("z = " + z);
        int A = (int)'A';
        System.out.println("A = " + A);
        int Z = (int)'Z';
        System.out.println("Z = " + Z);
        int etc = (int)'%';
        System.out.println("etc = " + etc);
    }

    public static void main(String[] args) {
        reference();    // 아스키 코드 참고
        solution();
    }
}
