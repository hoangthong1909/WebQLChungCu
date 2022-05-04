<%--
  Created by IntelliJ IDEA.
  User: thongpro
  Date: 3/31/22
  Time: 5:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="d-sm-flex align-items-center justify-content-between mb-4 offset-5">
    <h1 class="h3 mb-0 text-gray-800">Chi Tiết Hợp Đồng</h1>
</div>
<h3>Mã Hợp Đồng : <span style="color: red;">HD${contract.id}</span></h3>
<h3>Tên Hợp Đồng: <span style="color: red;">${contract.name}</span></h3>
<table class="table table-success table-striped">
    <thead>
    <tr>
        <th scope="col">Số CMND Khách Hàng</th>
        <th scope="col">Tên Khách Hàng</th>
        <th scope="col">Liên Hệ</th>
        <th scope="col">Tòa Nhà</th>
        <th scope="col">Địa Chỉ Toà Nhà</th>
        <th scope="col">Tầng</th>
        <th scope="col">Căn Hộ</th>
        <th scope="col">Diện Tích</th>
        <th scope="col">Số Phương Tiện</th>
        <th scope="col">Số Người</th>
        <th><a class="btn btn-primary" href="/Contract">Trở Về</a></th>

    </tr>
    </thead>
    <tbody>
        <tr>
            <td>${contract.idCustomer.cmnd}</td>
            <td>${contract.idCustomer.nameCustomer}</td>
            <td>${contract.idCustomer.phone}</td>
            <td>${contract.idRoom.idFloor.idBuilding.nameBuilding}</td>
            <td>${contract.idRoom.idFloor.idBuilding.address}</td>
            <td>${contract.idRoom.idFloor.nameFloor}</td>
            <td>${contract.idRoom.nameRoom}</td>
            <td><fmt:formatNumber value="${contract.idRoom.acreage}" pattern="#,###"/> m <sup>2</sup></td>
            <td>${contract.vehicle}</td>
            <td>${contract.people}</td>
            <td></td>
        </tr>
    </tbody>
</table>


