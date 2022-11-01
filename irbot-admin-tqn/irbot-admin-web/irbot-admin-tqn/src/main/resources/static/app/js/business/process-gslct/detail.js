const PREFIX = ctx + "business/process-gslct";

// Init screen
$(document).ready(function () {
  loadListImport();

  loadListExport();
});

function loadListImport() {

  if (!importDetails) {
      $("#chi-tiet-nhap").hide();
      return;
  }

  const OPTIONS1 = {
    id: "bootstrap-table-import",
    pagination: false,
    showSearch: false,
    showRefresh: false,
    modalName: 'Chi tiết nhập',
    data: importDetails,
    columns: [
        {
            field: 'id',
            title: 'STT',
            align: 'center', 
            formatter: function(value, row, index) {
                return index + 1;
            }
        },
        {
            field: 'detailId',
            title: 'Detail Id',
            visible: false
        },
        {
            field: 'productId',
            title: 'Mã hàng'
        },
        {
            field: 'productName',
            title: 'Tên hàng'
        },
        {
            field: 'realQuantity',
            title: 'SL từ NCC/Cty'
        },
        {
            field: 'akRate',
            title: 'Ak(%)'
        },
        {
            field: 'wtpRate',
            title: 'Wtp(%)'
        },
        {
            field: 'convertQuantity',
            title: 'SL quy ẩm'
        },
        {
            field: 'unitId',
            title: 'ĐVT'
        }
    ],
  };
  $.table.init(OPTIONS1);
}

function loadListExport() {
    if (!exportDetails) {
        $("#chi-tiet-xuat").hide();
        return;
    }
    const OPTIONS2 = {
      id: "bootstrap-table-export",
      pagination: false,
      showSearch: false,
      showRefresh: false,
      modalName: 'Chi tiết xuất',
      data: exportDetails,
      columns: [
          {
              field: 'id',
              title: 'STT',
              align: 'center', 
              formatter: function(value, row, index) {
                  return index + 1;
              }
          },
          {
              field: 'detailId',
              title: 'Detail Id',
              visible: false
          },
          {
              field: 'productId',
              title: 'Mã hàng'
          },
          {
              field: 'productName',
              title: 'Tên hàng'
          },
          {
              field: 'realQuantity',
              title: 'SL từ NCC/Cty'
          },
          {
              field: 'akRate',
              title: 'Ak(%)'
          },
          {
              field: 'wtpRate',
              title: 'Wtp(%)'
          },
          {
              field: 'convertQuantity',
              title: 'SL quy ẩm'
          },
          {
              field: 'unitId',
              title: 'ĐVT'
          }
      ],
    };
    $.table.init(OPTIONS2);
  }
