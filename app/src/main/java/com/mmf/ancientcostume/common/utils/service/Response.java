package com.mmf.ancientcostume.common.utils.service;
import com.mmf.ancientcostume.common.utils.Constant;

public class Response<T> {

    public String error_code;
    public String reason;
//    public T result;
    public T contents;
    private String status;
    private int total;
    private int size;


    public boolean isSuccess() {
        return status.equals(Constant.OK)||error_code.equals(Constant.OK);
    }
}
