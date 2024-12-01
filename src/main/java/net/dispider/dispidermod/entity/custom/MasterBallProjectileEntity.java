package net.dispider.dispidermod.entity.custom;

import net.dispider.dispidermod.entity.ModEntities;
import net.dispider.dispidermod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class MasterBallProjectileEntity extends ThrowableItemProjectile {
    public MasterBallProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public MasterBallProjectileEntity(Level pLevel) {
        super(ModEntities.Masterball_PROJECTILE.get(),pLevel);
    }
    public MasterBallProjectileEntity(LivingEntity livingEntity, Level pLevel) {
        super(ModEntities.Masterball_PROJECTILE.get(),livingEntity,pLevel);
    }



    @Override
    protected Item getDefaultItem() {
        return ModItems.Masterball.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if(!this.level().isClientSide()){

            pResult.getEntity().hurt(pResult.getEntity().damageSources().magic(),4f);
            if(pResult.getEntity() instanceof WitherBoss skeleton){
                level().getNearestPlayer(pResult.getEntity(),50).addItem(new ItemStack (ModItems.WitherMasterball.get()));
                this.discard();
                pResult.getEntity().kill();
            }
            if(pResult.getEntity() instanceof EnderDragonPart skeleton){
                level().getNearestPlayer(pResult.getEntity(),50).addItem(new ItemStack (ModItems.EnderDragonMasterball.get()));
                this.discard();
                skeleton.parentMob.kill();
            }


        }
        super.onHitEntity(pResult);
    }

}