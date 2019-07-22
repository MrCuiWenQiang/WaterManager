$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/tabuser/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50,  hidden: true},
            {label: '用户id', name: 'userId', index: 'user_id', width: 80, key: true,hidden: true},
            {label: '微信用户唯一标识', name: 'openId', index: 'open_id', width: 80, hidden: true},
            {label: '用户昵称', name: 'nickname', index: 'nickname', width: 80},
            {label: '用户名', name: 'username', index: 'username', width: 80},
            {
                label: '用户状态', name: 'status', index: 'status', width: 40, formatter: function (cellvalue) {
                    var text;
                    switch (cellvalue) {
                        case 0:
                            text = '停用';
                            break;
                        case 1:
                            text = '正常';
                            break;
                        default:
                            text = '未知状态';
                    }
                    return text;
                }
            },
            {label: '手机号', name: 'mobile', index: 'mobile', width: 80},
            {label: '密码', name: 'password', index: 'password', width: 80, hidden: true},
            {label: '盐', name: 'salt', index: 'salt', width: 80, hidden: true},
            {label: '普通用户性别，1为男性，2为女性', name: 'sex', index: 'sex', width: 80, hidden: true},
            {label: '头像', name: 'headimgurl', index: 'headimgurl', width: 80, hidden: true},
            {label: '部门ID', name: 'deptId', index: 'dept_id', width: 80, hidden: true},
            {label: '上次登录时间', name: 'loginTimer', index: 'login_timer', width: 80},
            {label: '创建时间', name: 'createTimer', index: 'create_timer', width: 100},
            {
                label: '操作', width: 80, formatter: function (cellvalue, options, rowObject) {
                    return "<div class=\"grid-btn\"><a class=\"btn btn-primary\" onclick=\"queryUD(\'"+ options.rowId +"\')\"><i class=\"asterisk\"></i>&nbsp;查看设备</a>"+"<a class=\"btn btn-primary\" onclick=\"bind(\'"+ options.rowId +"\')\"><i class=\"asterisk\"></i>&nbsp;绑定设备</a></div>";
                }
            }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: false,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

function bind(userId) {
    var iflayero;
    layer.open({
        type: 2,
        content: baseURL + "device/list",
        area: ['1100px', '600px'],
        title: '设备选择',
        btn: ['确定', '取消'],
        success: function (layero, index) {
            iflayero = layero;
        },
        yes: function (index) {
            var iframeWin = window[iflayero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
            var deviceno = iframeWin.getSelectNO();
            if (deviceno == null) {
                return;
            }
            $.ajax({
                type:'post',
                contentType:'application/json',
                url:baseURL + 'sys/warterdevice/bind',
                data:'{"userId":"' + userId + '","deviceNO":"' + deviceno  + '"}',
                success:function (r) {
                    layer.msg(r.msg)
                    layer.close(index);
                    vm.reload()
                },
                fail: function (m) {
                    layer.msg('连接失败');
                    layer.close(index);
                }
            });
            layer.close(index);
        },
        clean: function () {
        }
    });
}

function queryUD(userId) {
    var iflayero;
    layer.open({
        type: 2,
        content: baseURL + "device/query/"+userId,
        area: ['1100px', '600px'],
        title: '设备查询',
        btn: [ '关闭'],
        success: function (layero, index) {
            iflayero = layero;
        },
        clean: function () {
        }
    });
}

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        tabUser: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.tabUser = {};
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function () {
                var url = vm.tabUser.id == null ? "sys/tabuser/save" : "sys/tabuser/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.tabUser),
                    success: function (r) {
                        if (r.code === 0) {
                            layer.msg("操作成功", {icon: 1});
                            vm.reload();
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        } else {
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
            });
        },
        del: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }
            var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                if (!lock) {
                    lock = true;
                    $.ajax({
                        type: "POST",
                        url: baseURL + "sys/tabuser/delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
                        success: function (r) {
                            if (r.code == 0) {
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            } else {
                                layer.alert(r.msg);
                            }
                        }
                    });
                }
            }, function () {
            });
        },
        getInfo: function (id) {
            $.get(baseURL + "sys/tabuser/info/" + id, function (r) {
                vm.tabUser = r.tabUser;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: page
            }).trigger("reloadGrid");
        }
    }
});