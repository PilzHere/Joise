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
import com.sudoplay.joise.noise.NoiseLUT;
import com.sudoplay.joise.noise.function.spi.Function6D;
import com.sudoplay.joise.util.Bits;

public class Function6DWhite implements
    Function6D {

  private byte[] buffer;

  public Function6DWhite() {
    this.buffer = new byte[56];
  }

  @Override
  public double get(double x, double y, double z, double w, double u, double v, long seed, Interpolator interpolator) {
    Bits.doubleToByteArray(x, this.buffer, 0);
    Bits.doubleToByteArray(y, this.buffer, 8);
    Bits.doubleToByteArray(z, this.buffer, 16);
    Bits.doubleToByteArray(w, this.buffer, 24);
    Bits.doubleToByteArray(u, this.buffer, 32);
    Bits.doubleToByteArray(v, this.buffer, 40);
    Bits.longToByteArray(seed, this.buffer, 48);
    int hash = Noise.xorFoldHash(Noise.fnv32ABuf(this.buffer));
    return NoiseLUT.whitenoiseLUT[hash];
  }
}
