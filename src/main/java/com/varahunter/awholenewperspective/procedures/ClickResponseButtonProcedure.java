package com.varahunter.awholenewperspective.procedures;

import net.minecraft.world.IWorld;

import java.util.Map;

import com.varahunter.awholenewperspective.AWholeNewPerspectiveModVariables;
import com.varahunter.awholenewperspective.AWholeNewPerspectiveModElements;

@AWholeNewPerspectiveModElements.ModElement.Tag
public class ClickResponseButtonProcedure extends AWholeNewPerspectiveModElements.ModElement {
	public ClickResponseButtonProcedure(AWholeNewPerspectiveModElements instance) {
		super(instance, 78);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure ClickResponseButton!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		AWholeNewPerspectiveModVariables.MapVariables.get(world).responseSelected = (boolean) (true);
		AWholeNewPerspectiveModVariables.MapVariables.get(world).syncData(world);
	}
}
