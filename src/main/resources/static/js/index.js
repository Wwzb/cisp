//搜素框处理
$("#Search").click(function(e) {
    layer.open({
        title: false,
        type: 1,
        closeBtn: 0,
        shadeClose: true,
        offset: 'auto',
        area: ['550px', '78px'],
        content: '<div class="search"><form action="search" method="get"><input name="keyword" type="text" required placeholder="请输入标题"  lay-submit  class="layui-input"/></form></div>'
    });
});

//登录框处理
$('.subLogin').click(function() {
    layer.open({
        title: "",
        type: 2,
        closeBtn: 1,
        offset: 'auto',
        area: ['400px', '400px'],
        content: '/login.html',
        scrolling: 'no'

    });

});

/*
   注册框处理
*/
$('#subReg').click(function() {
    layer.open({
        title: "",
        type: 2,
        closeBtn: 1,
        offset: 'auto',
        area: ['400px', '400px'],
        content: '/login.html',
        scrolling: 'no'

    });
});

/*
* 获取验证码
* */

$('#code').click(function(){
    $.ajax({
        url:'/getImage?'+Date.parse(new Date()),
        type:'get',
        success:function(data){
            $('#code').attr('src',this.url);
        }
    });
})

//获取地址栏函数
function getQueryVariable(variable){
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}

