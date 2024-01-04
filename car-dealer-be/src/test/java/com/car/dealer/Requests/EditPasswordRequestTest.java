package com.car.dealer.Requests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EditPasswordRequestTest {

    @Test
    public void testShouldPasswordsMatch(){
        //given
        EditPasswordRequest passwordRequest = new EditPasswordRequest("email","haslo", "noweHaslo", "noweHaslo");
        //then
        Assertions.assertTrue(passwordRequest.passwordMatch());
    }

    @Test
    public void testShouldPasswordsNotMatch(){
        //given
        EditPasswordRequest passwordRequest = new EditPasswordRequest("email","haslo", "noweHaslo", "inneHaslo");
        //then
        Assertions.assertFalse(passwordRequest.passwordMatch());
    }
    
}