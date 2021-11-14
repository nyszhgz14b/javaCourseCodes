package com.example.utils;

import org.apache.shardingsphere.sharding.algorithm.keygen.SnowflakeKeyGenerateAlgorithm;

public class SnowFlakeUtils {
    public static Comparable<?> getId(Long workerId) {
        if (!(workerId >= 0 && workerId < 1024L)) {
            throw new RuntimeException(String.format("workerId is not support range must be [0,1024)"));
        }
        SnowflakeKeyGenerateAlgorithm snowflakeKeyGenerateAlgorithm = new SnowflakeKeyGenerateAlgorithm();
        snowflakeKeyGenerateAlgorithm.getProps().put("worker.id", String.valueOf(workerId));
        Comparable<?> generateKey = snowflakeKeyGenerateAlgorithm.generateKey();
        return generateKey;
    }
}
