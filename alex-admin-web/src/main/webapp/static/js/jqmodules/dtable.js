/*
 * @Author: Paco
 * @Date:   2017-02-10
 * @lastModify 2017-05-05
 * +----------------------------------------------------------------------
 * | jqadmin [ jq酷打造的一款懒人后台模板 ]
 * | Copyright (c) 2017 http://jqadmin.jqcool.net All rights reserved.
 * | Licensed ( http://jqadmin.jqcool.net/licenses/ )
 * | Author: Paco <admin@jqcool.net>
 * +----------------------------------------------------------------------
 */
layui.define(['jquery', 'laytpl', 'layer', 'modal', 'ajax', 'laypage', 'form'], function(exports) {
    var $ = layui.jquery,
        layer = layui.layer,
        device = layui.device(),
        tpl = layui.laytpl,
        modal = layui.modal,
        ajax = layui.ajax,
        form = layui.form(),
        laypage = layui.laypage,
        dtable = function() {
            this.options = {
                method: "GET",
                dataType: "json",
                timeout: 10000,
                cache: false,
                url: '',
                data: '',
                tplid: "list-tpl",
                listid: "list",
                pageid: "", //如果留空则不分页
                curr: 1,
                pages: 0, //分页的总页数，通过服务端获取
            };
        };

    /**
     *@todo 初始化数据
     */
    dtable.prototype.init = function(tplid) {
        this.setOption(tplid);
        var curr_page = parseInt(location.hash.replace('#!page=', ''));
        if (curr_page > 1) {
            this.setUrlPage(curr_page);
        }
        this.ajax();
        this.setFormUrl();
        modal.init();
    }

    /**
     *@设置请求参数
     *@param string id 渲染模板的ID
     *备注：模板需配置data-params属性，值需严格的json格式，如：'{"content": "add-article.html", "title": "{{item.title}}"}'
     */
    dtable.prototype.setOption = function(id) {
        if (undefined != id || null != id) {
            this.options.tplid = id;
        }
        var params = $("#" + this.options.tplid).data("params");
        if (typeof params === 'string') {
            params = JSON.parse(params)
        }
        this.options = $.extend({}, this.options, params);
        if (undefined == this.options.url || null == this.options.url) {
            this.options.url = window.location.pathname;
        }

        if (this.options.url.indexOf("_=") != -1) {
            this.options.url = this.options.url.replace(/_=\d+/, "_=" + new Date().getTime());
        } else {
            if (this.options.url.indexOf("?") != -1) {
                this.options.url = this.options.url + "&_=" + new Date().getTime();
            } else {
                this.options.url = this.options.url + "?_=" + new Date().getTime();
            }
        }
    }

    /**
     *@todo 设置url中page参数为当前点击的页数
     *@param int curr 需要跳转到的页数（即点击分页按钮后的页数）
     */
    dtable.prototype.setUrlPage = function(curr) {

        if (this.options.url.indexOf("page=") != -1) {
            this.options.url = this.options.url.replace(/page=\d+/, "page=" + curr);
        } else {
            if (this.options.url.indexOf("?") != -1) {
                this.options.url = this.options.url + "&page=" + curr;
            } else {
                this.options.url = this.options.url + "?page=" + curr;
            }
        }
        this.ajax(curr);
    }

    /**
     *@todo 把表单参数拼成url
     */
    dtable.prototype.setFormUrl = function() {
        var _this = this;
        form.on('submit(search)', function(data) {
            var params = $.param(data.field);
            if (params == "" || params == undefined) return false;
            var url = $(data.form).attr('action');
            if (url.indexOf("?") != -1) {
                _this.options.url = url + "&" + params;
            } else {
                _this.options.url = url + "?" + params;
            }
            _this.ajax(-1);
            return false;
        })
    }

    /**
     *@todo 向服务请求数据
     */
    dtable.prototype.ajax = function(curr) {
        var _this = this,
            l = layer.load(0);
        $.ajax({
            type: _this.options.method,
            url: _this.options.url,
            dataType: _this.options.dataType,
            data: _this.options.data,
            cache: _this.options.cache,
            timeout: _this.options.timeout,
            error: function(XMLHttpRequest, status, thrownError) {

                layer.close(l);
                layer.msg('网络繁忙，请稍后重试...');
                return;
            },
            success: function(ret) {
                layer.close(l);
                if (ret.status == 200) {
                    if (undefined == ret.data || "" == ret.data) {
                        return false;
                    }
                    _this.options.pages = ret.data.pages;

                    if (_this.options.dataName) {
                        layui.data(_this.options.dataName, {
                            key: "cacheData",
                            value: ret.data
                        });
                    }
                    _this.render(ret.data.records);
                    if ("" != _this.options.pageid && curr == undefined) {
                        _this.page(0);
                    }
                    if ("" != _this.options.pageid && curr === -1) {
                        _this.page(1);
                    }
                } else {
                    layer.msg(ret.msg);
                    return;
                }
            }
        });
    }

    dtable.prototype.sort = function(event) {
        var _this = event.data._this,
            filed = $(this).data('filed'),
            dataAsc = $(this).data('asc'),
            index = $(".sort").index($(this));

        var asc = function(prop) {
            return function(obj1, obj2) {
                var val1 = obj1[prop];
                var val2 = obj2[prop];
                if (!isNaN(Number(val1)) && !isNaN(Number(val2))) {
                    val1 = Number(val1);
                    val2 = Number(val2);
                }
                if (val1 < val2) {
                    return -1;
                } else if (val1 > val2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }

        var desc = function(prop) {
            return function(obj1, obj2) {
                var val1 = obj1[prop];
                var val2 = obj2[prop];
                if (!isNaN(Number(val1)) && !isNaN(Number(val2))) {
                    val1 = Number(val1);
                    val2 = Number(val2);
                }
                if (val1 < val2) {
                    return 1;
                } else if (val1 > val2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
        var _data = layui.data(_this.options.dataName);
        if (_data['cacheData']) {
            var _list = _data['cacheData'];
            if (dataAsc) {
                _list.list.sort(asc(filed));
            } else {
                _list.list.sort(desc(filed));
            }
            _this.render(_list);
            $($(".sort").get(index)).addClass('curr');
        }
    }


    dtable.prototype.bindSort = function() {
        var _this = this;
        $(".sort:not([bind])").each(function() {
            $(this).attr("bind", 1);
            $(this).on('click', { _this: _this }, _this.sort);
        });
    }


    dtable.prototype.bindEdit = function() {
        var _this = this;
        $(".edit:not([bind])").each(function() {
            $(this).attr("bind", 1);
            $(this).on('click', { _this: _this }, _this.edit);
        });
    }

    dtable.prototype.edit = function(event) {
        var _this = event.data._this,
            oldTxt = $.trim($(this).text()),
            td = $(this),
            list = JSON.parse(td.attr("data-list")),
            input = $("<input type='text' name='" + list.filed + "' value='" + oldTxt + "'/>");
        input.css({ "width": "100%", "height": "40px", "padding": "5px" })
        td.html(input);
        input.click(function() { return false; });
        input.select();
        //文本框失去焦点后提交内容，重新变为文本 
        input.blur(function() {
            var newtxt = $(this).val();
            //判断文本有没有修改 
            if (newtxt != oldTxt) {
                //异步修改数据
                td.on('change', { ajax: ajax, content: newtxt, field: list.filed }, ajax.click)
                td.trigger('change');
                td.html(newtxt);
            } else {
                td.html(oldTxt);
            }
        })
    }

    /**
     *@todo 数据绑定到模板视图
     *@param json data 后台取得的数据
     */
    dtable.prototype.render = function(data) {
        var _this = this;
        if ("" != data || undefined != data) {
            var getTpl = $('#' + _this.options.tplid).html();
            var obj = $('#' + _this.options.listid);
            tpl(getTpl).render(data, function(html) {
                obj.html(html);
            })

            //如果为移动端判断表格的高度有没有撑开，如果撑开则加宽表格            
            if (device.ios || device.android || device.weixin) {

                if (_this.options.tableWidth) {
                    obj.find("table").wrap('<div class="table-box"></div>');
                    obj.find("table").css("width", _this.options.tableWidth);
                    //绑定滑动事件
                    _this.touch(obj);
                } else {
                    var maxHeight = 0;
                    obj.find("table tr").each(function(i, o) {
                        var _trHeight = parseInt($(o).height());
                        if (_trHeight > maxHeight) {
                            maxHeight = _trHeight;
                        }
                    });

                    var dpr = window.devicePixelRatio,
                        imgHeight = 57;
                    if (obj.find("img").length > 0) {
                        imgHeight = parseInt(obj.find("img").height()) + 10 * dpr;
                    }

                    var H = imgHeight;
                    if (maxHeight > H) {
                        obj.find("table").wrap('<div class="table-box"></div>');
                        var count = n = maxHeight / 57;
                        while (count > 0 && maxHeight > H) {

                            var tableWithd = parseInt(obj.find("table").width());
                            tableWithd = tableWithd + 170 * n;
                            obj.find("table").css("width", tableWithd + "px")
                            var tmp = 0;
                            obj.find("table tr").each(function(i, o) {
                                var trHeight = parseInt($(o).height());
                                if (trHeight > tmp) {
                                    tmp = trHeight;
                                }
                            });
                            if (tmp > H) {
                                maxHeight = tmp;
                            } else {
                                maxHeight = 0;
                            }
                            count--;
                        }

                        //绑定滑动事件
                        _this.touch(obj);
                    }
                }
            }
        }

        _this.selectAll();
        modal.init();
        ajax.init(_this);
        form.render();
        _this.bindSort();
        _this.bindEdit();
    }

    dtable.prototype.touch = function(obj) {
        var boxWihtd = obj.find(".table-box").width(),
            tableWithd = parseInt(obj.find("table").width()),
            maxml = parseInt(tableWithd) - parseInt(boxWihtd) - 2;

        obj.find("table").on("touchstart", function(e) {
            //e.preventDefault();
            var touch = e.originalEvent.targetTouches[0] || e.originalEvent.changedTouches[0];
            var _x = touch.pageX - parseInt(obj.find("table").css("marginLeft")); //取得鼠标到标签左边left的距离

            obj.find("table").on("touchmove", function(event) {
                //event.preventDefault();
                var move = event.originalEvent.targetTouches[0] || event.originalEvent.changedTouches[0];
                x = move.pageX - _x;
                if (x > -2) {
                    x = -2;
                } else if (x < -maxml) {
                    x = -maxml;
                }
                obj.find("table").css({ "marginLeft": x });
            });

            obj.find("table").on("touchend", function(e) {
                obj.find("table").off("touchmove");
            })

            obj.find("table").on("touchcancel", function(e) {
                obj.find("table").off("touchmove");
            })
        })
    }

    dtable.prototype.selectAll = function() {
        var _this = this;
        form.on('checkbox(check)', function(data) {

            var checked = data.elem.checked,
                name = $(data.elem).data('name');
            $('#' + _this.options.listid).find("input[name=" + name + "]").prop("checked", function() {
                return checked;
            });
            form.render();
        });
    }

    dtable.prototype.page = function(init) {
        var _this = this;
        var nowpage = location.hash.replace('#!page=', '');
        if (init) {
            nowpage = 1;
        }
        laypage({
            cont: $(_this.options.pageid),
            pages: _this.options.pages,
            first: 1,
            last: _this.options.pages,
          //  prev: '<em><</em>',
          //  next: '<em>></em>',
            curr: nowpage, //获取hash值为fenye的当前页
            hash: 'page', //自定义hash值
            skip: true,
            jump: function(obj, first) {
                if (!first) {
                    _this.setUrlPage(obj.curr);
                }
            }
        });
    }

    exports('dtable', dtable);
});