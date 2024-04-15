package com.example.bank.study;

import com.example.bank._core.errors.exception.Exception400;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

public class StudyTest {

    @Test
    public void amount_format_test(){
        long money = 1000000000;

        DecimalFormat df = new DecimalFormat("###,###");
        String formatMoney = df.format(money);

        System.out.println(formatMoney); // 1,000,000
    }
    
    @Test
    public void same_account_test(){
        // given
        Integer senderNumber = 1000000000;
        Integer receiverNumber = 100000000;
      
        // when
        if(senderNumber.intValue() == receiverNumber.intValue()){
            System.out.println("동일");
        }else{
            System.out.println("동일하지 않음");
        }
      
        // then
    }
}
