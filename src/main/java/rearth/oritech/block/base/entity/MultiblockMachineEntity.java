package rearth.oritech.block.base.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import rearth.oritech.block.base.block.MultiblockMachine;
import rearth.oritech.block.blocks.MachineCoreBlock;
import rearth.oritech.block.entity.machines.processing.MachineCoreEntity;
import rearth.oritech.client.init.ParticleContent;
import rearth.oritech.util.Geometry;
import rearth.oritech.util.ImplementedInventory;
import rearth.oritech.util.MultiblockMachineController;
import team.reborn.energy.api.EnergyStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class MultiblockMachineEntity extends UpgradableMachineBlockEntity implements MultiblockMachineController {
    
    private final ArrayList<BlockPos> coreBlocksConnected = new ArrayList<>();
    
    private float coreQuality = 1f;
    
    public MultiblockMachineEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int energyPerTick) {
        super(type, pos, state, energyPerTick);
    }
    
    @Override
    public ArrayList<BlockPos> getConnectedCores() {
        return coreBlocksConnected;
    }
    
    @Override
    public Direction getFacingForMultiblock() {
        return getFacing();
    }
    
    @Override
    public void setCoreQuality(float quality) {
        this.coreQuality = quality;
    }
    
    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        addToNbt(nbt);
    }
    
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        getFromNbt(nbt);
    }
    
    @Override
    public float getCoreQuality() {
        return this.coreQuality;
    }
    
    @Override
    public ImplementedInventory getInventoryForLink() {
        return this;
    }
    
    @Override
    public EnergyStorage getEnergyStorageForLink() {
        return energyStorage;
    }
    
    @Override
    public boolean isActive(BlockState state) {
        return state.get(MultiblockMachine.ASSEMBLED);
    }
    
    // this seems to work as expected for some reason?
    public static Vec3i worldToRelativePos(Vec3i ownWorldPos, Vec3i worldPos, Direction ownFacing) {
        return worldPos.subtract(ownWorldPos);
    }
}
