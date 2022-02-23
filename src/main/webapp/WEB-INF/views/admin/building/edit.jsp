<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"/>

<html>
<head>
    <title>Chỉnh sửa toà nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href='<c:url value="/admin/home" />'>Home</a>
                </li>
                <li class="active">Chỉnh sửa toà nhà</li>
            </ul><!-- /.breadcrumb -->

        </div>

        <div class="page-content">


            <div class="row">

                <div class="col-xs-12">
                    <form:form modelAttribute="model" id="formEdit" cssClass="form-horizontal" enctype="multipart/form-data">

                        <div class="form-group">
                            <div class="col-xs-9">
                                <form:hidden path="id" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="name"> Tên toà nhà </label>
                            <div class="col-xs-9">
                                <form:input path="name" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="districtCode"> Quận </label>
                            <div class="col-xs-9">
                                <form:select path="districtCode" cssClass="chosen-select">
                                    <form:option value="">--- Chọn Quận ---</form:option>
                                    <form:options items="${districts}" />
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="ward"> Phường </label>
                            <div class="col-xs-9">
                                <form:input path="ward" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="street"> Đường
                            </label>
                            <div class="col-xs-9">
                                <form:input path="street" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="structure"> Kết Cấu
                            </label>
                            <div class="col-xs-9">
                                <form:input path="structure" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="numberOfBasement"> Số tầng hầm
                            </label>
                            <div class="col-xs-9">
                                <input type="number" name="numberOfBasement" id="numberOfBasement"
                                       class="form-control" value="${model.numberOfBasement}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="floorArea"> Diện tích sàn
                            </label>
                            <div class="col-xs-9">
                                <input type="number" name="floorArea" id="floorArea"
                                       class="form-control" value="${model.floorArea}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="direction"> Hướng
                            </label>
                            <div class="col-xs-9">
                                <form:input path="direction" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="level"> Hạng
                            </label>
                            <div class="col-xs-9">
                                <form:input path="level" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="rentArea"> Diện tích thuê
                            </label>
                            <div class="col-xs-9">
                                <form:input path="rentArea" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="rentAreaDescription"> Mô tả diện tích
                            </label>
                            <div class="col-xs-9">
                                <form:textarea path="rentAreaDescription" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="rentPrice"> Giá thuê </label>
                            <div class="col-xs-9">
                                <input type="number" name="rentPrice" id="rentPrice" class="form-control" value="${model.rentPrice}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="rentPriceDescription"> Mô tả giá </label>
                            <div class="col-xs-9">
                                <form:input path="rentPriceDescription" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="serviceFee"> Phí dịch vụ</label>
                            <div class="col-xs-9">
                                <form:input path="serviceFee" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="carFee"> Phí ô tô</label>
                            <div class="col-xs-9">
                                <form:input path="carFee" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="motobikeFee"> Phí mô tô</label>
                            <div class="col-xs-9">
                                <form:input path="motobikeFee" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="overtimeFee"> Phí ngoài giờ</label>
                            <div class="col-xs-9">
                                <form:input path="overtimeFee" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="waterFee"> Tiền nước</label>
                            <div class="col-xs-9">
                                <form:input path="waterFee" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="electricityFee"> Tiền điện</label>
                            <div class="col-xs-9">
                                <form:input path="electricityFee" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="deposit"> Đặt cọc</label>
                            <div class="col-xs-9">
                                <form:input path="deposit" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="payment"> Thanh toán</label>
                            <div class="col-xs-9">
                                <form:input path="payment" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="rentTime"> Thời gian thuê</label>
                            <div class="col-xs-9">
                                <form:input path="rentTime" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="decorationTime"> Thời gian trang trí</label>
                            <div class="col-xs-9">
                                <form:input path="decorationTime" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="managerName"> Tên quản lý</label>
                            <div class="col-xs-9">
                                <form:input path="managerName" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="managerPhone"> SĐT quản lý</label>
                            <div class="col-xs-9">
                                <form:input path="managerPhone" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="brokerageFee"> Phí môi giới</label>
                            <div class="col-xs-9">
                                <form:input path="brokerageFee" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right"> Loại toà nhà </label>
                            <div class="col-xs-9">
                                <%--<label class="checkbox-inline"><input type="checkbox" value="TANG_TRET"--%>
                                                                      <%--name="buildingTypes">Tầng Trệt</label>--%>
                                <%--<label class="checkbox-inline"><input type="checkbox" value="NGUYEN_CAN"--%>
                                                                      <%--name="buildingTypes">Nguyên Căn</label>--%>
                                <%--<label class="checkbox-inline"><input type="checkbox" value="NOI_THAT"--%>
                                                                      <%--name="buildingTypes">Nội thất</label>--%>

                                <c:forEach var="item" items="${buildingTypes}">

                                    <label class="checkbox-inline">
                                        <form:checkbox path="buildingTypes" value="${item.key}"/>${item.value}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="note"> Ghi chú
                            </label>
                            <div class="col-xs-9">
                                <form:textarea path="note" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="linkOfBuilding"> Link sản phẩm</label>
                            <div class="col-xs-9">
                                <form:input path="linkOfBuilding" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="map"> Bản đồ</label>
                            <div class="col-xs-9">
                                <form:input path="map" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="image"> Hình đại diện</label>
                            <div class="col-xs-3">
                                <form:input path="image" type="file" />
                            </div>
                            <div class="col-xs-6">
                                <img src='<c:url value="${model.imageUrl}"/>' alt="Ảnh đại diện">
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-xs-9 col-xs-push-3">
                                <c:choose>
                                    <c:when test="${not empty model.id}">
                                        <input type="button" class="btn btn-md btn-primary"
                                               value="Cập nhật toà nhà" id="updateBuildingBtn"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="submit" class="btn btn-md btn-primary"
                                               value="Thêm mới toà nhà" id="createBuildingBtn"/>
                                    </c:otherwise>
                                </c:choose>
                                <input type="button" class="btn btn-md btn-warning"
                                       value="Huỷ" id="cancelBuildingBtn"/>
                                <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image">



                            </div>
                        </div>

                    </form:form>
                </div>


            </div><!-- /.row -->


        </div> <!-- PAGE CONTENT ENDS -->
    </div><!-- /.page-content -->
</div><!-- /.main-content -->

<script>


    $(document).ready(function () {

        // function convertData(data){
        //     var buildingTypes = [];
        //     var formData = $("#formEdit").serializeArray();
        //     $.each(formData, function (indexInArray, element) {
        //         if (element.name == 'buildingTypes') {
        //             buildingTypes.push(element.value);
        //         } else {
        //             data["" + element.name + ""] = element.value;
        //         }
        //     });
        //     data['buildingTypes'] = buildingTypes;
        // }
        // //
        // $("#createBuildingBtn").click(function (e) {
        //     e.preventDefault();
        //     const formData = new FormData();
        //     formData.append("image", $("#formEdit input[name=image]")[0].files[0]);
        //     var building = {};
        //     convertData(building);
        //     formData.append("building", new Blob([JSON.stringify(building)], {
        //         type: "application/json"
        //     }));
        //     createBuilding(formData);
        // })

        $("#createBuildingBtn").click(function (e) {
            e.preventDefault();
            let form = $("#formEdit")[0];
            const formData = new FormData(form);
            createBuilding(formData);
        })

        function createBuilding(formData){
            $('#loading_image').show();
            // API
            $.ajax({
                type: "POST",
                url: "${buildingAPI}",
                data: formData,
                dataType: "json",
                enctype: 'multipart/form-data',
                // prevent jQuery from automatically transforming the data into a query string
                processData: false,
                contentType: false,
                success: function (response) {
                    showAlertAfterCreateSuccess(function () {
                        $('#loading_image').hide();
                        window.location.href = "<c:url value='/admin/building-edit-"+response.id+"?message=insert_success'/>";
                    });
                },
                error: function (response) {
                    showAlertAfterFail(function () {
                        $('#loading_image').hide();
                        window.location.href = "<c:url value='/admin/building-edit?message=error_system'/>";
                    });
                }
            });
        }
        // Update building
        $("#updateBuildingBtn").click(function () {
            var data = {};
            convertData(data);
            updateBuilding(data);
        });
        function updateBuilding(data){
            $('#loading_image').show();
            // API
            $.ajax({
                type: "PUT",
                url: "${buildingAPI}/"+data['id'],
                data: JSON.stringify(data),
                dataType: "json",
                contentType: "application/json",
                success: function (response) {
                    showAlertAfterUpdateSuccess(function () {
                        $('#loading_image').hide();
                        window.location.href = "<c:url value='/admin/building-edit-"+response.id+"?message=update_success'/>";
                    })
                },
                error: function (response) {
                    showAlertAfterFail(function () {
                        $('#loading_image').hide();
                        window.location.href = "<c:url value='/admin/building-edit-"+response.id+"?message=error_system'/>";
                    })
                }
            });
        }
        // Cancel building
        $("#cancelBuildingBtn").click(function () {
            window.location.href = "<c:url value='/admin/building-list?message=add_cancel'/>"
        });

    });


</script>

</body>
</html>
