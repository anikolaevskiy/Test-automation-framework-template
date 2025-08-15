package com.example.aqa.tools;

@FunctionalInterface
public interface Screenshot {

    byte[] get() throws Exception;
}
