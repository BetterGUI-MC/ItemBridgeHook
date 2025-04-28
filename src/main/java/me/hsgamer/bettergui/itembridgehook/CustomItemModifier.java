package me.hsgamer.bettergui.itembridgehook;

import me.hsgamer.hscore.common.StringReplacer;
import me.hsgamer.hscore.minecraft.item.ItemComparator;
import me.hsgamer.hscore.minecraft.item.ItemModifier;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class CustomItemModifier implements ItemModifier<ItemStack>, ItemComparator<ItemStack> {
    private String id = "";

    protected abstract String getId(ItemStack itemStack);

    protected abstract ItemStack getItemStack(String id);

    @Override
    public @NotNull ItemStack modify(@NotNull ItemStack itemStack, UUID uuid, @NotNull StringReplacer stringReplacer) {
        String replaced = stringReplacer.replaceOrOriginal(id, uuid);
        ItemStack newItemStack = getItemStack(replaced);
        return newItemStack == null ? itemStack : newItemStack;
    }

    @Override
    public Object toObject() {
        return id;
    }

    @Override
    public void loadFromObject(Object o) {
        this.id = String.valueOf(o);
    }

    @Override
    public boolean loadFromItem(ItemStack itemStack) {
        String itemId = getId(itemStack);
        if (itemId != null) {
            this.id = itemId;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean compare(@NotNull ItemStack itemStack, UUID uuid, @NotNull StringReplacer stringReplacer) {
        String replaced = stringReplacer.replaceOrOriginal(id, uuid);
        String itemId = getId(itemStack);
        return replaced.equals(itemId);
    }
}
