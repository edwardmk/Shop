package com.example.c15395091.shop.Template;

public class AmericanExpressValidation extends AbstractCardValidation {

    public AmericanExpressValidation(String cardName, String cardNumber, int expiryDateMonth,
                                     int expiryDateYear, String cvv) {

        super(cardName, cardNumber, expiryDateMonth, expiryDateYear, cvv);

    }

    protected boolean validateCardNumberLength() {

        boolean errorInNumber = false;

        if (cardNumber.length() != 15 ) {
            if(cardNumber.length() != 16) {
                errorInNumber = true;
            }

        } else {

            for (int i = 0; i < cardNumber.length(); i++) {

                if (cardNumber.charAt(i) > '9' || cardNumber.charAt(i) < '0') {

                    errorInNumber = true;

                }

            }

        }

        return !errorInNumber;
    }

    protected boolean validateCardNumberFormat() {

        /* Check number is in correct format for American Express */

        boolean errorInFormat = false;

        if (cardNumber.charAt(0) == '3' && (cardNumber.charAt(1) == '4' || cardNumber.charAt(1) == '7')) {

        } else {

            errorInFormat = true;

        }

        return !errorInFormat;

    }

}