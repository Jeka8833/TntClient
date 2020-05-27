package net.optifine.shaders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.src.Config;
import net.optifine.config.MatchBlock;

public class BlockAlias {
   private int blockAliasId;
   private MatchBlock[] matchBlocks;

   public BlockAlias(int blockAliasId, MatchBlock[] matchBlocks) {
      this.blockAliasId = blockAliasId;
      this.matchBlocks = matchBlocks;
   }

   public int getBlockAliasId() {
      return this.blockAliasId;
   }

   public boolean matches(int id, int metadata) {
      for(int i = 0; i < this.matchBlocks.length; ++i) {
         MatchBlock matchBlock = this.matchBlocks[i];
         if (matchBlock.matches(id, metadata)) {
            return true;
         }
      }

      return false;
   }

   public int[] getMatchBlockIds() {
      Set<Integer> blockIdSet = new HashSet();

      for(int i = 0; i < this.matchBlocks.length; ++i) {
         MatchBlock matchBlock = this.matchBlocks[i];
         int blockId = matchBlock.getBlockId();
         blockIdSet.add(blockId);
      }

      Integer[] blockIdsArr = (Integer[])blockIdSet.toArray(new Integer[blockIdSet.size()]);
      int[] blockIds = Config.toPrimitive(blockIdsArr);
      return blockIds;
   }

   public MatchBlock[] getMatchBlocks(int matchBlockId) {
      List<MatchBlock> listMatchBlock = new ArrayList();

      for(int i = 0; i < this.matchBlocks.length; ++i) {
         MatchBlock mb = this.matchBlocks[i];
         if (mb.getBlockId() == matchBlockId) {
            listMatchBlock.add(mb);
         }
      }

      MatchBlock[] mbs = (MatchBlock[])((MatchBlock[])listMatchBlock.toArray(new MatchBlock[listMatchBlock.size()]));
      return mbs;
   }

   public String toString() {
      return "block." + this.blockAliasId + "=" + Config.arrayToString((Object[])this.matchBlocks);
   }
}