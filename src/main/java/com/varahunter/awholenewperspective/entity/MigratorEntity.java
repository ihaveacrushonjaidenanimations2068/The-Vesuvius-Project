
package com.varahunter.awholenewperspective.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import java.util.Map;
import java.util.HashMap;

import com.varahunter.awholenewperspective.procedures.MigratorOnInitialEntitySpawnProcedure;
import com.varahunter.awholenewperspective.procedures.IsMigratorHostileProcedure;
import com.varahunter.awholenewperspective.item.TripmineItemItem;
import com.varahunter.awholenewperspective.block.BraincubeBlock;
import com.varahunter.awholenewperspective.AWholeNewPerspectiveModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

import com.google.common.collect.ImmutableMap;

@AWholeNewPerspectiveModElements.ModElement.Tag
public class MigratorEntity extends AWholeNewPerspectiveModElements.ModElement {
	public static EntityType entity = null;
	public MigratorEntity(AWholeNewPerspectiveModElements instance) {
		super(instance, 71);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 4.5f)).build("migrator")
						.setRegistryName("migrator");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -13421773, -16724788, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("migrator_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelMigratorCivilized(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("a_whole_new_perspective:textures/migrator_new_rev1.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity implements IRangedAttackMob {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, (float) 6));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, false) {
				@Override
				public boolean shouldExecute() {
					double x = CustomEntity.this.getPosX();
					double y = CustomEntity.this.getPosY();
					double z = CustomEntity.this.getPosZ();
					Entity entity = CustomEntity.this;
					return super.shouldExecute() && IsMigratorHostileProcedure.executeProcedure(ImmutableMap.of("world", world));
				}
			});
			this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2, false));
			this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 1));
			this.targetSelector.addGoal(5, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(7, new SwimGoal(this));
			this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25, 20, 10) {
				@Override
				public boolean shouldContinueExecuting() {
					return this.shouldExecute();
				}
			});
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(BraincubeBlock.block, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.FALL)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, ILivingEntityData livingdata,
				CompoundNBT tag) {
			ILivingEntityData retval = super.onInitialSpawn(world, difficulty, reason, livingdata, tag);
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("world", world);
				MigratorOnInitialEntitySpawnProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
		}

		public void attackEntityWithRangedAttack(LivingEntity target, float flval) {
			TripmineItemItem.shoot(this, target);
		}
	}

	// Made with Blockbench 3.8.4
	// Exported for Minecraft version 1.15 - 1.16
	// Paste this class into your mod and generate all required imports
	public static class ModelMigratorCivilized extends EntityModel<Entity> {
		private final ModelRenderer legLeft7;
		private final ModelRenderer legLeft8;
		private final ModelRenderer legLeft9;
		private final ModelRenderer bone;
		private final ModelRenderer bone2;
		private final ModelRenderer legLeft10;
		private final ModelRenderer legLeft11;
		private final ModelRenderer legLeft12;
		private final ModelRenderer bone3;
		private final ModelRenderer bone4;
		private final ModelRenderer bone5;
		private final ModelRenderer bone6;
		private final ModelRenderer bone15;
		private final ModelRenderer bone13;
		private final ModelRenderer bone14;
		private final ModelRenderer bone19;
		private final ModelRenderer bone16;
		private final ModelRenderer bone17;
		private final ModelRenderer bone18;
		private final ModelRenderer bone20;
		private final ModelRenderer bone22;
		private final ModelRenderer bone21;
		private final ModelRenderer bone7;
		private final ModelRenderer bone8;
		private final ModelRenderer bone9;
		private final ModelRenderer bone10;
		private final ModelRenderer bone11;
		private final ModelRenderer bone12;
		public ModelMigratorCivilized() {
			textureWidth = 128;
			textureHeight = 128;
			legLeft7 = new ModelRenderer(this);
			legLeft7.setRotationPoint(-5.5F, 4.25F, 8.75F);
			setRotationAngle(legLeft7, 0.0436F, 0.0F, 0.0F);
			legLeft7.setTextureOffset(57, 0).addBox(-5.5F, -1.25F, -2.75F, 6.0F, 3.0F, 3.0F, 0.0F, false);
			legLeft8 = new ModelRenderer(this);
			legLeft8.setRotationPoint(1.5F, 1.0672F, -10.3851F);
			legLeft7.addChild(legLeft8);
			setRotationAngle(legLeft8, -1.0036F, 0.0F, 0.0F);
			legLeft8.setTextureOffset(66, 97).addBox(-8.0F, -9.8471F, 1.9554F, 6.0F, 12.0F, 6.0F, 0.0F, false);
			legLeft9 = new ModelRenderer(this);
			legLeft9.setRotationPoint(-4.0F, 6.6828F, 8.6351F);
			legLeft8.addChild(legLeft9);
			setRotationAngle(legLeft9, 1.0036F, 0.0F, 0.0F);
			legLeft9.setTextureOffset(24, 79).addBox(-4.0F, -8.0F, 0.0F, 6.0F, 16.0F, 6.0F, 0.0F, false);
			bone = new ModelRenderer(this);
			bone.setRotationPoint(-4.0F, 20.4F, -11.9F);
			legLeft7.addChild(bone);
			setRotationAngle(bone, 0.0F, 0.4363F, 0.0F);
			bone.setTextureOffset(106, 30).addBox(-2.4063F, -1.5F, -4.9226F, 3.0F, 1.0F, 5.0F, 0.0F, false);
			bone2 = new ModelRenderer(this);
			bone2.setRotationPoint(-1.0F, 20.4F, -11.9F);
			legLeft7.addChild(bone2);
			setRotationAngle(bone2, 0.0F, -0.4363F, 0.0F);
			bone2.setTextureOffset(106, 24).addBox(-2.4063F, -1.5F, -4.0774F, 3.0F, 1.0F, 5.0F, 0.0F, false);
			legLeft10 = new ModelRenderer(this);
			legLeft10.setRotationPoint(1.0F, 4.1252F, 6.8859F);
			legLeft10.setTextureOffset(25, 0).addBox(0.0F, -1.1243F, -0.8423F, 6.0F, 3.0F, 3.0F, 0.0F, false);
			legLeft11 = new ModelRenderer(this);
			legLeft11.setRotationPoint(4.0F, 6.3824F, 0.5631F);
			legLeft10.addChild(legLeft11);
			setRotationAngle(legLeft11, -1.0036F, 0.0F, 0.0F);
			legLeft11.setTextureOffset(42, 97).addBox(-3.0F, -5.0738F, -7.5044F, 6.0F, 12.0F, 6.0F, 0.0F, false);
			legLeft12 = new ModelRenderer(this);
			legLeft12.setRotationPoint(1.0F, 11.4924F, -0.849F);
			legLeft11.addChild(legLeft12);
			setRotationAngle(legLeft12, 1.0036F, 0.0F, 0.0F);
			legLeft12.setTextureOffset(0, 79).addBox(-4.0F, -7.999F, 0.0436F, 6.0F, 16.0F, 6.0F, 0.0F, false);
			bone3 = new ModelRenderer(this);
			bone3.setRotationPoint(3.7887F, 19.8248F, -9.6891F);
			legLeft10.addChild(bone3);
			setRotationAngle(bone3, 0.0F, 0.4363F, 0.0F);
			bone3.setTextureOffset(106, 18).addBox(-3.0F, -0.999F, -5.2564F, 3.0F, 1.0F, 5.0F, 0.0F, false);
			bone4 = new ModelRenderer(this);
			bone4.setRotationPoint(5.5F, 19.3257F, -8.9923F);
			legLeft10.addChild(bone4);
			setRotationAngle(bone4, 0.0F, -0.4363F, 0.0F);
			bone4.setTextureOffset(99, 63).addBox(-1.5F, -0.5F, -5.5F, 3.0F, 1.0F, 5.0F, 0.0F, false);
			bone5 = new ModelRenderer(this);
			bone5.setRotationPoint(-2.0F, -4.0F, 8.0F);
			bone5.setTextureOffset(90, 97).addBox(-3.0F, 5.0F, -3.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);
			bone6 = new ModelRenderer(this);
			bone6.setRotationPoint(2.0F, -2.0F, -8.0F);
			bone5.addChild(bone6);
			bone6.setTextureOffset(24, 101).addBox(-3.0F, -11.0F, 7.0F, 2.0F, 18.0F, 2.0F, 0.0F, false);
			bone15 = new ModelRenderer(this);
			bone15.setRotationPoint(1.125F, -3.5F, 6.0F);
			bone6.addChild(bone15);
			bone15.setTextureOffset(102, 39).addBox(-0.375F, -2.0F, -2.0F, 3.0F, 5.0F, 4.0F, 0.0F, false);
			bone13 = new ModelRenderer(this);
			bone13.setRotationPoint(2.625F, 1.0F, 0.0F);
			bone15.addChild(bone13);
			setRotationAngle(bone13, 0.0F, 0.0F, 0.4363F);
			bone13.setTextureOffset(90, 51).addBox(-2.75F, -2.5F, -2.0F, 13.0F, 4.0F, 4.0F, 0.0F, false);
			bone14 = new ModelRenderer(this);
			bone14.setRotationPoint(9.75F, 0.0F, 0.0F);
			bone13.addChild(bone14);
			setRotationAngle(bone14, 0.0F, 0.6981F, 0.0F);
			bone14.setTextureOffset(48, 79).addBox(0.5F, -2.5F, -2.0F, 14.0F, 4.0F, 4.0F, 0.0F, false);
			bone19 = new ModelRenderer(this);
			bone19.setRotationPoint(-9.75F, 0.0F, 0.0F);
			bone14.addChild(bone19);
			bone19.setTextureOffset(0, 70).addBox(24.0F, -8.0F, -0.5F, 24.0F, 9.0F, 0.0F, 0.0F, false);
			bone16 = new ModelRenderer(this);
			bone16.setRotationPoint(-5.125F, -3.5F, 6.0F);
			bone6.addChild(bone16);
			bone16.setTextureOffset(90, 59).addBox(-2.625F, -2.0F, -2.0F, 3.0F, 5.0F, 4.0F, 0.0F, false);
			bone17 = new ModelRenderer(this);
			bone17.setRotationPoint(-0.625F, 1.0F, 1.0F);
			bone16.addChild(bone17);
			setRotationAngle(bone17, 0.0F, 0.0F, -0.48F);
			bone17.setTextureOffset(80, 89).addBox(-12.25F, -2.5F, -3.0F, 13.0F, 4.0F, 4.0F, 0.0F, false);
			bone18 = new ModelRenderer(this);
			bone18.setRotationPoint(-12.25F, -0.5F, -1.0F);
			bone17.addChild(bone18);
			setRotationAngle(bone18, 0.0F, -0.6545F, 0.0F);
			bone18.setTextureOffset(66, 43).addBox(-14.0F, -2.0F, -2.0F, 14.0F, 4.0F, 4.0F, 0.0F, false);
			bone20 = new ModelRenderer(this);
			bone20.setRotationPoint(12.25F, 0.5F, 1.0F);
			bone18.addChild(bone20);
			bone20.setTextureOffset(66, 0).addBox(-44.0F, -4.0F, -6.0F, 18.0F, 9.0F, 9.0F, 0.0F, false);
			bone22 = new ModelRenderer(this);
			bone22.setRotationPoint(0.0F, 14.0F, 0.0F);
			bone20.addChild(bone22);
			bone22.setTextureOffset(84, 79).addBox(-57.0F, -11.0F, -1.0F, 16.0F, 10.0F, 0.0F, 0.0F, false);
			bone21 = new ModelRenderer(this);
			bone21.setRotationPoint(0.0F, 0.0F, 0.0F);
			bone20.addChild(bone21);
			bone21.setTextureOffset(48, 87).addBox(-57.0F, -11.0F, -1.0F, 16.0F, 10.0F, 0.0F, 0.0F, false);
			bone7 = new ModelRenderer(this);
			bone7.setRotationPoint(0.0F, 0.0F, 0.0F);
			bone6.addChild(bone7);
			bone7.setTextureOffset(0, 101).addBox(-5.0F, -6.0F, 3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
			bone8 = new ModelRenderer(this);
			bone8.setRotationPoint(0.0F, -13.0F, 0.0F);
			bone5.addChild(bone8);
			setRotationAngle(bone8, 0.4363F, 0.0F, 0.0F);
			bone8.setTextureOffset(32, 101).addBox(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
			bone9 = new ModelRenderer(this);
			bone9.setRotationPoint(0.0F, -7.0F, 0.0F);
			bone8.addChild(bone9);
			setRotationAngle(bone9, -0.1745F, 0.0F, 0.0F);
			bone9.setTextureOffset(48, 51).addBox(-5.0F, -9.7504F, -14.853F, 12.0F, 10.0F, 18.0F, 0.0F, false);
			bone10 = new ModelRenderer(this);
			bone10.setRotationPoint(1.5F, -5.7504F, 3.647F);
			bone9.addChild(bone10);
			setRotationAngle(bone10, 0.0F, 0.0F, -0.3054F);
			bone10.setTextureOffset(34, 0).addBox(0.5F, -5.0F, -1.5F, 7.0F, 60.0F, 9.0F, 0.0F, false);
			bone11 = new ModelRenderer(this);
			bone11.setRotationPoint(-2.0F, -5.7504F, 3.647F);
			bone9.addChild(bone11);
			setRotationAngle(bone11, 0.0F, 0.0F, 0.3054F);
			bone11.setTextureOffset(0, 0).addBox(-4.0F, -5.0F, -1.5F, 8.0F, 61.0F, 9.0F, 0.0F, false);
			bone12 = new ModelRenderer(this);
			bone12.setRotationPoint(-2.4F, -9.5F, 3.0F);
			bone12.setTextureOffset(66, 18).addBox(-7.0F, -10.5F, -4.0F, 16.0F, 21.0F, 4.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			legLeft7.render(matrixStack, buffer, packedLight, packedOverlay);
			legLeft10.render(matrixStack, buffer, packedLight, packedOverlay);
			bone5.render(matrixStack, buffer, packedLight, packedOverlay);
			bone12.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.legLeft10.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.legLeft7.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.bone15.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.bone16.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.bone9.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.bone9.rotateAngleX = f4 / (180F / (float) Math.PI);
		}
	}
}
