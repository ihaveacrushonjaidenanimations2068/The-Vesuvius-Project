
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
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.monster.MonsterEntity;
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
public class ResistanceConstructEntity extends AWholeNewPerspectiveModElements.ModElement {
	public static EntityType entity = null;
	public ResistanceConstructEntity(AWholeNewPerspectiveModElements instance) {
		super(instance, 58);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("resistance_construct")
						.setRegistryName("resistance_construct");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -16763956, -16711681, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("resistance_construct_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelResistanceConstruct(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("a_whole_new_perspective:textures/modelresistanceconstruct.png");
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
			setNoAI(true);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
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

	// Made with Blockbench 3.7.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class ModelResistanceConstruct extends EntityModel<Entity> {
		private final ModelRenderer bone;
		private final ModelRenderer head;
		private final ModelRenderer head2;
		private final ModelRenderer head9;
		private final ModelRenderer head3;
		private final ModelRenderer head6;
		private final ModelRenderer head7;
		private final ModelRenderer head8;
		private final ModelRenderer cube_r1;
		private final ModelRenderer head4;
		private final ModelRenderer head5;
		private final ModelRenderer armRight;
		private final ModelRenderer bone2;
		private final ModelRenderer armRight2;
		private final ModelRenderer bone3;
		private final ModelRenderer armRight3;
		private final ModelRenderer bone4;
		private final ModelRenderer armRight4;
		private final ModelRenderer bone5;
		private final ModelRenderer thruster;
		private final ModelRenderer bone6;
		private final ModelRenderer thruster2;
		private final ModelRenderer bone7;
		public ModelResistanceConstruct() {
			textureWidth = 128;
			textureHeight = 128;
			bone = new ModelRenderer(this);
			bone.setRotationPoint(0.0F, -3.0F, 0.0F);
			bone.setTextureOffset(0, 0).addBox(-5.0F, -6.0F, -5.0F, 11.0F, 11.0F, 11.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 0.0F, 0.0F);
			bone.addChild(head);
			head.setTextureOffset(0, 22).addBox(-4.0F, -5.0F, -10.0F, 9.0F, 9.0F, 5.0F, 0.0F, false);
			head2 = new ModelRenderer(this);
			head2.setRotationPoint(9.0F, 0.0F, 0.0F);
			bone.addChild(head2);
			head2.setTextureOffset(60, 9).addBox(-4.0F, -4.0F, -8.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
			head9 = new ModelRenderer(this);
			head9.setRotationPoint(-6.0F, 0.0F, 0.0F);
			bone.addChild(head9);
			head9.setTextureOffset(60, 0).addBox(-4.0F, -4.0F, -8.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
			head3 = new ModelRenderer(this);
			head3.setRotationPoint(4.5F, -4.5F, -6.5F);
			bone.addChild(head3);
			setRotationAngle(head3, 0.0F, 0.0F, 0.6545F);
			head3.setTextureOffset(44, 16).addBox(0.5F, -13.5F, -1.5F, 5.0F, 13.0F, 3.0F, 0.0F, false);
			head6 = new ModelRenderer(this);
			head6.setRotationPoint(5.5F, 4.5F, -6.5F);
			bone.addChild(head6);
			setRotationAngle(head6, 0.0F, 0.0F, -0.6109F);
			head6.setTextureOffset(0, 55).addBox(-0.5F, -0.5F, -1.5F, 5.0F, 9.0F, 3.0F, 0.0F, false);
			head7 = new ModelRenderer(this);
			head7.setRotationPoint(-3.5F, 4.0F, -6.5F);
			bone.addChild(head7);
			setRotationAngle(head7, 0.0F, 0.0F, 0.6109F);
			head7.setTextureOffset(52, 32).addBox(-5.5F, 0.0F, -1.5F, 5.0F, 10.0F, 3.0F, 0.0F, false);
			head8 = new ModelRenderer(this);
			head8.setRotationPoint(-8.0F, -7.0F, 0.0F);
			bone.addChild(head8);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(3.5F, 2.5F, -6.5F);
			head8.addChild(cube_r1);
			setRotationAngle(cube_r1, 0.0F, 0.0F, -0.6981F);
			cube_r1.setTextureOffset(44, 0).addBox(-4.5F, -13.5F, -1.5F, 5.0F, 13.0F, 3.0F, 0.0F, false);
			head4 = new ModelRenderer(this);
			head4.setRotationPoint(1.0F, -7.0F, 0.0F);
			bone.addChild(head4);
			head4.setTextureOffset(56, 45).addBox(-4.0F, -4.0F, -8.0F, 7.0F, 6.0F, 3.0F, 0.0F, false);
			head5 = new ModelRenderer(this);
			head5.setRotationPoint(1.0F, 8.0F, 0.0F);
			bone.addChild(head5);
			head5.setTextureOffset(56, 56).addBox(-4.0F, -4.0F, -8.0F, 7.0F, 6.0F, 3.0F, 0.0F, false);
			armRight = new ModelRenderer(this);
			armRight.setRotationPoint(0.0F, 24.0F, 0.0F);
			bone2 = new ModelRenderer(this);
			bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
			armRight.addChild(bone2);
			bone2.setTextureOffset(40, 43).addBox(6.0F, -32.0F, 0.0F, 4.0F, 15.0F, 4.0F, 0.0F, false);
			armRight2 = new ModelRenderer(this);
			armRight2.setRotationPoint(8.0F, -17.0F, 2.0F);
			armRight.addChild(armRight2);
			setRotationAngle(armRight2, -0.7418F, 0.0F, 0.0F);
			bone3 = new ModelRenderer(this);
			bone3.setRotationPoint(-8.0F, 32.0F, -2.0F);
			armRight2.addChild(bone3);
			bone3.setTextureOffset(16, 43).addBox(6.0F, -32.0F, 0.0F, 4.0F, 15.0F, 4.0F, 0.0F, false);
			armRight3 = new ModelRenderer(this);
			armRight3.setRotationPoint(1.0F, 24.0F, 0.0F);
			bone4 = new ModelRenderer(this);
			bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
			armRight3.addChild(bone4);
			bone4.setTextureOffset(0, 36).addBox(-10.0F, -32.0F, 0.0F, 4.0F, 15.0F, 4.0F, 0.0F, false);
			armRight4 = new ModelRenderer(this);
			armRight4.setRotationPoint(-8.0F, -17.0F, 2.0F);
			armRight3.addChild(armRight4);
			setRotationAngle(armRight4, -0.7418F, 0.0F, 0.0F);
			bone5 = new ModelRenderer(this);
			bone5.setRotationPoint(8.0F, 32.0F, -2.0F);
			armRight4.addChild(bone5);
			bone5.setTextureOffset(28, 28).addBox(-10.0F, -32.0F, 0.0F, 4.0F, 15.0F, 4.0F, 0.0F, false);
			thruster = new ModelRenderer(this);
			thruster.setRotationPoint(-2.0F, 2.0F, 3.0F);
			setRotationAngle(thruster, 0.3927F, 0.0F, 0.0F);
			bone6 = new ModelRenderer(this);
			bone6.setRotationPoint(2.0F, 22.0F, -3.0F);
			thruster.addChild(bone6);
			bone6.setTextureOffset(60, 18).addBox(-4.0F, -22.0F, 1.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
			thruster2 = new ModelRenderer(this);
			thruster2.setRotationPoint(4.0F, 3.0F, 3.0F);
			setRotationAngle(thruster2, 0.4363F, 0.0F, 0.0F);
			bone7 = new ModelRenderer(this);
			bone7.setRotationPoint(1.0F, 21.0F, -3.0F);
			thruster2.addChild(bone7);
			bone7.setTextureOffset(28, 58).addBox(-4.0F, -22.0F, 1.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
		}

		@Override
		public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			// previously the render function, render code was moved to a method below
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			bone.render(matrixStack, buffer, packedLight, packedOverlay);
			armRight.render(matrixStack, buffer, packedLight, packedOverlay);
			armRight3.render(matrixStack, buffer, packedLight, packedOverlay);
			thruster.render(matrixStack, buffer, packedLight, packedOverlay);
			thruster2.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	}
}
