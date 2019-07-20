$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/warterdevice/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true ,hidden:true},
			{ label: '设备编号', name: 'no', index: 'no', width: 80 },
			{ label: '设备名称', name: 'name', index: 'name', width: 80 },
            { label: '设备型号', name: 'model', index: 'model', width: 80 },
            { label: '状态', name: 'status', index: 'status', width: 80,
            formatter:function (cellvalue) {
			    var text;
                switch (cellvalue) {
                    case 0: text = '欠费停用';break;
                    case 1: text = '正常';break;
                    case 2: text = '未安装';break;
                    case 3: text = '故障';break;
                    case 4: text = '电量不足';break;
                    default:text='未知状态';
                }
                return text;
            }},
			{ label: '安装日期', name: 'installDate', index: 'install_date', width: 80 },
			{ label: '安装人员', name: 'installUserWorlId',  width: 80 ,sortable:false},
			{ label: '电池更换期限', name: 'replaceDate', index: 'replace_date', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });

});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
        select_status:null,
		warterDevice: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
            vm.getDevStatus();
			vm.showList = false;
			vm.title = "新增";
			vm.warterDevice = {};
		},
		update: function (event) {
            vm.getDevStatus();
            var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
        getDevStatus:function(){
            if (vm.select_status!=null){
                return;
            }
		    $.ajax({
                type:"POST",
                url:baseURL+'sys/warterdevice/status',
                contentType: "application/json",
                success:function (r) {
                    if(r.code === 0){
                        var stus= r.status;
                        vm.select_status = r.status;
                        for (var i=0;i<stus.length;i++) {
                            var value = stus[i].code;
                            var lable = stus[i].value;
                            $('#statusselect').append("<option value="+value+">"+lable+"</option>");
                        }
                    }else{
                        layer.alert(r.msg);
                    }
                }
            });
        },
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.warterDevice.id == null ? "sys/warterdevice/save" : "sys/warterdevice/update";
                var status = $('#statusselect  option:selected').val();
                vm.warterDevice.status=status;
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.warterDevice),
                    success: function(r){
                        if(r.code === 0){
                             layer.msg("操作成功", {icon: 1});
                             vm.reload();
                             $('#btnSaveOrUpdate').button('reset');
                             $('#btnSaveOrUpdate').dequeue();
                        }else{
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
			if(ids == null){
				return ;
			}
			var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
               if(!lock) {
                    lock = true;
		            $.ajax({
                        type: "POST",
                        url: baseURL + "sys/warterdevice/delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
                        success: function(r){
                            if(r.code == 0){
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            }else{
                                layer.alert(r.msg);
                            }
                        }
				    });
			    }
             }, function(){
             });
		},
		getInfo: function(id){
			$.get(baseURL + "sys/warterdevice/info/"+id, function(r){
                vm.warterDevice = r.warterDevice;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});