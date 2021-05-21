package com.varahunter.awholenewperspective.procedures;

import net.minecraft.world.IWorld;

import java.util.Map;

import com.varahunter.awholenewperspective.AWholeNewPerspectiveModVariables;
import com.varahunter.awholenewperspective.AWholeNewPerspectiveModElements;

@AWholeNewPerspectiveModElements.ModElement.Tag
public class SetMigratorHostileProcedure extends AWholeNewPerspectiveModElements.ModElement {
	public SetMigratorHostileProcedure(AWholeNewPerspectiveModElements instance) {
		super(instance, 82);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure SetMigratorHostile!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		AWholeNewPerspectiveModVariables.MapVariables.get(world).migratorPersonalityType = (double) 3;
		AWholeNewPerspectiveModVariables.MapVariables.get(world).syncData(world);
	}
}
