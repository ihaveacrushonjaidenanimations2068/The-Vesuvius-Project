
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

import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.World;
import net.minecraft.world.BossInfo;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import com.varahunter.awholenewperspective.AWholeNewPerspectiveModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@AWholeNewPerspectiveModElements.ModElement.Tag
public class VividonianHuntermechEntity extends AWholeNewPerspectiveModElements.ModElement {
	public static EntityType entity = null;
	public VividonianHuntermechEntity(AWholeNewPerspectiveModElements instance) {
		super(instance, 18);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.4f, 3.1f)).build("vividonian_huntermech")
						.setRegistryName("vividonian_huntermech");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -6750055, -65281, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("vividonian_huntermech_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelVividonianHuntermech(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("a_whole_new_perspective:textures/modelvividonianhuntermech.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
			enablePersistence();
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false));
			this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1));
			this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(5, new SwimGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
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

		@Override
		public boolean isNonBoss() {
			return false;
		}
		private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.PINK, BossInfo.Overlay.PROGRESS);
		@Override
		public void addTrackingPlayer(ServerPlayerEntity player) {
			super.addTrackingPlayer(player);
			this.bossInfo.addPlayer(player);
		}

		@Override
		public void removeTrackingPlayer(ServerPlayerEntity player) {
			super.removeTrackingPlayer(player);
			this.bossInfo.removePlayer(player);
		}

		@Override
		public void updateAITasks() {
			super.updateAITasks();
			this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		}
	}

	// Made with Blockbench 3.5.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class ModelVividonianHuntermech extends EntityModel<Entity> {
		private final ModelRenderer bone;
		private final ModelRenderer bone2;
		private final ModelRenderer bone3;
		private final ModelRenderer bone4;
		private final ModelRenderer bone5;
		private final ModelRenderer bone6;
		private final ModelRenderer bone7;
		private final ModelRenderer bone17;
		private final ModelRenderer bone18;
		private final ModelRenderer bone8;
		private final ModelRenderer bone9;
		private final ModelRenderer bone10;
		private final ModelRenderer bone11;
		private final ModelRenderer bone12;
		private final ModelRenderer bone13;
		private final ModelRenderer bone14;
		private final ModelRenderer bone15;
		private final ModelRenderer bone16;
		private final ModelRenderer waistBase;
		private final ModelRenderer bone19;
		private final ModelRenderer bone20;
		private final ModelRenderer bone21;
		private final ModelRenderer bone22;
		private final ModelRenderer spine;
		private final ModelRenderer bone23;
		private final ModelRenderer bone24;
		private final ModelRenderer bone25;
		private final ModelRenderer bone26;
		private final ModelRenderer bone27;
		private final ModelRenderer bone28;
		private final ModelRenderer armBase;
		private final ModelRenderer armBase2;
		private final ModelRenderer armBase7;
		private final ModelRenderer armBase3;
		private final ModelRenderer bone29;
		private final ModelRenderer bone42;
		private final ModelRenderer bone43;
		private final ModelRenderer armBase4;
		private final ModelRenderer armBase5;
		private final ModelRenderer armBase6;
		private final ModelRenderer bone30;
		private final ModelRenderer headBase;
		private final ModelRenderer bone31;
		private final ModelRenderer bone32;
		private final ModelRenderer bone33;
		private final ModelRenderer bone34;
		private final ModelRenderer bone35;
		private final ModelRenderer bone36;
		private final ModelRenderer bone38;
		private final ModelRenderer bone39;
		private final ModelRenderer bone40;
		private final ModelRenderer bone41;
		private final ModelRenderer bone37;
		private final ModelRenderer sword;
		private final ModelRenderer sword2;
		private final ModelRenderer sword3;
		private final ModelRenderer sword4;
		private final ModelRenderer sword5;
		private final ModelRenderer sword6;
		public ModelVividonianHuntermech() {
			textureWidth = 256;
			textureHeight = 256;
			bone = new ModelRenderer(this);
			bone.setRotationPoint(-8.0F, -20.4F, 0.6F);
			bone.setTextureOffset(108, 142).addBox(-5.0F, 40.4F, -9.6F, 6.0F, 4.0F, 4.0F, 0.0F, false);
			bone2 = new ModelRenderer(this);
			bone2.setRotationPoint(14.0F, 44.4F, -0.6F);
			bone.addChild(bone2);
			bone3 = new ModelRenderer(this);
			bone3.setRotationPoint(-7.0F, -2.0F, -4.5F);
			bone2.addChild(bone3);
			setRotationAngle(bone3, 0.0F, -0.6981F, 0.0F);
			bone3.setTextureOffset(158, 159).addBox(-6.846F, -2.0F, 1.1756F, 6.0F, 4.0F, 3.0F, 0.0F, false);
			bone4 = new ModelRenderer(this);
			bone4.setRotationPoint(-16.0F, -2.0F, -4.5F);
			bone2.addChild(bone4);
			setRotationAngle(bone4, 0.0F, -2.2689F, 0.0F);
			bone4.setTextureOffset(152, 24).addBox(-0.7528F, -2.0F, 1.5479F, 6.0F, 4.0F, 3.0F, 0.0F, false);
			bone5 = new ModelRenderer(this);
			bone5.setRotationPoint(8.0F, 44.4F, -0.6F);
			bone.addChild(bone5);
			bone5.setTextureOffset(74, 139).addBox(-15.0F, -4.0F, -5.0F, 11.0F, 4.0F, 12.0F, 0.0F, false);
			bone6 = new ModelRenderer(this);
			bone6.setRotationPoint(8.0F, 44.4F, -0.6F);
			bone.addChild(bone6);
			bone6.setTextureOffset(120, 166).addBox(-12.0F, -25.0F, -1.0F, 4.0F, 21.0F, 4.0F, 0.0F, false);
			bone7 = new ModelRenderer(this);
			bone7.setRotationPoint(0.0F, 0.0F, 0.0F);
			bone6.addChild(bone7);
			bone7.setTextureOffset(0, 125).addBox(-15.0F, -21.0F, -4.0F, 10.0F, 14.0F, 10.0F, 0.0F, false);
			bone17 = new ModelRenderer(this);
			bone17.setRotationPoint(8.0F, 23.4F, -0.6F);
			bone.addChild(bone17);
			bone17.setTextureOffset(56, 43).addBox(-12.0F, -25.0F, -1.0F, 4.0F, 21.0F, 4.0F, 0.0F, false);
			bone18 = new ModelRenderer(this);
			bone18.setRotationPoint(0.0F, 0.0F, 0.0F);
			bone17.addChild(bone18);
			bone18.setTextureOffset(112, 20).addBox(-15.0F, -17.0F, -4.0F, 10.0F, 14.0F, 10.0F, 0.0F, false);
			bone8 = new ModelRenderer(this);
			bone8.setRotationPoint(6.8F, -20.4F, 1.6F);
			bone8.setTextureOffset(118, 110).addBox(-0.8F, 40.4F, -10.6F, 6.0F, 4.0F, 4.0F, 0.0F, false);
			bone9 = new ModelRenderer(this);
			bone9.setRotationPoint(18.2F, 44.4F, -1.6F);
			bone8.addChild(bone9);
			bone10 = new ModelRenderer(this);
			bone10.setRotationPoint(-7.0F, -2.0F, -4.5F);
			bone9.addChild(bone10);
			setRotationAngle(bone10, 0.0F, -0.6981F, 0.0F);
			bone10.setTextureOffset(62, 142).addBox(-6.846F, -2.0F, 1.1756F, 6.0F, 4.0F, 3.0F, 0.0F, false);
			bone11 = new ModelRenderer(this);
			bone11.setRotationPoint(-16.0F, -2.0F, -4.5F);
			bone9.addChild(bone11);
			setRotationAngle(bone11, 0.0F, -2.2689F, 0.0F);
			bone11.setTextureOffset(81, 66).addBox(-0.7528F, -2.0F, 1.5479F, 6.0F, 4.0F, 3.0F, 0.0F, false);
			bone12 = new ModelRenderer(this);
			bone12.setRotationPoint(12.2F, 44.4F, -1.6F);
			bone8.addChild(bone12);
			bone12.setTextureOffset(28, 137).addBox(-15.0F, -4.0F, -5.0F, 11.0F, 4.0F, 12.0F, 0.0F, false);
			bone13 = new ModelRenderer(this);
			bone13.setRotationPoint(12.2F, 44.4F, -1.6F);
			bone8.addChild(bone13);
			bone13.setTextureOffset(16, 166).addBox(-12.0F, -25.0F, -1.0F, 4.0F, 21.0F, 4.0F, 0.0F, false);
			bone14 = new ModelRenderer(this);
			bone14.setRotationPoint(0.0F, 0.0F, 0.0F);
			bone13.addChild(bone14);
			bone14.setTextureOffset(118, 86).addBox(-15.0F, -21.0F, -4.0F, 10.0F, 14.0F, 10.0F, 0.0F, false);
			bone15 = new ModelRenderer(this);
			bone15.setRotationPoint(12.2F, 23.4F, -1.6F);
			bone8.addChild(bone15);
			bone15.setTextureOffset(0, 166).addBox(-12.0F, -25.0F, -1.0F, 4.0F, 21.0F, 4.0F, 0.0F, false);
			bone16 = new ModelRenderer(this);
			bone16.setRotationPoint(0.0F, 0.0F, 0.0F);
			bone15.addChild(bone16);
			bone16.setTextureOffset(118, 118).addBox(-15.0F, -17.0F, -4.0F, 10.0F, 14.0F, 10.0F, 0.0F, false);
			waistBase = new ModelRenderer(this);
			waistBase.setRotationPoint(0.0F, 24.0F, 0.0F);
			waistBase.setTextureOffset(143, 0).addBox(-8.0F, -49.0F, -1.0F, 15.0F, 3.0F, 4.0F, 0.0F, false);
			bone19 = new ModelRenderer(this);
			bone19.setRotationPoint(-11.0F, -3.0F, -2.0F);
			waistBase.addChild(bone19);
			bone19.setTextureOffset(81, 47).addBox(-1.0F, -50.0F, -3.0F, 23.0F, 7.0F, 12.0F, 0.0F, false);
			bone20 = new ModelRenderer(this);
			bone20.setRotationPoint(-2.0F, 11.0F, -2.0F);
			waistBase.addChild(bone20);
			bone20.setTextureOffset(0, 153).addBox(-5.0F, -57.0F, -1.0F, 13.0F, 5.0F, 8.0F, 0.0F, false);
			bone21 = new ModelRenderer(this);
			bone21.setRotationPoint(10.5F, -50.5F, 1.0F);
			waistBase.addChild(bone21);
			setRotationAngle(bone21, 0.0F, 0.0F, 0.8727F);
			bone21.setTextureOffset(150, 134).addBox(0.5F, -2.5F, -4.0F, 13.0F, 5.0F, 8.0F, 0.0F, false);
			bone22 = new ModelRenderer(this);
			bone22.setRotationPoint(-12.5F, -50.5F, 1.0F);
			waistBase.addChild(bone22);
			setRotationAngle(bone22, 0.0F, 0.0F, -0.9599F);
			bone22.setTextureOffset(148, 81).addBox(-12.5F, -2.5F, -4.0F, 13.0F, 5.0F, 8.0F, 0.0F, false);
			spine = new ModelRenderer(this);
			spine.setRotationPoint(0.0F, 24.0F, 0.0F);
			spine.setTextureOffset(90, 96).addBox(-4.0F, -88.0F, -4.0F, 6.0F, 35.0F, 8.0F, 0.0F, false);
			bone23 = new ModelRenderer(this);
			bone23.setRotationPoint(1.0F, -72.0F, 4.0F);
			spine.addChild(bone23);
			bone23.setTextureOffset(0, 0).addBox(-15.0F, -13.0F, 0.0F, 25.0F, 31.0F, 10.0F, 0.0F, false);
			bone24 = new ModelRenderer(this);
			bone24.setRotationPoint(-28.0F, -72.0F, 4.0F);
			spine.addChild(bone24);
			bone24.setTextureOffset(70, 0).addBox(11.0F, -13.0F, -9.0F, 5.0F, 31.0F, 16.0F, 0.0F, false);
			bone25 = new ModelRenderer(this);
			bone25.setRotationPoint(7.0F, -72.0F, 4.0F);
			spine.addChild(bone25);
			bone25.setTextureOffset(56, 56).addBox(3.0F, -13.0F, -10.0F, 4.0F, 31.0F, 17.0F, 0.0F, false);
			bone26 = new ModelRenderer(this);
			bone26.setRotationPoint(12.0F, -69.5F, -11.5F);
			spine.addChild(bone26);
			setRotationAngle(bone26, 0.0F, 0.9599F, 0.0F);
			bone26.setTextureOffset(32, 93).addBox(-5.7678F, -15.5F, -6.4202F, 4.0F, 31.0F, 11.0F, 0.0F, false);
			bone27 = new ModelRenderer(this);
			bone27.setRotationPoint(-14.5F, -69.5F, -12.5F);
			spine.addChild(bone27);
			setRotationAngle(bone27, 0.0F, -0.9599F, 0.0F);
			bone27.setTextureOffset(0, 78).addBox(2.0869F, -15.5F, -4.8467F, 5.0F, 31.0F, 11.0F, 0.0F, false);
			bone28 = new ModelRenderer(this);
			bone28.setRotationPoint(0.0F, 24.0F, 0.0F);
			bone28.setTextureOffset(0, 41).addBox(-12.0F, -84.0F, -10.0F, 22.0F, 31.0F, 6.0F, 0.0F, false);
			armBase = new ModelRenderer(this);
			armBase.setRotationPoint(14.7143F, -54.4286F, 2.4286F);
			armBase.setTextureOffset(58, 153).addBox(2.2857F, -3.5714F, -1.4286F, 4.0F, 32.0F, 4.0F, 0.0F, false);
			armBase2 = new ModelRenderer(this);
			armBase2.setRotationPoint(-14.7143F, 63.4286F, -2.4286F);
			armBase.addChild(armBase2);
			armBase2.setTextureOffset(112, 0).addBox(14.0F, -69.0F, -4.0F, 9.0F, 7.0F, 13.0F, 0.0F, false);
			armBase7 = new ModelRenderer(this);
			armBase7.setRotationPoint(7.7857F, -5.5714F, 0.0714F);
			armBase.addChild(armBase7);
			setRotationAngle(armBase7, 0.0F, 0.0F, -0.6981F);
			armBase7.setTextureOffset(139, 31).addBox(0.5F, 0.0F, -6.5F, 9.0F, 2.0F, 13.0F, 0.0F, false);
			armBase3 = new ModelRenderer(this);
			armBase3.setRotationPoint(-14.7143F, 77.4286F, -2.4286F);
			armBase.addChild(armBase3);
			armBase3.setTextureOffset(142, 57).addBox(14.0F, -69.0F, -2.0F, 9.0F, 15.0F, 9.0F, 0.0F, false);
			bone29 = new ModelRenderer(this);
			bone29.setRotationPoint(-14.7143F, 78.4286F, -2.4286F);
			armBase.addChild(bone29);
			bone29.setTextureOffset(158, 147).addBox(15.0F, -50.0F, 0.0F, 7.0F, 6.0F, 6.0F, 0.0F, false);
			bone42 = new ModelRenderer(this);
			bone42.setRotationPoint(-14.7143F, 78.4286F, -2.4286F);
			armBase.addChild(bone42);
			bone42.setTextureOffset(168, 168).addBox(23.0F, -67.0F, 0.0F, 1.0F, 9.0F, 5.0F, 0.0F, false);
			bone43 = new ModelRenderer(this);
			bone43.setRotationPoint(24.5F, -62.5F, 4.5F);
			bone42.addChild(bone43);
			setRotationAngle(bone43, 0.0F, -1.9199F, 0.0F);
			bone43.setTextureOffset(41, 78).addBox(-0.5F, -4.5F, -4.5F, 1.0F, 9.0F, 5.0F, 0.0F, false);
			armBase4 = new ModelRenderer(this);
			armBase4.setRotationPoint(-22.5F, -56.5F, 2.75F);
			armBase4.setTextureOffset(42, 153).addBox(-1.5F, -1.5F, -1.75F, 4.0F, 32.0F, 4.0F, 0.0F, false);
			armBase5 = new ModelRenderer(this);
			armBase5.setRotationPoint(19.5F, 65.5F, -2.75F);
			armBase4.addChild(armBase5);
			armBase5.setTextureOffset(98, 66).addBox(-23.0F, -69.0F, -4.0F, 9.0F, 7.0F, 13.0F, 0.0F, false);
			armBase6 = new ModelRenderer(this);
			armBase6.setRotationPoint(19.5F, 79.5F, -2.75F);
			armBase4.addChild(armBase6);
			armBase6.setTextureOffset(120, 142).addBox(-23.0F, -69.0F, -2.0F, 9.0F, 15.0F, 9.0F, 0.0F, false);
			bone30 = new ModelRenderer(this);
			bone30.setRotationPoint(18.5F, 80.5F, -2.75F);
			armBase4.addChild(bone30);
			bone30.setTextureOffset(96, 0).addBox(-22.0F, -50.0F, 0.0F, 7.0F, 6.0F, 6.0F, 0.0F, false);
			headBase = new ModelRenderer(this);
			headBase.setRotationPoint(-0.1F, -64.25F, -0.1F);
			headBase.setTextureOffset(148, 100).addBox(-4.9F, -8.75F, -5.9F, 8.0F, 9.0F, 10.0F, 0.0F, false);
			bone31 = new ModelRenderer(this);
			bone31.setRotationPoint(0.1F, 88.25F, 0.1F);
			headBase.addChild(bone31);
			bone31.setTextureOffset(146, 10).addBox(-7.0F, -101.0F, -7.0F, 11.0F, 4.0F, 10.0F, 0.0F, false);
			bone32 = new ModelRenderer(this);
			bone32.setRotationPoint(0.1F, 90.25F, -14.9F);
			headBase.addChild(bone32);
			bone32.setTextureOffset(158, 119).addBox(-7.0F, -102.0F, 6.0F, 11.0F, 12.0F, 3.0F, 0.0F, false);
			bone33 = new ModelRenderer(this);
			bone33.setRotationPoint(13.1F, 90.25F, -14.9F);
			headBase.addChild(bone33);
			bone33.setTextureOffset(146, 156).addBox(-9.0F, -102.0F, 8.0F, 1.0F, 12.0F, 10.0F, 0.0F, false);
			bone34 = new ModelRenderer(this);
			bone34.setRotationPoint(13.1F, 90.25F, -14.9F);
			headBase.addChild(bone34);
			bone34.setTextureOffset(74, 155).addBox(-21.0F, -102.0F, 8.0F, 4.0F, 12.0F, 10.0F, 0.0F, false);
			bone35 = new ModelRenderer(this);
			bone35.setRotationPoint(-4.9F, -3.75F, -6.9F);
			headBase.addChild(bone35);
			setRotationAngle(bone35, 0.0F, -0.2618F, 0.0F);
			bone35.setTextureOffset(174, 94).addBox(-1.7412F, -3.0F, -5.0341F, 4.0F, 6.0F, 4.0F, 0.0F, false);
			bone36 = new ModelRenderer(this);
			bone36.setRotationPoint(2.1F, -3.75F, -8.9F);
			headBase.addChild(bone36);
			setRotationAngle(bone36, 0.0F, 0.3491F, 0.0F);
			bone36.setTextureOffset(169, 55).addBox(-2.342F, -3.0F, -3.0603F, 4.0F, 6.0F, 4.0F, 0.0F, false);
			bone38 = new ModelRenderer(this);
			bone38.setRotationPoint(0.1F, 88.25F, 0.1F);
			headBase.addChild(bone38);
			bone38.setTextureOffset(170, 24).addBox(5.0F, -97.0F, -5.0F, 1.0F, 6.0F, 6.0F, 0.0F, false);
			bone39 = new ModelRenderer(this);
			bone39.setRotationPoint(1.0F, 0.0F, 0.0F);
			bone38.addChild(bone39);
			bone39.setTextureOffset(0, 78).addBox(5.0F, -96.0F, -4.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
			bone40 = new ModelRenderer(this);
			bone40.setRotationPoint(-2.9F, 88.25F, 0.1F);
			headBase.addChild(bone40);
			bone40.setTextureOffset(70, 4).addBox(-6.0F, -97.0F, -5.0F, 1.0F, 6.0F, 6.0F, 0.0F, false);
			bone41 = new ModelRenderer(this);
			bone41.setRotationPoint(-1.0F, 0.0F, 0.0F);
			bone40.addChild(bone41);
			bone41.setTextureOffset(0, 0).addBox(-6.0F, -96.0F, -4.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
			bone37 = new ModelRenderer(this);
			bone37.setRotationPoint(0.1F, 88.25F, 0.1F);
			headBase.addChild(bone37);
			bone37.setTextureOffset(60, 0).addBox(-7.0F, -100.0F, -11.0F, 11.0F, 2.0F, 2.0F, 0.0F, false);
			sword = new ModelRenderer(this);
			sword.setRotationPoint(-0.0833F, -48.25F, 14.8333F);
			setRotationAngle(sword, 0.0F, 0.0F, -0.7854F);
			sword.setTextureOffset(102, 155).addBox(-2.9167F, 3.25F, 0.1667F, 5.0F, 27.0F, 4.0F, 0.0F, false);
			sword2 = new ModelRenderer(this);
			sword2.setRotationPoint(2.0833F, 26.25F, -14.8333F);
			sword.addChild(sword2);
			sword2.setTextureOffset(139, 46).addBox(-12.0F, -28.0F, 15.0F, 17.0F, 5.0F, 4.0F, 0.0F, false);
			sword3 = new ModelRenderer(this);
			sword3.setRotationPoint(2.0833F, 21.25F, -14.8333F);
			sword.addChild(sword3);
			sword3.setTextureOffset(62, 104).addBox(-7.0F, -57.0F, 15.0F, 8.0F, 34.0F, 4.0F, 0.0F, false);
			sword4 = new ModelRenderer(this);
			sword4.setRotationPoint(2.0833F, -12.75F, -14.8333F);
			sword.addChild(sword4);
			sword4.setTextureOffset(98, 86).addBox(-6.0F, -27.0F, 15.0F, 6.0F, 4.0F, 4.0F, 0.0F, false);
			sword5 = new ModelRenderer(this);
			sword5.setRotationPoint(13.0833F, 26.25F, -14.8333F);
			sword.addChild(sword5);
			sword5.setTextureOffset(21, 78).addBox(-6.0F, -27.0F, 15.0F, 6.0F, 4.0F, 4.0F, 0.0F, false);
			sword6 = new ModelRenderer(this);
			sword6.setRotationPoint(-9.9167F, 26.25F, -14.8333F);
			sword.addChild(sword6);
			sword6.setTextureOffset(72, 47).addBox(-6.0F, -27.0F, 15.0F, 6.0F, 4.0F, 4.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			bone.render(matrixStack, buffer, packedLight, packedOverlay);
			bone8.render(matrixStack, buffer, packedLight, packedOverlay);
			waistBase.render(matrixStack, buffer, packedLight, packedOverlay);
			spine.render(matrixStack, buffer, packedLight, packedOverlay);
			bone28.render(matrixStack, buffer, packedLight, packedOverlay);
			armBase.render(matrixStack, buffer, packedLight, packedOverlay);
			armBase4.render(matrixStack, buffer, packedLight, packedOverlay);
			headBase.render(matrixStack, buffer, packedLight, packedOverlay);
			sword.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.headBase.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.headBase.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.armBase.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.armBase4.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.bone.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.bone8.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		}
	}
}
