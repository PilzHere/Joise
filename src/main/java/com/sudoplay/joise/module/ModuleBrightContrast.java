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

import com.sudoplay.joise.ModuleInstanceMap;
import com.sudoplay.joise.ModuleMap;
import com.sudoplay.joise.ModulePropertyMap;

public class ModuleBrightContrast extends
    SourcedModule {

  private ScalarParameter bright = new ScalarParameter(0.0);
  private ScalarParameter threshold = new ScalarParameter(0.0);
  private ScalarParameter factor = new ScalarParameter(1.0);

  public void setBrightness(double b) {
    this.bright.set(b);
  }

  public void setContrastThreshold(double t) {
    this.threshold.set(t);
  }

  public void setContrastFactor(double f) {
    this.factor.set(f);
  }

  @SuppressWarnings("unused")
  public void setBrightness(Module source) {
    this.bright.set(source);
  }

  public void setContrastThreshold(Module source) {
    this.threshold.set(source);
  }

  public void setContrastFactor(Module source) {
    this.factor.set(source);
  }

  @SuppressWarnings("unused")
  public void setBrightness(ScalarParameter scalarParameter) {
    this.bright.set(scalarParameter);
  }

  @SuppressWarnings("unused")
  public void setContrastThreshold(ScalarParameter scalarParameter) {
    this.threshold.set(scalarParameter);
  }

  @SuppressWarnings("unused")
  public void setContrastFactor(ScalarParameter scalarParameter) {
    this.factor.set(scalarParameter);
  }

  @Override
  public double get(double x, double y) {
    double val = this.source.get(x, y);
    // apply brightness
    val += this.bright.get(x, y);
    // subtract threshold, scale by factor, add threshold
    double t = this.threshold.get(x, y);
    val -= t;
    val *= this.factor.get(x, y);
    val += t;
    return val;
  }

  @Override
  public double get(double x, double y, double z) {
    double val = this.source.get(x, y, z);
    // apply brightness
    val += this.bright.get(x, y, z);
    // subtract threshold, scale by factor, add threshold
    double t = this.threshold.get(x, y, z);
    val -= t;
    val *= this.factor.get(x, y, z);
    val += t;
    return val;
  }

  @Override
  public double get(double x, double y, double z, double w) {
    double val = this.source.get(x, y, z, w);
    // apply brightness
    val += this.bright.get(x, y, z, w);
    // subtract threshold, scale by factor, add threshold
    double t = this.threshold.get(x, y, z, w);
    val -= t;
    val *= this.factor.get(x, y, z, w);
    val += t;
    return val;
  }

  @Override
  public double get(double x, double y, double z, double w, double u, double v) {
    double val = this.source.get(x, y, z, w, u, v);
    // apply brightness
    val += this.bright.get(x, y, z, w, u, v);
    // subtract threshold, scale by factor, add threshold
    double t = this.threshold.get(x, y, z, w, u, v);
    val -= t;
    val *= this.factor.get(x, y, z, w, u, v);
    val += t;
    return val;
  }

  @Override
  public void setSeed(String seedName, long seed) {
    super.setSeed(seedName, seed);
    this.bright.setSeed(seedName, seed);
    this.factor.setSeed(seedName, seed);
    this.threshold.setSeed(seedName, seed);
  }

  @Override
  public void writeToMap(ModuleMap moduleMap) {
    ModulePropertyMap modulePropertyMap = new ModulePropertyMap(this);
    modulePropertyMap
        .writeScalar("brightness", this.bright, moduleMap)
        .writeScalar("contrastFactor", this.factor, moduleMap)
        .writeScalar("contrastThreshold", this.threshold, moduleMap)
        .writeScalar("source", this.source, moduleMap);
    moduleMap.put(this.getId(), modulePropertyMap);
  }

  @Override
  public Module buildFromPropertyMap(ModulePropertyMap modulePropertyMap, ModuleInstanceMap moduleInstanceMap) {
    this.setBrightness(modulePropertyMap.readScalar("brightness", moduleInstanceMap));
    this.setContrastFactor(modulePropertyMap.readScalar("contrastFactor", moduleInstanceMap));
    this.setContrastThreshold(modulePropertyMap.readScalar("contrastThreshold", moduleInstanceMap));
    this.setSource(modulePropertyMap.readScalar("source", moduleInstanceMap));
    return this;
  }
}
