package com.elmostafa.ethicstask;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void isTextEmpty() {
        // when pass empty text
        boolean isEmpty=Validator.isTextEmpty("");
        Assert.assertTrue(isEmpty);

        // when pass unEmpty text
        boolean isNotEmpty=Validator.isTextEmpty("hello");
        Assert.assertFalse(isNotEmpty);
    }


    @Test
    public void validPasswordLength() {
        // when pass valid pass
        boolean isPassValid=Validator.validPasswordLength("25222ss");
        Assert.assertTrue(isPassValid);

        // when pass Invalid pass
        boolean isPassInValid=Validator.isValid("21");
        Assert.assertFalse(isPassInValid);
    }

    @Test
    public void isValid() {
        // when pass valid pass
        boolean isPassValid=Validator.isValid("asww2223");
        Assert.assertTrue(isPassValid);

        // when pass Invalid pass
        boolean isPassInValid=Validator.isValid("dsa");
        Assert.assertFalse(isPassInValid);
    }

    @Test
    public void isValidMail() {
        // when pass valid mail
        boolean validEmail=Validator.isValidMail("karim@gmail.com");
        Assert.assertTrue(validEmail);

        // when pass Invalid mail
        boolean InvalidEmail=Validator.isValidMail("karim@gmail");
        Assert.assertFalse(InvalidEmail);
    }
}