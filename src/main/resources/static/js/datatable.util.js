var DataTableUtil = !window.DataTableUtil ? {} : window.DataTableUtil;

DataTableUtil.loadData = function(selector, data, columns, sortColumn, sortOrd) {
    var orderIndex = 0;
    columns.forEach(function(item, idx) {
        if(item.data = sortColumn) {
            orderIndex = idx;
            return;
        }
    })
    $(selector).dataTable({
        data : data,
        columns : columns,
        order : [ [ orderIndex, sortOrd ] ],
        searching : false
    })
};

window.DataTableUtil = DataTableUtil;