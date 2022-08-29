<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingListURL" value="/admin/building-list" />
<c:url var="buildingAPI" value="/api/building" />
<c:url var="userAPI" value="/api/user" />
<html>
<head>
    <title>Danh sách toà nhà</title>
</head>
<div class="main-content">

    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href='<c:url value="/admin/home" />'>Home</a>
                </li>
                <li class="active">Danh sách toà nhà</li>
            </ul><!-- /.breadcrumb -->

        </div>

        <!-- PAGE CONTENT BEGINS -->
        <div class="page-content">


            <div class="row">

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
                                <form:form modelAttribute="modelSearch" action="${buildingListURL}" id="listForm" method="GET">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <div class="col-xs-6">
                                                <label for="name">Tên toà nhà</label>
                                                <form:input path="name" cssClass="form-control"/>
                                            </div>
                                            <div class="col-xs-6">
                                                <label for="floorArea">Diện tích sàn</label>
                                                <input type="number" id="floorArea" name="floorArea" value="${modelSearch.floorArea}" class="form-control">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-4">
                                                <label for="districtCode">Quận hiện có</label>
                                                <br>
                                                <form:select path="districtCode" cssClass="chosen-select">
                                                    <form:option value="">--- Chọn quận ---</form:option>
                                                    <form:options items="${districts}" />
                                                </form:select>
                                            </div>
                                            <div class="col-xs-4">
                                                <label for="ward">Phường</label>
                                                <form:input path="ward" cssClass="form-control" />
                                            </div>
                                            <div class="col-xs-4">
                                                <label for="street">Đường</label>
                                                <form:input path="street" cssClass="form-control" />
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-4">
                                                <label for="numberOfBasement">Số tầng hầm</label>
                                                <input type="number" id="numberOfBasement" name="numberOfBasement" value="${modelSearch.numberOfBasement}" class="form-control">
                                            </div>
                                            <div class="col-xs-4">
                                                <label for="direction">Hướng</label>
                                                <form:input path="direction" cssClass="form-control" />
                                            </div>
                                            <div class="col-xs-4">
                                                <label for="level">Hạng</label>
                                                <form:input path="level" cssClass="form-control" />
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-3">
                                                <label for="rentAreaFrom">Diện tích từ</label>
                                                <input type="number" id="rentAreaFrom" name="rentAreaFrom" value="${modelSearch.rentAreaFrom}" class="form-control">
                                            </div>
                                            <div class="col-xs-3">
                                                <label for="rentAreaTo">Diện tích đến</label>
                                                <input type="number" id="rentAreaTo" name="rentAreaTo" value="${modelSearch.rentAreaTo}" class="form-control">
                                            </div>
                                            <div class="col-xs-3">
                                                <label for="rentPriceFrom">Giá thuê từ</label>
                                                <input type="number" id="rentPriceFrom" name="rentPriceFrom" value="${modelSearch.rentPriceFrom}" class="form-control">
                                            </div>
                                            <div class="col-xs-3">
                                                <label for="rentPriceTo">Giá thuê đến</label>
                                                <input type="number" id="rentPriceTo" name="rentPriceTo" value="${modelSearch.rentPriceTo}" class="form-control">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-4">
                                                <label for="managerName">Tên quản lý</label>
                                                <form:input path="managerName" cssClass="form-control" />
                                            </div>
                                            <div class="col-xs-4">
                                                <label for="managerPhone">Số điện thoại</label>
                                                <form:input path="managerPhone" cssClass="form-control" />
                                            </div>
                                            <security:authorize access="hasRole('MANAGER')">

                                                <div class="col-xs-4">
                                                    <label for="staffId">Nhân viên</label>
                                                    <br>
                                                    <form:select path="staffId">
                                                        <form:option value="">--- Chọn nhân viên phụ trách ---</form:option>
                                                        <form:options items="${staffs}" />
                                                    </form:select>
                                                </div>

                                            </security:authorize>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">

                                                <label>Loại toà nhà</label>
                                                <br>
                                                <c:forEach var="item" items="${buildingTypes}">

                                                    <label class="checkbox-inline">
                                                        <form:checkbox path="buildingTypes" value="${item.key}"/>${item.value}
                                                    </label>
                                                </c:forEach>

                                            </div>
                                        </div>

                                        <button class="btn btn-md btn-success" id="btnSearch">
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
                            title="Thêm toà nhà"
                            href='<c:url value="/admin/building-edit"/>'>
                            <span><i class="fa fa-plus-circle bigger-110 purple"></i></span>
                            </a>
                        </security:authorize>
                        <button id="btnDelete" type="button" disabled
                                class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                data-toggle="tooltip"
                                title="Xóa toà nhà">
                            <span><i class="fa fa-trash-o bigger-110 pink"></i></span>
                        </button>

                    </div>

                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="table-responsive">
                        <display:table name="${modelResponse.listResult}" cellspacing="0" cellpadding="0"
                                       requestURI="${buildingListURL}" partialList="true" sort="external" defaultsort="2" defaultorder="ascending"
                                       id="${modelResponse.tableId}" size="${modelResponse.totalItems}" pagesize="${modelResponse.totalPageItems}"
                                       export="true"
                                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                       style="margin: 3em 0 1.5em;">

                            <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell export-wthtml"
                                            headerClass="center select-cell export-wthtml" >
                                <fieldset>
                                    <input type="checkbox" name="checkList" value="${tableList.id}"
                                           id="checkbox_${tableList.id}" class="check-box-element"/>
                                </fieldset>
                            </display:column>
                            <display:column headerClass="text-left" property="name" title="Tên toà nhà" sortName="name" sortable="true"/>
                            <display:column headerClass="text-left" property="numberOfBasement" title="Số tầng hầm" sortName="numberOfBasement" sortable="true"/>
                            <display:column headerClass="text-left" property="address" title="Địa Chi"/>
                            <display:column headerClass="text-left" property="managerName" title="Tên quản lý" sortName="managerName" sortable="true"/>
                            <display:column headerClass="text-left" property="managerPhone" title="Số điện thoại" sortName="managerPhone" sortable="true"/>
                            <display:column headerClass="text-left" property="floorArea" title="Giá Thuê" sortName="floorArea" sortable="true"/>
                            <display:column headerClass="text-left" property="rentPrice" title="Phí dịch vụ" sortName="rentPrice" sortable="true"/>
                            <display:column headerClass="col-actions" title="Thao tác">
                                <security:authorize access="hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-info" title="Giao toà nhà"
                                            onclick="openAssignBuildingModal(${tableList.id})">
                                        <i class="fa fa-bars" aria-hidden="true"></i>
                                    </button>
                                </security:authorize>
                                <a class="btn btn-xs btn-info" data-toggle="tooltip"
                                   title="Cập nhật toà nhà"
                                   href='<c:url value="/admin/building-edit-${tableList.id}"/>'>
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
<div id="assignmentBuildingModal" class="modal fade" role="dialog">
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
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="assignBuildingBtn">Giao toà nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>

<script type="text/javascript">

    // Search building
    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    });
    // Load Modal
    function openAssignBuildingModal(buildingId) {
        // Open modal
        $('#assignmentBuildingModal').modal();
        // Load staff into modal
        loadStaffs(buildingId);
        $('#buildingId').val(buildingId);
    };
    function loadStaffs(buildingId) {
        $.ajax({
            type: "GET",
            url: "${buildingAPI}/"+ buildingId +"/staffs",
            dataType: "json",
            success: function (response) {
                var row = '';
               $.each(response, function (index, item) {
                   row += '<tr>';
                   row += '<td class="text-center"><input type="checkbox" name="checkList" value='+ item.staffId +' id="checkbox_'+ item.staffId + '" '+ item.checked +' /></td>';
                   row += '<td class="text-center">'+ item.fullName + '</td>'
                   row += '</tr>'
               });
               $('#staffList tbody').html(row);

            },
            error: function (respone) {
                console.log(respone);
            }
        });
    }
    // Assign building
    $('#assignBuildingBtn').click(function (e) {
        e.preventDefault();
        var data = {};
        data['buildingId'] = $("#buildingId").val();
        var staffIds = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffIds'] = staffIds;
        assignBuilding(data);
    });
    function assignBuilding(data) {
        $.ajax({
            type: "POST",
            url: "${buildingAPI}/assignment-building",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (res) {
                showAlertAfterSuccess(function () {
                    console.log(res);
                });
            },
            error: function (res) {
                showAlertAfterFail(function () {
                    console.log(res);
                });
            }
        });
    }

    // Delete buildings
    $('#btnDelete').click(function (e) {
        e.preventDefault();
        showAlertBeforeDelete(function () {
            var buildingIds = $('#tableList').find('tbody input[type=checkbox][name="checkList"]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteBuilding(buildingIds);
        });
    });

    function deleteBuilding(data) {
        $.ajax({
            type: "DELETE",
            url: "${buildingAPI}",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (res) {
                showAlertAfterDeleteSuccess(function () {
                    window.location.href = "<c:url value='/admin/building-list?message=delete_success'/>";
                });
            },
            error: function (res) {
                showAlertAfterFail(function () {
                    window.location.href = "<c:url value='/admin/building-list?message=error_system'/>";
                });
            }
        });
    }

</script>


</body>
</html>
