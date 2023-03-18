package com.github.zly2006.enclosure.mixin;

import com.github.zly2006.enclosure.utils.Permission;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.github.zly2006.enclosure.ServerMain.*;

@Mixin(FarmlandBlock.class)
public class MixinFarmlandBlock {
    @Inject(method = "onLandedUpon", at = @At("HEAD"), cancellable = true)
    private void onLandedUpon(World world, BlockPos pos, Entity entity, float distance, CallbackInfo ci) {
        if (entity instanceof PlayerEntity player) {
            if (!Instance.checkPermission(world, pos, player, Permission.FARMLAND_DESTROY)) {
                ((PlayerEntity) entity).sendMessage(Permission.FARMLAND_DESTROY.getNoPermissionMsg(player),false);
                ci.cancel();
            }
        }
    }
}
