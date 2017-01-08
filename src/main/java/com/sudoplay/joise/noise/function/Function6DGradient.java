/*
 * Copyright (C) 2016 Jason Taylor.
 * Released as open-source under the Apache License, Version 2.0.
 *
 * ============================================================================
 * | Joise
 * ============================================================================
 *
 * Copyright (C) 2016 Jason Taylor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ============================================================================
 * | Accidental Noise Library
 * | --------------------------------------------------------------------------
 * | Joise is a derivative work based on Josua Tippetts' C++ library:
 * | http://accidentalnoise.sourceforge.net/index.html
 * ============================================================================
 *
 * Copyright (C) 2011 Joshua Tippetts
 *
 *   This software is provided 'as-is', without any express or implied
 *   warranty.  In no event will the authors be held liable for any damages
 *   arising from the use of this software.
 *
 *   Permission is granted to anyone to use this software for any purpose,
 *   including commercial applications, and to alter it and redistribute it
 *   freely, subject to the following restrictions:
 *
 *   1. The origin of this software must not be misrepresented; you must not
 *      claim that you wrote the original software. If you use this software
 *      in a product, an acknowledgment in the product documentation would be
 *      appreciated but is not required.
 *   2. Altered source versions must be plainly marked as such, and must not be
 *      misrepresented as being the original software.
 *   3. This notice may not be removed or altered from any source distribution.
 */

package com.sudoplay.joise.noise.function;

import com.sudoplay.joise.noise.Interpolator;
import com.sudoplay.joise.noise.Noise;
import com.sudoplay.joise.noise.function.spi.Function6D;
import com.sudoplay.joise.noise.worker.WorkerNoise6Gradient;
import com.sudoplay.joise.noise.worker.spi.WorkerNoise6;

public class Function6DGradient implements
    Function6D {

  private WorkerNoise6 worker;

  public Function6DGradient() {
    this.worker = new WorkerNoise6Gradient();
  }

  @Override
  public double get(double x, double y, double z, double w, double u, double v, long seed, Interpolator interpolator) {
    int x0 = Noise.fastFloor(x);
    int y0 = Noise.fastFloor(y);
    int z0 = Noise.fastFloor(z);
    int w0 = Noise.fastFloor(w);
    int u0 = Noise.fastFloor(u);
    int v0 = Noise.fastFloor(v);

    int x1 = x0 + 1;
    int y1 = y0 + 1;
    int z1 = z0 + 1;
    int w1 = w0 + 1;
    int u1 = u0 + 1;
    int v1 = v0 + 1;

    double xs = interpolator.interpolate((x - (double) x0));
    double ys = interpolator.interpolate((y - (double) y0));
    double zs = interpolator.interpolate((z - (double) z0));
    double ws = interpolator.interpolate((w - (double) w0));
    double us = interpolator.interpolate((u - (double) u0));
    double vs = interpolator.interpolate((v - (double) v0));

    return Noise.interpolateXYZWUV6(x, y, z, w, u, v, xs, ys, zs, ws, us, vs, x0,
        x1, y0, y1, z0, z1, w0, w1, u0, u1, v0, v1, seed,
        this.worker);
  }
}
