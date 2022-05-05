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
        <th scope="col">Chủ Hộ</th>
        <th scope="col">Căn Hộ</th>
        <th scope="col">Liên Hệ</th>
        <th scope="col">Phí Dịch Vụ</th>
        <th scope="col">Phí Gửi Xe</th>
        <th scope="col">Phí Điện</th>
        <th scope="col">Phí Nước</th>
        <th scope="col">Internet</th>
        <th scope="col">Tổng Tiền</th>
        <th scope="col"><button data-toggle="modal" data-target="#xuat" class="btn btn-success">Xuất Excel</button></th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${list}" var="receipt">
        <tr>
            <td>${receipt.contract_id.idCustomer.nameCustomer}</td>
            <td>${receipt.contract_id.idRoom.nameRoom}</td>
            <td>${receipt.contract_id.idCustomer.phone}</td>
            <td><fmt:formatNumber value="${receipt.service}" pattern="#,###"/> VND</td>
            <td><fmt:formatNumber value="${receipt.parking}" pattern="#,###"/> VND</td>
            <td><fmt:formatNumber value="${receipt.electricity}" pattern="#,###"/> VND</td>
            <td><fmt:formatNumber value="${receipt.water}" pattern="#,###"/> VND</td>
            <td><fmt:formatNumber value="${receipt.internet}" pattern="#,###"/> VND</td>
            <td style="color: red"><fmt:formatNumber value="${receipt.internet+receipt.water+receipt.electricity+receipt.parking+receipt.service}" pattern="#,###"/> VND</td>

            <td>
                <form action="/editReceipt" method="post">
                    <input type="hidden" value="${receipt.id}" name="id">
                    <button class="btn btn-primary">Cập Nhật</button>
                </form>
            </td>
            <td>
                <button class="btn btn-danger" data-toggle="modal" data-target="#cz${receipt.id}">Xóa</button>
            </td>
            <div id="cz${receipt.id}" class="modal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title">Xác nhận</h3>
                            <button type="button" class="btn-close" data-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <h5>Bạn muốn xóa Hóa Đơn Của Chủ Hộ  ${receipt.contract_id.idCustomer.nameCustomer} ?</h5>
                        </div>
                        <div class="modal-footer">
                            <form action="/deleteReceipt" method="post">
                                <input type="hidden" value="${receipt.id}" name="id">
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
<div id="xuat" class="modal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Xác nhận</h3>
                <button type="button" class="btn-close" data-dismiss="modal"
                        aria-label="Close"></button>
            </div>
            <form action="exportExcelReceipt" method="post">
                <div class="modal-body">
                    <label class="form-label fw-bold">Nhập Tên File</label>
                    <input type="text" class="form-control" name="nameExcel">
                </div>
                <div class="modal-footer">
                    <button class="btn btn-danger">Xác nhận</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"
                            aria-label="Close">Hủy
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

