package com.varahunter.awholenewperspective.procedures;

import java.util.Map;

import com.varahunter.awholenewperspective.AWholeNewPerspectiveModVariables;
import com.varahunter.awholenewperspective.AWholeNewPerspectiveModElements;

@AWholeNewPerspectiveModElements.ModElement.Tag
public class WasResponseButtonClickedProcedure extends AWholeNewPerspectiveModElements.ModElement {
	public WasResponseButtonClickedProcedure(AWholeNewPerspectiveModElements instance) {
		super(instance, 77);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		return ((AWholeNewPerspectiveModVariables.responseSelected) == (true));
	}
}
