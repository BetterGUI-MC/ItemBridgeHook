package me.hsgamer.bettergui.itembridgehook;

import io.github.projectunified.uniitem.all.AllItemProvider;
import io.github.projectunified.uniitem.api.ItemProvider;
import io.github.projectunified.uniitem.itembridge.ItemBridgeProvider;
import me.hsgamer.bettergui.api.addon.GetLogger;
import me.hsgamer.bettergui.builder.ItemModifierBuilder;
import me.hsgamer.hscore.expansion.common.Expansion;

public final class ItemBridgeHook implements Expansion, GetLogger {
    @Override
    public void onEnable() {
        AllItemProvider allItemProvider = new AllItemProvider();
        for (ItemProvider itemProvider : allItemProvider.getProviders()) {
            String[] type;
            if (itemProvider instanceof ItemBridgeProvider) {
                type = new String[]{
                        "itembridge",
                        "item-bridge"
                };
            } else {
                type = itemProvider.type();
            }
            ItemModifierBuilder.INSTANCE.register(() -> new CustomItemModifier(itemProvider), type);
        }
        ItemModifierBuilder.INSTANCE.register(() -> new CustomItemModifier(allItemProvider), "uni-item", "uniitem");
    }
}
