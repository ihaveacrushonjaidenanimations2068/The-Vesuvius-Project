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
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		pulseCannon.render(matrixStack, buffer, packedLight, packedOverlay);
		emplacementStand.render(matrixStack, buffer, packedLight, packedOverlay);
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