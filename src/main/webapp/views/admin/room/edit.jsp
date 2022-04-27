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
    <h1 class="h3 mb-0 text-gray-800">Room Management</h1>
</div>
<form class=" row mt-3 ms-0 pe-0" action="updateRoom?id=${room.id}" method="post" >
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">NameBuilding</label>
        <select class="form-select" name="building_id">
            <c:forEach items="${ dsbuilding }" var="building">
                <option value="${ building.id }">
                        ${ building.nameBuilding }
                </option>
            </c:forEach>
        </select>
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Floor</label>
        <select class="form-select" name="floor_id" >
            <c:forEach items="${ dsfloor }" var="dsfloor">
                <option ${room.idFloor.id==dsfloor.id ? "selected":""}   value="${ dsfloor.id }">
                        ${ dsfloor.nameFloor}
                </option>
            </c:forEach>
        </select>
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">NameRoom</label>
        <input type="text" class="form-control" value="${room.nameRoom}" name="nameRoom">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Acreage</label>
        <input type="text" class="form-control" value="${room.acreage}" name="acreage">
    </div>
    <div class="mt-3">
        <button  class="btn btn-success">Update</button>
        <button type="reset" class="btn btn-primary">Reset</button>
    </div>
</form>
<br>


