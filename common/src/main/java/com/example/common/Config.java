package com.example.common;

public final class Config {
    private Config() {}

    public static String get(String key, String defaultValue) {
        String sys = System.getProperty(key);
        if (sys != null && !sys.isEmpty()) {
            return sys;
        }
        String env = System.getenv(key);
        return env != null && !env.isEmpty() ? env : defaultValue;
    }
}
