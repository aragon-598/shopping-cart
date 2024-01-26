package com.store.shopping_cart.shoppingcart.common;


public class StandarizedApiMessageResponse {
    private String message;

    public StandarizedApiMessageResponse() {
    }

    public StandarizedApiMessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
