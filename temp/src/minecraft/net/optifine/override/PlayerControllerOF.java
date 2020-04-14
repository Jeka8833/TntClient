package net.optifine.override;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class PlayerControllerOF extends PlayerControllerMP {
   private boolean acting = false;
   private BlockPos lastClickBlockPos = null;
   private Entity lastClickEntity = null;

   public PlayerControllerOF(Minecraft mcIn, NetHandlerPlayClient netHandler) {
      super(mcIn, netHandler);
   }

   public boolean func_180511_b(BlockPos loc, EnumFacing face) {
      this.acting = true;
      this.lastClickBlockPos = loc;
      boolean res = super.func_180511_b(loc, face);
      this.acting = false;
      return res;
   }

   public boolean func_180512_c(BlockPos posBlock, EnumFacing directionFacing) {
      this.acting = true;
      this.lastClickBlockPos = posBlock;
      boolean res = super.func_180512_c(posBlock, directionFacing);
      this.acting = false;
      return res;
   }

   public boolean func_78769_a(EntityPlayer player, World worldIn, ItemStack stack) {
      this.acting = true;
      boolean res = super.func_78769_a(player, worldIn, stack);
      this.acting = false;
      return res;
   }

   public boolean func_178890_a(EntityPlayerSP p_178890_1, WorldClient p_178890_2, ItemStack p_178890_3, BlockPos p_178890_4, EnumFacing p_178890_5, Vec3 p_178890_6) {
      this.acting = true;
      this.lastClickBlockPos = p_178890_4;
      boolean res = super.func_178890_a(p_178890_1, p_178890_2, p_178890_3, p_178890_4, p_178890_5, p_178890_6);
      this.acting = false;
      return res;
   }

   public boolean func_78768_b(EntityPlayer player, Entity target) {
      this.lastClickEntity = target;
      return super.func_78768_b(player, target);
   }

   public boolean func_178894_a(EntityPlayer player, Entity target, MovingObjectPosition ray) {
      this.lastClickEntity = target;
      return super.func_178894_a(player, target, ray);
   }

   public boolean isActing() {
      return this.acting;
   }

   public BlockPos getLastClickBlockPos() {
      return this.lastClickBlockPos;
   }

   public Entity getLastClickEntity() {
      return this.lastClickEntity;
   }
}
