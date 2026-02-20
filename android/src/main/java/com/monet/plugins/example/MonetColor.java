package com.monet.plugins.example;

import com.getcapacitor.Logger;

public class MonetColor {

    public String echo(String value) {
        Logger.info("Echo", value);
        return value;
    }
}
