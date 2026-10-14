package com.zacharyjeremy.veilfall.client;

public final class ClientCorruptionState {
    private static volatile int corruption = 0;

    private ClientCorruptionState() {}

    public static int get() {
        return corruption;
    }

    public static void set(int value) {
        corruption = Math.max(0, Math.min(100, value));
    }
}
