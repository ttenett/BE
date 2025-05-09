// Practice2
// 괄호 짝 검사

// 입출력 예시)
// 입력: "("
// 출력: Fail

// 입력: ")"
// 출력: Fail

// 입력: "()"
// 출력: Pass

import java.util.Stack;

public class Practice2 {
    public static void checkParenthesis(String str) {
        Stack stack = new Stack();
        boolean checkFlag = true;

        for (String s: str.split("")) {
            if (s.equals("(")) {
                stack.push(s);
            } else {
                // )가 들어왔을때, 대응하는 괄호가 stack에 없을 경우
                if (stack.isEmpty()) {
                    checkFlag = false;
                    break;
                } else {
                    stack.pop();
                }
            }
        }
        // pass, fail 출력용
        if (checkFlag && stack.isEmpty()) {
            System.out.println("PASS!");
        } else {
            System.out.println("FAIL!");
        }
    }

    public static void main(String[] args) {
        // Test code
        checkParenthesis("(");          // FAIL!
        checkParenthesis(")");          // FAIL!
        checkParenthesis("()");         // PASS!
        checkParenthesis("()()()");     // PASS!
        checkParenthesis("(())()");     // PASS! -> ( => push, ) => pop
        checkParenthesis("(((()))");    // FAIL!
    }
}
