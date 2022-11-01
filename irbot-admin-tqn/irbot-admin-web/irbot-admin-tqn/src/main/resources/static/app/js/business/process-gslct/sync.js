const PREFIX = ctx + "business/process-gslct";

let date = new Date();
// let y = date.getFullYear();
// let m = date.getMonth();
// let firstDay = new Date(y, m, 1);
// let lastDay = new Date(y, m + 1, 0);
let firstDay = lastDay = date;
$("input[name='fromDate']").val(parseTime(firstDay, "{y}-{m}-{d}"));
$("input[name='toDate']").val(parseTime(lastDay, "{y}-{m}-{d}"));
// $("input[name='fromDate']").val("2021-11-21");
// $("input[name='toDate']").val("2021-11-21");

function submitHandler() {
  let dataRequest = {
    fromDate: $("input[name='fromDate']").val(),
    toDate: $("input[name='toDate']").val(),
    serviceType: $("select[name='serviceType']").val(),
    //sendRobot: $("input[name='sendRobot']").prop("checked")
  }
  if ($.validate.form()) {
    $.ajax({
      type: "POST",
      url: PREFIX + "/sync",
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      data: JSON.stringify(dataRequest),
      beforeSend: function () {
        $.modal.loading("Đang xử lý, vui lòng đợi...");
      },
      error: function (request) {
        $.modal.closeLoading();
        $.modal.alertError("System error!");
      },
      success: function (data) {
        if (data == null) {
          return;
        }
        if (data.code == 0) {
          $.modal.closeLoading();
          $.modal.close();
          parent.$.modal.alertSuccess("Đã đồng bộ " + data.result + " record!");
          parent.$.table.refresh();
        } else {
          $.modal.closeLoading();
          $.modal.alertError("System error! \r\n " + data.msg);
        }
      },
    });
  }
}