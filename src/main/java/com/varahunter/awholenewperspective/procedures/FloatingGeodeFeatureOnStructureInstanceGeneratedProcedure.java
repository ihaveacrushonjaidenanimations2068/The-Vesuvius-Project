package com.varahunter.awholenewperspective.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import com.varahunter.awholenewperspective.AWholeNewPerspectiveModElements;

@AWholeNewPerspectiveModElements.ModElement.Tag
public class FloatingGeodeFeatureOnStructureInstanceGeneratedProcedure extends AWholeNewPerspectiveModElements.ModElement {
	public FloatingGeodeFeatureOnStructureInstanceGeneratedProcedure(AWholeNewPerspectiveModElements instance) {
		super(instance, 33);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure FloatingGeodeFeatureOnStructureInstanceGenerated!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity && !entity.world.isRemote) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("[DEBUG]: Generating structure"), (false));
		}
	}
}
