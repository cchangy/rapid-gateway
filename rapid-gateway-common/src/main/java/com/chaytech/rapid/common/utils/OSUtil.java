package com.chaytech.rapid.common.utils;

import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 系统工具类
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/05
 */
@Slf4j
public class OSUtil {

    private OSUtil() {
    }

    private static final SystemInfo SI = new SystemInfo();
    public static final String TWO_DECIMAL = "0.00";

    private static HardwareAbstractionLayer hal = SI.getHardware();

    public static double availablePhysicalMemorySize() {
        GlobalMemory memory = hal.getMemory();
        double availablePhysicalMemorySize = (memory.getAvailable() + memory.getSwapUsed()) / 1024.0 / 1024 / 1024;

        DecimalFormat df = new DecimalFormat(TWO_DECIMAL);
        df.setRoundingMode(RoundingMode.HALF_UP);
        return Double.parseDouble(df.format(availablePhysicalMemorySize));

    }

    public static double availablePhysicalMemorySizeProportion() {
        GlobalMemory memory = hal.getMemory();
        double availablePhysicalMemorySize = (memory.getAvailable() + memory.getSwapUsed()) / 1024.0 / 1024 / 1024;
        availablePhysicalMemorySize = availablePhysicalMemorySize / (memory.getTotal() / 1024.0 / 1024 / 1024);
        DecimalFormat df = new DecimalFormat(TWO_DECIMAL);
        df.setRoundingMode(RoundingMode.HALF_UP);
        return Double.parseDouble(df.format(1 - availablePhysicalMemorySize));
    }

    public static double loadAverage() {
        double loadAverage = hal.getProcessor().getSystemLoadAverage();

        DecimalFormat df = new DecimalFormat(TWO_DECIMAL);

        df.setRoundingMode(RoundingMode.HALF_UP);
        return Double.parseDouble(df.format(loadAverage));
    }

    public static Boolean checkResource(double cpuUsage, double reservedMemory) {
        double availablePhysicalMemorySize = OSUtil.availablePhysicalMemorySize();
        if (1 - OSUtil.cpuUsage() < cpuUsage || availablePhysicalMemorySize < reservedMemory) {
            log.warn("rapid已负载,cpu已使用[{}],内存已使用[{}]", OSUtil.cpuUsage(), availablePhysicalMemorySize);
            return false;
        } else {
            return true;
        }
    }

    public static double cpuUsage() {
        CentralProcessor processor = hal.getProcessor();
        double cpuUsage = processor.getSystemCpuLoad();
        DecimalFormat df = new DecimalFormat(TWO_DECIMAL);
        df.setRoundingMode(RoundingMode.HALF_UP);
        return Double.parseDouble(df.format(cpuUsage));
    }

}
