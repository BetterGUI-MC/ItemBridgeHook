package me.hsgamer.bettergui.itembridgehook;

import io.th0rgal.oraxen.api.OraxenItems;
import io.th0rgal.oraxen.items.ItemBuilder;
import org.bukkit.inventory.ItemStack;

public class OraxenModifier extends CustomItemModifier {
    @Override
    protected String getId(ItemStack itemStack) {
        return OraxenItems.getIdByItem(itemStack);
    }

    @Override
    protected ItemStack getItemStack(String id) {
        ItemBuilder itemBuilder = OraxenItems.getItemById(id);
        return itemBuilder == null ? null : itemBuilder.build();
    }
}
