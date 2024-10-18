package com.Library.E_Library.service;

import com.Library.E_Library.ELibraryApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ELibraryApplication.class)
public class MyServiceTest {

    private final MyService myService;

    @Autowired
    public MyServiceTest(MyService myService) {
        this.myService = myService;
    }


    @Test
    void divideTwoNumbers_whenInputsAreValid () throws Exception {
        int a = 20;
        int b = 5;

        double result = myService.divideTwoNumbers(a,b);

        Assertions.assertEquals(4.0, result);
    }

    @Test
    void divideTwoNumbers_whenInputsAreValidButAIsLessThan20() throws Exception {
        int a = 10;
        int b = 5;

        double result = myService.divideTwoNumbers(a,b);

        Assertions.assertEquals(0.0, result);
    }

    @Test
    void divideTwoNumbers_whenInputsAreNotValid()  {
        int a = 20;
        int b = 0;


        Assertions.assertThrows(ArithmeticException.class,()->{
            this.myService.divideTwoNumbers(a,b);
                });
    }


}
