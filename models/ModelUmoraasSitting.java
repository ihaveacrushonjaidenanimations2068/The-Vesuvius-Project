// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports

public static class ModelUmoraasSitting extends EntityModel<Entity> {
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

	public ModelUmoraasSitting() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(1.5F, 10.5F, 6.0F);
		setRotationAngle(bone, -1.0908F, 0.0F, 0.0F);
		bone.setTextureOffset(0, 0).addBox(-8.5F, -2.7628F, -23.9779F, 16.0F, 17.0F, 32.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(-1.0F, 4.567F, -16.073F);
		bone.addChild(bone4);
		setRotationAngle(bone4, 1.0472F, 0.3054F, 0.0F);
		bone4.setTextureOffset(0, 49).addBox(-8.0F, -12.2086F, -21.4691F, 17.0F, 17.0F, 19.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone2);
		bone2.setTextureOffset(53, 18).addBox(0.0F, -12.4414F, -23.962F, 0.0F, 9.0F, 31.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-0.5F, -19.8711F, -19.1976F);
		bone.addChild(bone3);
		setRotationAngle(bone3, 0.6981F, 0.0F, 0.0F);
		bone3.setTextureOffset(0, 41).addBox(0.5F, 2.7798F, -16.7271F, 0.0F, 11.0F, 8.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 12.0F, -5.5F);
		bone.addChild(bone5);
		setRotationAngle(bone5, 0.0F, -0.3927F, 0.7854F);
		bone5.setTextureOffset(64, 0).addBox(-6.5103F, -4.9833F, -9.9553F, 14.0F, 14.0F, 7.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(6.0495F, 1.8853F, -1.3036F);
		bone5.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.3054F, 0.0F);
		bone6.setTextureOffset(64, 26).addBox(2.5827F, 0.2411F, -5.0726F, 13.0F, 4.0F, 1.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(1.7132F, -12.3806F, -14.1964F);
		bone6.addChild(bone7);
		setRotationAngle(bone7, 0.0F, 0.48F, 0.0F);
		bone7.setTextureOffset(64, 21).addBox(7.7274F, 12.9046F, 14.4431F, 13.0F, 4.0F, 1.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0704F, 7.7949F, -3.5F);
		bone5.addChild(bone8);
		setRotationAngle(bone8, 0.0F, -0.2182F, -1.9199F);
		bone8.setTextureOffset(53, 63).addBox(-14.9163F, -1.5513F, -3.6649F, 13.0F, 4.0F, 1.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(11.2083F, -8.9759F, 2.5F);
		bone8.addChild(bone9);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(-8.6085F, 2.3564F, -15.5F);
		bone9.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -0.3927F, 0.0F);
		cube_r1.setTextureOffset(53, 58).addBox(-25.2128F, 5.3511F, 15.6694F, 13.0F, 4.0F, 1.0F, 0.0F, false);

		legLeft7 = new ModelRenderer(this);
		legLeft7.setRotationPoint(-7.5F, 16.25F, 8.75F);
		setRotationAngle(legLeft7, -0.8727F, 0.5672F, 0.0F);
		legLeft7.setTextureOffset(78, 65).addBox(-0.3508F, 0.0487F, -6.4506F, 6.0F, 3.0F, 3.0F, 0.0F, false);

		legLeft8 = new ModelRenderer(this);
		legLeft8.setRotationPoint(3.5F, -11.9328F, -10.3851F);
		legLeft7.addChild(legLeft8);
		setRotationAngle(legLeft8, -1.0036F, 0.0F, 0.0F);
		legLeft8.setTextureOffset(24, 85).addBox(-4.8508F, 0.9566F, 12.0265F, 6.0F, 12.0F, 6.0F, 0.0F, false);

		legLeft9 = new ModelRenderer(this);
		legLeft9.setRotationPoint(-4.0F, 6.6828F, 8.6351F);
		legLeft8.addChild(legLeft9);
		setRotationAngle(legLeft9, 1.0036F, 0.0F, 0.0F);
		legLeft9.setTextureOffset(72, 72).addBox(-0.8508F, 8.2987F, -3.7006F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		legLeft10 = new ModelRenderer(this);
		legLeft10.setRotationPoint(10.5F, 15.2395F, 5.2702F);
		setRotationAngle(legLeft10, -0.7418F, -0.4363F, 0.0F);
		legLeft10.setTextureOffset(0, 60).addBox(-6.1905F, 0.5785F, -6.469F, 6.0F, 3.0F, 3.0F, 0.0F, false);

		legLeft11 = new ModelRenderer(this);
		legLeft11.setRotationPoint(-1.5F, -6.8675F, -0.6365F);
		legLeft10.addChild(legLeft11);
		setRotationAngle(legLeft11, -1.0036F, 0.0F, 0.0F);
		legLeft11.setTextureOffset(0, 85).addBox(-3.6905F, 6.6629F, 1.5621F, 6.0F, 12.0F, 6.0F, 0.0F, false);

		legLeft12 = new ModelRenderer(this);
		legLeft12.setRotationPoint(1.0F, 11.4924F, -0.849F);
		legLeft11.addChild(legLeft12);
		setRotationAngle(legLeft12, 1.0036F, 0.0F, 0.0F);
		legLeft12.setTextureOffset(0, 0).addBox(-4.6905F, 7.9537F, -4.9836F, 6.0F, 24.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		bone.render(matrixStack, buffer, packedLight, packedOverlay);
		legLeft7.render(matrixStack, buffer, packedLight, packedOverlay);
		legLeft10.render(matrixStack, buffer, packedLight, packedOverlay);
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