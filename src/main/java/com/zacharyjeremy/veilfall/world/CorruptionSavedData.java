package com.zacharyjeremy.veilfall.world;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public final class CorruptionSavedData extends SavedData {
    private static final String DATA_NAME = "veilfall_corruption";
    private int corruption;

    public CorruptionSavedData() {
        this.corruption = 0;
    }

    private CorruptionSavedData(CompoundTag tag) {
        this.corruption = clamp(tag.getInt("Corruption"));
    }

    public static CorruptionSavedData load(CompoundTag tag) {
        return new CorruptionSavedData(tag);
    }

    public static CorruptionSavedData get(ServerLevel level) {
        return level.getServer().overworld().getDataStorage()
                .computeIfAbsent(CorruptionSavedData::load, CorruptionSavedData::new, DATA_NAME);
    }

    public int getCorruption() {
        return corruption;
    }

    public void setCorruption(int value) {
        int next = clamp(value);
        if (next != corruption) {
            corruption = next;
            setDirty();
        }
    }

    public void addCorruption(int amount) {
        setCorruption(corruption + amount);
    }

    private static int clamp(int value) {
        return Math.max(0, Math.min(100, value));
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putInt("Corruption", corruption);
        return tag;
    }
}
