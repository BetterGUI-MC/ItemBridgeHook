package me.hsgamer.bettergui.itembridgehook;

import io.github.projectunified.uniitem.all.AllItemProvider;
import io.github.projectunified.uniitem.api.ItemKey;
import me.hsgamer.hscore.common.StringReplacer;
import me.hsgamer.hscore.minecraft.item.ItemComparator;
import me.hsgamer.hscore.minecraft.item.ItemModifier;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.function.Function;

public class CustomItemModifier implements ItemModifier<ItemStack>, ItemComparator<ItemStack> {
    private final Function<String, ItemKey> keyFunction;
    private final AllItemProvider provider;
    private String id = "";

    public CustomItemModifier(AllItemProvider provider) {
        this.keyFunction = ItemKey::fromString;
        this.provider = provider;
    }

    public CustomItemModifier(String type, AllItemProvider provider) {
        this.keyFunction = id -> new ItemKey(type, id);
        this.provider = provider;
    }

    @Override
    public @NotNull ItemStack modify(@NotNull ItemStack itemStack, UUID uuid, @NotNull StringReplacer stringReplacer) {
        String replaced = stringReplacer.replaceOrOriginal(id, uuid);
        ItemKey itemKey;
        try {
            itemKey = keyFunction.apply(replaced);
        } catch (Throwable throwable) {
            return itemStack;
        }
        ItemStack newItemStack = provider.item(itemKey);
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
        ItemKey itemKey;
        try {
            itemKey = keyFunction.apply(replaced);
        } catch (Throwable throwable) {
            return false;
        }
        return provider.isSimilar(itemStack, itemKey);
    }
}
