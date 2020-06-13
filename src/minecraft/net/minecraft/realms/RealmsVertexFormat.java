package net.minecraft.realms;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;

public class RealmsVertexFormat {
    private VertexFormat v;

    public RealmsVertexFormat(VertexFormat p_i46456_1_) {
        this.v = p_i46456_1_;
    }

    public void from(VertexFormat p_from_1_) {
        this.v = p_from_1_;
    }

    public VertexFormat getVertexFormat() {
        return this.v;
    }

    public void clear() {
        this.v.clear();
    }

    public void addElement(RealmsVertexFormatElement p_addElement_1_) {
        this.from(this.v.func_181721_a(p_addElement_1_.getVertexFormatElement()));
    }

    public List<RealmsVertexFormatElement> getElements() {
        List<RealmsVertexFormatElement> list = new ArrayList();

        for (VertexFormatElement vertexformatelement : this.v.getElements()) {
            list.add(new RealmsVertexFormatElement(vertexformatelement));
        }

        return list;
    }


    public boolean equals(Object p_equals_1_) {
        return this.v.equals(p_equals_1_);
    }

    public int hashCode() {
        return this.v.hashCode();
    }

    public String toString() {
        return this.v.toString();
    }
}
