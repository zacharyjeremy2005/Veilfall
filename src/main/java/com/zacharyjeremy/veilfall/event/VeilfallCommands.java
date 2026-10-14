package com.zacharyjeremy.veilfall.event;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.zacharyjeremy.veilfall.world.CorruptionSavedData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "veilfall", bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class VeilfallCommands {
    private VeilfallCommands() {}

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(Commands.literal("veilfall")
                .then(Commands.literal("status")
                        .executes(context -> {
                            int value = CorruptionSavedData.get(context.getSource().getLevel()).getCorruption();
                            context.getSource().sendSuccess(
                                    () -> Component.literal("Veilfall | World corruption: " + value + "%"), false);
                            return value;
                        }))
                .then(Commands.literal("set")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.argument("percent", IntegerArgumentType.integer(0, 100))
                                .executes(context -> {
                                    int value = IntegerArgumentType.getInteger(context, "percent");
                                    CorruptionSavedData.get(context.getSource().getLevel()).setCorruption(value);
                                    context.getSource().sendSuccess(
                                            () -> Component.literal("Veilfall corruption set to " + value + "%."), true);
                                    return value;
                                })))
                .then(Commands.literal("clean")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.argument("amount", IntegerArgumentType.integer(1, 100))
                                .executes(context -> {
                                    int amount = IntegerArgumentType.getInteger(context, "amount");
                                    CorruptionSavedData data = CorruptionSavedData.get(context.getSource().getLevel());
                                    data.addCorruption(-amount);
                                    int value = data.getCorruption();
                                    context.getSource().sendSuccess(
                                            () -> Component.literal("Purification complete. World corruption: " + value + "%."), true);
                                    return value;
                                }))));
    }
}
