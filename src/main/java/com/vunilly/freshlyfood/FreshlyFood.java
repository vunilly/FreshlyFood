package com.vunilly.freshlyfood;

import org.bukkit.plugin.java.JavaPlugin;

import com.vunilly.freshlyfood.food.FoodManager;
import com.vunilly.freshlyfood.food.RecipeListener;
import com.vunilly.freshlyfood.food.items.CookedVeganFish;
import com.vunilly.freshlyfood.food.items.CookedVeganSteak;
import com.vunilly.freshlyfood.food.items.GoldenPear;
import com.vunilly.freshlyfood.food.items.Pear;
import com.vunilly.freshlyfood.food.items.VeganFish;
import com.vunilly.freshlyfood.food.items.VeganSteak;
import com.vunilly.freshlyfood.menu.FreshlyFoodCommand;
import com.vunilly.freshlyfood.utils.ClickMenuListener;
import com.vunilly.freshlyfood.utils.Lang;

public class FreshlyFood extends JavaPlugin {

    private FoodManager foodManager;

    @Override
    public void onEnable() {
        getLogger().info("FreshlyFood Plugin by vunilly enabled!");

        getServer().getPluginManager().registerEvents(new ClickMenuListener(), this);
        
        getServer().getPluginManager().registerEvents(new RecipeListener(this), this);

        Lang.init(this);
        Lang.loadLang();

        this.foodManager = new FoodManager(this);

        if (getCommand("freshlyfood") != null) {
            getCommand("freshlyfood").setExecutor(new FreshlyFoodCommand());
        } else {
            getLogger().severe("FreshlyFood failed to register the /freshlyfood command!");
        }
        
        getServer().getPluginManager()
            .registerEvents(new ResourcePackListener(), this);

        getLogger().info("FreshlyFood Plugin by vunilly enabled!");
    }

    public FoodManager getFoodManager() {
        return foodManager;
    }

    @Override
    public void onDisable() {
        getLogger().info("FreshlyFood Plugin by vunilly disabled!");
    }
}