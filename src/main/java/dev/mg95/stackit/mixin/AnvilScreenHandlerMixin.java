package dev.mg95.stackit.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {

    @Shadow
    @Final
    private static Logger LOGGER;

    @Final
    @Shadow
    private Property levelCost;

    public AnvilScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    @Shadow
    public abstract void updateResult();

    @Inject(
            method = "updateResult()V",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "net/minecraft/item/ItemStack.copy ()Lnet/minecraft/item/ItemStack;",
                    ordinal = 0
            )
    )
    public void fixResultCount(CallbackInfo ci, @Local(ordinal = 1) ItemStack itemStack2) {
        if (!itemStack2.isEmpty()) {
            itemStack2.setCount(1);
        }
    }

    @Redirect(
            method = "onTakeOutput(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)V",
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/inventory/Inventory.setStack (ILnet/minecraft/item/ItemStack;)V",
                    ordinal = 0
            )
    )
    public void decreaseStackedInputLeft(Inventory instance, int i, ItemStack itemStack) {
        ItemStack input = instance.getStack(0);
        if (input.getCount() > 1) {
            input.decrement(1);
        } else {
            instance.setStack(0, ItemStack.EMPTY);
        }

    }

    @Redirect(
            method = "onTakeOutput(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)V",
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/inventory/Inventory.setStack (ILnet/minecraft/item/ItemStack;)V",
                    ordinal = 3
            )
    )
    public void decreaseStackedInputRight(Inventory instance, int i, ItemStack itemStack) {
        ItemStack input = instance.getStack(1);
        if (input.getCount() > 1) {
            input.decrement(1);
        } else {
            instance.setStack(1, ItemStack.EMPTY);
        }
    }

    @Redirect(
            method = "updateResult()V",
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/item/ItemStack.getCount ()I",
                    ordinal = 1
            )
    )
    public int preventStackedTooExpensive(ItemStack instance) {
        return 1;
    }

    @Inject(
            method = "onTakeOutput(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)V",
            at = @At("TAIL")
    )
    public void updateAfterTaking(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        updateResult();
    }

}
