// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports

public static class ModelAmbulatory extends EntityModel<Entity> {
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone4;
	private final ModelRenderer bone3;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;

	public ModelAmbulatory() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(1.0833F, 16.75F, 3.25F);
		setRotationAngle(bone, 0.2618F, 0.0F, 0.0F);
		bone.setTextureOffset(0, 0).addBox(-5.0833F, -4.75F, -8.25F, 9.0F, 9.0F, 13.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(4.6667F, 1.0F, -9.0F);
		bone.addChild(bone2);
		setRotationAngle(bone2, -0.5672F, -0.6545F, 0.0F);
		bone2.setTextureOffset(0, 43).addBox(-1.75F, -1.75F, -12.25F, 3.0F, 3.0F, 13.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(-0.25F, -0.25F, -12.75F);
		bone2.addChild(bone4);
		setRotationAngle(bone4, 1.5708F, 0.0F, 0.0F);
		bone4.setTextureOffset(24, 25).addBox(-1.5F, -1.5F, -17.5F, 3.0F, 3.0F, 18.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-6.8333F, 1.0F, -8.0F);
		bone.addChild(bone3);
		setRotationAngle(bone3, -0.5672F, 0.6545F, 0.0F);
		bone3.setTextureOffset(31, 9).addBox(-1.25F, -1.75F, -12.25F, 3.0F, 3.0F, 13.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.25F, -0.25F, -12.75F);
		bone3.addChild(bone5);
		setRotationAngle(bone5, 1.5708F, 0.0F, 0.0F);
		bone5.setTextureOffset(0, 22).addBox(-1.5F, -1.5F, -17.5F, 3.0F, 3.0F, 18.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(-0.5833F, -4.75F, 0.75F);
		bone.addChild(bone6);
		setRotationAngle(bone6, -0.7418F, 0.0F, 0.0F);
		bone6.setTextureOffset(0, 22).addBox(-4.5F, -10.0F, 3.0F, 9.0F, 10.0F, 0.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-0.0833F, 5.25F, -1.25F);
		bone.addChild(bone8);
		bone8.setTextureOffset(3, 3).addBox(3.0F, -1.0F, 1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(-1.0833F, 5.25F, -1.25F);
		bone.addChild(bone9);
		bone9.setTextureOffset(0, 0).addBox(-4.0F, -1.0F, 1.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		bone.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}