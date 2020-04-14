package net.minecraft.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PotionEffect {
   private static final Logger field_180155_a = LogManager.getLogger();
   private int field_76462_a;
   private int field_76460_b;
   private int field_76461_c;
   private boolean field_82723_d;
   private boolean field_82724_e;
   private boolean field_100013_f;
   private boolean field_180156_h;

   public PotionEffect(int p_i1574_1_, int p_i1574_2_) {
      this(p_i1574_1_, p_i1574_2_, 0);
   }

   public PotionEffect(int p_i1575_1_, int p_i1575_2_, int p_i1575_3_) {
      this(p_i1575_1_, p_i1575_2_, p_i1575_3_, false, true);
   }

   public PotionEffect(int p_i45896_1_, int p_i45896_2_, int p_i45896_3_, boolean p_i45896_4_, boolean p_i45896_5_) {
      this.field_76462_a = p_i45896_1_;
      this.field_76460_b = p_i45896_2_;
      this.field_76461_c = p_i45896_3_;
      this.field_82724_e = p_i45896_4_;
      this.field_180156_h = p_i45896_5_;
   }

   public PotionEffect(PotionEffect p_i1577_1_) {
      this.field_76462_a = p_i1577_1_.field_76462_a;
      this.field_76460_b = p_i1577_1_.field_76460_b;
      this.field_76461_c = p_i1577_1_.field_76461_c;
      this.field_82724_e = p_i1577_1_.field_82724_e;
      this.field_180156_h = p_i1577_1_.field_180156_h;
   }

   public void func_76452_a(PotionEffect p_76452_1_) {
      if (this.field_76462_a != p_76452_1_.field_76462_a) {
         field_180155_a.warn("This method should only be called for matching effects!");
      }

      if (p_76452_1_.field_76461_c > this.field_76461_c) {
         this.field_76461_c = p_76452_1_.field_76461_c;
         this.field_76460_b = p_76452_1_.field_76460_b;
      } else if (p_76452_1_.field_76461_c == this.field_76461_c && this.field_76460_b < p_76452_1_.field_76460_b) {
         this.field_76460_b = p_76452_1_.field_76460_b;
      } else if (!p_76452_1_.field_82724_e && this.field_82724_e) {
         this.field_82724_e = p_76452_1_.field_82724_e;
      }

      this.field_180156_h = p_76452_1_.field_180156_h;
   }

   public int func_76456_a() {
      return this.field_76462_a;
   }

   public int func_76459_b() {
      return this.field_76460_b;
   }

   public int func_76458_c() {
      return this.field_76461_c;
   }

   public void func_82721_a(boolean p_82721_1_) {
      this.field_82723_d = p_82721_1_;
   }

   public boolean func_82720_e() {
      return this.field_82724_e;
   }

   public boolean func_180154_f() {
      return this.field_180156_h;
   }

   public boolean func_76455_a(EntityLivingBase p_76455_1_) {
      if (this.field_76460_b > 0) {
         if (Potion.field_76425_a[this.field_76462_a].func_76397_a(this.field_76460_b, this.field_76461_c)) {
            this.func_76457_b(p_76455_1_);
         }

         this.func_76454_e();
      }

      return this.field_76460_b > 0;
   }

   private int func_76454_e() {
      return --this.field_76460_b;
   }

   public void func_76457_b(EntityLivingBase p_76457_1_) {
      if (this.field_76460_b > 0) {
         Potion.field_76425_a[this.field_76462_a].func_76394_a(p_76457_1_, this.field_76461_c);
      }

   }

   public String func_76453_d() {
      return Potion.field_76425_a[this.field_76462_a].func_76393_a();
   }

   public int hashCode() {
      return this.field_76462_a;
   }

   public String toString() {
      String lvt_1_1_ = "";
      if (this.func_76458_c() > 0) {
         lvt_1_1_ = this.func_76453_d() + " x " + (this.func_76458_c() + 1) + ", Duration: " + this.func_76459_b();
      } else {
         lvt_1_1_ = this.func_76453_d() + ", Duration: " + this.func_76459_b();
      }

      if (this.field_82723_d) {
         lvt_1_1_ = lvt_1_1_ + ", Splash: true";
      }

      if (!this.field_180156_h) {
         lvt_1_1_ = lvt_1_1_ + ", Particles: false";
      }

      return Potion.field_76425_a[this.field_76462_a].func_76395_i() ? "(" + lvt_1_1_ + ")" : lvt_1_1_;
   }

   public boolean equals(Object p_equals_1_) {
      if (!(p_equals_1_ instanceof PotionEffect)) {
         return false;
      } else {
         PotionEffect lvt_2_1_ = (PotionEffect)p_equals_1_;
         return this.field_76462_a == lvt_2_1_.field_76462_a && this.field_76461_c == lvt_2_1_.field_76461_c && this.field_76460_b == lvt_2_1_.field_76460_b && this.field_82723_d == lvt_2_1_.field_82723_d && this.field_82724_e == lvt_2_1_.field_82724_e;
      }
   }

   public NBTTagCompound func_82719_a(NBTTagCompound p_82719_1_) {
      p_82719_1_.func_74774_a("Id", (byte)this.func_76456_a());
      p_82719_1_.func_74774_a("Amplifier", (byte)this.func_76458_c());
      p_82719_1_.func_74768_a("Duration", this.func_76459_b());
      p_82719_1_.func_74757_a("Ambient", this.func_82720_e());
      p_82719_1_.func_74757_a("ShowParticles", this.func_180154_f());
      return p_82719_1_;
   }

   public static PotionEffect func_82722_b(NBTTagCompound p_82722_0_) {
      int lvt_1_1_ = p_82722_0_.func_74771_c("Id");
      if (lvt_1_1_ >= 0 && lvt_1_1_ < Potion.field_76425_a.length && Potion.field_76425_a[lvt_1_1_] != null) {
         int lvt_2_1_ = p_82722_0_.func_74771_c("Amplifier");
         int lvt_3_1_ = p_82722_0_.func_74762_e("Duration");
         boolean lvt_4_1_ = p_82722_0_.func_74767_n("Ambient");
         boolean lvt_5_1_ = true;
         if (p_82722_0_.func_150297_b("ShowParticles", 1)) {
            lvt_5_1_ = p_82722_0_.func_74767_n("ShowParticles");
         }

         return new PotionEffect(lvt_1_1_, lvt_3_1_, lvt_2_1_, lvt_4_1_, lvt_5_1_);
      } else {
         return null;
      }
   }

   public void func_100012_b(boolean p_100012_1_) {
      this.field_100013_f = p_100012_1_;
   }

   public boolean func_100011_g() {
      return this.field_100013_f;
   }
}
