package com.example.bank.account;

import com.example.bank._core.utils.ApiUtil;
import com.example.bank.user.SessionUser;
import com.example.bank.user.UserRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountController {
    private final AccountService accountService;
    private final HttpSession session;

    @GetMapping("/api/accounts/{number}")
    public ResponseEntity<?> accountDetail(@PathVariable Integer number){
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        AccountResponse.DetailDTO respDTO = accountService.계좌상세보기(number, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }

    @GetMapping("/api/accounts")
    public ResponseEntity<?> accountList(){
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        AccountResponse.MainDTO respDTO = accountService.계좌목록보기(sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }
}
