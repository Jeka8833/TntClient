package net.minecraft.client.renderer.chunk;

import java.util.Iterator;
import java.util.Set;
import net.minecraft.util.EnumFacing;

public class SetVisibility {
   private static final int field_178623_a = EnumFacing.values().length;
   private long bits;

   public void func_178620_a(Set<EnumFacing> p_178620_1_) {
      Iterator i$ = p_178620_1_.iterator();

      while(i$.hasNext()) {
         EnumFacing enumfacing = (EnumFacing)i$.next();
         Iterator i$ = p_178620_1_.iterator();

         while(i$.hasNext()) {
            EnumFacing enumfacing1 = (EnumFacing)i$.next();
            this.func_178619_a(enumfacing, enumfacing1, true);
         }
      }

   }

   public void func_178619_a(EnumFacing p_178619_1_, EnumFacing p_178619_2_, boolean p_178619_3_) {
      this.setBit(p_178619_1_.ordinal() + p_178619_2_.ordinal() * field_178623_a, p_178619_3_);
      this.setBit(p_178619_2_.ordinal() + p_178619_1_.ordinal() * field_178623_a, p_178619_3_);
   }

   public void func_178618_a(boolean p_178618_1_) {
      if (p_178618_1_) {
         this.bits = -1L;
      } else {
         this.bits = 0L;
      }

   }

   public boolean func_178621_a(EnumFacing p_178621_1_, EnumFacing p_178621_2_) {
      return this.getBit(p_178621_1_.ordinal() + p_178621_2_.ordinal() * field_178623_a);
   }

   public String toString() {
      StringBuilder stringbuilder = new StringBuilder();
      stringbuilder.append(' ');
      EnumFacing[] arr$ = EnumFacing.values();
      int len$ = arr$.length;

      int i$;
      EnumFacing enumfacing2;
      for(i$ = 0; i$ < len$; ++i$) {
         enumfacing2 = arr$[i$];
         stringbuilder.append(' ').append(enumfacing2.toString().toUpperCase().charAt(0));
      }

      stringbuilder.append('\n');
      arr$ = EnumFacing.values();
      len$ = arr$.length;

      for(i$ = 0; i$ < len$; ++i$) {
         enumfacing2 = arr$[i$];
         stringbuilder.append(enumfacing2.toString().toUpperCase().charAt(0));
         EnumFacing[] arr$ = EnumFacing.values();
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            EnumFacing enumfacing1 = arr$[i$];
            if (enumfacing2 == enumfacing1) {
               stringbuilder.append("  ");
            } else {
               boolean flag = this.func_178621_a(enumfacing2, enumfacing1);
               stringbuilder.append(' ').append((char)(flag ? 'Y' : 'n'));
            }
         }

         stringbuilder.append('\n');
      }

      return stringbuilder.toString();
   }

   private boolean getBit(int p_getBit_1_) {
      return (this.bits & (long)(1 << p_getBit_1_)) != 0L;
   }

   private void setBit(int p_setBit_1_, boolean p_setBit_2_) {
      if (p_setBit_2_) {
         this.setBit(p_setBit_1_);
      } else {
         this.clearBit(p_setBit_1_);
      }

   }

   private void setBit(int p_setBit_1_) {
      this.bits |= (long)(1 << p_setBit_1_);
   }

   private void clearBit(int p_clearBit_1_) {
      this.bits &= (long)(~(1 << p_clearBit_1_));
   }
}
