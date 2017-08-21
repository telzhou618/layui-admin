/*
 * @Author: Paco
 * @Date:   2017-02-15
 * +----------------------------------------------------------------------
 * | jqadmin [ jq酷打造的一款懒人后台模板 ]
 * | Copyright (c) 2017 http://jqadmin.jqcool.net All rights reserved.
 * | Licensed ( http://jqadmin.jqcool.net/licenses/ )
 * | Author: Paco <admin@jqcool.net>
 * +----------------------------------------------------------------------
 */

layui.define(['jquery', 'layer'], function(exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        old_text = "",
        tags = function() {};

    /**
     *@todo 初始化方法
     */
    tags.prototype.init = function() {
        var _this = this;
        //绑定自定义按钮单击事件
        $(".tag-defined:not([tag-bind]),.tag:not([tag-bind])").each(function() {
            _this.setBind($(this));
            $(this).bind('click', { tags: _this }, _this.click);
        });
    }


    /**
     *@todo 为已绑定事件元素设置绑定属性，防止再次初始化时重复绑定
     *@param obj 当前对象
     */
    tags.prototype.setBind = function(obj) {
        obj.attr('tag-bind', 1);
    }

    /**
     *@todo 添加自定义标签
     */
    tags.prototype.addTag = function(obj) {
        var text = $.trim(obj.val()),
            _this = this;
        if (text != "") {
            if (text.indexOf(",") >= 0) {
                layer.msg('标签不能包含","');
                old_text = text;
                return;
            }

            if (text.indexOf("|") >= 0) {
                layer.msg('标签不能包含"|"');
                old_text = text;
                return;
            }

            var len = text.length;
            if (len > 10) {
                layer.msg('您最多只能输入10个字符');
                old_text = text;
                return;
            }

            if (Number(text)) {
                layer.msg('标签不能纯数字');
                old_text = '';
                return;
            }

            var tag = '<div class="tag" data-id="0"><p class="text">' + text + '</p><p class="tick-box"><span class="tick-bg"></span><i class="iconfont tick">&#xe6a1;</i></p></div>';
            obj.parent('div.tag-defined').before(tag).empty().css({ "padding": "0 20px" }).html("自定义标签").prev('.tag').bind('click', { tags: _this }, _this.click).trigger('click');
            old_text = "";
        } else {
            obj.parent('div').empty().css({ "padding": "0 20px" }).html("自定义标签");
        }
    }

    /**
     *@todo 生成文本框
     */
    tags.prototype.text = function(obj) {
        var _this = this;
        var input = $('<input type="text" />').css({ "width": "112px", "height": "36px", "border": "none", "outline": "none", "text-align": "center" });
        obj.css({ "padding": "0" }).html(input);
        input.focus().val(old_text);

        //失去焦点时触发
        input.blur(function() {
            _this.addTag($(this));
        });

        input.keyup(function(event) {
            if (event.which == 13) {
                _this.addTag($(this));
                return false;
            }
        });
    }

    /**
     *@todo 自定义单击事件
     */
    tags.prototype.click = function(event) {
        _tags = event.data.tags;
        if ($(this).attr('data-id') == undefined) {
            _tags.text($(this));
        } else {
            _tags.select($(this));
        }

    }

    /**
     *@todo 选中标签
     */
    tags.prototype.select = function(obj) {
        var val = obj.attr('data-id') + "|" + $.trim(obj.find('.text').text());
        if (obj.hasClass('tag-selected')) {
            obj.removeClass('tag-selected');
            obj.find('.tick-box').hide();
            this.setval(obj, val, "del");
        } else {
            obj.addClass('tag-selected');
            obj.find(".tick-box").show();
            this.setval(obj, val, "add");
        }
    }

    /**
     *@todo 根椐选择的标签设定隐藏域的值
     */
    tags.prototype.setval = function(obj, val, sence) {

        var _this = obj.parent('div.layui-input-block').find('input[type=hidden]'),
            tag_val = _this.val(),
            count = obj.parent('div.layui-input-block').find('.tag-selected').length;

        if (count > parseInt(_this.data('count'))) {
            layer.msg('最多只能选择5个标签');
            obj.removeClass('tag-selected');
            obj.find('.tick-box').hide();
            return
        }

        if ("" != tag_val) {
            var _tags = tag_val.split(",");
            if (sence == "del") {
                _tags.remove(val);
            } else {
                _tags.push(val);
            }
            val = _tags.join(",");
        }

        _this.val(val);
        return;
    }

    /**
     *根据值查找所在位置index值，查不到就返回-1
     */
    Array.prototype.indexOf = function(val) {
        for (var i = 0; i < this.length; i++) {
            if (this[i] == val) return i;
        }
        return -1;
    };


    /**
     *根椐值删除元素
     */
    Array.prototype.remove = function(val) {
        var index = this.indexOf(val);
        if (index > -1) {
            this.splice(index, 1);
        }
    };

    var tags = new tags();
    tags.init();
    exports('tags', tags);
});