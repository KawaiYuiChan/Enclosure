package com.github.zly2006.enclosure.utils;

import com.github.zly2006.enclosure.ServerMain;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public class TrT {
    public static @NotNull MutableText of(String key, Object... arguments) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
            ServerMain.load();
            if (ServerMain.translation.has(key)) {
                return Text.translatableWithFallback(key, ServerMain.translation.get(key).getAsString(), arguments);
            }
        }
        return Text.translatable(key, arguments);
    }

    public static @NotNull MutableText limit(Field field) {
        return of("enclosure.limit." + Utils.camelCaseToSnakeCase(field.getName()));
    }
}
