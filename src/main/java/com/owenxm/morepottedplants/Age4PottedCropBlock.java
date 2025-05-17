package com.owenxm.morepottedplants;

import net.minecraft.block.Block;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;

public class Age4PottedCropBlock extends PottedCropBlock {
    public Age4PottedCropBlock(Block content, Settings settings) {
        super(content, settings);
    }
    protected IntProperty getAgeProperty() {
        return Properties.AGE_4;
    }

    public int getMaxAge() {
        return 4;
    }
}
