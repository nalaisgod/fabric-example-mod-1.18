package net.nalaisgod.nalasmod.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.nalaisgod.nalasmod.block.entity.OriginiteBlasterEntity;

public class OriginiteBlasterRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final ItemStack result;
    private final DefaultedList<Ingredient> recipeItems;

    public OriginiteBlasterRecipe(Identifier id, ItemStack result, DefaultedList<Ingredient> recipeItems) {
        this.id = id;
        this.result = result;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) {return false;}
        if(recipeItems.get(0).test(inventory.getStack(1))) {
            return recipeItems.get(1).test(inventory.getStack(2));
        }

        return false;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory) {
        return result;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return result.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<OriginiteBlasterRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "originite_blaster";
    }

    public static class Serializer implements RecipeSerializer<OriginiteBlasterRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "originite_blaster";
        // this is the name given in the json file

        @Override
        public OriginiteBlasterRecipe read(Identifier id, JsonObject json) {
            ItemStack result = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result"));

            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new OriginiteBlasterRecipe(id, result, inputs);
        }

        @Override
        public OriginiteBlasterRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack result = buf.readItemStack();
            return new OriginiteBlasterRecipe(id, result, inputs);
        }

        @Override
        public void write(PacketByteBuf buf, OriginiteBlasterRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }
            buf.writeItemStack(recipe.getOutput());
        }
    }
}