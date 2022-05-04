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
    <h1 class="h3 mb-0 text-gray-800">Quản Lý Hóa Đơn </h1>
</div>
<form class=" row mt-3 ms-0 pe-0" action="storeReceipt" method="post" >
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Mã Hợp Đồng</label>
        <select class="form-select" name="contractID" >
            <c:forEach items="${ dsContract }" var="contract">
                <option  value="${ contract.id }">
                        HD${ contract.id }
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Phí Dịch Vụ</label>
        <input type="text" class="form-control" name="service">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Phí Gửi Xe</label>
        <input type="text" class="form-control" name="parking">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Phí Điện</label>
        <input type="text" class="form-control" name="electricity">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Phí Nước</label>
        <input type="text" class="form-control" name="water">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Internet</label>
        <input type="text" class="form-control" name="internet">
    </div>
    <div class="mt-3">
        <button  class="btn btn-success">Thêm</button>
        <button type="reset" class="btn btn-primary">Làm Mới</button>
    </div>
</form>
<br>


