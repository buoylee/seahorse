'use strict';

var angular = require('angular');

var common = angular.module('ds.common', []);

require('./navigation-bar/navigation-bar.module.js').inject(common);
require('./api-clients/api-clients.module.js').inject(common);
require('./services/services.module.js').inject(common);
require('./sticky-window/sticky-window.js').inject(common);
require('./services/uuid-generator.js').inject(common);
require('./modals/modals.module.js').inject(common);
require('./dots/dots.js').inject(common);
require('./toggle/toggle.js').inject(common);
require('./resizable/resizable.js').inject(common);
require('./resizable/resizable-listener.js').inject(common);
require('./filters/cut-words.js').inject(common);

module.exports = common;
