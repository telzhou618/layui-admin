/*
 * @Author: Paco
 * @Date:   2017-03-12
 * @lastModify 2017-05-08
 * +----------------------------------------------------------------------
 * | jqadmin [ jq酷打造的一款懒人后台模板 ]
 * | Copyright (c) 2017 http://jqadmin.jqcool.net All rights reserved.
 * | Licensed ( http://jqadmin.jqcool.net/licenses/ )
 * | Author: Paco <admin@jqcool.net>
 * +----------------------------------------------------------------------
 */

layui.define(['jquery', 'laytpl', 'tabmenu', 'layer', 'elem'], function(exports) {
    var $ = layui.jquery,
        tpl = layui.laytpl,
        element = layui.elem(),
        tabmenu = layui.tabmenu(),
        layer = layui.layer,
        init = true,
        jqmenu = function() {
            this.options = {
                method: "GET",
                dataType: "json",
                timeout: 10000,
                cache: false,
                url: '',
                data: '',
                tplid: "menu-tpl",
                listid: "menu",
                icon: false,
                isFresh: false
            };
        };

    /**
     *@todo 初始化数据
     */
    jqmenu.prototype.init = function(tplid) {
        this.setOption(tplid);
        this.openOldMenu();
    }

    /**
     *@设置请求参数
     *@param string id 渲染模板的ID
     *备注：模板需配置data-params属性，值需严格的json格式，如：'{"content": "add-article.html", "title": "{{item.title}}"}'
     */
    jqmenu.prototype.setOption = function(id) {
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
        this.ajax();
    }


    /**
     *@todo 向服务请求数据
     */
    jqmenu.prototype.ajax = function() {
        var _this = this,
            l = layer.load(1);
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
                    _this.render(ret.data);

                } else {
                    layer.msg('请求数据出错，请稍后再试...');
                    return;
                }
            }
        });
    }

    /**
     *@todo 数据绑定到模板视图
     *@param json data 后台取得的数据
     */
    jqmenu.prototype.render = function(data) {

        var _this = this;
        if ("" != data || undefined != data) {
            var getTpl = $('#' + _this.options.tplid).html();
            tpl(getTpl).render(data, function(html) {
                $('#' + _this.options.listid).html(html);
            })
            var submenuTpl = $('#submenu-tpl').html();

            tpl(submenuTpl).render(data, function(html) {
                $('#submenu').html(html);
            })
        }

        _this.resize();
        _this.menuBind();
        element.init();
        _this.menuShowType();


    }

    /**
     *@todo 自适应窗口
     */
    jqmenu.prototype.resize = function() {
        $(window).on('resize', function() {
            tabmenu.init();
            tabmenu.tabMove(0, 1);
        }).resize();
    }

    /**
     *@todo 初始化菜单 
     */
    jqmenu.prototype.menuBind = function() {
        var _this = this;
        //初始化时显示第一个菜单
        $('.sub-menu').eq(0).slideDown();

        //绑定左侧树菜单的单击事件
        $('.sub-menu .layui-nav-item,.tab-menu,.menu-list li').bind("click", function() {
            var obj = $(this);

            $('.menu-list').slideUp();
            $('.tab-move-btn').find('i').html("&#xe604;");

            if (obj.find('dl').length > 0) {
                if (obj.attr('data-bind') == 1) {
                    return;
                }
                obj.attr('data-bind', '1');
                obj.find('dd').bind("click", function() {
                    _this.menuSetOption($(this));
                });
            } else {
                _this.menuSetOption(obj);
            }
        });


        //绑定主菜单单击事件，点击时显示相应的菜单
        element.on('nav(main-menu)', function(elem) {
            var index = (elem.index()),
                locationShowType = layui.data('showType'),
                showType = locationShowType.showType ? locationShowType.showType == 3 ? 2 : locationShowType.showType : 2;
            _this.menuShowType(showType);
            $('.sub-menu').slideUp().eq(index).slideDown();
            if ($(window).width() < 450) {
                $('.jqadmin-main-menu .layui-nav').hide();
            }
        });

        //绑定tab切换事件
        // element.on('tab(tabmenu)', function(data) {
        //     alert(123);
        // });


        //绑定更多按钮事件
        $('.tab-move-btn').bind("click", function() {
            var show = $('.menu-list').css('display');
            if (show == "none") {
                _this.menulist();
                $('.menu-list li').bind("click", function() {
                    _this.menuSetOption($(this));
                });

                $('.menu-list').slideDown();
                $(this).find('i').html("&#xe603;");
            } else {
                $('.menu-list').slideUp();
                $(this).find('i').html("&#xe604;");
            }
        })

        //禁止双击选中
        $('span.move-left-btn,span.move-right-btn').bind("selectstart", function() {
            return false;
        })

    }

    /**
     *@todo 设置菜单项
     */
    jqmenu.prototype.menuSetOption = function(obj) {
        var $a = obj.children('a'),
            href = $a.data('url'),
            icon = $a.children('i:first').data('icon'),
            title = $a.data('title'),
            parent = $a.data('parent'),
            data = {
                href: href,
                icon: icon,
                title: title,
                parent: parent
            }
        this.menuOpen(data);
    }


    /**
     *@todo 打开菜单项
     */
    jqmenu.prototype.menuOpen = function(data) {
        tabmenu.tabAdd(data, this.options.isFresh);
        if ($(window).width() < 450) {
            this.menuShowType(3);

        }
    }

    /**
     * 刷新后打开原打开的菜单
     */
    jqmenu.prototype.openOldMenu = function() {
        element.on('tab(tabmenu)', function() {
            var layId = $(this).attr("lay-id");

            $('.menu-list').slideUp();
            $('.tab-move-btn').find('i').html("&#xe604;");
            var data = {
                layId: layId,
                parent: false
            }
            if (!init) {
                tabmenu.storage(data, "change");
            }

            if ($(this).attr("fresh")) {
                $(this).removeAttr("fresh");
                tabmenu.fresh(layId);
            }

        });
        var sStorage = window.sessionStorage;
        var explorer = navigator.userAgent;
        if (sStorage.menu) {
            var menulist = sStorage.menu;
            menulist = menulist.split("|");
            for (index in menulist) {
                if (index == "remove") {
                    continue;
                }
                if (typeof menulist[index] === 'string') {
                    var menu = JSON.parse(menulist[index]);

                }

                menu.nodo = true;

                if (explorer.indexOf("Firefox") >= 0) {
                    menu.old = true;
                }
                this.menuOpen(menu);
            }
        }
        if (sStorage.curMenu && sStorage.curMenu != "undefined") {
            var menu = sStorage.curMenu;

            if (typeof menu === 'string') {
                menu = JSON.parse(menu);
            }
            if (explorer.indexOf("Firefox") >= 0) {
                menu.old = true;
            }
            this.menuOpen(menu);
        }
        init = false;
    }


    jqmenu.prototype.menulist = function() {

        var storage = window.sessionStorage,
            menulist = storage.menu;
        if (menulist) {
            var menulist = menulist.split("|"),
                list = [],
                data = {};

            for (index in menulist) {
                if (index == "remove") {
                    continue;
                }
                if (typeof menulist[index] === 'string') {
                    var menu = JSON.parse(menulist[index])
                }

                list.push(menu);
            }
            data.list = list;
            console.log(data);
            if ("" != data || undefined != data) {
                var getTpl = $('#menu-list-tpl').html();
                tpl(getTpl).render(data, function(html) {
                    $('#menu-list').html(html);
                })
            }
        }
    }



    jqmenu.prototype.menuShowType = function(type) {

        var locationShowType = layui.data('showType');
        var showType = locationShowType.showType ? locationShowType.showType : 3;
        if (type) {
            showType = type;
            layui.data('showType', {
                key: 'showType',
                value: type
            });
        }
        if ($(window).width() < 450) {
            if (!type) { showType = 3 };
        }
        if (showType == 3) {
            $('.menu-type').data('type', 1);
        } else {
            $('.menu-type').data('type', showType + 1);
        }

        var bar = $('.jqamdin-left-bar');
        var showIcon = $(".menu-type").find("i");
        switch (showType) {
            case 2:
                $('.jqadmin-body').animate({ left: '60' });
                bar.animate({ width: '60' });
                $('.jqadmin-foot').animate({ left: '60' });
                showIcon.html('&#xe616;');
                $('#submenu,.jqadmin-home').find("span").hide();
                $('#submenu').find("ul li").css("width", "60px").find("a").css("padding-left", "10px").find('i').css("font-size", "20px");
                $('.jqadmin-home').find("a").css("padding-left", "0").find('i').css("font-size", "32px");
                $('#submenu').find("ul li dl dd a").css("padding-left", "15px");
                $('#submenu').find("ul li").find("a").on('mouseenter', function() {
                    layer.tips($(this).data("title"), $(this));
                });

                if (this.options.icon) {
                    $('.jqadmin-main-menu').find("span").hide();
                    $('.jqadmin-main-menu').find("ul li").css({ "width": "64px", "line-height": "56px" }).find('i').css("font-size", "26px");
                    $('.jqadmin-main-menu').find("ul li").find("a").on('mouseenter', function() {
                        layer.tips($(this).data("title"), $(this));
                    });
                }

                layui.data('showType', {
                    key: 'moveType',
                    value: 2
                });
                layui.data('showType', {
                    key: 'menuShow',
                    value: true
                });
                $('.menu-btn').find("i").html('&#xe616;');
                break;
            case 1:
                $('.jqadmin-body').animate({ left: '200' });
                bar.animate({ width: '200' });
                $('.jqadmin-foot').animate({ left: '200' });
                showIcon.html('&#xe683;');
                $('#submenu,.tab-menu').find("span").show();
                $('#submenu').find("ul li").css("width", "200px").find("a").css("padding-left", "30px").find('i').css("font-size", "14px");
                $('#submenu').find("ul li").find("a").off('mouseenter');
                $('.tab-menu').find("a").css("padding-left", "10px").find('i').css("font-size", "16px");
                if (this.options.icon) {
                    $('.jqadmin-main-menu').find("span").show();
                    $('.jqadmin-main-menu').find("ul li").css({ "width": "auto", "line-height": "60px" }).find('i').css("font-size", "14px");
                    $('.jqadmin-main-menu').find("ul li").find("a").off('mouseenter');
                }
                layui.data('showType', {
                    key: 'moveType',
                    value: 1
                });
                $('.menu-btn').find("i").html('&#xe618;');
                break;
            default:
                $('.jqadmin-body').animate({ left: '0' });
                bar.animate({ width: '0' });
                $('.jqadmin-foot').animate({ left: '0' });
                showIcon.html('&#xe61a;');
                $('.menu-btn').find("i").html('&#xe618;');
                if (showType != 3) {
                    layui.data('showType', {
                        key: 'moveType',
                        value: 1
                    });
                }
        }
    }

    jqmenu.prototype.showLeftMenu = function(obj) {
        var bar = $('.jqamdin-left-bar');
        if (bar.width() == 0) {
            var locationShowType = layui.data('showType');
            var showType = locationShowType.moveType ? locationShowType.moveType : 1;
            if (showType == 1) {
                $('.jqadmin-body').animate({ left: '200' });
                bar.animate({ width: '200' });
                $('.jqadmin-foot').animate({ left: '200' });
                obj.find("i").html('&#xe616;');
            } else {
                $('.jqadmin-body').animate({ left: '60' });
                bar.animate({ width: '60' });
                $('.jqadmin-foot').animate({ left: '60' });
                obj.find("i").html('&#xe616;');
            }

        } else {
            $('.jqadmin-body').animate({ left: '0' });
            bar.animate({ width: '0' });
            $('.jqadmin-foot').animate({ left: '0' });
            obj.find("i").html('&#xe618;');
        }

    }

    exports('jqmenu', jqmenu);
});