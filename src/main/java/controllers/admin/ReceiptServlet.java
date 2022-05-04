package controllers.admin;
import Dao.contractDao;
import Dao.receiptDao;
import JPAUtils.ExcelUtils;
import entitys.BuildingEntity;
import entitys.ContractEntity;
import entitys.FloorEntity;
import entitys.ReceiptEntity;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@MultipartConfig
@WebServlet({"/Receipt", "/storeReceipt", "/updateReceipt", "/deleteReceipt", "/editReceipt","/exportExcelReceipt"})
public class ReceiptServlet extends HttpServlet {
    private receiptDao dao;
    private contractDao contractDao;

    public ReceiptServlet() {
        this.dao = new receiptDao();
        this.contractDao = new contractDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("Receipt")) {
            this.create(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("updateReceipt")) {
            this.update(request, response);
        } else if (uri.contains("storeReceipt")) {
            this.store(request, response);
        } else if (uri.contains("editReceipt")) {
            this.edit(request, response);
        } else if (uri.contains("deleteReceipt")) {
            this.delete(request, response);
        }else if (uri.contains("exportExcelReceipt")){
            this.exportExcel(request, response);
        }

    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ContractEntity> dsContract = this.contractDao.all();
        request.setAttribute("dsContract", dsContract);
        List<ReceiptEntity> list = this.dao.all();
        request.setAttribute("list", list);
        request.setAttribute("view", "/views/admin/receipt/create.jsp");
        request.setAttribute("view1", "/views/admin/receipt/table.jsp");
        request.getRequestDispatcher("/views/admin/admin.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        int idContract = Integer.parseInt(request.getParameter("contractID"));
        try {
            ContractEntity contractEntity = this.contractDao.findByID(idContract);
            ReceiptEntity entity = this.dao.findByID(id);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setContract_id(contractEntity);
            this.dao.update(entity);
            session.setAttribute("message", "Cập Nhật Thành Công");
            response.sendRedirect("/Receipt");
        } catch (Exception e) {
            response.sendRedirect("/Receipt");
            session.setAttribute("error", "Cập Nhật Thất Bại");
            e.printStackTrace();
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        try {
            ReceiptEntity entity = this.dao.findByID(id);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setStatus(false);
            this.dao.update(entity);
            session.setAttribute("message", "Xóa Thành Công");
            response.sendRedirect("/Receipt");
        } catch (Exception e) {
            session.setAttribute("error", "Xóa Thất Bại");
            response.sendRedirect("/Receipt");
            e.printStackTrace();
        }
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ContractEntity> dsContract = this.contractDao.all();
        request.setAttribute("dsContract", dsContract);
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        ReceiptEntity entity = this.dao.findByID(id);
        request.setAttribute("receipt", entity);
        List<ReceiptEntity> list = this.dao.all();
        request.setAttribute("list", list);
        request.setAttribute("view", "/views/admin/receipt/edit.jsp");
        request.setAttribute("view1", "/views/admin/receipt/table.jsp");
        request.getRequestDispatcher("views/admin/admin.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("contractID");
        int id = Integer.parseInt(s);
        ReceiptEntity entity = new ReceiptEntity();
        List<ReceiptEntity> list = new ArrayList<>();
        try {
            BeanUtils.populate(entity, request.getParameterMap());
            ContractEntity contract = this.contractDao.findByID(id);
            entity.setContract_id(contract);
            entity.setStatus(true);
            this.dao.create(entity);
            session.setAttribute("message", "Thêm Mới Thành Công");
            list.add(entity);
            request.setAttribute("list", list);
            List<ReceiptEntity> all = this.dao.all();
            request.setAttribute("list", all);
            response.sendRedirect("/Receipt");
        } catch (Exception e) {
            session.setAttribute("error", "Thêm Mới Thất Bại");
            response.sendRedirect("/Receipt");
            e.printStackTrace();
        }
    }
    protected void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        String nameExcel=request.getParameter("nameExcel");
        try {
        new ExcelUtils().xuatExcelReceipt(nameExcel);
        session.setAttribute("message","Xuất Excel Thành Công");
            response.sendRedirect("/Receipt");
        } catch (IOException e) {
            session.setAttribute("message","Xuất Excel Thất Bại");
            response.sendRedirect("/Receipt");
            e.printStackTrace();
        }
    }
}
