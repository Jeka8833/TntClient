package net.minecraft.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.client.resources.model.WeightedBakedModel;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.WorldType;

public class BlockRendererDispatcher implements IResourceManagerReloadListener {
   private BlockModelShapes field_175028_a;
   private final GameSettings field_175026_b;
   private final BlockModelRenderer field_175027_c = new BlockModelRenderer();
   private final ChestRenderer field_175024_d = new ChestRenderer();
   private final BlockFluidRenderer field_175025_e = new BlockFluidRenderer();

   public BlockRendererDispatcher(BlockModelShapes p_i46237_1_, GameSettings p_i46237_2_) {
      this.field_175028_a = p_i46237_1_;
      this.field_175026_b = p_i46237_2_;
   }

   public BlockModelShapes func_175023_a() {
      return this.field_175028_a;
   }

   public void func_175020_a(IBlockState p_175020_1_, BlockPos p_175020_2_, TextureAtlasSprite p_175020_3_, IBlockAccess p_175020_4_) {
      Block lvt_5_1_ = p_175020_1_.func_177230_c();
      int lvt_6_1_ = lvt_5_1_.func_149645_b();
      if (lvt_6_1_ == 3) {
         p_175020_1_ = lvt_5_1_.func_176221_a(p_175020_1_, p_175020_4_, p_175020_2_);
         IBakedModel lvt_7_1_ = this.field_175028_a.func_178125_b(p_175020_1_);
         IBakedModel lvt_8_1_ = (new SimpleBakedModel.Builder(lvt_7_1_, p_175020_3_)).func_177645_b();
         this.field_175027_c.func_178259_a(p_175020_4_, lvt_8_1_, p_175020_1_, p_175020_2_, Tessellator.func_178181_a().func_178180_c());
      }
   }

   public boolean func_175018_a(IBlockState p_175018_1_, BlockPos p_175018_2_, IBlockAccess p_175018_3_, WorldRenderer p_175018_4_) {
      try {
         int lvt_5_1_ = p_175018_1_.func_177230_c().func_149645_b();
         if (lvt_5_1_ == -1) {
            return false;
         } else {
            switch(lvt_5_1_) {
            case 1:
               return this.field_175025_e.func_178270_a(p_175018_3_, p_175018_1_, p_175018_2_, p_175018_4_);
            case 2:
               return false;
            case 3:
               IBakedModel lvt_6_1_ = this.func_175022_a(p_175018_1_, p_175018_3_, p_175018_2_);
               return this.field_175027_c.func_178259_a(p_175018_3_, lvt_6_1_, p_175018_1_, p_175018_2_, p_175018_4_);
            default:
               return false;
            }
         }
      } catch (Throwable var8) {
         CrashReport lvt_6_2_ = CrashReport.func_85055_a(var8, "Tesselating block in world");
         CrashReportCategory lvt_7_1_ = lvt_6_2_.func_85058_a("Block being tesselated");
         CrashReportCategory.func_180523_a(lvt_7_1_, p_175018_2_, p_175018_1_.func_177230_c(), p_175018_1_.func_177230_c().func_176201_c(p_175018_1_));
         throw new ReportedException(lvt_6_2_);
      }
   }

   public BlockModelRenderer func_175019_b() {
      return this.field_175027_c;
   }

   private IBakedModel func_175017_a(IBlockState p_175017_1_, BlockPos p_175017_2_) {
      IBakedModel lvt_3_1_ = this.field_175028_a.func_178125_b(p_175017_1_);
      if (p_175017_2_ != null && this.field_175026_b.field_178880_u && lvt_3_1_ instanceof WeightedBakedModel) {
         lvt_3_1_ = ((WeightedBakedModel)lvt_3_1_).func_177564_a(MathHelper.func_180186_a(p_175017_2_));
      }

      return lvt_3_1_;
   }

   public IBakedModel func_175022_a(IBlockState p_175022_1_, IBlockAccess p_175022_2_, BlockPos p_175022_3_) {
      Block lvt_4_1_ = p_175022_1_.func_177230_c();
      if (p_175022_2_.func_175624_G() != WorldType.field_180272_g) {
         try {
            p_175022_1_ = lvt_4_1_.func_176221_a(p_175022_1_, p_175022_2_, p_175022_3_);
         } catch (Exception var6) {
         }
      }

      IBakedModel lvt_5_1_ = this.field_175028_a.func_178125_b(p_175022_1_);
      if (p_175022_3_ != null && this.field_175026_b.field_178880_u && lvt_5_1_ instanceof WeightedBakedModel) {
         lvt_5_1_ = ((WeightedBakedModel)lvt_5_1_).func_177564_a(MathHelper.func_180186_a(p_175022_3_));
      }

      return lvt_5_1_;
   }

   public void func_175016_a(IBlockState p_175016_1_, float p_175016_2_) {
      int lvt_3_1_ = p_175016_1_.func_177230_c().func_149645_b();
      if (lvt_3_1_ != -1) {
         switch(lvt_3_1_) {
         case 1:
         default:
            break;
         case 2:
            this.field_175024_d.func_178175_a(p_175016_1_.func_177230_c(), p_175016_2_);
            break;
         case 3:
            IBakedModel lvt_4_1_ = this.func_175017_a(p_175016_1_, (BlockPos)null);
            this.field_175027_c.func_178266_a(lvt_4_1_, p_175016_1_, p_175016_2_, true);
         }

      }
   }

   public boolean func_175021_a(Block p_175021_1_, int p_175021_2_) {
      if (p_175021_1_ == null) {
         return false;
      } else {
         int lvt_3_1_ = p_175021_1_.func_149645_b();
         if (lvt_3_1_ == 3) {
            return false;
         } else {
            return lvt_3_1_ == 2;
         }
      }
   }

   public void func_110549_a(IResourceManager p_110549_1_) {
      this.field_175025_e.func_178268_a();
   }
}
