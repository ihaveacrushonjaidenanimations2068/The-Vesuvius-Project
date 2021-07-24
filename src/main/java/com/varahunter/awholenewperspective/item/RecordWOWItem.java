
package com.varahunter.awholenewperspective.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.Item;

import com.varahunter.awholenewperspective.itemgroup.AWNPItemsItemGroup;
import com.varahunter.awholenewperspective.AWholeNewPerspectiveModElements;

@AWholeNewPerspectiveModElements.ModElement.Tag
public class RecordWOWItem extends AWholeNewPerspectiveModElements.ModElement {
	@ObjectHolder("a_whole_new_perspective:record_wow")
	public static final Item block = null;
	public RecordWOWItem(AWholeNewPerspectiveModElements instance) {
		super(instance, 110);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}
	public static class MusicDiscItemCustom extends MusicDiscItem {
		public MusicDiscItemCustom() {
			super(0, AWholeNewPerspectiveModElements.sounds.get(new ResourceLocation("a_whole_new_perspective:record_wow")),
					new Item.Properties().group(AWNPItemsItemGroup.tab).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("record_wow");
		}
	}
}
