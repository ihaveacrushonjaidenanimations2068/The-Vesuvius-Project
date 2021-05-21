
package com.varahunter.awholenewperspective.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
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
public class DazedViperPlantEntity extends AWholeNewPerspectiveModElements.ModElement {
	public static EntityType entity = null;
	public DazedViperPlantEntity(AWholeNewPerspectiveModElements instance) {
		super(instance, 21);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("dazed_viper_plant")
						.setRegistryName("dazed_viper_plant");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -6750055, -65281, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("dazed_viper_plant_spawn_egg"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("a_whole_new_perspective:oneiric_rainforest")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(entity, 20, 1, 1));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::canMonsterSpawn);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelDazedViperPlant(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("a_whole_new_perspective:textures/viper_detail.png");
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
	}

	// Made with Blockbench 3.5.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class ModelDazedViperPlant extends EntityModel<Entity> {
		private final ModelRenderer bone;
		private final ModelRenderer bone2;
		private final ModelRenderer bone3;
		private final ModelRenderer bone4;
		private final ModelRenderer bone6;
		private final ModelRenderer bone7;
		private final ModelRenderer bone5;
		private final ModelRenderer bone8;
		private final ModelRenderer bone9;
		private final ModelRenderer bone10;
		private final ModelRenderer bone11;
		private final ModelRenderer bone12;
		private final ModelRenderer bone15;
		private final ModelRenderer bone13;
		private final ModelRenderer bone14;
		public ModelDazedViperPlant() {
			textureWidth = 128;
			textureHeight = 128;
			bone = new ModelRenderer(this);
			bone.setRotationPoint(0.0F, 24.0F, 0.0F);
			bone.setTextureOffset(44, 44).addBox(-1.0F, -7.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
			bone2 = new ModelRenderer(this);
			bone2.setRotationPoint(0.0F, -7.0F, 0.0F);
			bone.addChild(bone2);
			bone2.setTextureOffset(39, 15).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
			bone3 = new ModelRenderer(this);
			bone3.setRotationPoint(-2.0F, -2.0F, 0.0F);
			bone2.addChild(bone3);
			setRotationAngle(bone3, 0.0F, 0.0F, 0.4363F);
			bone3.setTextureOffset(36, 36).addBox(-10.0F, 0.0F, -3.0F, 10.0F, 0.0F, 6.0F, 0.0F, false);
			bone4 = new ModelRenderer(this);
			bone4.setRotationPoint(2.0F, -2.0F, 0.0F);
			bone2.addChild(bone4);
			setRotationAngle(bone4, 0.0F, 0.0F, -0.4363F);
			bone4.setTextureOffset(16, 31).addBox(0.0F, 0.0F, -3.0F, 10.0F, 0.0F, 6.0F, 0.0F, false);
			bone6 = new ModelRenderer(this);
			bone6.setRotationPoint(0.0F, -2.0F, 2.0F);
			bone2.addChild(bone6);
			setRotationAngle(bone6, 0.5236F, 0.0F, 0.0F);
			bone6.setTextureOffset(0, 26).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 0.0F, 10.0F, 0.0F, false);
			bone7 = new ModelRenderer(this);
			bone7.setRotationPoint(0.0F, -2.0F, -2.0F);
			bone2.addChild(bone7);
			setRotationAngle(bone7, -0.4363F, 0.0F, 0.0F);
			bone7.setTextureOffset(21, 9).addBox(-3.0F, 0.0F, -10.0F, 6.0F, 0.0F, 10.0F, 0.0F, false);
			bone5 = new ModelRenderer(this);
			bone5.setRotationPoint(0.0F, -9.5F, 0.0F);
			bone.addChild(bone5);
			bone5.setTextureOffset(0, 20).addBox(-1.0F, -7.5F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
			bone8 = new ModelRenderer(this);
			bone8.setRotationPoint(0.0F, -7.5F, 0.0F);
			bone5.addChild(bone8);
			setRotationAngle(bone8, 0.6109F, 0.0F, 0.0F);
			bone8.setTextureOffset(0, 10).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
			bone9 = new ModelRenderer(this);
			bone9.setRotationPoint(0.0F, -8.0F, 0.0F);
			bone8.addChild(bone9);
			setRotationAngle(bone9, 0.9599F, 0.0F, 0.0F);
			bone9.setTextureOffset(0, 0).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
			bone10 = new ModelRenderer(this);
			bone10.setRotationPoint(1.0F, -8.0F, 0.0F);
			bone9.addChild(bone10);
			bone10.setTextureOffset(22, 38).addBox(-5.0F, -11.0F, -1.0F, 7.0F, 11.0F, 4.0F, 0.0F, false);
			bone11 = new ModelRenderer(this);
			bone11.setRotationPoint(-1.0F, -8.0F, -1.0F);
			bone9.addChild(bone11);
			setRotationAngle(bone11, 0.4363F, 0.0F, 0.0F);
			bone11.setTextureOffset(0, 36).addBox(-3.0F, -11.0F, -4.0F, 7.0F, 11.0F, 4.0F, 0.0F, false);
			bone12 = new ModelRenderer(this);
			bone12.setRotationPoint(-0.5F, -7.0F, 7.5F);
			bone9.addChild(bone12);
			bone12.setTextureOffset(0, 13).addBox(-4.5F, 0.0F, -6.5F, 9.0F, 0.0F, 13.0F, 0.0F, false);
			bone15 = new ModelRenderer(this);
			bone15.setRotationPoint(0.0F, 0.0F, -13.0F);
			bone9.addChild(bone15);
			bone15.setTextureOffset(0, 0).addBox(-5.0F, -7.0F, 1.0F, 9.0F, 0.0F, 13.0F, 0.0F, false);
			bone13 = new ModelRenderer(this);
			bone13.setRotationPoint(-6.0F, 0.0F, -5.0F);
			bone9.addChild(bone13);
			bone13.setTextureOffset(22, 0).addBox(-9.0F, -7.0F, 1.0F, 13.0F, 0.0F, 9.0F, 0.0F, false);
			bone14 = new ModelRenderer(this);
			bone14.setRotationPoint(11.0F, 0.0F, -5.0F);
			bone9.addChild(bone14);
			bone14.setTextureOffset(22, 22).addBox(-9.0F, -7.0F, 1.0F, 13.0F, 0.0F, 9.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			bone.render(matrixStack, buffer, packedLight, packedOverlay);
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
