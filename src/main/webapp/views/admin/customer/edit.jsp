<%--
  Created by IntelliJ IDEA.
  User: thongpro
  Date: 3/31/22
  Time: 9:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="d-sm-flex align-items-center justify-content-between mb-4 offset-5">
    <h1 class="h3 mb-0 text-gray-800">Quản Lý Khách Hàng</h1>
</div>
<form class=" row mt-3 ms-0 pe-0" action="updateCustomer?id=${customer.id}" method="post" >
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Họ Tên</label>
        <input type="text" class="form-control" name="nameCustomer" value="${customer.nameCustomer}">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Điện Thoại</label>
        <input type="text" class="form-control" name="phone" value="${customer.phone}">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Email</label>
        <input type="email" class="form-control" name="email" value="${customer.email}">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Địa Chỉ</label>
        <input type="text" class="form-control" name="address" value="${customer.address}">
    </div>
    <div class="mb-3 col-6">
        <label class="form-label fw-bold">Số CMND</label>
        <input type="text" class="form-control" name="cmnd" value="${customer.cmnd}">
    </div>

    <div class=" p-3 mt-4 col-6">
        <label class="form-label fw-bold pe-4">Giới Tính</label>
        <input class="form-check-input" type="radio" value="1" ${customer.sex==true ? "checked" : ""}  name="sex">
        <label class="form-check-label me-5">Nam</label>
        <input class="form-check-input" type="radio" value="0" ${customer.sex==false ? "checked" : ""} name="sex">
        <label class="form-check-label me-3">Nữ</label>
    </div>

    <div class="mt-3">
        <button  class="btn btn-success">Cập Nhật</button>
        <button type="reset" class="btn btn-primary">Làm mới</button>
    </div>
</form>
<br>

