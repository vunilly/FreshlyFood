package com.vunilly.freshlyfood.food;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractFood {
    protected final JavaPlugin plugin;
    private final float customModelData;

    public AbstractFood(JavaPlugin plugin, float customModelData) {
        this.plugin = plugin;
        this.customModelData = customModelData;
    }

    public float getCustomModelData() {
        return customModelData;
    }

    public abstract ItemStack createItem();
    public abstract void registerRecipe();
    public abstract void onItemConsume(Player player);
}