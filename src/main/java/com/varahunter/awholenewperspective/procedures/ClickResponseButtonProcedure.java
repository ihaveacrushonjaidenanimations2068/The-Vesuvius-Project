package com.varahunter.awholenewperspective.procedures;

import java.util.Map;

import com.varahunter.awholenewperspective.AWholeNewPerspectiveModVariables;
import com.varahunter.awholenewperspective.AWholeNewPerspectiveModElements;

@AWholeNewPerspectiveModElements.ModElement.Tag
public class ClickResponseButtonProcedure extends AWholeNewPerspectiveModElements.ModElement {
	public ClickResponseButtonProcedure(AWholeNewPerspectiveModElements instance) {
		super(instance, 78);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		AWholeNewPerspectiveModVariables.responseSelected = (boolean) (true);
	}
}
