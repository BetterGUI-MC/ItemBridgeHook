package me.hsgamer.bettergui.itembridgehook;

import com.jojodmo.itembridge.ItemBridge;
import com.jojodmo.itembridge.ItemBridgeKey;
import org.bukkit.inventory.ItemStack;

public class ItemBridgeModifier extends CustomItemModifier {
    @Override
    protected String getId(ItemStack itemStack) {
        ItemBridgeKey itemBridgeKey = ItemBridge.getItemKey(itemStack);
        return itemBridgeKey != null ? itemBridgeKey.toString() : null;
    }

    @Override
    protected ItemStack getItemStack(String id) {
        return ItemBridge.getItemStack(id);
    }
}
