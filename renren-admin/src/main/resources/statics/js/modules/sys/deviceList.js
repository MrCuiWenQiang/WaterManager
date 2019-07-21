$(function () {
    $("#jqGrid").jqGrid({
        url: '../' + 'sys/warterdevice/noBindlist',
        datatype: "json",

        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true},
            {label: '设备编号', name: 'no', index: 'no', width: 80},
            {label: '设备名称', name: 'name', index: 'name', width: 80},
            {label: '设备型号', name: 'model', index: 'model', width: 80},
            {
                label: '状态', name: 'status', index: 'status', width: 80,
                formatter: function (cellvalue) {
                    var text;
                    switch (cellvalue) {
                        case 0:
                            text = '欠费停用';
                            break;
                        case 1:
                            text = '正常';
                            break;
                        case 2:
                            text = '未安装';
                            break;
                        case 3:
                            text = '故障';
                            break;
                        case 4:
                            text = '电量不足';
                            break;
                        default:
                            text = '未知状态';
                    }
                    return text;
                }
            },
            {label: '安装日期', name: 'installDate', index: 'install_date', width: 80},
            {label: '安装人员', name: 'installUserWorlId', width: 80, sortable: false},
            {label: '电池更换期限', name: 'replaceDate', index: 'replace_date', width: 80}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
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

function getSelectNO() {
    return vm.getSelectNO();
}
var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            no: null
        },

    },
    methods: {
        query: function () {
            vm.reload();
        },

        reload: function () {
            var page = $("jpGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'no': vm.q.no},
                page: page
            }).trigger("reloadGrid");
        },

        getSelectNO: function () {
            var ids=$("#jqGrid").jqGrid("getGridParam","selarrrow");
            if(ids.length==1){
                var rowid=$("#jqGrid").jqGrid("getGridParam","selrow");
                var rowData = $("#jqGrid").jqGrid("getRowData",rowid);
                return rowData.no;
            }else{
                alert("请选择一条记录");
                return null;
            }

        }
    }
});