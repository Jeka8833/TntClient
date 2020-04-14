package net.minecraft.entity.ai;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.profiler.Profiler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityAITasks {
   private static final Logger field_151506_a = LogManager.getLogger();
   private List<EntityAITasks.EntityAITaskEntry> field_75782_a = Lists.newArrayList();
   private List<EntityAITasks.EntityAITaskEntry> field_75780_b = Lists.newArrayList();
   private final Profiler field_75781_c;
   private int field_75778_d;
   private int field_75779_e = 3;

   public EntityAITasks(Profiler p_i1628_1_) {
      this.field_75781_c = p_i1628_1_;
   }

   public void func_75776_a(int p_75776_1_, EntityAIBase p_75776_2_) {
      this.field_75782_a.add(new EntityAITasks.EntityAITaskEntry(p_75776_1_, p_75776_2_));
   }

   public void func_85156_a(EntityAIBase p_85156_1_) {
      Iterator lvt_2_1_ = this.field_75782_a.iterator();

      while(lvt_2_1_.hasNext()) {
         EntityAITasks.EntityAITaskEntry lvt_3_1_ = (EntityAITasks.EntityAITaskEntry)lvt_2_1_.next();
         EntityAIBase lvt_4_1_ = lvt_3_1_.field_75733_a;
         if (lvt_4_1_ == p_85156_1_) {
            if (this.field_75780_b.contains(lvt_3_1_)) {
               lvt_4_1_.func_75251_c();
               this.field_75780_b.remove(lvt_3_1_);
            }

            lvt_2_1_.remove();
         }
      }

   }

   public void func_75774_a() {
      this.field_75781_c.func_76320_a("goalSetup");
      Iterator lvt_1_3_;
      EntityAITasks.EntityAITaskEntry lvt_2_1_;
      if (this.field_75778_d++ % this.field_75779_e == 0) {
         lvt_1_3_ = this.field_75782_a.iterator();

         label50:
         while(true) {
            while(true) {
               if (!lvt_1_3_.hasNext()) {
                  break label50;
               }

               lvt_2_1_ = (EntityAITasks.EntityAITaskEntry)lvt_1_3_.next();
               boolean lvt_3_1_ = this.field_75780_b.contains(lvt_2_1_);
               if (!lvt_3_1_) {
                  break;
               }

               if (!this.func_75775_b(lvt_2_1_) || !this.func_75773_a(lvt_2_1_)) {
                  lvt_2_1_.field_75733_a.func_75251_c();
                  this.field_75780_b.remove(lvt_2_1_);
                  break;
               }
            }

            if (this.func_75775_b(lvt_2_1_) && lvt_2_1_.field_75733_a.func_75250_a()) {
               lvt_2_1_.field_75733_a.func_75249_e();
               this.field_75780_b.add(lvt_2_1_);
            }
         }
      } else {
         lvt_1_3_ = this.field_75780_b.iterator();

         while(lvt_1_3_.hasNext()) {
            lvt_2_1_ = (EntityAITasks.EntityAITaskEntry)lvt_1_3_.next();
            if (!this.func_75773_a(lvt_2_1_)) {
               lvt_2_1_.field_75733_a.func_75251_c();
               lvt_1_3_.remove();
            }
         }
      }

      this.field_75781_c.func_76319_b();
      this.field_75781_c.func_76320_a("goalTick");
      lvt_1_3_ = this.field_75780_b.iterator();

      while(lvt_1_3_.hasNext()) {
         lvt_2_1_ = (EntityAITasks.EntityAITaskEntry)lvt_1_3_.next();
         lvt_2_1_.field_75733_a.func_75246_d();
      }

      this.field_75781_c.func_76319_b();
   }

   private boolean func_75773_a(EntityAITasks.EntityAITaskEntry p_75773_1_) {
      boolean lvt_2_1_ = p_75773_1_.field_75733_a.func_75253_b();
      return lvt_2_1_;
   }

   private boolean func_75775_b(EntityAITasks.EntityAITaskEntry p_75775_1_) {
      Iterator lvt_2_1_ = this.field_75782_a.iterator();

      while(lvt_2_1_.hasNext()) {
         EntityAITasks.EntityAITaskEntry lvt_3_1_ = (EntityAITasks.EntityAITaskEntry)lvt_2_1_.next();
         if (lvt_3_1_ != p_75775_1_) {
            if (p_75775_1_.field_75731_b >= lvt_3_1_.field_75731_b) {
               if (!this.func_75777_a(p_75775_1_, lvt_3_1_) && this.field_75780_b.contains(lvt_3_1_)) {
                  return false;
               }
            } else if (!lvt_3_1_.field_75733_a.func_75252_g() && this.field_75780_b.contains(lvt_3_1_)) {
               return false;
            }
         }
      }

      return true;
   }

   private boolean func_75777_a(EntityAITasks.EntityAITaskEntry p_75777_1_, EntityAITasks.EntityAITaskEntry p_75777_2_) {
      return (p_75777_1_.field_75733_a.func_75247_h() & p_75777_2_.field_75733_a.func_75247_h()) == 0;
   }

   class EntityAITaskEntry {
      public EntityAIBase field_75733_a;
      public int field_75731_b;

      public EntityAITaskEntry(int p_i1627_2_, EntityAIBase p_i1627_3_) {
         this.field_75731_b = p_i1627_2_;
         this.field_75733_a = p_i1627_3_;
      }
   }
}
