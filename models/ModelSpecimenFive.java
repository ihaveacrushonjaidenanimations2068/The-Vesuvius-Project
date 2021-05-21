// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports

public static class ModelSpecimenFive extends EntityModel<Entity> {
	private final ModelRenderer head;
	private final ModelRenderer head_r1;
	private final ModelRenderer body;
	private final ModelRenderer body_r1;
	private final ModelRenderer leftArm;
	private final ModelRenderer leftArm_r1;
	private final ModelRenderer leftArm2;
	private final ModelRenderer leftArm_r2;
	private final ModelRenderer rightArm;
	private final ModelRenderer rightArm_r1;
	private final ModelRenderer rightArm2;
	private final ModelRenderer rightArm_r2;
	private final ModelRenderer leftLeg;
	private final ModelRenderer leftLeg_r1;
	private final ModelRenderer leftLeg2;
	private final ModelRenderer leftLeg_r2;
	private final ModelRenderer rightLeg;
	private final ModelRenderer rightLeg_r1;
	private final ModelRenderer rightLeg2;
	private final ModelRenderer rightLeg_r2;
	private final ModelRenderer hookHead;
	private final ModelRenderer head_r2;
	private final ModelRenderer hookHead2;
	private final ModelRenderer head_r3;

	public ModelSpecimenFive() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -6.0F, -2.0F);

		head_r1 = new ModelRenderer(this);
		head_r1.setRotationPoint(-1.0F, 28.0F, 8.0F);
		head.addChild(head_r1);
		setRotationAngle(head_r1, -3.1416F, 0.0F, 3.1416F);
		head_r1.setTextureOffset(0, 16).addBox(-4.0F, -30.0F, 8.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);

		body_r1 = new ModelRenderer(this);
		body_r1.setRotationPoint(0.0F, 12.0F, 0.0F);
		body.addChild(body_r1);
		setRotationAngle(body_r1, -3.1416F, 0.0F, 3.1416F);
		body_r1.setTextureOffset(0, 0).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(-4.0F, -10.0F, 0.0F);

		leftArm_r1 = new ModelRenderer(this);
		leftArm_r1.setRotationPoint(4.0F, 22.0F, 0.0F);
		leftArm.addChild(leftArm_r1);
		setRotationAngle(leftArm_r1, -3.1416F, 0.0F, 3.1416F);
		leftArm_r1.setTextureOffset(16, 32).addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		leftArm2 = new ModelRenderer(this);
		leftArm2.setRotationPoint(-1.0F, 24.0F, 0.0F);
		leftArm.addChild(leftArm2);

		leftArm_r2 = new ModelRenderer(this);
		leftArm_r2.setRotationPoint(5.0F, 10.0F, 0.0F);
		leftArm2.addChild(leftArm_r2);
		setRotationAngle(leftArm_r2, -3.1416F, 0.0F, 3.1416F);
		leftArm_r2.setTextureOffset(32, 32).addBox(-7.0F, -24.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(5.0F, -10.0F, 0.0F);

		rightArm_r1 = new ModelRenderer(this);
		rightArm_r1.setRotationPoint(-5.0F, 22.0F, 0.0F);
		rightArm.addChild(rightArm_r1);
		setRotationAngle(rightArm_r1, -3.1416F, 0.0F, 3.1416F);
		rightArm_r1.setTextureOffset(0, 28).addBox(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		rightArm2 = new ModelRenderer(this);
		rightArm2.setRotationPoint(0.0F, 24.0F, 0.0F);
		rightArm.addChild(rightArm2);

		rightArm_r2 = new ModelRenderer(this);
		rightArm_r2.setRotationPoint(-5.0F, 10.0F, 0.0F);
		rightArm2.addChild(rightArm_r2);
		setRotationAngle(rightArm_r2, -3.1416F, 0.0F, 3.1416F);
		rightArm_r2.setTextureOffset(40, 0).addBox(5.0F, -24.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(-1.9F, 0.0F, 0.0F);

		leftLeg_r1 = new ModelRenderer(this);
		leftLeg_r1.setRotationPoint(1.9F, 12.0F, 0.0F);
		leftLeg.addChild(leftLeg_r1);
		setRotationAngle(leftLeg_r1, -3.1416F, 0.0F, 3.1416F);
		leftLeg_r1.setTextureOffset(24, 16).addBox(-3.9F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		leftLeg2 = new ModelRenderer(this);
		leftLeg2.setRotationPoint(0.0F, 24.0F, 0.0F);
		leftLeg.addChild(leftLeg2);

		leftLeg_r2 = new ModelRenderer(this);
		leftLeg_r2.setRotationPoint(1.9F, 0.0F, 0.0F);
		leftLeg2.addChild(leftLeg_r2);
		setRotationAngle(leftLeg_r2, -3.1416F, 0.0F, 3.1416F);
		leftLeg_r2.setTextureOffset(40, 14).addBox(-2.9F, -12.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(1.9F, 0.0F, 0.0F);

		rightLeg_r1 = new ModelRenderer(this);
		rightLeg_r1.setRotationPoint(-1.9F, 12.0F, 0.0F);
		rightLeg.addChild(rightLeg_r1);
		setRotationAngle(rightLeg_r1, -3.1416F, 0.0F, 3.1416F);
		rightLeg_r1.setTextureOffset(24, 0).addBox(-0.1F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		rightLeg2 = new ModelRenderer(this);
		rightLeg2.setRotationPoint(0.0F, 24.0F, 0.0F);
		rightLeg.addChild(rightLeg2);

		rightLeg_r2 = new ModelRenderer(this);
		rightLeg_r2.setRotationPoint(-1.9F, 0.0F, 0.0F);
		rightLeg2.addChild(rightLeg_r2);
		setRotationAngle(rightLeg_r2, -3.1416F, 0.0F, 3.1416F);
		rightLeg_r2.setTextureOffset(40, 28).addBox(0.9F, -12.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

		hookHead = new ModelRenderer(this);
		hookHead.setRotationPoint(0.5F, -12.75F, 0.0F);
		setRotationAngle(hookHead, -0.0873F, 0.0F, 0.0F);

		head_r2 = new ModelRenderer(this);
		head_r2.setRotationPoint(0.5F, 24.602F, 4.3907F);
		hookHead.addChild(head_r2);
		setRotationAngle(head_r2, 2.9671F, 0.0F, 3.1416F);
		head_r2.setTextureOffset(40, 42).addBox(0.0F, -35.0F, -1.0F, 2.0F, 11.0F, 2.0F, 0.0F, false);

		hookHead2 = new ModelRenderer(this);
		hookHead2.setRotationPoint(1.5F, -9.898F, 4.3907F);
		hookHead.addChild(hookHead2);
		setRotationAngle(hookHead2, -0.3491F, 0.0F, 0.0F);

		head_r3 = new ModelRenderer(this);
		head_r3.setRotationPoint(-1.0F, 23.5F, 0.0F);
		hookHead2.addChild(head_r3);
		setRotationAngle(head_r3, 2.2689F, 0.0F, -3.1416F);
		head_r3.setTextureOffset(0, 44).addBox(0.0F, -23.0F, -14.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
		rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		hookHead.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.rightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.leftLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.rightLeg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.leftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.hookHead.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}