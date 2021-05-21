package com.varahunter.awholenewperspective.procedures;

import net.minecraft.world.IWorld;

import java.util.Map;

import com.varahunter.awholenewperspective.AWholeNewPerspectiveModVariables;
import com.varahunter.awholenewperspective.AWholeNewPerspectiveModElements;

@AWholeNewPerspectiveModElements.ModElement.Tag
public class IsMigratorIndignantProcedure extends AWholeNewPerspectiveModElements.ModElement {
	public IsMigratorIndignantProcedure(AWholeNewPerspectiveModElements instance) {
		super(instance, 74);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure IsMigratorIndignant!");
			return false;
		}
		IWorld world = (IWorld) dependencies.get("world");
		return ((AWholeNewPerspectiveModVariables.MapVariables.get(world).migratorPersonalityType) == 2);
	}
}
