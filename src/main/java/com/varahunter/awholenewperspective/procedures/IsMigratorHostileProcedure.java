package com.varahunter.awholenewperspective.procedures;

import net.minecraft.world.IWorld;

import java.util.Map;

import com.varahunter.awholenewperspective.AWholeNewPerspectiveModVariables;
import com.varahunter.awholenewperspective.AWholeNewPerspectiveModElements;

@AWholeNewPerspectiveModElements.ModElement.Tag
public class IsMigratorHostileProcedure extends AWholeNewPerspectiveModElements.ModElement {
	public IsMigratorHostileProcedure(AWholeNewPerspectiveModElements instance) {
		super(instance, 73);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure IsMigratorHostile!");
			return false;
		}
		IWorld world = (IWorld) dependencies.get("world");
		return ((AWholeNewPerspectiveModVariables.MapVariables.get(world).migratorPersonalityType) == 3);
	}
}
