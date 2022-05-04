package controllers.admin;
import Dao.UserDao;
import Dao.buildingDao;
import Dao.floorDao;
import JPAUtils.ExcelUtils;
import entitys.BuildingEntity;
import entitys.FloorEntity;
import entitys.UsersEntity;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet({"/Building", "/storeBuilding", "/updateBuilding", "/deleteBuilding", "/editBuilding","/exportExcelBuilding"})
public class BuildingServlet extends HttpServlet {
    private buildingDao dao;
    private UserDao userDao;
    private floorDao foorDao;

    public BuildingServlet() {
        this.dao = new buildingDao();
        this.userDao = new UserDao();
        this.foorDao = new floorDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("Building")) {
            this.create(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("updateBuilding")) {
            this.update(request, response);
        } else if (uri.contains("storeBuilding")) {
            this.store(request, response);
        } else if (uri.contains("editBuilding")) {
            this.edit(request, response);
        } else if (uri.contains("deleteBuilding")) {
            this.delete(request, response);
        } else if (uri.contains("exportExcelBuilding")) {
            this.exportExcel(request, response);
        }
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BuildingEntity> list = this.dao.all();
        request.setAttribute("list", list);
        request.setAttribute("view", "/views/admin/building/create.jsp");
        request.setAttribute("view1", "/views/admin/building/table.jsp");
        request.getRequestDispatcher("/views/admin/admin.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        try {
            BuildingEntity entity = this.dao.findByID(id);
            BeanUtils.populate(entity, request.getParameterMap());
            this.dao.update(entity);
            session.setAttribute("message", "Cập Nhật Thành Công");
            response.sendRedirect("/Building");
        } catch (Exception e) {
            session.setAttribute("error", "Cập Nhật Thất Bại");
            response.sendRedirect("/Building");
            e.printStackTrace();
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        try {
            BuildingEntity entity = this.dao.findByID(id);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setStatus(0);
            this.dao.update(entity);
            session.setAttribute("message", "Xóa Thành Công");
            response.sendRedirect("/Building");
        } catch (Exception e) {
            session.setAttribute("error", "Xóa Thất Bại");
            response.sendRedirect("/Building");
            e.printStackTrace();
        }
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        BuildingEntity entity = this.dao.findByID(id);
        request.setAttribute("building", entity);
        List<BuildingEntity> list = this.dao.all();
        request.setAttribute("list", list);
        request.setAttribute("view", "/views/admin/building/edit.jsp");
        request.setAttribute("view1", "/views/admin/building/table.jsp");
        request.getRequestDispatcher("views/admin/admin.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        BuildingEntity entity = new BuildingEntity();
        List<BuildingEntity> list = new ArrayList<>();
        List<BuildingEntity> all = this.dao.all();
        try {
            BeanUtils.populate(entity, request.getParameterMap());
            UsersEntity user = (UsersEntity) session.getAttribute("user");
            entity.setIdUser(user);
            entity.setStatus(1);
            entity.setDateCreate(new Date());
            this.dao.create(entity);
            session.setAttribute("message", "Thêm Mới Thành Công");
            list.add(entity);
            request.setAttribute("list", list);
            request.setAttribute("list", all);
            response.sendRedirect("/Building");
        } catch (Exception e) {
            session.setAttribute("error", "Thêm Mới Thất Bại");
            response.sendRedirect("/Building");
            e.printStackTrace();
        }
    }

    protected void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        String nameExcel=request.getParameter("nameExcelBuilding");
        try {
            new ExcelUtils().xuatExcelBuiding(nameExcel);
            session.setAttribute("message","Xuất Excel Thành Công");
            response.sendRedirect("/Building");
        } catch (IOException e) {
            session.setAttribute("error","Xuất Excel Thất Bại");
            response.sendRedirect("/Building");
            e.printStackTrace();
        }
    }
}
