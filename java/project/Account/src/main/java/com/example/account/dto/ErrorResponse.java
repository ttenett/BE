package com.example.account.dto;

import com.example.account.exception.AccountException;
import com.example.account.type.ErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private ErrorCode errorCode;
    private String errorMessage;

    // 우리어플이 익셉션을 다 밖으로 던짐. 처리하지 않고 있음.
    public AccountException toAccountException() {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        return new AccountException(errorCode, errorMessage);
    }

}
