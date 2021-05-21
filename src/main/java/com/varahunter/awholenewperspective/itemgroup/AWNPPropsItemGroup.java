
package com.varahunter.awholenewperspective.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import com.varahunter.awholenewperspective.block.WastelandFridgeBlock;
import com.varahunter.awholenewperspective.AWholeNewPerspectiveModElements;

@AWholeNewPerspectiveModElements.ModElement.Tag
public class AWNPPropsItemGroup extends AWholeNewPerspectiveModElements.ModElement {
	public AWNPPropsItemGroup(AWholeNewPerspectiveModElements instance) {
		super(instance, 56);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabawnp_props") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(WastelandFridgeBlock.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
