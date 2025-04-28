package me.hsgamer.bettergui.itembridgehook;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.inventory.ItemStack;

public class ItemsAdderModifier extends CustomItemModifier {
    @Override
    protected String getId(ItemStack itemStack) {
        CustomStack customStack = CustomStack.byItemStack(itemStack);
        return customStack == null ? null : customStack.getId();
    }

    @Override
    protected ItemStack getItemStack(String id) {
        CustomStack customStack = CustomStack.getInstance(id);
        return customStack == null ? null : customStack.getItemStack();
    }
}
