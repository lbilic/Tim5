package ftn.isamrs.tim5.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import ftn.isamrs.tim5.model.System;

public class ScaleDTO implements Serializable {
    @NotNull
    List<Integer> scale;

    public ScaleDTO() {}

    public ScaleDTO(System system) {
        this.scale = system.getScale();
    }

    public ScaleDTO(@NotNull List<Integer> scale) {
        this.scale = scale;
    }

    public List<Integer> getScale() {
        return scale;
    }

    public void setScale(List<Integer> scale) {
        this.scale = scale;
    }

    @Override
    public String toString() {
        return "ScaleDTO{" +
                "scale=" + scale +
                '}';
    }
}
