package net.minecraft.realms;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.SaveFormatComparator;

public class RealmsAnvilLevelStorageSource {
   private ISaveFormat levelStorageSource;

   public RealmsAnvilLevelStorageSource(ISaveFormat p_i1106_1_) {
      this.levelStorageSource = p_i1106_1_;
   }

   public String getName() {
      return this.levelStorageSource.func_154333_a();
   }

   public boolean levelExists(String p_levelExists_1_) {
      return this.levelStorageSource.func_90033_f(p_levelExists_1_);
   }

   public boolean convertLevel(String p_convertLevel_1_, IProgressUpdate p_convertLevel_2_) {
      return this.levelStorageSource.func_75805_a(p_convertLevel_1_, p_convertLevel_2_);
   }

   public boolean requiresConversion(String p_requiresConversion_1_) {
      return this.levelStorageSource.func_75801_b(p_requiresConversion_1_);
   }

   public boolean isNewLevelIdAcceptable(String p_isNewLevelIdAcceptable_1_) {
      return this.levelStorageSource.func_154335_d(p_isNewLevelIdAcceptable_1_);
   }

   public boolean deleteLevel(String p_deleteLevel_1_) {
      return this.levelStorageSource.func_75802_e(p_deleteLevel_1_);
   }

   public boolean isConvertible(String p_isConvertible_1_) {
      return this.levelStorageSource.func_154334_a(p_isConvertible_1_);
   }

   public void renameLevel(String p_renameLevel_1_, String p_renameLevel_2_) {
      this.levelStorageSource.func_75806_a(p_renameLevel_1_, p_renameLevel_2_);
   }

   public void clearAll() {
      this.levelStorageSource.func_75800_d();
   }

   public List<RealmsLevelSummary> getLevelList() throws AnvilConverterException {
      List<RealmsLevelSummary> lvt_1_1_ = Lists.newArrayList();
      Iterator lvt_2_1_ = this.levelStorageSource.func_75799_b().iterator();

      while(lvt_2_1_.hasNext()) {
         SaveFormatComparator lvt_3_1_ = (SaveFormatComparator)lvt_2_1_.next();
         lvt_1_1_.add(new RealmsLevelSummary(lvt_3_1_));
      }

      return lvt_1_1_;
   }
}
