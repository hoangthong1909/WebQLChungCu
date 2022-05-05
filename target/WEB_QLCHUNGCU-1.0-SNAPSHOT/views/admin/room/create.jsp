<%--
  Created by IntelliJ IDEA.
  User: thongpro
  Date: 3/31/22
  Time: 9:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="d-sm-flex align-items-center justify-content-between mb-4 offset-5">
    <h1 class="h3 mb-0 text-gray-800">Quản Lý Căn Hộ</h1>
</div>
<form class=" row mt-3 ms-0 pe-0" action="storeRoom" method="post" >
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Tòa Nhà</label>
        <select class="form-select" name="building_id" >
            <c:forEach items="${ dsbuilding }" var="building">
                <option  value="${ building.id }">
                        ${ building.nameBuilding }
                </option>
            </c:forEach>
        </select>
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Tầng</label>
        <select class="form-select" name="floor_id" >
            <c:forEach items="${ dsfloor }" var="dsfloor">
                <option  value="${ dsfloor.id }">
                        ${ dsfloor.nameFloor}
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Căn Hộ</label>
        <input type="text" class="form-control" name="nameRoom">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Diện Tích</label>
        <input type="text" class="form-control" name="acreage">
    </div>

    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Số Phòng Tắm</label>
        <input type="text" class="form-control" name="bathroom">
    </div>

    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Số Phòng Ngủ</label>
        <input type="text" class="form-control" name="bedroom">
    </div>
    <div class="mt-3">
        <button  class="btn btn-success">Thêm</button>
        <button type="reset" class="btn btn-primary">Làm Mới</button>
    </div>
</form>
<br>


