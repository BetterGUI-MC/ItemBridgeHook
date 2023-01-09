package me.hsgamer.bettergui.itembridgehook;

import me.hsgamer.bettergui.builder.ItemModifierBuilder;
import me.hsgamer.hscore.bukkit.addon.PluginAddon;

public final class ItemBridgeHook extends PluginAddon {
    @Override
    public void onEnable() {
        ItemModifierBuilder.INSTANCE.register(ItemBridgeModifier::new, "item-bridge", "itembridge");
    }
}
