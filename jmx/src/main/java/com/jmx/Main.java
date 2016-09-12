package com.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * Created by aureliano on 03.09.2016.
 */
public class Main {
    private static final int VALUE = 10;
    private static final String DESCR = "Some text";

    public static void main(String[] args) throws MalformedObjectNameException, InterruptedException,
            InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {

        //Get the MBean server
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        //register the MBean
        SystemConfig mBean = new SystemConfig(VALUE, DESCR);
        ObjectName name = new ObjectName("com.jmx:type=SystemConfig");
        mbs.registerMBean(mBean, name);
        do {
            Thread.sleep(10000);
            System.out.println(mBean.doConfig());
        } while (mBean.getParamVal() != 0);
    }

}
