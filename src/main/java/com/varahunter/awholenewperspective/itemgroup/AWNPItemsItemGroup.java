
package com.varahunter.awholenewperspective.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import com.varahunter.awholenewperspective.item.UnrefinedAmygdallyteItem;
import com.varahunter.awholenewperspective.AWholeNewPerspectiveModElements;

@AWholeNewPerspectiveModElements.ModElement.Tag
public class AWNPItemsItemGroup extends AWholeNewPerspectiveModElements.ModElement {
	public AWNPItemsItemGroup(AWholeNewPerspectiveModElements instance) {
		super(instance, 53);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabawnp_items") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(UnrefinedAmygdallyteItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
