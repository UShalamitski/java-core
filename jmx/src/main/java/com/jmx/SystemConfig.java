package com.jmx;

/**
 * Created by aureliano on 03.09.2016.
 */
public class SystemConfig implements SystemConfigMBean {

    private int value;
    private String descr;

    SystemConfig(int value, String descr){
        this.value = value;
        this.descr = descr;
    }

    @Override
    public void setParamVal(int value) {
        this.value = value;
    }

    @Override
    public int getParamVal() {
        return value;
    }

    @Override
    public void setParamDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public String getParamDescr() {
        return descr;
    }

    @Override
    public String doConfig() {
        return "Value : " + value + ", Descr : " + descr;
    }

}