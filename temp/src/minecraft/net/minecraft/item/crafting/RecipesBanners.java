package net.minecraft.item.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.world.World;

public class RecipesBanners {
   void func_179534_a(CraftingManager p_179534_1_) {
      EnumDyeColor[] lvt_2_1_ = EnumDyeColor.values();
      int lvt_3_1_ = lvt_2_1_.length;

      for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_; ++lvt_4_1_) {
         EnumDyeColor lvt_5_1_ = lvt_2_1_[lvt_4_1_];
         p_179534_1_.func_92103_a(new ItemStack(Items.field_179564_cE, 1, lvt_5_1_.func_176767_b()), "###", "###", " | ", '#', new ItemStack(Blocks.field_150325_L, 1, lvt_5_1_.func_176765_a()), '|', Items.field_151055_y);
      }

      p_179534_1_.func_180302_a(new RecipesBanners.RecipeDuplicatePattern());
      p_179534_1_.func_180302_a(new RecipesBanners.RecipeAddPattern());
   }

   static class RecipeAddPattern implements IRecipe {
      private RecipeAddPattern() {
      }

      public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
         boolean lvt_3_1_ = false;

         for(int lvt_4_1_ = 0; lvt_4_1_ < p_77569_1_.func_70302_i_(); ++lvt_4_1_) {
            ItemStack lvt_5_1_ = p_77569_1_.func_70301_a(lvt_4_1_);
            if (lvt_5_1_ != null && lvt_5_1_.func_77973_b() == Items.field_179564_cE) {
               if (lvt_3_1_) {
                  return false;
               }

               if (TileEntityBanner.func_175113_c(lvt_5_1_) >= 6) {
                  return false;
               }

               lvt_3_1_ = true;
            }
         }

         if (!lvt_3_1_) {
            return false;
         } else {
            return this.func_179533_c(p_77569_1_) != null;
         }
      }

      public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
         ItemStack lvt_2_1_ = null;

         for(int lvt_3_1_ = 0; lvt_3_1_ < p_77572_1_.func_70302_i_(); ++lvt_3_1_) {
            ItemStack lvt_4_1_ = p_77572_1_.func_70301_a(lvt_3_1_);
            if (lvt_4_1_ != null && lvt_4_1_.func_77973_b() == Items.field_179564_cE) {
               lvt_2_1_ = lvt_4_1_.func_77946_l();
               lvt_2_1_.field_77994_a = 1;
               break;
            }
         }

         TileEntityBanner.EnumBannerPattern lvt_3_2_ = this.func_179533_c(p_77572_1_);
         if (lvt_3_2_ != null) {
            int lvt_4_2_ = 0;

            ItemStack lvt_6_1_;
            for(int lvt_5_1_ = 0; lvt_5_1_ < p_77572_1_.func_70302_i_(); ++lvt_5_1_) {
               lvt_6_1_ = p_77572_1_.func_70301_a(lvt_5_1_);
               if (lvt_6_1_ != null && lvt_6_1_.func_77973_b() == Items.field_151100_aR) {
                  lvt_4_2_ = lvt_6_1_.func_77960_j();
                  break;
               }
            }

            NBTTagCompound lvt_5_2_ = lvt_2_1_.func_179543_a("BlockEntityTag", true);
            lvt_6_1_ = null;
            NBTTagList lvt_6_2_;
            if (lvt_5_2_.func_150297_b("Patterns", 9)) {
               lvt_6_2_ = lvt_5_2_.func_150295_c("Patterns", 10);
            } else {
               lvt_6_2_ = new NBTTagList();
               lvt_5_2_.func_74782_a("Patterns", lvt_6_2_);
            }

            NBTTagCompound lvt_7_1_ = new NBTTagCompound();
            lvt_7_1_.func_74778_a("Pattern", lvt_3_2_.func_177273_b());
            lvt_7_1_.func_74768_a("Color", lvt_4_2_);
            lvt_6_2_.func_74742_a(lvt_7_1_);
         }

         return lvt_2_1_;
      }

      public int func_77570_a() {
         return 10;
      }

      public ItemStack func_77571_b() {
         return null;
      }

      public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_) {
         ItemStack[] lvt_2_1_ = new ItemStack[p_179532_1_.func_70302_i_()];

         for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.length; ++lvt_3_1_) {
            ItemStack lvt_4_1_ = p_179532_1_.func_70301_a(lvt_3_1_);
            if (lvt_4_1_ != null && lvt_4_1_.func_77973_b().func_77634_r()) {
               lvt_2_1_[lvt_3_1_] = new ItemStack(lvt_4_1_.func_77973_b().func_77668_q());
            }
         }

         return lvt_2_1_;
      }

      private TileEntityBanner.EnumBannerPattern func_179533_c(InventoryCrafting p_179533_1_) {
         TileEntityBanner.EnumBannerPattern[] lvt_2_1_ = TileEntityBanner.EnumBannerPattern.values();
         int lvt_3_1_ = lvt_2_1_.length;

         for(int lvt_4_1_ = 0; lvt_4_1_ < lvt_3_1_; ++lvt_4_1_) {
            TileEntityBanner.EnumBannerPattern lvt_5_1_ = lvt_2_1_[lvt_4_1_];
            if (lvt_5_1_.func_177270_d()) {
               boolean lvt_6_1_ = true;
               int lvt_9_2_;
               if (lvt_5_1_.func_177269_e()) {
                  boolean lvt_7_1_ = false;
                  boolean lvt_8_1_ = false;

                  for(lvt_9_2_ = 0; lvt_9_2_ < p_179533_1_.func_70302_i_() && lvt_6_1_; ++lvt_9_2_) {
                     ItemStack lvt_10_1_ = p_179533_1_.func_70301_a(lvt_9_2_);
                     if (lvt_10_1_ != null && lvt_10_1_.func_77973_b() != Items.field_179564_cE) {
                        if (lvt_10_1_.func_77973_b() == Items.field_151100_aR) {
                           if (lvt_8_1_) {
                              lvt_6_1_ = false;
                              break;
                           }

                           lvt_8_1_ = true;
                        } else {
                           if (lvt_7_1_ || !lvt_10_1_.func_77969_a(lvt_5_1_.func_177272_f())) {
                              lvt_6_1_ = false;
                              break;
                           }

                           lvt_7_1_ = true;
                        }
                     }
                  }

                  if (!lvt_7_1_) {
                     lvt_6_1_ = false;
                  }
               } else if (p_179533_1_.func_70302_i_() == lvt_5_1_.func_177267_c().length * lvt_5_1_.func_177267_c()[0].length()) {
                  int lvt_7_2_ = -1;

                  for(int lvt_8_2_ = 0; lvt_8_2_ < p_179533_1_.func_70302_i_() && lvt_6_1_; ++lvt_8_2_) {
                     lvt_9_2_ = lvt_8_2_ / 3;
                     int lvt_10_2_ = lvt_8_2_ % 3;
                     ItemStack lvt_11_1_ = p_179533_1_.func_70301_a(lvt_8_2_);
                     if (lvt_11_1_ != null && lvt_11_1_.func_77973_b() != Items.field_179564_cE) {
                        if (lvt_11_1_.func_77973_b() != Items.field_151100_aR) {
                           lvt_6_1_ = false;
                           break;
                        }

                        if (lvt_7_2_ != -1 && lvt_7_2_ != lvt_11_1_.func_77960_j()) {
                           lvt_6_1_ = false;
                           break;
                        }

                        if (lvt_5_1_.func_177267_c()[lvt_9_2_].charAt(lvt_10_2_) == ' ') {
                           lvt_6_1_ = false;
                           break;
                        }

                        lvt_7_2_ = lvt_11_1_.func_77960_j();
                     } else if (lvt_5_1_.func_177267_c()[lvt_9_2_].charAt(lvt_10_2_) != ' ') {
                        lvt_6_1_ = false;
                        break;
                     }
                  }
               } else {
                  lvt_6_1_ = false;
               }

               if (lvt_6_1_) {
                  return lvt_5_1_;
               }
            }
         }

         return null;
      }
   }

   static class RecipeDuplicatePattern implements IRecipe {
      private RecipeDuplicatePattern() {
      }

      public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
         ItemStack lvt_3_1_ = null;
         ItemStack lvt_4_1_ = null;

         for(int lvt_5_1_ = 0; lvt_5_1_ < p_77569_1_.func_70302_i_(); ++lvt_5_1_) {
            ItemStack lvt_6_1_ = p_77569_1_.func_70301_a(lvt_5_1_);
            if (lvt_6_1_ != null) {
               if (lvt_6_1_.func_77973_b() != Items.field_179564_cE) {
                  return false;
               }

               if (lvt_3_1_ != null && lvt_4_1_ != null) {
                  return false;
               }

               int lvt_7_1_ = TileEntityBanner.func_175111_b(lvt_6_1_);
               boolean lvt_8_1_ = TileEntityBanner.func_175113_c(lvt_6_1_) > 0;
               if (lvt_3_1_ != null) {
                  if (lvt_8_1_) {
                     return false;
                  }

                  if (lvt_7_1_ != TileEntityBanner.func_175111_b(lvt_3_1_)) {
                     return false;
                  }

                  lvt_4_1_ = lvt_6_1_;
               } else if (lvt_4_1_ != null) {
                  if (!lvt_8_1_) {
                     return false;
                  }

                  if (lvt_7_1_ != TileEntityBanner.func_175111_b(lvt_4_1_)) {
                     return false;
                  }

                  lvt_3_1_ = lvt_6_1_;
               } else if (lvt_8_1_) {
                  lvt_3_1_ = lvt_6_1_;
               } else {
                  lvt_4_1_ = lvt_6_1_;
               }
            }
         }

         return lvt_3_1_ != null && lvt_4_1_ != null;
      }

      public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
         for(int lvt_2_1_ = 0; lvt_2_1_ < p_77572_1_.func_70302_i_(); ++lvt_2_1_) {
            ItemStack lvt_3_1_ = p_77572_1_.func_70301_a(lvt_2_1_);
            if (lvt_3_1_ != null && TileEntityBanner.func_175113_c(lvt_3_1_) > 0) {
               ItemStack lvt_4_1_ = lvt_3_1_.func_77946_l();
               lvt_4_1_.field_77994_a = 1;
               return lvt_4_1_;
            }
         }

         return null;
      }

      public int func_77570_a() {
         return 2;
      }

      public ItemStack func_77571_b() {
         return null;
      }

      public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_) {
         ItemStack[] lvt_2_1_ = new ItemStack[p_179532_1_.func_70302_i_()];

         for(int lvt_3_1_ = 0; lvt_3_1_ < lvt_2_1_.length; ++lvt_3_1_) {
            ItemStack lvt_4_1_ = p_179532_1_.func_70301_a(lvt_3_1_);
            if (lvt_4_1_ != null) {
               if (lvt_4_1_.func_77973_b().func_77634_r()) {
                  lvt_2_1_[lvt_3_1_] = new ItemStack(lvt_4_1_.func_77973_b().func_77668_q());
               } else if (lvt_4_1_.func_77942_o() && TileEntityBanner.func_175113_c(lvt_4_1_) > 0) {
                  lvt_2_1_[lvt_3_1_] = lvt_4_1_.func_77946_l();
                  lvt_2_1_[lvt_3_1_].field_77994_a = 1;
               }
            }
         }

         return lvt_2_1_;
      }
   }
}
