// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports

public static class ModelTentacleTreeJuveenle extends EntityModel<Entity> {
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;

	public ModelTentacleTreeJuveenle() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.setTextureOffset(0, 24).addBox(-2.0F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, -12.0F, 0.0F);
		bone.addChild(bone2);
		setRotationAngle(bone2, 0.4363F, 0.0F, 0.0F);
		bone2.setTextureOffset(12, 12).addBox(-2.0F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -12.0F, 0.0F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, 0.5236F, 0.0F, 0.0F);
		bone3.setTextureOffset(0, 0).addBox(-2.0F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, -12.0F, -1.0F);
		bone3.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.7854F, 0.0F);
		bone4.setTextureOffset(12, 0).addBox(0.0F, -2.0F, -1.0F, 9.0F, 2.0F, 2.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, -12.0F, -1.0F);
		bone3.addChild(bone5);
		setRotationAngle(bone5, 0.0F, -0.7854F, 0.0F);
		bone5.setTextureOffset(16, 4).addBox(-8.0F, -2.0F, -1.0F, 8.0F, 2.0F, 2.0F, 0.0F, false);
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