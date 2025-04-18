package com.example.account.controller;

import com.example.account.domain.Account;
import com.example.account.dto.AccountDto;
import com.example.account.dto.AccountInfo;
import com.example.account.dto.CreateAccount;
import com.example.account.dto.DeleteAccount;
import com.example.account.repository.AccountRepository;
import com.example.account.service.AccountService;
import com.example.account.service.RedisTestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController // 빈으로 등록해달라고 스프링에게 얘기해줌
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final RedisTestService redisTestService;

    @PostMapping("/account")
    public CreateAccount.Response createAccount( // 요청 응답값 지정
            @RequestBody @Valid CreateAccount.Request request //CA의Request객체로 요청들어옴
    ) { // 그 값으로 아래 생성 실행
        return CreateAccount.Response.from(
                accountService.createAccount( // 여기서 잘 생성됨. 넘어가서 사용자잇는지 확인,없으면 새로운 계좌 생성
                        request.getUserId(),
                        request.getInitialBalance() // AccountService에서 하나의 새로운 어카운트리파지토리 생성
                )
        );
    }

    @DeleteMapping("/account")
    public DeleteAccount.Response deleteAccount( // 요청 응답값 지정
            @RequestBody @Valid DeleteAccount.Request request
    ) { // 그 값으로 아래 생성 실행
        return DeleteAccount.Response.from(
                accountService.deleteAccount( // 여기서 잘 생성됨. 넘어가서 사용자잇는지 확인,없으면 새로운 계좌 생성
                        request.getUserId(),
                        request.getAccountNumber() // AccountService에서 하나의 새로운 어카운트리파지토리 생성
                )
        );
    }

    @GetMapping("/account")
    public List<AccountInfo> getAccountsByUserId(
            @RequestParam("user_id") Long userId
    ) {
        return accountService.getAccountsByUserId(userId)
                // accountInfo로 변경
                // 성능은 문제가 될때까지는 문제가 되지 않는다.
                // 처음부터 성능위주로 하면 복잡도가 올라가고 희생됨
                // (사용자가 미세하게 못느끼는 향상때문에 유지보수성이나 안정성이 떨어지기도 하니까)
                .stream().map(accountDto ->
                        AccountInfo.builder()
                        .accountNumber(accountDto.getAccountNumber())
                        .balance(accountDto.getBalance())
                        .build())
                .collect(Collectors.toList());

    }

    @GetMapping("/get-lock")
    public String getLock() {
        return redisTestService.getLock(); // getLock한 결과를 바로 리턴
    } // redistTestService의 getlock 호출, lock을 획득해올것임


    @GetMapping("/account/{id}")
    public Account getAccount(
            @PathVariable Long id) {
        return accountService.getAccount(id);
    } // url account 뒤의 숫자를 가지고 id로 담아서 getAccount 호출
    // AccountService에서 accountRepository 테이블에서 id로 셀렉트, 값을 응답
}
