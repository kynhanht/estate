package com.laptrinhjavaweb.api.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubClass extends SuperClass {


    @Override
    public ArrayList test()throws IOException {
        return (ArrayList) super.test();
    }


}
