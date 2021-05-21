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