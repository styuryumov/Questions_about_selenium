package ru.stqa.selenium;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class DataProviders {

    @DataProvider
    public static Object[] [] validIterations() {
        return new Object[] [] {
            {
                    Iterations.newEntity()
                        .withIterations(3).build() },
        };
    }

}
