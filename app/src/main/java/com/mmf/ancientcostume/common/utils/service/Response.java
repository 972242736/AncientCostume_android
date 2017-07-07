package com.mmf.ancientcostume.common.utils.service;
import com.mmf.ancientcostume.common.utils.Constant;

public class Response<T> {

    public String code;
    public String message;
//    public T result;
    public T data;
    private String status;
    private int total;
    private int size;


    public boolean isSuccess() {
        return code.equals(Constant.OK);
    }
}
