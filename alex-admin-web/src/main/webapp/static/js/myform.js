
layui.define(['jquery', 'tags', 'layedit', 'jqform', 'upload'], function(exports) {
    var $ = layui.jquery,
        layedit = layui.layedit,
        box = "",
        form = layui.jqform,
        tags = layui.tags;
    
   form.set({
        "blur": true,
        "form": "#form1",
        "complete":function(){
        	parent.oneList.init('list-tpl');  //表单提交成功回调,刷新当前table
        }
    }).init();
    
    //自定义
    form.verify({
        username: [
            /^[a-zA-Z][a-zA-Z0-9_]{4,16}$/, '用户名字母开头，允许5-16字节，允许字母数字下划线'
        ],
        password: [
            /^[a-zA-Z]\w{5,17}$/, '密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线'
        ]
    });
    tags.init();

    //上传文件设置
    layui.upload({
        url: '/file/upload',
        before: function(input) {
            box = $(input).parent('form').parent('div').parent('.layui-input-block');
            if (box.next('div').length > 0) {
                box.next('div').html('<div class="imgbox"><p>上传中...</p></div>');
            } else {
                box.after('<div class="layui-input-block"><div class="imgbox"><p>上传中...</p></div></div>');
            }
        },
        success: function(res) {
            if (res.status == 200) {
                box.next('div').find('div.imgbox').html('<img src="' + res.url + '" alt="..." class="img-thumbnail">');
                box.find('input[type=hidden]').val(res.url);
                form.check(box.find('input[type=hidden]'));
            } else {
                box.next('div').find('p').html('上传失败...')
            }
        }
    });

    //富文本框
    layedit.set({
        uploadImage: {
            url: '/uploda/upload.php'
        }
    });
    var editIndex = layedit.build('content');
    exports('myform', {});
});