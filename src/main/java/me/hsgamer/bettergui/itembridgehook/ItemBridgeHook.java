package me.hsgamer.bettergui.itembridgehook;

import me.hsgamer.bettergui.api.addon.GetLogger;
import me.hsgamer.bettergui.builder.ItemModifierBuilder;
import me.hsgamer.hscore.expansion.common.Expansion;
import me.hsgamer.hscore.logger.common.LogLevel;
import me.hsgamer.hscore.minecraft.item.ItemModifier;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public final class ItemBridgeHook implements Expansion, GetLogger {
    @Override
    public void onEnable() {
        register("ItemBridge", ItemBridgeModifier::new, "item-bridge", "itembridge");
        register("ItemsAdder", ItemsAdderModifier::new, "itemsadder", "ia");
        register("Oraxen", OraxenModifier::new, "oraxen");
        register("Nexo", NexoModifier::new, "nexo");
    }

    private void register(String plugin, Supplier<ItemModifier<ItemStack>> creator, String... type) {
        if (Bukkit.getPluginManager().isPluginEnabled(plugin)) {
            ItemModifierBuilder.INSTANCE.register(creator, type);
            getLogger().log(LogLevel.INFO, plugin + " is registered");
        }
    }
}
