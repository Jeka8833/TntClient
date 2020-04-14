package net.minecraft.init;

import com.mojang.authlib.GameProfile;
import java.io.PrintStream;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.LoggingPrintStream;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bootstrap {
   private static final PrintStream field_179872_a;
   private static boolean field_151355_a;
   private static final Logger field_179871_c;

   public static boolean func_179869_a() {
      return field_151355_a;
   }

   static void func_151353_a() {
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151032_g, new BehaviorProjectileDispense() {
         protected IProjectile func_82499_a(World p_82499_1_, IPosition p_82499_2_) {
            EntityArrow lvt_3_1_ = new EntityArrow(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c());
            lvt_3_1_.field_70251_a = 1;
            return lvt_3_1_;
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151110_aK, new BehaviorProjectileDispense() {
         protected IProjectile func_82499_a(World p_82499_1_, IPosition p_82499_2_) {
            return new EntityEgg(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c());
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151126_ay, new BehaviorProjectileDispense() {
         protected IProjectile func_82499_a(World p_82499_1_, IPosition p_82499_2_) {
            return new EntitySnowball(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c());
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151062_by, new BehaviorProjectileDispense() {
         protected IProjectile func_82499_a(World p_82499_1_, IPosition p_82499_2_) {
            return new EntityExpBottle(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c());
         }

         protected float func_82498_a() {
            return super.func_82498_a() * 0.5F;
         }

         protected float func_82500_b() {
            return super.func_82500_b() * 1.25F;
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151068_bn, new IBehaviorDispenseItem() {
         private final BehaviorDefaultDispenseItem field_150843_b = new BehaviorDefaultDispenseItem();

         public ItemStack func_82482_a(IBlockSource p_82482_1_, final ItemStack p_82482_2_) {
            return ItemPotion.func_77831_g(p_82482_2_.func_77960_j()) ? (new BehaviorProjectileDispense() {
               protected IProjectile func_82499_a(World p_82499_1_, IPosition p_82499_2_) {
                  return new EntityPotion(p_82499_1_, p_82499_2_.func_82615_a(), p_82499_2_.func_82617_b(), p_82499_2_.func_82616_c(), p_82482_2_.func_77946_l());
               }

               protected float func_82498_a() {
                  return super.func_82498_a() * 0.5F;
               }

               protected float func_82500_b() {
                  return super.func_82500_b() * 1.25F;
               }
            }).func_82482_a(p_82482_1_, p_82482_2_) : this.field_150843_b.func_82482_a(p_82482_1_, p_82482_2_);
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151063_bx, new BehaviorDefaultDispenseItem() {
         public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            EnumFacing lvt_3_1_ = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
            double lvt_4_1_ = p_82487_1_.func_82615_a() + (double)lvt_3_1_.func_82601_c();
            double lvt_6_1_ = (double)((float)p_82487_1_.func_180699_d().func_177956_o() + 0.2F);
            double lvt_8_1_ = p_82487_1_.func_82616_c() + (double)lvt_3_1_.func_82599_e();
            Entity lvt_10_1_ = ItemMonsterPlacer.func_77840_a(p_82487_1_.func_82618_k(), p_82487_2_.func_77960_j(), lvt_4_1_, lvt_6_1_, lvt_8_1_);
            if (lvt_10_1_ instanceof EntityLivingBase && p_82487_2_.func_82837_s()) {
               ((EntityLiving)lvt_10_1_).func_96094_a(p_82487_2_.func_82833_r());
            }

            p_82487_2_.func_77979_a(1);
            return p_82487_2_;
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151152_bP, new BehaviorDefaultDispenseItem() {
         public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            EnumFacing lvt_3_1_ = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
            double lvt_4_1_ = p_82487_1_.func_82615_a() + (double)lvt_3_1_.func_82601_c();
            double lvt_6_1_ = (double)((float)p_82487_1_.func_180699_d().func_177956_o() + 0.2F);
            double lvt_8_1_ = p_82487_1_.func_82616_c() + (double)lvt_3_1_.func_82599_e();
            EntityFireworkRocket lvt_10_1_ = new EntityFireworkRocket(p_82487_1_.func_82618_k(), lvt_4_1_, lvt_6_1_, lvt_8_1_, p_82487_2_);
            p_82487_1_.func_82618_k().func_72838_d(lvt_10_1_);
            p_82487_2_.func_77979_a(1);
            return p_82487_2_;
         }

         protected void func_82485_a(IBlockSource p_82485_1_) {
            p_82485_1_.func_82618_k().func_175718_b(1002, p_82485_1_.func_180699_d(), 0);
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151059_bz, new BehaviorDefaultDispenseItem() {
         public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            EnumFacing lvt_3_1_ = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
            IPosition lvt_4_1_ = BlockDispenser.func_149939_a(p_82487_1_);
            double lvt_5_1_ = lvt_4_1_.func_82615_a() + (double)((float)lvt_3_1_.func_82601_c() * 0.3F);
            double lvt_7_1_ = lvt_4_1_.func_82617_b() + (double)((float)lvt_3_1_.func_96559_d() * 0.3F);
            double lvt_9_1_ = lvt_4_1_.func_82616_c() + (double)((float)lvt_3_1_.func_82599_e() * 0.3F);
            World lvt_11_1_ = p_82487_1_.func_82618_k();
            Random lvt_12_1_ = lvt_11_1_.field_73012_v;
            double lvt_13_1_ = lvt_12_1_.nextGaussian() * 0.05D + (double)lvt_3_1_.func_82601_c();
            double lvt_15_1_ = lvt_12_1_.nextGaussian() * 0.05D + (double)lvt_3_1_.func_96559_d();
            double lvt_17_1_ = lvt_12_1_.nextGaussian() * 0.05D + (double)lvt_3_1_.func_82599_e();
            lvt_11_1_.func_72838_d(new EntitySmallFireball(lvt_11_1_, lvt_5_1_, lvt_7_1_, lvt_9_1_, lvt_13_1_, lvt_15_1_, lvt_17_1_));
            p_82487_2_.func_77979_a(1);
            return p_82487_2_;
         }

         protected void func_82485_a(IBlockSource p_82485_1_) {
            p_82485_1_.func_82618_k().func_175718_b(1009, p_82485_1_.func_180699_d(), 0);
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151124_az, new BehaviorDefaultDispenseItem() {
         private final BehaviorDefaultDispenseItem field_150842_b = new BehaviorDefaultDispenseItem();

         public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            EnumFacing lvt_3_1_ = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
            World lvt_4_1_ = p_82487_1_.func_82618_k();
            double lvt_5_1_ = p_82487_1_.func_82615_a() + (double)((float)lvt_3_1_.func_82601_c() * 1.125F);
            double lvt_7_1_ = p_82487_1_.func_82617_b() + (double)((float)lvt_3_1_.func_96559_d() * 1.125F);
            double lvt_9_1_ = p_82487_1_.func_82616_c() + (double)((float)lvt_3_1_.func_82599_e() * 1.125F);
            BlockPos lvt_11_1_ = p_82487_1_.func_180699_d().func_177972_a(lvt_3_1_);
            Material lvt_12_1_ = lvt_4_1_.func_180495_p(lvt_11_1_).func_177230_c().func_149688_o();
            double lvt_13_3_;
            if (Material.field_151586_h.equals(lvt_12_1_)) {
               lvt_13_3_ = 1.0D;
            } else {
               if (!Material.field_151579_a.equals(lvt_12_1_) || !Material.field_151586_h.equals(lvt_4_1_.func_180495_p(lvt_11_1_.func_177977_b()).func_177230_c().func_149688_o())) {
                  return this.field_150842_b.func_82482_a(p_82487_1_, p_82487_2_);
               }

               lvt_13_3_ = 0.0D;
            }

            EntityBoat lvt_15_1_ = new EntityBoat(lvt_4_1_, lvt_5_1_, lvt_7_1_ + lvt_13_3_, lvt_9_1_);
            lvt_4_1_.func_72838_d(lvt_15_1_);
            p_82487_2_.func_77979_a(1);
            return p_82487_2_;
         }

         protected void func_82485_a(IBlockSource p_82485_1_) {
            p_82485_1_.func_82618_k().func_175718_b(1000, p_82485_1_.func_180699_d(), 0);
         }
      });
      IBehaviorDispenseItem lvt_0_1_ = new BehaviorDefaultDispenseItem() {
         private final BehaviorDefaultDispenseItem field_150841_b = new BehaviorDefaultDispenseItem();

         public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            ItemBucket lvt_3_1_ = (ItemBucket)p_82487_2_.func_77973_b();
            BlockPos lvt_4_1_ = p_82487_1_.func_180699_d().func_177972_a(BlockDispenser.func_149937_b(p_82487_1_.func_82620_h()));
            if (lvt_3_1_.func_180616_a(p_82487_1_.func_82618_k(), lvt_4_1_)) {
               p_82487_2_.func_150996_a(Items.field_151133_ar);
               p_82487_2_.field_77994_a = 1;
               return p_82487_2_;
            } else {
               return this.field_150841_b.func_82482_a(p_82487_1_, p_82487_2_);
            }
         }
      };
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151129_at, lvt_0_1_);
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151131_as, lvt_0_1_);
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151133_ar, new BehaviorDefaultDispenseItem() {
         private final BehaviorDefaultDispenseItem field_150840_b = new BehaviorDefaultDispenseItem();

         public ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            World lvt_3_1_ = p_82487_1_.func_82618_k();
            BlockPos lvt_4_1_ = p_82487_1_.func_180699_d().func_177972_a(BlockDispenser.func_149937_b(p_82487_1_.func_82620_h()));
            IBlockState lvt_5_1_ = lvt_3_1_.func_180495_p(lvt_4_1_);
            Block lvt_6_1_ = lvt_5_1_.func_177230_c();
            Material lvt_7_1_ = lvt_6_1_.func_149688_o();
            Item lvt_8_3_;
            if (Material.field_151586_h.equals(lvt_7_1_) && lvt_6_1_ instanceof BlockLiquid && (Integer)lvt_5_1_.func_177229_b(BlockLiquid.field_176367_b) == 0) {
               lvt_8_3_ = Items.field_151131_as;
            } else {
               if (!Material.field_151587_i.equals(lvt_7_1_) || !(lvt_6_1_ instanceof BlockLiquid) || (Integer)lvt_5_1_.func_177229_b(BlockLiquid.field_176367_b) != 0) {
                  return super.func_82487_b(p_82487_1_, p_82487_2_);
               }

               lvt_8_3_ = Items.field_151129_at;
            }

            lvt_3_1_.func_175698_g(lvt_4_1_);
            if (--p_82487_2_.field_77994_a == 0) {
               p_82487_2_.func_150996_a(lvt_8_3_);
               p_82487_2_.field_77994_a = 1;
            } else if (((TileEntityDispenser)p_82487_1_.func_150835_j()).func_146019_a(new ItemStack(lvt_8_3_)) < 0) {
               this.field_150840_b.func_82482_a(p_82487_1_, new ItemStack(lvt_8_3_));
            }

            return p_82487_2_;
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151033_d, new BehaviorDefaultDispenseItem() {
         private boolean field_150839_b = true;

         protected ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            World lvt_3_1_ = p_82487_1_.func_82618_k();
            BlockPos lvt_4_1_ = p_82487_1_.func_180699_d().func_177972_a(BlockDispenser.func_149937_b(p_82487_1_.func_82620_h()));
            if (lvt_3_1_.func_175623_d(lvt_4_1_)) {
               lvt_3_1_.func_175656_a(lvt_4_1_, Blocks.field_150480_ab.func_176223_P());
               if (p_82487_2_.func_96631_a(1, lvt_3_1_.field_73012_v)) {
                  p_82487_2_.field_77994_a = 0;
               }
            } else if (lvt_3_1_.func_180495_p(lvt_4_1_).func_177230_c() == Blocks.field_150335_W) {
               Blocks.field_150335_W.func_176206_d(lvt_3_1_, lvt_4_1_, Blocks.field_150335_W.func_176223_P().func_177226_a(BlockTNT.field_176246_a, true));
               lvt_3_1_.func_175698_g(lvt_4_1_);
            } else {
               this.field_150839_b = false;
            }

            return p_82487_2_;
         }

         protected void func_82485_a(IBlockSource p_82485_1_) {
            if (this.field_150839_b) {
               p_82485_1_.func_82618_k().func_175718_b(1000, p_82485_1_.func_180699_d(), 0);
            } else {
               p_82485_1_.func_82618_k().func_175718_b(1001, p_82485_1_.func_180699_d(), 0);
            }

         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151100_aR, new BehaviorDefaultDispenseItem() {
         private boolean field_150838_b = true;

         protected ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            if (EnumDyeColor.WHITE == EnumDyeColor.func_176766_a(p_82487_2_.func_77960_j())) {
               World lvt_3_1_ = p_82487_1_.func_82618_k();
               BlockPos lvt_4_1_ = p_82487_1_.func_180699_d().func_177972_a(BlockDispenser.func_149937_b(p_82487_1_.func_82620_h()));
               if (ItemDye.func_179234_a(p_82487_2_, lvt_3_1_, lvt_4_1_)) {
                  if (!lvt_3_1_.field_72995_K) {
                     lvt_3_1_.func_175718_b(2005, lvt_4_1_, 0);
                  }
               } else {
                  this.field_150838_b = false;
               }

               return p_82487_2_;
            } else {
               return super.func_82487_b(p_82487_1_, p_82487_2_);
            }
         }

         protected void func_82485_a(IBlockSource p_82485_1_) {
            if (this.field_150838_b) {
               p_82485_1_.func_82618_k().func_175718_b(1000, p_82485_1_.func_180699_d(), 0);
            } else {
               p_82485_1_.func_82618_k().func_175718_b(1001, p_82485_1_.func_180699_d(), 0);
            }

         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Item.func_150898_a(Blocks.field_150335_W), new BehaviorDefaultDispenseItem() {
         protected ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            World lvt_3_1_ = p_82487_1_.func_82618_k();
            BlockPos lvt_4_1_ = p_82487_1_.func_180699_d().func_177972_a(BlockDispenser.func_149937_b(p_82487_1_.func_82620_h()));
            EntityTNTPrimed lvt_5_1_ = new EntityTNTPrimed(lvt_3_1_, (double)lvt_4_1_.func_177958_n() + 0.5D, (double)lvt_4_1_.func_177956_o(), (double)lvt_4_1_.func_177952_p() + 0.5D, (EntityLivingBase)null);
            lvt_3_1_.func_72838_d(lvt_5_1_);
            lvt_3_1_.func_72956_a(lvt_5_1_, "game.tnt.primed", 1.0F, 1.0F);
            --p_82487_2_.field_77994_a;
            return p_82487_2_;
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Items.field_151144_bL, new BehaviorDefaultDispenseItem() {
         private boolean field_179240_b = true;

         protected ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            World lvt_3_1_ = p_82487_1_.func_82618_k();
            EnumFacing lvt_4_1_ = BlockDispenser.func_149937_b(p_82487_1_.func_82620_h());
            BlockPos lvt_5_1_ = p_82487_1_.func_180699_d().func_177972_a(lvt_4_1_);
            BlockSkull lvt_6_1_ = Blocks.field_150465_bP;
            if (lvt_3_1_.func_175623_d(lvt_5_1_) && lvt_6_1_.func_176415_b(lvt_3_1_, lvt_5_1_, p_82487_2_)) {
               if (!lvt_3_1_.field_72995_K) {
                  lvt_3_1_.func_180501_a(lvt_5_1_, lvt_6_1_.func_176223_P().func_177226_a(BlockSkull.field_176418_a, EnumFacing.UP), 3);
                  TileEntity lvt_7_1_ = lvt_3_1_.func_175625_s(lvt_5_1_);
                  if (lvt_7_1_ instanceof TileEntitySkull) {
                     if (p_82487_2_.func_77960_j() == 3) {
                        GameProfile lvt_8_1_ = null;
                        if (p_82487_2_.func_77942_o()) {
                           NBTTagCompound lvt_9_1_ = p_82487_2_.func_77978_p();
                           if (lvt_9_1_.func_150297_b("SkullOwner", 10)) {
                              lvt_8_1_ = NBTUtil.func_152459_a(lvt_9_1_.func_74775_l("SkullOwner"));
                           } else if (lvt_9_1_.func_150297_b("SkullOwner", 8)) {
                              String lvt_10_1_ = lvt_9_1_.func_74779_i("SkullOwner");
                              if (!StringUtils.func_151246_b(lvt_10_1_)) {
                                 lvt_8_1_ = new GameProfile((UUID)null, lvt_10_1_);
                              }
                           }
                        }

                        ((TileEntitySkull)lvt_7_1_).func_152106_a(lvt_8_1_);
                     } else {
                        ((TileEntitySkull)lvt_7_1_).func_152107_a(p_82487_2_.func_77960_j());
                     }

                     ((TileEntitySkull)lvt_7_1_).func_145903_a(lvt_4_1_.func_176734_d().func_176736_b() * 4);
                     Blocks.field_150465_bP.func_180679_a(lvt_3_1_, lvt_5_1_, (TileEntitySkull)lvt_7_1_);
                  }

                  --p_82487_2_.field_77994_a;
               }
            } else {
               this.field_179240_b = false;
            }

            return p_82487_2_;
         }

         protected void func_82485_a(IBlockSource p_82485_1_) {
            if (this.field_179240_b) {
               p_82485_1_.func_82618_k().func_175718_b(1000, p_82485_1_.func_180699_d(), 0);
            } else {
               p_82485_1_.func_82618_k().func_175718_b(1001, p_82485_1_.func_180699_d(), 0);
            }

         }
      });
      BlockDispenser.field_149943_a.func_82595_a(Item.func_150898_a(Blocks.field_150423_aK), new BehaviorDefaultDispenseItem() {
         private boolean field_179241_b = true;

         protected ItemStack func_82487_b(IBlockSource p_82487_1_, ItemStack p_82487_2_) {
            World lvt_3_1_ = p_82487_1_.func_82618_k();
            BlockPos lvt_4_1_ = p_82487_1_.func_180699_d().func_177972_a(BlockDispenser.func_149937_b(p_82487_1_.func_82620_h()));
            BlockPumpkin lvt_5_1_ = (BlockPumpkin)Blocks.field_150423_aK;
            if (lvt_3_1_.func_175623_d(lvt_4_1_) && lvt_5_1_.func_176390_d(lvt_3_1_, lvt_4_1_)) {
               if (!lvt_3_1_.field_72995_K) {
                  lvt_3_1_.func_180501_a(lvt_4_1_, lvt_5_1_.func_176223_P(), 3);
               }

               --p_82487_2_.field_77994_a;
            } else {
               this.field_179241_b = false;
            }

            return p_82487_2_;
         }

         protected void func_82485_a(IBlockSource p_82485_1_) {
            if (this.field_179241_b) {
               p_82485_1_.func_82618_k().func_175718_b(1000, p_82485_1_.func_180699_d(), 0);
            } else {
               p_82485_1_.func_82618_k().func_175718_b(1001, p_82485_1_.func_180699_d(), 0);
            }

         }
      });
   }

   public static void func_151354_b() {
      if (!field_151355_a) {
         field_151355_a = true;
         if (field_179871_c.isDebugEnabled()) {
            func_179868_d();
         }

         Block.func_149671_p();
         BlockFire.func_149843_e();
         Item.func_150900_l();
         StatList.func_151178_a();
         func_151353_a();
      }
   }

   private static void func_179868_d() {
      System.setErr(new LoggingPrintStream("STDERR", System.err));
      System.setOut(new LoggingPrintStream("STDOUT", field_179872_a));
   }

   public static void func_179870_a(String p_179870_0_) {
      field_179872_a.println(p_179870_0_);
   }

   static {
      field_179872_a = System.out;
      field_151355_a = false;
      field_179871_c = LogManager.getLogger();
   }
}
