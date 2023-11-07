package me.hsgamer.bettergui.itembridgehook;

import me.hsgamer.bettergui.builder.ItemModifierBuilder;
import me.hsgamer.hscore.expansion.common.Expansion;

public final class ItemBridgeHook implements Expansion {
    @Override
    public void onEnable() {
        ItemModifierBuilder.INSTANCE.register(ItemBridgeModifier::new, "item-bridge", "itembridge");
    }
}
