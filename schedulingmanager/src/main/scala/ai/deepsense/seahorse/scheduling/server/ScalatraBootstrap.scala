/**
 * Copyright 2016 deepsense.ai (CodiLime, Inc)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ai.deepsense.seahorse.scheduling.server

import javax.servlet.ServletContext

import scala.util.control.NonFatal

import org.scalatra.LifeCycle

import ai.deepsense.seahorse.scheduling.api.SchedulingManagerApi

class ScalatraBootstrap extends LifeCycle {

  override def init(context: ServletContext): Unit = {
    try {
      context mount (new SchedulingManagerApi(), "/*")
    } catch {
      case NonFatal(e) => e.printStackTrace()
    }
  }

}
