package me.hsgamer.bettergui.itembridgehook;

import io.github.projectunified.uniitem.all.AllItemProvider;
import io.github.projectunified.uniitem.api.ItemKey;
import me.hsgamer.hscore.common.StringReplacer;
import me.hsgamer.hscore.minecraft.item.ItemComparator;
import me.hsgamer.hscore.minecraft.item.ItemModifier;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class CustomItemModifier implements ItemModifier<ItemStack>, ItemComparator<ItemStack> {
    private final String type;
    private final AllItemProvider provider;
    private String id = "";

    public CustomItemModifier(String type, AllItemProvider provider) {
        this.type = type;
        this.provider = provider;
    }

    @Override
    public @NotNull ItemStack modify(@NotNull ItemStack itemStack, UUID uuid, @NotNull StringReplacer stringReplacer) {
        String replaced = stringReplacer.replaceOrOriginal(id, uuid);
        ItemStack newItemStack = provider.item(new ItemKey(type, replaced));
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
        ItemKey itemKey = provider.key(itemStack);
        if (itemKey != null) {
            this.id = itemKey.id();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean compare(@NotNull ItemStack itemStack, UUID uuid, @NotNull StringReplacer stringReplacer) {
        String replaced = stringReplacer.replaceOrOriginal(id, uuid);
        return provider.isSimilar(itemStack, new ItemKey(type, replaced));
    }
}
