/*
 * @Author: Paco
 * @Date:   2017-02-08
 * @lastmodify 2017-04-04
 * +----------------------------------------------------------------------
 * | jqadmin [ jq酷打造的一款懒人后台模板 ]
 * | Copyright (c) 2017 http://jqadmin.jqcool.net All rights reserved.
 * | Licensed ( http://jqadmin.jqcool.net/licenses/ )
 * | Author: Paco <admin@jqcool.net>
 * +----------------------------------------------------------------------
 */

layui.define(['jquery', 'layer', 'form'], function(exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form(),
        modal = function() {
            this.options = {
                type: 1,
                title: false,
                full: false,
                maxmin: true,
                shadeClose: true,
                shade: 0.3,
                content: "",
                area: 'auto',
                anim: 5,
            };
        };

    /**
     *@todo 初始化方法
     */
    modal.prototype.init = function() {
        var _this = this;
        //绑定模态iframe
        $(".modal-iframe:not([modal-bind])").each(function() {
            _this.setBind($(this));
            $(this).bind('click', _this.iframe);
        });

        //绑定模态捕获页
        $(".modal-catch:not([modal-bind])").each(function() {
            _this.setBind($(this));
            $(this).bind('click', _this.catch);
        });
        //绑定相册
        $(".modal-photo:not([modal-bind])").each(function() {
            _this.setBind($(this));
            _this.photos;
        });
    }


    /**
     *@todo 为已绑定事件元素设置绑定属性，防止再次初始化时重复绑定
     *@param obj 当前对象
     */
    modal.prototype.setBind = function(obj) {
        obj.attr('modal-bind', 1);
    }

    /**
     *@todo 合并参数
     *@param obj 当前对象
     */
    modal.prototype.setOptions = function(obj) {

        var params = obj.data('params');
        if (undefined == params || null == params) {
            layer.msg('请为当前元素配置data-garams属性');
            return;
        }
        if (typeof params === 'string') {
            params = JSON.parse(params)
        }

        var options = $.extend({}, this.options, params);

        return options;
    }


    /**
     *@todo 打开iframe窗口
     */
    modal.prototype.iframe = function(event) {
        //取得参数并设置
        var options = modal.setOptions($(this));
        if (undefined == options.content || null == options.content) {
            layer.msg('请为当前元素配置的data-garams属性配置content值');
            return false;
        };



        var _area = "auto";
        if (options.area != "auto") {
        	
            var width = parseInt(options.area),
                _area = width + "px";
            
            if(options.area.indexOf(',') > 0){
            	var wh = options.area.split(',');
            	_area = [wh[0],wh[1]];
            }
            
            dpr = window.devicePixelRatio,
                maxWidth = $(window).width() - 20 * dpr;
            if (width > maxWidth) {
                _area = (maxWidth) + "px";
            }
        }

        if (options.full) {
            var l = layer.open({
                type: 2,
                title: options.title,
                shadeClose: options.shadeClose,
                maxmin: options.maxmin,
                shade: options.shade,
                content: options.content

            });
            layer.full(l);
        } else {
            var l = layer.open({
                type: 2,
                title: options.title,
                maxmin: options.maxmin,
                shadeClose: options.shadeClose,
                shade: options.shade,
                area: _area,
                content: options.content
            });
        }


    }

    /**
     *@todo 捕捉窗口
     */
    modal.prototype.catch = function() {

        //取得参数并设置
        var options = modal.setOptions($(this));
        if (undefined == options.content || null == options.content) {
            layer.msg('请为当前元素配置的data-garams属性配置content值');
            return;
        }

        var _area = "auto";
        if (options.area != "auto") {

            var width = parseInt(options.area),
                _area = width + "px";
            dpr = window.devicePixelRatio,
                maxWidth = $(window).width() - 20 * dpr;
            if (width > maxWidth) {
                _area = (maxWidth) + "px";
            }
        }
        //判断是否有表单存在，如果有就重置表单
        if ($(options.content).find("form").length > 0) {
            $(options.content).find("form")[0].reset();
            $(options.content).find("div").removeClass("has-warning");
            $(options.content).find(".jq-error,.error").remove();
            $(options.content).find(".defined-error,.imgbox").empty();

            $(options.content).children("form").data("key", null);
        }

        //动态改表单的提交地址
        if (undefined != options.data || null != options.data) {

            // if (options.data.indexOf('&')) {

            var data = options.data.split("&");
            for (var i = 0; i < data.length; i++) {
                var val = data[i].split("=");
                if (val[0] == "url") {
                    $(options.content).children("form").attr("action", val[1]);
                    continue;
                }
            }
        }

        if (options.subcat) {
            var filed = options.subcat.split("=");
            var str = '<input type="hidden" name="' + filed[0] + '" value="' + filed[1] + '" />';
            $(options.content).children("form").append(str);
        }

        //判断是否需要为表单填充数据
        if (options.key) {
            var inputHtml = "",
                dataKey = options.key.split("="),
                record = {};
            $(options.content).children("form").data("key", options.key);
            var dataName = $(this).data("data-name") ? $(this).data("name") : $(this).parents("table").data("name");
            var _data = layui.data(dataName),
                list = _data['cacheData'].list;
            for (var key in list) {
                if (list[key][dataKey[0]] == dataKey[1]) {
                    record = list[key];
                    break;
                }
            }
            for (var key in record) {
                var obj = $(options.content).find('input[name=' + key + ']');
                if (obj.length > 0) {
                    if (obj.prop('type') == "text" || obj.prop('type') == "hidden") {
                        obj.val(record[key]);
                    } else if (obj.prop('type') == "checkbox" || obj.prop('type') == "radio") {
                        obj.each(function(i, n) {
                            if ($(n).val() == record[key]) {
                                $(n).prop("checked", true);
                            }
                        })

                    }
                } else if ($(options.content).find('textarea[name=' + key + ']').length > 0) {
                    $(options.content).find('textarea[name=' + key + ']').val(record[key]);
                } else if ($(options.content).find('select[name=' + key + ']').length > 0) {
                    $(options.content).find('select[name=' + key + ']').val(record[key]);
                } else if ($(options.content).find('.imgbox[name=' + key + ']').length > 0) {
                    var img = "<img src='" + record[key] + "' alt='' />"
                    $(options.content).find('img[name=' + key + ']').append(img);
                } else {
                    inputHtml += '<input type="hidden" name="' + key + '" value="' + record[key] + '" />';
                }
            }
            $(options.content).children("form").append(inputHtml);
            form.render();
        }


        if (options.full) {
            var l = layer.open({
                type: options.type,
                title: options.title,
                shade: options.shade,
                shadeClose: options.shadeClose,
                content: $(options.content)
            });
            layer.full(l);
        } else {
            var l = layer.open({
                type: options.type,
                title: options.title,
                shade: options.shade,
                shadeClose: options.shadeClose,
                area: _area,
                content: $(options.content)
            });
        }
    }


    /**
     *@todo 相册窗口
     */

    modal.prototype.photos = function() {
        var options = modal.setOptions($(this));
        if (undefined == options.content || null == options.content) {
            layer.msg('请为当前元素配置的data-garams属性配置content值');
            return;
        }
        layer.photos({
            photos: options.content,
            anim: options.anim
        });
    }

    var modal = new modal();
    //modal.init();
    exports('modal', modal);
});