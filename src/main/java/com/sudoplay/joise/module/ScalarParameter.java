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

package com.sudoplay.joise.module;

public class ScalarParameter {

  private Module module;
  private double value;

  public ScalarParameter(Module source) {
    this.set(source);
  }

  public ScalarParameter(double source) {
    this.set(source);
  }

  public void set(Module source) {
    this.module = source;
  }

  public void set(double source) {
    this.module = null;
    this.value = source;
  }

  public void set(ScalarParameter scalarParameter) {

    if (scalarParameter.isModule()) {
      this.set(scalarParameter.getModule());

    } else {
      this.set(scalarParameter.getValue());
    }
  }

  public boolean isModule() {
    return this.module != null;
  }

  public Module getModule() {
    return this.module;
  }

  public double getValue() {
    return this.value;
  }

  public double get(double x, double y) {

    if (this.module != null) {
      return this.module.get(x, y);
    }
    return this.value;
  }

  public double get(double x, double y, double z) {

    if (this.module != null) {
      return this.module.get(x, y, z);
    }
    return this.value;
  }

  public double get(double x, double y, double z, double w) {

    if (this.module != null) {
      return this.module.get(x, y, z, w);
    }
    return this.value;
  }

  public double get(double x, double y, double z, double w, double u, double v) {

    if (this.module != null) {
      return this.module.get(x, y, z, w, u, v);
    }
    return this.value;
  }

  public void setSeed(String seedName, long seed) {

    if (this.module != null) {
      this.module.setSeed(seedName, seed);
    }
  }

  @Override
  public String toString() {

    if (this.module != null) {
      return this.module.getId();

    } else {
      return String.valueOf(this.value);
    }
  }
}
