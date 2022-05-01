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
        <th scope="col">Tòa Nhà</th>
        <th scope="col">Địa Chỉ</th>
        <th scope="col">Người Tạo</th>
        <th scope="col">Ngày Tạo</th>
        <th scope="col">Phí Vệ Sinh</th>
        <th scope="col">Phí An Ninh</th>
        <th scope="col">Phí Chăm Sóc Cảnh Quan</th>
        <th scope="col">Phí Hoạt Động,Bảo Dưỡng Tiện Ích Chung</th>
        <th scope="col">Phí Thu Dọn Rác</th>
        <th scope="col">Tổng Tiền</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="building">
        <tr>
            <td>${building.nameBuilding}</td>
            <td>${building.address}</td>
            <td>${building.user.name}</td>
            <td><fmt:formatDate value="${building.dateCreate}" pattern="dd/MM/yyyy"/></td>
            <td><fmt:formatNumber value="${building.toilet}" pattern="#,###"/> VND</td>
            <td><fmt:formatNumber value="${building.security}" pattern="#,###"/> VND</td>
            <td><fmt:formatNumber value="${building.landscapeCare}" pattern="#,###"/> VND</td>
            <td><fmt:formatNumber value="${building.work}" pattern="#,###"/> VND</td>
            <td><fmt:formatNumber value="${building.garbage}" pattern="#,###"/> VND</td>
            <td style="color: red"><fmt:formatNumber value="${building.garbage+building.toilet+building.security+building.landscapeCare+building.work}" pattern="#,###"/> VND</td>
            <td>
                <form action="editBuilding" method="post">
                    <input type="hidden" value="${building.id}" name="id">
                    <button class="btn btn-primary">Cập Nhật</button>
                </form>
            </td>
            <td>
                <button data-toggle="modal" data-target="#b${building.id}" class="btn btn-danger">Xóa</button>
            </td>
            <div id="b${building.id}" class="modal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title">Xác nhận</h3>
                            <button type="button" class="btn-close" data-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <h5>Bạn muốn xóa tòa nhà ${building.nameBuilding} ?</h5>
                        </div>
                        <div class="modal-footer">
                            <form action="deleteBuilding" method="post">
                                <input type="hidden" value="${building.id}" name="id">
                                <button class="btn btn-danger">Xóa</button>
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
<%--</c:if>--%>


