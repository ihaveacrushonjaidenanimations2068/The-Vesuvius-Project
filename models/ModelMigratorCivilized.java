// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports

public static class ModelMigratorCivilized extends EntityModel<Entity> {
	private final ModelRenderer legLeft7;
	private final ModelRenderer legLeft8;
	private final ModelRenderer legLeft9;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer legLeft10;
	private final ModelRenderer legLeft11;
	private final ModelRenderer legLeft12;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone15;
	private final ModelRenderer bone13;
	private final ModelRenderer bone14;
	private final ModelRenderer bone19;
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer bone20;
	private final ModelRenderer bone22;
	private final ModelRenderer bone21;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;

	public ModelMigratorCivilized() {
		textureWidth = 128;
		textureHeight = 128;

		legLeft7 = new ModelRenderer(this);
		legLeft7.setRotationPoint(-5.5F, 4.25F, 8.75F);
		setRotationAngle(legLeft7, 0.0436F, 0.0F, 0.0F);
		legLeft7.setTextureOffset(57, 0).addBox(-5.5F, -1.25F, -2.75F, 6.0F, 3.0F, 3.0F, 0.0F, false);

		legLeft8 = new ModelRenderer(this);
		legLeft8.setRotationPoint(1.5F, 1.0672F, -10.3851F);
		legLeft7.addChild(legLeft8);
		setRotationAngle(legLeft8, -1.0036F, 0.0F, 0.0F);
		legLeft8.setTextureOffset(66, 97).addBox(-8.0F, -9.8471F, 1.9554F, 6.0F, 12.0F, 6.0F, 0.0F, false);

		legLeft9 = new ModelRenderer(this);
		legLeft9.setRotationPoint(-4.0F, 6.6828F, 8.6351F);
		legLeft8.addChild(legLeft9);
		setRotationAngle(legLeft9, 1.0036F, 0.0F, 0.0F);
		legLeft9.setTextureOffset(24, 79).addBox(-4.0F, -8.0F, 0.0F, 6.0F, 16.0F, 6.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-4.0F, 20.4F, -11.9F);
		legLeft7.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.4363F, 0.0F);
		bone.setTextureOffset(106, 30).addBox(-2.4063F, -1.5F, -4.9226F, 3.0F, 1.0F, 5.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-1.0F, 20.4F, -11.9F);
		legLeft7.addChild(bone2);
		setRotationAngle(bone2, 0.0F, -0.4363F, 0.0F);
		bone2.setTextureOffset(106, 24).addBox(-2.4063F, -1.5F, -4.0774F, 3.0F, 1.0F, 5.0F, 0.0F, false);

		legLeft10 = new ModelRenderer(this);
		legLeft10.setRotationPoint(1.0F, 4.1252F, 6.8859F);
		legLeft10.setTextureOffset(25, 0).addBox(0.0F, -1.1243F, -0.8423F, 6.0F, 3.0F, 3.0F, 0.0F, false);

		legLeft11 = new ModelRenderer(this);
		legLeft11.setRotationPoint(4.0F, 6.3824F, 0.5631F);
		legLeft10.addChild(legLeft11);
		setRotationAngle(legLeft11, -1.0036F, 0.0F, 0.0F);
		legLeft11.setTextureOffset(42, 97).addBox(-3.0F, -5.0738F, -7.5044F, 6.0F, 12.0F, 6.0F, 0.0F, false);

		legLeft12 = new ModelRenderer(this);
		legLeft12.setRotationPoint(1.0F, 11.4924F, -0.849F);
		legLeft11.addChild(legLeft12);
		setRotationAngle(legLeft12, 1.0036F, 0.0F, 0.0F);
		legLeft12.setTextureOffset(0, 79).addBox(-4.0F, -7.999F, 0.0436F, 6.0F, 16.0F, 6.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(3.7887F, 19.8248F, -9.6891F);
		legLeft10.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.4363F, 0.0F);
		bone3.setTextureOffset(106, 18).addBox(-3.0F, -0.999F, -5.2564F, 3.0F, 1.0F, 5.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(5.5F, 19.3257F, -8.9923F);
		legLeft10.addChild(bone4);
		setRotationAngle(bone4, 0.0F, -0.4363F, 0.0F);
		bone4.setTextureOffset(99, 63).addBox(-1.5F, -0.5F, -5.5F, 3.0F, 1.0F, 5.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-2.0F, -4.0F, 8.0F);
		bone5.setTextureOffset(90, 97).addBox(-3.0F, 5.0F, -3.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(2.0F, -2.0F, -8.0F);
		bone5.addChild(bone6);
		bone6.setTextureOffset(24, 101).addBox(-3.0F, -11.0F, 7.0F, 2.0F, 18.0F, 2.0F, 0.0F, false);

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(1.125F, -3.5F, 6.0F);
		bone6.addChild(bone15);
		bone15.setTextureOffset(102, 39).addBox(-0.375F, -2.0F, -2.0F, 3.0F, 5.0F, 4.0F, 0.0F, false);

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(2.625F, 1.0F, 0.0F);
		bone15.addChild(bone13);
		setRotationAngle(bone13, 0.0F, 0.0F, 0.4363F);
		bone13.setTextureOffset(90, 51).addBox(-2.75F, -2.5F, -2.0F, 13.0F, 4.0F, 4.0F, 0.0F, false);

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(9.75F, 0.0F, 0.0F);
		bone13.addChild(bone14);
		setRotationAngle(bone14, 0.0F, 0.6981F, 0.0F);
		bone14.setTextureOffset(48, 79).addBox(0.5F, -2.5F, -2.0F, 14.0F, 4.0F, 4.0F, 0.0F, false);

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(-9.75F, 0.0F, 0.0F);
		bone14.addChild(bone19);
		bone19.setTextureOffset(0, 70).addBox(24.0F, -8.0F, -0.5F, 24.0F, 9.0F, 0.0F, 0.0F, false);

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(-5.125F, -3.5F, 6.0F);
		bone6.addChild(bone16);
		bone16.setTextureOffset(90, 59).addBox(-2.625F, -2.0F, -2.0F, 3.0F, 5.0F, 4.0F, 0.0F, false);

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(-0.625F, 1.0F, 1.0F);
		bone16.addChild(bone17);
		setRotationAngle(bone17, 0.0F, 0.0F, -0.48F);
		bone17.setTextureOffset(80, 89).addBox(-12.25F, -2.5F, -3.0F, 13.0F, 4.0F, 4.0F, 0.0F, false);

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(-12.25F, -0.5F, -1.0F);
		bone17.addChild(bone18);
		setRotationAngle(bone18, 0.0F, -0.6545F, 0.0F);
		bone18.setTextureOffset(66, 43).addBox(-14.0F, -2.0F, -2.0F, 14.0F, 4.0F, 4.0F, 0.0F, false);

		bone20 = new ModelRenderer(this);
		bone20.setRotationPoint(12.25F, 0.5F, 1.0F);
		bone18.addChild(bone20);
		bone20.setTextureOffset(66, 0).addBox(-44.0F, -4.0F, -6.0F, 18.0F, 9.0F, 9.0F, 0.0F, false);

		bone22 = new ModelRenderer(this);
		bone22.setRotationPoint(0.0F, 14.0F, 0.0F);
		bone20.addChild(bone22);
		bone22.setTextureOffset(84, 79).addBox(-57.0F, -11.0F, -1.0F, 16.0F, 10.0F, 0.0F, 0.0F, false);

		bone21 = new ModelRenderer(this);
		bone21.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone20.addChild(bone21);
		bone21.setTextureOffset(48, 87).addBox(-57.0F, -11.0F, -1.0F, 16.0F, 10.0F, 0.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone6.addChild(bone7);
		bone7.setTextureOffset(0, 101).addBox(-5.0F, -6.0F, 3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, -13.0F, 0.0F);
		bone5.addChild(bone8);
		setRotationAngle(bone8, 0.4363F, 0.0F, 0.0F);
		bone8.setTextureOffset(32, 101).addBox(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, -7.0F, 0.0F);
		bone8.addChild(bone9);
		setRotationAngle(bone9, -0.1745F, 0.0F, 0.0F);
		bone9.setTextureOffset(48, 51).addBox(-5.0F, -9.7504F, -14.853F, 12.0F, 10.0F, 18.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(1.5F, -5.7504F, 3.647F);
		bone9.addChild(bone10);
		setRotationAngle(bone10, 0.0F, 0.0F, -0.3054F);
		bone10.setTextureOffset(34, 0).addBox(0.5F, -5.0F, -1.5F, 7.0F, 60.0F, 9.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(-2.0F, -5.7504F, 3.647F);
		bone9.addChild(bone11);
		setRotationAngle(bone11, 0.0F, 0.0F, 0.3054F);
		bone11.setTextureOffset(0, 0).addBox(-4.0F, -5.0F, -1.5F, 8.0F, 61.0F, 9.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(-2.4F, -9.5F, 3.0F);
		bone12.setTextureOffset(66, 18).addBox(-7.0F, -10.5F, -4.0F, 16.0F, 21.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		legLeft7.render(matrixStack, buffer, packedLight, packedOverlay);
		legLeft10.render(matrixStack, buffer, packedLight, packedOverlay);
		bone5.render(matrixStack, buffer, packedLight, packedOverlay);
		bone12.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.legLeft10.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.legLeft7.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.bone15.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.bone16.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.bone9.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.bone9.rotateAngleX = f4 / (180F / (float) Math.PI);
	}
}