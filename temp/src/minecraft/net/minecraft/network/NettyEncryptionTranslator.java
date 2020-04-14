package net.minecraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;

public class NettyEncryptionTranslator {
   private final Cipher field_150507_a;
   private byte[] field_150505_b = new byte[0];
   private byte[] field_150506_c = new byte[0];

   protected NettyEncryptionTranslator(Cipher p_i45140_1_) {
      this.field_150507_a = p_i45140_1_;
   }

   private byte[] func_150502_a(ByteBuf p_150502_1_) {
      int lvt_2_1_ = p_150502_1_.readableBytes();
      if (this.field_150505_b.length < lvt_2_1_) {
         this.field_150505_b = new byte[lvt_2_1_];
      }

      p_150502_1_.readBytes((byte[])this.field_150505_b, 0, lvt_2_1_);
      return this.field_150505_b;
   }

   protected ByteBuf func_150503_a(ChannelHandlerContext p_150503_1_, ByteBuf p_150503_2_) throws ShortBufferException {
      int lvt_3_1_ = p_150503_2_.readableBytes();
      byte[] lvt_4_1_ = this.func_150502_a(p_150503_2_);
      ByteBuf lvt_5_1_ = p_150503_1_.alloc().heapBuffer(this.field_150507_a.getOutputSize(lvt_3_1_));
      lvt_5_1_.writerIndex(this.field_150507_a.update(lvt_4_1_, 0, lvt_3_1_, lvt_5_1_.array(), lvt_5_1_.arrayOffset()));
      return lvt_5_1_;
   }

   protected void func_150504_a(ByteBuf p_150504_1_, ByteBuf p_150504_2_) throws ShortBufferException {
      int lvt_3_1_ = p_150504_1_.readableBytes();
      byte[] lvt_4_1_ = this.func_150502_a(p_150504_1_);
      int lvt_5_1_ = this.field_150507_a.getOutputSize(lvt_3_1_);
      if (this.field_150506_c.length < lvt_5_1_) {
         this.field_150506_c = new byte[lvt_5_1_];
      }

      p_150504_2_.writeBytes((byte[])this.field_150506_c, 0, this.field_150507_a.update(lvt_4_1_, 0, lvt_3_1_, this.field_150506_c));
   }
}
