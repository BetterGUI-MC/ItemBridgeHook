package me.hsgamer.bettergui.itembridgehook;

import io.github.projectunified.uniitem.all.AllItemProvider;
import me.hsgamer.bettergui.api.addon.GetLogger;
import me.hsgamer.bettergui.builder.ItemModifierBuilder;
import me.hsgamer.hscore.expansion.common.Expansion;

public final class ItemBridgeHook implements Expansion, GetLogger {
    @Override
    public void onEnable() {
        AllItemProvider allItemProvider = new AllItemProvider();
        for (String type : allItemProvider.availableTypes()) {
            ItemModifierBuilder.INSTANCE.register(() -> new CustomItemModifier(type, allItemProvider), type);
            getLogger().log("Registered item modifier for type " + type);
        }
        ItemModifierBuilder.INSTANCE.register(() -> new CustomItemModifier(allItemProvider), "uniitem", "uni-item");
    }
}
