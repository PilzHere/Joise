package com.sudoplay.joise.examples.module.basis;

import com.sudoplay.joise.examples.SplitCanvas;

import static com.sudoplay.joise.module.ModuleBasisFunction.BasisType;
import static com.sudoplay.joise.module.ModuleBasisFunction.InterpolationType;

/**
 * Created by codetaylor on 2/8/2017.
 */
public class ModuleBasisFunctionGradvalExample extends
    AbstractModuleBasisFunctionExample {

  static {
    EXAMPLE_CLASS = ModuleBasisFunctionGradvalExample.class;
  }

  @Override
  protected String getWindowTitle() {
    return "ModuleBasisFunction Gradval Example";
  }

  @Override
  protected void run(SplitCanvas canvas) {

    BasisType basisType = BasisType.GRADVAL;

    canvas.updateImage(
        "none", createModuleBasisFunction(
            basisType,
            InterpolationType.NONE
        ),
        "linear", createModuleBasisFunction(
            basisType,
            InterpolationType.LINEAR
        ),
        "cubic", createModuleBasisFunction(
            basisType,
            InterpolationType.CUBIC
        ),
        "quintic", createModuleBasisFunction(
            basisType,
            InterpolationType.QUINTIC
        )
    );
  }
}
