/**
 * Created by zhouliang on 14-11-13.
 */


define(function(require, exports, module) {

        var A = require("A");
        var B = require("B");
        function C(){

        };

        C.prototype.say = function(){

            var a = new A();
            var b = new B();
            a.say();
            b.say();
        }

        module.exports = C;

    }
);