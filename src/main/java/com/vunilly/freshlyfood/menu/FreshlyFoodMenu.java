package com.vunilly.freshlyfood.menu;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import com.vunilly.freshlyfood.FreshlyFood;
import com.vunilly.freshlyfood.food.FoodManager;
import com.vunilly.freshlyfood.utils.ClickMenu;
import com.vunilly.freshlyfood.utils.Lang;
import com.vunilly.freshlyfood.utils.Utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class FreshlyFoodMenu implements ClickMenu {
    private Inventory inventory;
    private UUID playerUUID;
    public int pageIndex;
    public int maxPage;
    private static final int PAGE_WIDTH = 7;
    private static final int PAGE_HEIGHT = 2;
    private static final int ITEMS_PER_PAGE = PAGE_WIDTH * PAGE_HEIGHT;
    private static final int PAGE_START = 9 + 1; // erste Item-Position

    public FreshlyFoodMenu(Player player, int pageIndex) {
        this.pageIndex = pageIndex;
        this.maxPage = Math.max(0, (FoodManager.getItemSize() - 1) / ITEMS_PER_PAGE);

        this.inventory = Bukkit.createInventory(this, 54, Lang.get("menu.freshlyfood.title").getFirst());
        this.playerUUID = player.getUniqueId();
        setupInventory();
    }

    private void setupInventory() {
        inventory.clear();

        Utils.decorateInventory(Material.BLUE_STAINED_GLASS_PANE, inventory, 6);

        if (this.pageIndex > 0) {
            ItemStack lastPageBtn = Utils.getUiButton(Utils.createCustomHeadItem(
                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWFlNzg0NTFiZjI2Y2Y0OWZkNWY1NGNkOGYyYjM3Y2QyNWM5MmU1Y2E3NjI5OGIzNjM0Y2I1NDFlOWFkODkifX19"),
                    Lang.get("menu.freshlyfood.select").getFirst(), 1, new ArrayList<>());
            inventory.setItem(9 * 5 + 5, lastPageBtn);
        }

        if (this.pageIndex < this.maxPage) {
            ItemStack nextPageBtn = Utils.getUiButton(Utils.createCustomHeadItem(
                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTE3ZjM2NjZkM2NlZGZhZTU3Nzc4Yzc4MjMwZDQ4MGM3MTlmZDVmNjVmZmEyYWQzMjU1Mzg1ZTQzM2I4NmUifX19"),
                    Lang.get("menu.freshlyfood.select").getFirst(), 1, new ArrayList<>());
            inventory.setItem(9 * 5 + 3, nextPageBtn);
        }

        for (int i = 0; i < ITEMS_PER_PAGE; i++) {
            int row = i % PAGE_WIDTH;
            int col = (i / PAGE_WIDTH) * 9;

            int inventorySlot = PAGE_START + row + col;

            int id = inventorySlot - PAGE_START + this.pageIndex * ITEMS_PER_PAGE;

            ItemStack item = FoodManager.getItem(id);
            inventory.setItem(inventorySlot, item);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void handleClick(int slotId, ClickType clickType, Player player) {
        switch (slotId) {
            case (9*5 + 3):
                FreshlyFoodMenu freshlyFoodMenuLast = new FreshlyFoodMenu(player, this.pageIndex - 1);
                player.openInventory(freshlyFoodMenuLast.getInventory());
                break;

            case (9*5 + 5):
                FreshlyFoodMenu freshlyFoodMenuNext = new FreshlyFoodMenu(player, this.pageIndex + 1);
                player.openInventory(freshlyFoodMenuNext.getInventory());
                break;

            default:
                break;
        }
    }

}