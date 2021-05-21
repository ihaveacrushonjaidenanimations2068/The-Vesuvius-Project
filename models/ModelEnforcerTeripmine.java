// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelEnforcerTeripmine extends EntityModel<Entity> {
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;

	public ModelEnforcerTeripmine() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.setTextureOffset(0, 0).addBox(-4.0F, -3.0F, -4.0F, 8.0F, 3.0F, 8.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(5.0F, 0.0F, 0.0F);
		bone.addChild(bone2);
		bone2.setTextureOffset(16, 16).addBox(-1.0F, -1.0F, -4.0F, 4.0F, 1.0F, 8.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-8.0F, 0.0F, 0.0F);
		bone.addChild(bone3);
		bone3.setTextureOffset(0, 11).addBox(0.0F, -1.0F, -4.0F, 4.0F, 1.0F, 8.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(-4.0F, 0.0F, -8.0F);
		bone.addChild(bone4);
		bone4.setTextureOffset(24, 0).addBox(0.0F, -1.0F, 0.0F, 8.0F, 1.0F, 4.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-4.0F, 0.0F, 4.0F);
		bone.addChild(bone5);
		bone5.setTextureOffset(16, 11).addBox(0.0F, -1.0F, 0.0F, 8.0F, 1.0F, 4.0F, 0.0F, false);
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