package com.owenxm.morepottedplants;

import net.minecraft.block.Block;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;

public class Age3PottedCropBlock extends PottedCropBlock {
    public Age3PottedCropBlock(Block content, Settings settings) {
        super(content, settings);
    }
    protected IntProperty getAgeProperty() {
        return Properties.AGE_3;
    }

    public int getMaxAge() {
        return 3;
    }
}
