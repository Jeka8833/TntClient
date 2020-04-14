package net.minecraft.client.audio;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.util.ResourceLocation;

public class SoundEventAccessorComposite implements ISoundEventAccessor<SoundPoolEntry> {
   private final List<ISoundEventAccessor<SoundPoolEntry>> field_148736_a = Lists.newArrayList();
   private final Random field_148734_b = new Random();
   private final ResourceLocation field_148735_c;
   private final SoundCategory field_148732_d;
   private double field_148733_e;
   private double field_148731_f;

   public SoundEventAccessorComposite(ResourceLocation p_i45120_1_, double p_i45120_2_, double p_i45120_4_, SoundCategory p_i45120_6_) {
      this.field_148735_c = p_i45120_1_;
      this.field_148731_f = p_i45120_4_;
      this.field_148733_e = p_i45120_2_;
      this.field_148732_d = p_i45120_6_;
   }

   public int func_148721_a() {
      int lvt_1_1_ = 0;

      ISoundEventAccessor lvt_3_1_;
      for(Iterator lvt_2_1_ = this.field_148736_a.iterator(); lvt_2_1_.hasNext(); lvt_1_1_ += lvt_3_1_.func_148721_a()) {
         lvt_3_1_ = (ISoundEventAccessor)lvt_2_1_.next();
      }

      return lvt_1_1_;
   }

   public SoundPoolEntry func_148720_g() {
      int lvt_1_1_ = this.func_148721_a();
      if (!this.field_148736_a.isEmpty() && lvt_1_1_ != 0) {
         int lvt_2_1_ = this.field_148734_b.nextInt(lvt_1_1_);
         Iterator lvt_3_1_ = this.field_148736_a.iterator();

         ISoundEventAccessor lvt_4_1_;
         do {
            if (!lvt_3_1_.hasNext()) {
               return SoundHandler.field_147700_a;
            }

            lvt_4_1_ = (ISoundEventAccessor)lvt_3_1_.next();
            lvt_2_1_ -= lvt_4_1_.func_148721_a();
         } while(lvt_2_1_ >= 0);

         SoundPoolEntry lvt_5_1_ = (SoundPoolEntry)lvt_4_1_.func_148720_g();
         lvt_5_1_.func_148651_a(lvt_5_1_.func_148650_b() * this.field_148733_e);
         lvt_5_1_.func_148647_b(lvt_5_1_.func_148649_c() * this.field_148731_f);
         return lvt_5_1_;
      } else {
         return SoundHandler.field_147700_a;
      }
   }

   public void func_148727_a(ISoundEventAccessor<SoundPoolEntry> p_148727_1_) {
      this.field_148736_a.add(p_148727_1_);
   }

   public ResourceLocation func_148729_c() {
      return this.field_148735_c;
   }

   public SoundCategory func_148728_d() {
      return this.field_148732_d;
   }
}
