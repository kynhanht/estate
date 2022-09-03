<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerListURL" value="/admin/customer-list"/>
<c:url var="customerAPI" value="/api/customer"/>
<html>
<body>
<head>
    <title>Danh sách khách hàng</title>
</head>

<div class="main-content">

    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href='<c:url value="/admin/home" />'>Home</a>
                </li>
                <li class="active">Danh sách khách hàng</li>
            </ul><!-- /.breadcrumb -->

        </div>

        <div class="page-content">


            <div class="row">

                <!-- PAGE CONTENT BEGINS -->
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm kiếm</h4>

                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>

                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form modelAttribute="modelSearch" action="${customerListURL}" id="listForm"
                                           method="GET">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <div class="col-xs-4">
                                                <label for="fullName">Tên đầy đủ</label>
                                                <form:input path="fullName" cssClass="form-control"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label for="phone">Số điện thoại</label>
                                                <form:input path="phone" cssClass="form-control"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label for="email">Email</label>

                                                <form:input path="email" cssClass="form-control"/>
                                            </div>
                                        </div>
                                        <security:authorize access="hasRole('MANAGER')">
                                            <div class="form-group">
                                                <div class="col-xs-4">
                                                    <label for="staffId">Nhân viên phụ trách</label>
                                                    <br>
                                                    <form:select path="staffId" cssClass="chosen-select">
                                                        <form:option
                                                                value="">--- Chọn nhân viên phụ trách ---</form:option>
                                                        <form:options items="${staffs}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </security:authorize>


                                        <button class="btn btn-md btn-success" id="searchBtn">
                                            Tìm kiếm
                                            <i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i>
                                        </button>
                                    </div>
                                </form:form>

                            </div>
                        </div>
                    </div>
                </div>
            </div><!-- /.row -->
            <br>
            <div class="row">
                <div class="col-xs-12">
                    <div class="pull-right">
                        <security:authorize access="hasRole('MANAGER')">
                            <a flag="info"
                               class="btn btn-white btn-info btn-bold"
                               data-toggle="tooltip"
                               title="Thêm khách hàng"
                               href='<c:url value="/admin/customer-edit"/>'>
                                <span><i class="fa fa-plus-circle bigger-110 purple"></i></span>
                            </a>
                        </security:authorize>
                        <button id="btnDelete" type="button" disabled
                                class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                data-toggle="tooltip"
                                title="Xóa khách hàng">
                            <span><i class="fa fa-trash-o bigger-110 pink"></i></span>
                        </button>

                    </div>

                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div class="table-responsive">
                        <display:table name="${modelResponse.listResult}" cellspacing="0" cellpadding="0"
                                       requestURI="${customerListURL}" partialList="true" sort="external"
                                       defaultsort="2" defaultorder="ascending"
                                       id="${modelResponse.tableId}" size="${modelResponse.totalItems}"
                                       pagesize="${modelResponse.totalPageItems}"
                                       export="false"
                                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                       style="margin: 3em 0 1.5em;">

                            <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                            headerClass="center select-cell">
                                <fieldset>
                                    <input type="checkbox" name="checkList" value="${tableList.id}"
                                           id="checkbox_${tableList.id}" class="check-box-element"/>
                                </fieldset>
                            </display:column>
                            <display:column headerClass="text-left" property="fullName" title="Tên" sortName="fullName"
                                            sortable="true"/>
                            <display:column headerClass="text-left" property="phone" title="Số điện thoại"
                                            sortName="phone" sortable="true"/>
                            <display:column headerClass="text-left" property="email" title="Email" sortName="email"
                                            sortable="true"/>
                            <display:column headerClass="text-left" property="demand" title="Nhu cầu" sortName="demand"
                                            sortable="true"/>
                            <display:column headerClass="text-left" property="modifiedBy" title="Người nhập"
                                            sortName="modifiedBy" sortable="true"/>
                            <display:column headerClass="text-left" property="modifiedDate" title="Ngày nhập"
                                            sortName="modifiedDate" sortable="true"/>
                            <display:column headerClass="col-actions" title="Thao tác">
                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-info" title="Giao khách hàng"
                                            onclick="openAssignCustomerModal(${tableList.id})">
                                        <i class="fa fa-bars" aria-hidden="true"></i>
                                    </button>
                                </security:authorize>
                                <a class="btn btn-xs btn-info" data-toggle="tooltip"
                                   title="Cập nhật khách hàng"
                                   href='<c:url value="/admin/customer-edit/${tableList.id}"/>'>
                                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                </a>
                            </display:column>

                        </display:table>
                    </div>

                </div>
            </div>
        </div> <!-- PAGE CONTENT ENDS -->
    </div><!-- /.page-content -->
</div><!-- /.main-content -->

<!-- Assignment building modal -->
<div id="assignmentCustomerModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="staffList">
                    <thead>
                    <tr>
                        <th>Chọn nhân viên</th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <input type="hidden" id="customerId" name="customerId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="assignCustomerBtn">Giao khách hàng</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>

<script type="text/javascript">

    // Search customers
    $('#searchBtn').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();

    });


    // Load Modal
    function openAssignCustomerModal(customerId) {
        // Open modal
        $('#assignmentCustomerModal').modal();
        // Load staff into modal
        loadStaffs(customerId);
        $('#customerId').val(customerId);
    };

    function loadStaffs(customerId) {
        $.ajax({
            type: "GET",
            url: "${customerAPI}/" + customerId + "/staffs",
            dataType: "json",
            success: function (response) {
                var row = '';
                $.each(response, function (index, item) {
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" name="checkList" value=' + item.staffId + ' id="checkbox_' + item.staffId + '" ' + item.checked + ' /></td>';
                    row += '<td class="text-center">' + item.fullName + '</td>'
                    row += '</tr>'
                });
                $('#staffList tbody').html(row);

            },
            error: function (respone) {
                console.log(respone);
            }
        });
    }

    // Assign customers
    $('#assignCustomerBtn').click(function (e) {
        e.preventDefault();
        var data = {};
        data['customerId'] = $("#customerId").val();
        var staffIds = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffIds'] = staffIds;
        assignCustomer(data);


    });

    function assignCustomer(data) {
        $.ajax({
            type: "POST",
            url: "${customerAPI}/assignment-customer",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                showAlertAfterSuccess(function () {
                    console.log(response);
                });
            },
            error: function (respone) {
                showAlertAfterFail(function () {
                    console.log(respone);
                })
            }
        });
    }

    // Delete customers
    $('#btnDelete').click(function (e) {
        e.preventDefault();
        showAlertBeforeDelete(function () {
            var customerIds = $('#tableList').find('tbody input[type=checkbox][name="checkList"]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteCustomer(customerIds);
        });
    });

    function deleteCustomer(data) {
        $.ajax({
            type: "DELETE",
            url: "${customerAPI}",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                showAlertAfterDeleteSuccess(function () {
                    window.location.href = "/admin/customer-list";
                });
            },
            error: function (response) {
                showAlertAfterFail(function () {
                    window.location.href = "/admin/customer-list";
                });
            }
        });
    }


</script>


</body>
</html>

