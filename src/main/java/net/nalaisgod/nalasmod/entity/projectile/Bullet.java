package net.nalaisgod.nalasmod.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.nalaisgod.nalasmod.item.ModItems;


public class Bullet extends PersistentProjectileEntity{
    private int duration = 200;


    public Bullet(EntityType<? extends Bullet> entityType, World world) {
        super((EntityType<? extends PersistentProjectileEntity>)entityType, world);
    }

    public Bullet(World world, LivingEntity owner) {
        super(EntityType.SPECTRAL_ARROW, owner, world);
    }

    public Bullet(World world, double x, double y, double z) {
        super(EntityType.SPECTRAL_ARROW, x, y, z, world);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            Explosion.DestructionType destructionType = this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE;
            this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 6.0f, false, destructionType);
            this.discard();
        }
    }


    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.BULLET);
    }
}