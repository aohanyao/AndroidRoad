package com.jc.road;

public class TestSingleInstance {

    private static TestSingleInstance mTestSingleInstance = null;

    private TestSingleInstance() {
    }

    public static TestSingleInstance getInstance() {
        synchronized (TestSingleInstance.class) {
            if (mTestSingleInstance == null) {
                mTestSingleInstance = new TestSingleInstance();
            }
        }
        return mTestSingleInstance;
    }
}
