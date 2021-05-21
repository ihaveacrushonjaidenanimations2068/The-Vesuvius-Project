
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import com.varahunter.awholenewperspective.item.PulseTurretProjectileItem;
import com.varahunter.awholenewperspective.AWholeNewPerspectiveModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@AWholeNewPerspectiveModElements.ModElement.Tag
public class PulseTurretEntity extends AWholeNewPerspectiveModElements.ModElement {
	public static EntityType entity = null;
	public PulseTurretEntity(AWholeNewPerspectiveModElements instance) {
		super(instance, 45);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("pulse_turret")
						.setRegistryName("pulse_turret");
		elements.entities.add(() -> entity);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelPulseTurret(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("a_whole_new_perspective:textures/pulseturret.png");
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
			this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
			this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(4, new SwimGoal(this));
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

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
		}

		public void attackEntityWithRangedAttack(LivingEntity target, float flval) {
			PulseTurretProjectileItem.shoot(this, target);
		}
	}

	// Made with Blockbench 3.7.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class ModelPulseTurret extends EntityModel<Entity> {
		private final ModelRenderer pulseCannon;
		private final ModelRenderer bone4;
		private final ModelRenderer bone7;
		private final ModelRenderer bone;
		private final ModelRenderer emplacementStand;
		private final ModelRenderer bone6;
		private final ModelRenderer bone2;
		private final ModelRenderer legLeft;
		public ModelPulseTurret() {
			textureWidth = 256;
			textureHeight = 256;
			pulseCannon = new ModelRenderer(this);
			pulseCannon.setRotationPoint(0.25F, 6.75F, 5.0F);
			setRotationAngle(pulseCannon, -0.3491F, 0.0F, 0.0F);
			pulseCannon.setTextureOffset(0, 70).addBox(-9.25F, -9.75F, -6.0F, 19.0F, 17.0F, 14.0F, 0.0F, false);
			bone4 = new ModelRenderer(this);
			bone4.setRotationPoint(-0.25F, 17.25F, -5.0F);
			pulseCannon.addChild(bone4);
			bone4.setTextureOffset(0, 30).addBox(-9.0F, -27.0F, -21.0F, 19.0F, 17.0F, 20.0F, 0.0F, false);
			bone7 = new ModelRenderer(this);
			bone7.setRotationPoint(0.5F, -32.7115F, -5.2462F);
			bone4.addChild(bone7);
			setRotationAngle(bone7, 0.6981F, 0.0F, 0.0F);
			bone7.setTextureOffset(58, 30).addBox(-9.5F, -7.5F, -1.0F, 19.0F, 15.0F, 2.0F, 0.0F, false);
			bone = new ModelRenderer(this);
			bone.setRotationPoint(0.0F, 0.0F, 0.0F);
			pulseCannon.addChild(bone);
			bone.setTextureOffset(49, 84).addBox(-7.1F, -8.0F, -43.0F, 15.0F, 14.0F, 17.0F, 0.0F, false);
			emplacementStand = new ModelRenderer(this);
			emplacementStand.setRotationPoint(-8.5F, 12.0F, 4.5F);
			emplacementStand.setTextureOffset(86, 0).addBox(-5.5F, 0.0F, -2.5F, 6.0F, 12.0F, 6.0F, 0.0F, false);
			bone6 = new ModelRenderer(this);
			bone6.setRotationPoint(-15.5F, -2.0F, -4.5F);
			emplacementStand.addChild(bone6);
			bone6.setTextureOffset(0, 0).addBox(10.0F, 14.0F, -20.0F, 29.0F, 2.0F, 28.0F, 0.0F, false);
			bone2 = new ModelRenderer(this);
			bone2.setRotationPoint(8.5F, 9.0F, -5.5F);
			emplacementStand.addChild(bone2);
			setRotationAngle(bone2, 0.0F, 0.7854F, 0.0F);
			bone2.setTextureOffset(60, 60).addBox(-10.0F, -3.0F, -9.0F, 20.0F, 6.0F, 18.0F, 0.0F, false);
			legLeft = new ModelRenderer(this);
			legLeft.setRotationPoint(18.5F, 0.0F, 0.0F);
			emplacementStand.addChild(legLeft);
			legLeft.setTextureOffset(0, 0).addBox(-1.0F, 0.0F, -2.5F, 6.0F, 12.0F, 6.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			pulseCannon.render(matrixStack, buffer, packedLight, packedOverlay);
			emplacementStand.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
		}
	}
}
