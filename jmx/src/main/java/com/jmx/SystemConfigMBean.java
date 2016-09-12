package com.jmx;

/**
 * Created by aureliano on 03.09.2016.
 */
public interface SystemConfigMBean {

    public void setParamVal(int val);
    public int getParamVal();

    public void setParamDescr(String descr);
    public String getParamDescr();

    // any method starting with get and set are considered
    // as attributes getter and setter methods, so I am
    // using do* for operation.
    public String doConfig();
}