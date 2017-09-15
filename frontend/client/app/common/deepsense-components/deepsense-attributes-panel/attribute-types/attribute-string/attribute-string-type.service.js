/**
 * Copyright 2017, deepsense.ai
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

'use strict';

function StringParamTypeService() {
  this.setupStringValueBuffer = function (scope) {
    scope.valueBuffer = scope.parameter.value;
    scope.$watch('valueBuffer', function (newValue) {
      if (newValue === '') {
        scope.parameter.value = null;
      } else {
        scope.parameter.value = newValue;
      }
    });
  };
}

angular.module('deepsense.attributes-panel')
  .service('StringParamTypeService', StringParamTypeService);