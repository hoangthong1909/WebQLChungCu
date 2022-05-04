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
        <th scope="col">STT</th>
        <th scope="col">Mã Hợp Đồng</th>
        <th scope="col">Tên Hợp Đồng</th>
        <th scope="col">Loại Hợp Đồng</th>
        <th scope="col">Người Tạo</th>
        <th scope="col">Tên Khách Hàng</th>
        <th scope="col">Ngày Tạo</th>
        <th scope="col">Ngày Kết Thúc</th>
        <th scope="col">Tổng Tiền</th>
        <th></th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${list}" var="contract" varStatus="status">
        <tr>
            <td>#${status.count}</td>
            <td>HD${contract.id}</td>
            <td>${contract.name}</td>
            <td>${contract.idUser.name}</td>
            <td>${contract.idtype.name}</td>
            <td>${contract.idCustomer.nameCustomer}</td>
            <td><fmt:formatDate value="${contract.dateCreate}" pattern="dd/MM/yyyy"/></td>
            <td><fmt:formatDate value="${contract.dateEnd}" pattern="dd/MM/yyyy"/></td>
            <td style="color: red"><fmt:formatNumber value="${contract.price}" pattern="#,###"/> VND</td>
            <td>
                <form action="/editContract" method="post">
                    <input type="hidden" value="${contract.id}" name="id">
                    <button class="btn btn-primary">Cập Nhật</button>
                </form>
            </td>
            <td>
                <button class="btn btn-danger" data-toggle="modal" data-target="#c${contract.id}">Xóa</button>
            </td>
            <td>
            <form action="showDetail" method="post">
                <input type="hidden" name="id" value="${contract.id}" >
                <button class="btn btn-warning">Xem Chi Tiết</button>
            </form>
            </td>
            <div id="c${contract.id}" class="modal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title">Xác nhận</h3>
                            <button type="button" class="btn-close" data-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <h5>Bạn muốn xóa Mã Hợp Đồng HD${contract.id} ?</h5>
                        </div>
                        <div class="modal-footer">
                            <form action="/deleteContract" method="post">
                                <input type="hidden" value="${contract.id}" name="id">
                                <button class="btn btn-danger">Xóa</button>
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


