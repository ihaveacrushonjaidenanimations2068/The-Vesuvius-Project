
package com.varahunter.awholenewperspective.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.wrapper.EntityHandsInvWrapper;
import net.minecraftforge.items.wrapper.EntityArmorInvWrapper;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.Direction;
import net.minecraft.util.DamageSource;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.IPacket;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import javax.annotation.Nullable;
import javax.annotation.Nonnull;

import io.netty.buffer.Unpooled;

import com.varahunter.awholenewperspective.gui.MigratorChatIntGui;
import com.varahunter.awholenewperspective.AWholeNewPerspectiveModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@AWholeNewPerspectiveModElements.ModElement.Tag
public class UmorasEntity extends AWholeNewPerspectiveModElements.ModElement {
	public static EntityType entity = null;
	public UmorasEntity(AWholeNewPerspectiveModElements instance) {
		super(instance, 85);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 3.5f)).build("umoras")
						.setRegistryName("umoras");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -10066330, -16724737, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("umoras_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelMigratorDefault(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("a_whole_new_perspective:textures/migrator_c_detail.png");
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
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof PlayerEntity)
				return false;
			return super.attackEntityFrom(source, amount);
		}
		private final ItemStackHandler inventory = new ItemStackHandler(9) {
			@Override
			public int getSlotLimit(int slot) {
				return 64;
			}
		};
		private final CombinedInvWrapper combined = new CombinedInvWrapper(inventory, new EntityHandsInvWrapper(this),
				new EntityArmorInvWrapper(this));
		@Override
		public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
			if (this.isAlive() && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && side == null)
				return LazyOptional.of(() -> combined).cast();
			return super.getCapability(capability, side);
		}

		@Override
		protected void dropInventory() {
			super.dropInventory();
			for (int i = 0; i < inventory.getSlots(); ++i) {
				ItemStack itemstack = inventory.getStackInSlot(i);
				if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
					this.entityDropItem(itemstack);
				}
			}
		}

		@Override
		public void writeAdditional(CompoundNBT compound) {
			super.writeAdditional(compound);
			compound.put("InventoryCustom", inventory.serializeNBT());
		}

		@Override
		public void readAdditional(CompoundNBT compound) {
			super.readAdditional(compound);
			INBT inventoryCustom = compound.get("InventoryCustom");
			if (inventoryCustom instanceof CompoundNBT)
				inventory.deserializeNBT((CompoundNBT) inventoryCustom);
		}

		@Override
		public boolean processInteract(PlayerEntity sourceentity, Hand hand) {
			ItemStack itemstack = sourceentity.getHeldItem(hand);
			boolean retval = true;
			if (sourceentity instanceof ServerPlayerEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) sourceentity, new INamedContainerProvider() {
					@Override
					public ITextComponent getDisplayName() {
						return new StringTextComponent("Umoras");
					}

					@Override
					public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
						PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
						packetBuffer.writeBlockPos(new BlockPos(sourceentity));
						packetBuffer.writeByte(0);
						packetBuffer.writeVarInt(CustomEntity.this.getEntityId());
						return new MigratorChatIntGui.GuiContainerMod(id, inventory, packetBuffer);
					}
				}, buf -> {
					buf.writeBlockPos(new BlockPos(sourceentity));
					buf.writeByte(0);
					buf.writeVarInt(this.getEntityId());
				});
			}
			super.processInteract(sourceentity, hand);
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			return retval;
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

	// Made with Blockbench 3.8.4
	// Exported for Minecraft version 1.15 - 1.16
	// Paste this class into your mod and generate all required imports
	public static class ModelMigratorDefault extends EntityModel<Entity> {
		private final ModelRenderer bone;
		private final ModelRenderer bone4;
		private final ModelRenderer bone2;
		private final ModelRenderer bone3;
		private final ModelRenderer bone5;
		private final ModelRenderer bone6;
		private final ModelRenderer bone7;
		private final ModelRenderer bone8;
		private final ModelRenderer bone9;
		private final ModelRenderer cube_r1;
		private final ModelRenderer legLeft7;
		private final ModelRenderer legLeft8;
		private final ModelRenderer legLeft9;
		private final ModelRenderer legLeft10;
		private final ModelRenderer legLeft11;
		private final ModelRenderer legLeft12;
		public ModelMigratorDefault() {
			textureWidth = 128;
			textureHeight = 128;
			bone = new ModelRenderer(this);
			bone.setRotationPoint(1.5F, 10.5F, 6.0F);
			setRotationAngle(bone, -0.6545F, 0.0F, 0.0F);
			bone.setTextureOffset(0, 0).addBox(-8.5F, -22.8411F, -33.7497F, 16.0F, 17.0F, 32.0F, 0.0F, false);
			bone4 = new ModelRenderer(this);
			bone4.setRotationPoint(-1.0F, -13.433F, -6.073F);
			bone.addChild(bone4);
			setRotationAngle(bone4, 0.6545F, 0.0F, 0.0F);
			bone4.setTextureOffset(0, 49).addBox(-8.0F, -24.1922F, -36.2769F, 17.0F, 17.0F, 19.0F, 0.0F, false);
			bone2 = new ModelRenderer(this);
			bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
			bone.addChild(bone2);
			bone2.setTextureOffset(53, 18).addBox(0.0F, -32.5197F, -33.7338F, 0.0F, 9.0F, 31.0F, 0.0F, false);
			bone3 = new ModelRenderer(this);
			bone3.setRotationPoint(-0.5F, -19.8711F, -19.1976F);
			bone.addChild(bone3);
			setRotationAngle(bone3, 0.6981F, 0.0F, 0.0F);
			bone3.setTextureOffset(0, 41).addBox(0.5F, -18.8822F, -11.3066F, 0.0F, 11.0F, 8.0F, 0.0F, false);
			bone5 = new ModelRenderer(this);
			bone5.setRotationPoint(0.0F, -6.0F, -5.5F);
			bone.addChild(bone5);
			setRotationAngle(bone5, 0.0F, 0.0F, 0.7854F);
			bone5.setTextureOffset(64, 0).addBox(-6.4529F, -6.4529F, -19.2924F, 14.0F, 14.0F, 7.0F, 0.0F, false);
			bone6 = new ModelRenderer(this);
			bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
			bone5.addChild(bone6);
			bone6.setTextureOffset(64, 26).addBox(7.4853F, 0.6569F, -16.0F, 13.0F, 4.0F, 1.0F, 0.0F, false);
			bone7 = new ModelRenderer(this);
			bone7.setRotationPoint(20.4907F, 2.2326F, -15.5F);
			bone6.addChild(bone7);
			setRotationAngle(bone7, 0.0F, 0.48F, 0.0F);
			bone7.setTextureOffset(64, 21).addBox(-0.136F, -1.2929F, -0.5F, 13.0F, 4.0F, 1.0F, 0.0F, false);
			bone8 = new ModelRenderer(this);
			bone8.setRotationPoint(0.7983F, 9.5228F, -16.5F);
			bone5.addChild(bone8);
			setRotationAngle(bone8, 0.0F, 0.0F, -1.9199F);
			bone8.setTextureOffset(53, 63).addBox(-10.8485F, -1.0878F, -0.5F, 13.0F, 4.0F, 1.0F, 0.0F, false);
			bone9 = new ModelRenderer(this);
			bone9.setRotationPoint(-3.2326F, -1.4618F, 15.5F);
			bone8.addChild(bone9);
			cube_r1 = new ModelRenderer(this);
			cube_r1.setRotationPoint(-8.6085F, 2.3564F, -15.5F);
			bone9.addChild(cube_r1);
			setRotationAngle(cube_r1, 0.0F, -0.3927F, 0.0F);
			cube_r1.setTextureOffset(53, 58).addBox(-11.8768F, -1.6995F, -0.5F, 13.0F, 4.0F, 1.0F, 0.0F, false);
			legLeft7 = new ModelRenderer(this);
			legLeft7.setRotationPoint(-7.5F, -4.75F, 8.75F);
			legLeft7.setTextureOffset(78, 65).addBox(-2.5F, -2.25F, -2.75F, 6.0F, 3.0F, 3.0F, 0.0F, false);
			legLeft8 = new ModelRenderer(this);
			legLeft8.setRotationPoint(3.5F, 9.0672F, -10.3851F);
			legLeft7.addChild(legLeft8);
			setRotationAngle(legLeft8, -1.0036F, 0.0F, 0.0F);
			legLeft8.setTextureOffset(24, 85).addBox(-7.0F, -14.6828F, -5.6351F, 6.0F, 12.0F, 6.0F, 0.0F, false);
			legLeft9 = new ModelRenderer(this);
			legLeft9.setRotationPoint(-4.0F, 6.6828F, 8.6351F);
			legLeft8.addChild(legLeft9);
			setRotationAngle(legLeft9, 1.0036F, 0.0F, 0.0F);
			legLeft9.setTextureOffset(72, 72).addBox(-3.0F, -15.0F, 0.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);
			legLeft10 = new ModelRenderer(this);
			legLeft10.setRotationPoint(10.5F, -6.25F, 4.75F);
			setRotationAngle(legLeft10, 0.0436F, 0.0F, 0.0F);
			legLeft10.setTextureOffset(0, 60).addBox(-4.5F, -0.75F, -0.75F, 6.0F, 3.0F, 3.0F, 0.0F, false);
			legLeft11 = new ModelRenderer(this);
			legLeft11.setRotationPoint(-1.5F, 14.7576F, 0.099F);
			legLeft10.addChild(legLeft11);
			setRotationAngle(legLeft11, -1.0036F, 0.0F, 0.0F);
			legLeft11.setTextureOffset(0, 85).addBox(-2.0F, -9.8732F, -15.1191F, 6.0F, 12.0F, 6.0F, 0.0F, false);
			legLeft12 = new ModelRenderer(this);
			legLeft12.setRotationPoint(1.0F, 11.4924F, -0.849F);
			legLeft11.addChild(legLeft12);
			setRotationAngle(legLeft12, 1.0036F, 0.0F, 0.0F);
			legLeft12.setTextureOffset(0, 0).addBox(-3.0F, -15.0F, 0.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			bone.render(matrixStack, buffer, packedLight, packedOverlay);
			legLeft7.render(matrixStack, buffer, packedLight, packedOverlay);
			legLeft10.render(matrixStack, buffer, packedLight, packedOverlay);
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
