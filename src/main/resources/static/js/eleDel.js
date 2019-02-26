layui.extend({
  admin: '/static/js/admin'
});
layui.use(['laydate', 'jquery', 'admin'], function () {
      var laydate = layui.laydate,
          $ = layui.jquery,
          admin = layui.admin;
      //执行一个laydate实例
      laydate.render({
        elem: '#start' //指定元素
      });
      //执行一个laydate实例
      laydate.render({
        elem: '#end' //指定元素
      });
      /*用户-停用*/
      window.user_silence = function (obj, username) {
        if ($(obj).attr('title') == '禁言') {
          layer.confirm('确认要禁言吗？', function (index) {
            //发异步把用户状态进行更改
            var data = {"username": username, "status": "禁言"}
            $.ajax({
              url: '/user/changeStatus'
              , type: 'post'
              , contentType: "application/json"
              , data: JSON.stringify(data)
              , success: function (result) {
                if (result.code == 0) {
                  layer.msg(result.msg);
                  setTimeout('window.parent.location.reload()', 1000);
                } else {
                  layer.msg(result.msg, {
                    time: 1000
                  });
                  parent.layer.close();
                }
              }
            });
          });
        } else {
          layer.confirm('确认要取消禁言吗？', function (index) {

            var data = {"username": username, "status": "正常"}
            $.ajax({
              url: '/user/changeStatus'
              , type: 'post'
              , contentType: "application/json"
              , data: JSON.stringify(data)
              , success: function (result) {
                if (result.code == 0) {
                  layer.msg(result.msg);
                  setTimeout('window.parent.location.reload()', 1000);
                } else {
                  layer.msg(result.msg, {
                    time: 1000
                  });
                  parent.layer.close();
                }
              }
            });

          });
        }
      }

      /*用户-删除*/
      window.member_del = function (obj, id) {
        layer.confirm('确认要删除吗啊啊', function (index) {
          //发异步删除数据
          $(obj).parents("tr").remove();
          layer.msg('已删除!', {
            icon: 1,
            time: 1000
          });
        });
      }

      window.delAll = function (argument) {
        var data = tableCheck.getData();
        layer.confirm('确认要删除吗？' + data, function (index) {
          //捉到所有被选中的，发异步进行删除
          layer.msg('删除成功', {
            icon: 1
          });
          $(".layui-form-checked").not('.header').parents('tr').remove();
        });
      }
    }
);