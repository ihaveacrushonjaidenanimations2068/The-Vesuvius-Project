package com.varahunter.awholenewperspective.procedures;

import net.minecraftforge.fml.server.ServerLifecycleHooks;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.server.MinecraftServer;

import java.util.Map;

import com.varahunter.awholenewperspective.AWholeNewPerspectiveModElements;

@AWholeNewPerspectiveModElements.ModElement.Tag
public class NYIMigPProcedure extends AWholeNewPerspectiveModElements.ModElement {
	public NYIMigPProcedure(AWholeNewPerspectiveModElements instance) {
		super(instance, 84);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		{
			MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
			if (mcserv != null)
				mcserv.getPlayerList().sendMessage(new StringTextComponent("This option is currently non functional."));
		}
	}
}
