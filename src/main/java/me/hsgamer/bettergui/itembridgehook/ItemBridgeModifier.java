package me.hsgamer.bettergui.itembridgehook;

import com.jojodmo.itembridge.ItemBridge;
import com.jojodmo.itembridge.ItemBridgeKey;
import me.hsgamer.hscore.bukkit.item.ItemModifier;
import me.hsgamer.hscore.common.interfaces.StringReplacer;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

public class ItemBridgeModifier implements ItemModifier {
    private String name = "";

    @Override
    public String getName() {
        return "item-bridge";
    }

    @Override
    public ItemStack modify(ItemStack itemStack, UUID uuid, Map<String, StringReplacer> map) {
        String replaced = StringReplacer.replace(name, uuid, map.values());
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
    public void loadFromItemStack(ItemStack itemStack) {
        ItemBridgeKey itemBridgeKey = ItemBridge.getItemKey(itemStack);
        if (itemBridgeKey != null) {
            this.name = itemBridgeKey.toString();
        }
    }

    @Override
    public boolean compareWithItemStack(ItemStack itemStack, UUID uuid, Map<String, StringReplacer> map) {
        String replaced = StringReplacer.replace(name, uuid, map.values());
        ItemBridgeKey itemBridgeKey = ItemBridge.getItemKey(itemStack);
        return itemBridgeKey != null && itemBridgeKey.toString().equals(replaced);
    }
}
