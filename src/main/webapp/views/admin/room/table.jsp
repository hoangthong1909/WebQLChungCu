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
<c:if test="${empty list}">
    <p class="alert alert-warning">
        Vui Lòng Thêm Mới Dữ Liệu
    </p>
</c:if>
<c:if test="${!empty sessionScope.error}">
    <div class="alert alert-danger">
            ${sessionScope.error}
    </div>
    <c:remove var="error" scope="session"/>
</c:if>
<c:if test="${!empty sessionScope.message}">
    <div class="alert alert-success">
            ${sessionScope.message}
    </div>
    <c:remove var="message" scope="session"/>
</c:if>
<table class="table table-success table-striped">
    <thead>
    <tr>
        <th scope="col">Tầng</th>
        <th scope="col">Căn Hộ</th>
        <th scope="col">Diện Tích</th>
        <th scope="col">Số Phòng Tắm</th>
        <th scope="col">Số Phòng Ngủ</th>
        <th scope="col">Trạng Thái</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${list}" var="room">
        <tr>
            <td>${room.idFloor.nameFloor}</td>
            <td>${room.nameRoom}</td>
            <td><fmt:formatNumber value="${room.acreage}" pattern="#,###"/> m<sup>2</sup></td>
            <td>${room.bathroom}</td>
            <td>${room.bedroom}</td>
            <td>
                <c:choose>
                    <c:when test="${room.classify==0}"><span style="color: red">Chưa Sử Dụng</span></c:when>
                    <c:when test="${room.classify==1}"><span style="color: #0a58ca">Đã Cho Thuê</span></c:when>
                    <c:when test="${room.classify==2}"><span style="color: green">Đã Bán</span></c:when>
                    <c:otherwise>-</c:otherwise>
                </c:choose>
            </td>
            <td>
                <form action="/editRoom" method="post">
                    <input type="hidden" value="${room.id}" name="id">
                    <button class="btn btn-primary">Cập Nhật</button>
                </form>
            </td>
            <td>
                <button class="btn btn-danger" data-toggle="modal" data-target="#c${room.id}">Xóa</button>
            </td>
            <div id="c${room.id}" class="modal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title">Xác nhận</h3>
                            <button type="button" class="btn-close" data-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <h5>Bạn muốn xóa  ${room.nameRoom} ?</h5>
                        </div>
                        <div class="modal-footer">
                            <form action="/deleteRoom" method="post">
                                <input type="hidden" value="${room.id}" name="id">
                                <button class="btn btn-danger">Delete</button>
                            </form>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                    aria-label="Close">Hủy
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </tr>
    </c:forEach>
    </tbody>
</table>


