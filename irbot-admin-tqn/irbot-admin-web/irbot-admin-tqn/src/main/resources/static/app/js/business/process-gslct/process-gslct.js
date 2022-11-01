const PREFIX = ctx + "business/process-gslct";

let date = new Date();
let firstDay = lastDay = date;
$("input[name='params[beginTime]']").val(parseTime(firstDay, "{y}-{m}-{d}"));
$("input[name='params[endTime]']").val(parseTime(lastDay, "{y}-{m}-{d}"));

// Init screen
$(document).ready(function () {
  loadList();

  $("input").keyup(function (event) {
    if (event.keyCode == 13) {
      $.table.search();
      event.preventDefault();
    }
  });
});

function loadList() {
  const OPTIONS = {
    url: PREFIX + "/list",
    syncUrl: PREFIX + "/sync",
    removeUrl: PREFIX + "/remove",
    detailUrl: PREFIX + "/detail/{id}",
    updateStatusUrl: PREFIX + "/update-status",
    retryUrl: PREFIX + "/retry",
    modalName: "Phiếu",
    // sortName: "id",
    // sortOrder: "desc",
    pageSize: 20,
    pageList: [20, 50, 100, 200],
    columns: [
      {
        checkbox: true,
      },
      {
        field: 'id',
        title: 'Id',
        visible: false
      },
      {
        field: 'type',
        sortable: true,
        title: 'Loại phiếu',
        align: 'center',
        formatter: function (value, row, index) {
          return typeFormatter(value, row, index);
        }
      },
      {
        field: 'mode',
        sortable: true,
        title: 'Trạng thái',
        align: 'center',
        formatter: function (value, row, index) {
          return modeFormatter(value, row, index);
        }
      },
      {
        field: 'voucherNo',
        sortable: true,
        title: 'Mã phiếu'
      },
      {
        field: 'status',
        title: 'Trạng thái',
        align: 'center',
        formatter: function (value, row, index) {
          return processStatusFormat(row.status);
        }
      },
      {
        field: 'voucherDate',
        title: 'Ngày tạo phiếu'
      },
      {
        field: 'stockId',
        title: 'Kho'
      },
      {
        field: 'voucherNoGslct',
        title: 'Khóa đồng bộ'
      },
      {
        title: "Hành động",
        align: "center",
        formatter: function (value, row, index) {
          var actions = [];
          actions.push(
            '<a class="btn btn-primary btn-xs ma2" href="javascript:void(0)" title="Chi tiết" onclick="$.operate.detail(\'' +
            row.id +
            '\')"><i class="fa fa-eye"  ></i>&nbsp;Chi tiết</a> '
          );
          actions.push(
            '<a class="btn btn-danger btn-xs ma2" href="javascript:void(0)" title="Xóa" onclick="$.operate.remove(\'' +
            row.id +
            '\')"><i class="fa fa-remove"></i>&nbsp;Xóa</a> '
          );

          actions.push(
            '<a class="btn btn-success btn-xs ma2" href="javascript:void(0)" title="Xóa" onclick="viewHistory(\'' +
            row.id +
            '\')"><i class="fa fa-history"></i>&nbsp;Lịch sử làm lệnh</a> '
          );
          return actions.join("");
        },
      },
    ],
  };
  $.table.init(OPTIONS);
}

function typeFormatter(value, row, index) {
  if (row.type == 1) {
    return '<span class="label label-primary">Nhập than NK - Từ công ty khác</span>';
  } else if (row.type == 2) {
    return '<span class="label label-primary">Nhập than sạch - Từ công ty khác</span>';
  } else if (row.type == 3) {
    return '<span class="label label-primary">Nhập than sạch - Nội bộ</span>';
  } else if (row.type == 4) {
    return '<span class="label label-success">Xuất than sạch - Đến công ty khác</span>';
  } else if (row.type == 5) {
    return '<span class="label label-success">Xuất than NK - Nội bộ</span>';
  } else if (row.type == 6) {
    return '<span class="label label-success">Xuất than sạch - Nội bộ</span>';
  } else if (row.type == 7) {
    return '<span class="label label-warning">Phiếu pha trộn</span>';
  }
}

function modeFormatter(value, row, index) {
  if (row.mode == 0) {
    return '<span class="label label-success">Thêm mới</span>';
  } else if (row.mode == 1) {
    return '<span class="label label-warning">Cập nhật</span>';
  }
}

function processStatusFormat(status) {
  if (status == 0) {
    return '<span class="label label-default"> Chưa làm</span>';
  } else if (status == 1) {
    return '<span class="label label-warning"> Đã gửi</span>'
  } else if (status == 2) {
    return '<span  class="label label-success"> Đang làm</span>'
  } else if (status == 3) {
    return '<span class="label label-danger"> Thất bại</span>'
  } else {
    return '<span class="label label-primary">Thành công</span>';
  }
}

function viewHistory(id) {
  var _url = PREFIX + "/history/" + id;
  var options = {
    title: "Chi tiết làm lệnh",
    width: "900px",
    url: _url,
    skin: 'layui-layer-gray',
    btn: ['Đóng'],
    yes: function (index, layero) {
      layer.close(index);
    }
  };
  $.modal.openOptions(options);
}


function sync() {
  table.set();
  $.modal.open("Đồng bộ dữ liệu", table.options.syncUrl, '800', '480');
}

function updateStatus() {
  table.set();
  $.modal.open("Cập nhật trạng thái", table.options.updateStatusUrl, '500', '380');
}

function retry() {
  table.set();
  var rows = $.common.isEmpty(table.options.uniqueId) ? $.table.selectFirstColumns() : $.table.selectColumns(table.options.uniqueId);
  if (rows.length == 0) {
    $.modal.alertWarning("Vui lòng chọn ít nhất một bản ghi");
    return;
  }
  $.modal.confirm("Xác nhận gửi robot làm lệnh " + rows.length + " bản ghi đã chọn ?", function () {
    var url = table.options.retryUrl;
    var data = { "ids": rows.join() };
    $.operate.submit(url, "post", "json", data);
  });
}
