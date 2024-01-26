package rearth.oritech.util;

import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import rearth.oritech.Oritech;

import java.util.List;

public interface ScreenProvider {

    record GuiSlot (int index, int x, int y) {}

    record EnergyConfiguration(int x, int y, int width, int height) {}

    record ArrowConfiguration(Identifier empty, Identifier full, int x, int y, int width, int height, boolean horizontal) {}

    List<GuiSlot> getGuiSlots();

    default boolean showEnergy() {
        return true;
    }

    float getProgress();
    
    InventoryInputMode getInventoryInputMode();
    
    ScreenHandlerType<?> getScreenHandlerType();

    default EnergyConfiguration getEnergyConfiguration() {
        return new EnergyConfiguration(10, 11, 13, 50);
    }

    default ArrowConfiguration getIndicatorConfiguration() {
        return new ArrowConfiguration(
                new Identifier(Oritech.MOD_ID, "textures/gui/modular/arrow_empty.png"),
                new Identifier(Oritech.MOD_ID, "textures/gui/modular/arrow_full.png"),
                84, 30, 7, 26, false);
    }

}
