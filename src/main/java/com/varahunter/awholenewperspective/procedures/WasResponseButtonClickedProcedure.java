package com.varahunter.awholenewperspective.procedures;

import net.minecraft.world.IWorld;

import java.util.Map;

import com.varahunter.awholenewperspective.AWholeNewPerspectiveModVariables;
import com.varahunter.awholenewperspective.AWholeNewPerspectiveModElements;

@AWholeNewPerspectiveModElements.ModElement.Tag
public class WasResponseButtonClickedProcedure extends AWholeNewPerspectiveModElements.ModElement {
	public WasResponseButtonClickedProcedure(AWholeNewPerspectiveModElements instance) {
		super(instance, 77);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure WasResponseButtonClicked!");
			return false;
		}
		IWorld world = (IWorld) dependencies.get("world");
		return ((AWholeNewPerspectiveModVariables.MapVariables.get(world).responseSelected) == (true));
	}
}
