package net.nalaisgod.nalasmod.entity.projectile;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.nalaisgod.nalasmod.effect.ModEffects;
import net.nalaisgod.nalasmod.item.ModItems;
import net.nalaisgod.nalasmod.potion.ModPotions;

public class YeetArrow extends PersistentProjectileEntity{
    private int duration = 200;


    public YeetArrow(EntityType<? extends YeetArrow> entityType, World world) {
        super((EntityType<? extends PersistentProjectileEntity>)entityType, world);
    }

    public YeetArrow(World world, LivingEntity owner) {
        super(EntityType.SPECTRAL_ARROW, owner, world);
    }

    public YeetArrow(World world, double x, double y, double z) {
        super(EntityType.SPECTRAL_ARROW, x, y, z, world);
    }


    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (this.world.isClient) {
            return;
        }
        ItemStack itemStack = this.asItemStack();
        Potion potion = PotionUtil.getPotion(itemStack);
                this.applyLingeringPotion(itemStack, potion);
    }



    private void applyLingeringPotion(ItemStack stack, Potion potion) {
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.world, this.getX(), this.getY(), this.getZ());
        Entity entity = this.getOwner();
        if (entity instanceof LivingEntity) {
            areaEffectCloudEntity.setOwner((LivingEntity)entity);
        }
        areaEffectCloudEntity.setRadius(3.0f);
        areaEffectCloudEntity.setRadiusOnUse(-0.5f);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
        areaEffectCloudEntity.setPotion(ModPotions.FREEZE_POTION);
        for (StatusEffectInstance statusEffectInstance : PotionUtil.getCustomPotionEffects(stack)) {
            areaEffectCloudEntity.addEffect(new StatusEffectInstance(statusEffectInstance));
        }
        NbtCompound nbtCompound = stack.getNbt();
        if (nbtCompound != null && nbtCompound.contains("CustomPotionColor", 99)) {
            areaEffectCloudEntity.setColor(nbtCompound.getInt("CustomPotionColor"));
        }
        this.world.spawnEntity(areaEffectCloudEntity);
    }



    @Override
    public void tick() {
        super.tick();
        if (this.world.isClient && !this.inGround) {
            this.world.addParticle(ParticleTypes.INSTANT_EFFECT, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.FREEZE_ROD);
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        StatusEffectInstance statusEffectInstance = new StatusEffectInstance(ModEffects.FREEZE, 200, 0);
        target.addStatusEffect(statusEffectInstance, this.getEffectCause());
    }
}
