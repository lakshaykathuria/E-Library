package com.Library.E_Library.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {
    public double divideTwoNumbers(int a, int b) throws ArithmeticException{
        if(b==0){
            throw new ArithmeticException();
        }
        if(a>=20){
            return (double) a/b;
        }

        return 0D;
    }
}
