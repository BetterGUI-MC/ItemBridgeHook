package me.hsgamer.bettergui.itembridgehook;

import com.jojodmo.itembridge.ItemBridge;
import com.jojodmo.itembridge.ItemBridgeKey;
import me.hsgamer.hscore.common.StringReplacer;
import me.hsgamer.hscore.minecraft.item.ItemComparator;
import me.hsgamer.hscore.minecraft.item.ItemModifier;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ItemBridgeModifier implements ItemModifier<ItemStack>, ItemComparator<ItemStack> {
    private String name = "";

    @Override
    public @NotNull ItemStack modify(@NotNull ItemStack itemStack, UUID uuid, @NotNull StringReplacer stringReplacer) {
        String replaced = stringReplacer.replaceOrOriginal(name, uuid);
        ItemStack newItemStack = ItemBridge.getItemStack(replaced);
        return newItemStack == null ? itemStack : newItemStack;
    }

    @Override
    public Object toObject() {
        return name;
    }

    @Override
    public void loadFromObject(Object o) {
        this.name = String.valueOf(o);
    }

    @Override
    public boolean loadFromItem(ItemStack itemStack) {
        ItemBridgeKey itemBridgeKey = ItemBridge.getItemKey(itemStack);
        if (itemBridgeKey != null) {
            this.name = itemBridgeKey.toString();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean compare(@NotNull ItemStack itemStack, UUID uuid, @NotNull StringReplacer stringReplacer) {
        String replaced = stringReplacer.replaceOrOriginal(name, uuid);
        ItemBridgeKey itemBridgeKey = ItemBridge.getItemKey(itemStack);
        return itemBridgeKey != null && itemBridgeKey.toString().equals(replaced);
    }
}
