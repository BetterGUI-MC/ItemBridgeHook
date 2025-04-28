package me.hsgamer.bettergui.itembridgehook;

import com.nexomc.nexo.api.NexoItems;
import com.nexomc.nexo.items.ItemBuilder;
import org.bukkit.inventory.ItemStack;

public class NexoModifier extends CustomItemModifier {
    @Override
    protected String getId(ItemStack itemStack) {
        return NexoItems.idFromItem(itemStack);
    }

    @Override
    protected ItemStack getItemStack(String id) {
        ItemBuilder itemBuilder = NexoItems.itemFromId(id);
        return itemBuilder == null ? null : itemBuilder.build();
    }
}
