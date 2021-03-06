package controllers.admin;
import Dao.*;
import entitys.*;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@MultipartConfig
@WebServlet({"/Contract", "/storeContract", "/updateContract", "/deleteContract", "/editContract","/showDetail"})
public class ContractServlet extends HttpServlet {
    private contractDao contractDao;
    private roomDao roomDao;
    private typeDao typeDao;
    private customerDao customerDao;
    public ContractServlet() {
        this.contractDao=new contractDao();
        this.roomDao = new roomDao();
        this.typeDao=new typeDao();
        this.customerDao=new customerDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("Contract")) {
            this.create(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("updateContract")) {
            this.update(request, response);
        } else if (uri.contains("storeContract")) {
            this.store(request, response);
        } else if (uri.contains("editContract")) {
            this.edit(request, response);
        } else if (uri.contains("deleteContract")) {
            this.delete(request, response);
        }else if (uri.contains("showDetail")){
            this.show(request,response);
        }

    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<TypecontractEntity> dstype=this.typeDao.all();
        request.setAttribute("dstype",dstype);
        List<RoomEntity> dsroom=this.roomDao.empty();
        request.setAttribute("dsroom",dsroom);
        if (dsroom.isEmpty()){
            session.setAttribute("error","Kh??ng c??n c??n h??? n??o tr???ng");
        }
        List<ContractEntity> list = this.contractDao.all();
        request.setAttribute("list", list);
        request.setAttribute("view", "/views/admin/contract/create.jsp");
        request.setAttribute("view1", "/views/admin/contract/table.jsp");
        request.getRequestDispatcher("/views/admin/admin.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String cmnd= request.getParameter("cmnd");
        int id= Integer.parseInt(request.getParameter("id"));
        int id2= Integer.parseInt(request.getParameter("room_id"));
        int id3= Integer.parseInt(request.getParameter("type_id"));
        try {
            Customer customer = this.customerDao.findByCMND(cmnd);
            RoomEntity room = this.roomDao.findByID(id2);
            TypecontractEntity type = this.typeDao.findByID(id3);
            ContractEntity entity=this.contractDao.findByID(id);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setIdtype(type);
            entity.setIdCustomer(customer);
            entity.setIdRoom(room);
            this.contractDao.update(entity);
            session.setAttribute("message", "C???p Nh???t Th??nh C??ng");
            response.sendRedirect("/Contract");
        } catch (Exception e) {
            response.sendRedirect("/Contract");
            session.setAttribute("error", "C???p Nh???t Th???t B???i");
            e.printStackTrace();
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String s = request.getParameter("id");
        int id = Integer.parseInt(s);
        try {
            ContractEntity entity = this.contractDao.findByID(id);
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setStatus(false);
            this.contractDao.update(entity);
            session.setAttribute("message", "X??a Th??nh C??ng");
            response.sendRedirect("/Contract");
        } catch (Exception e) {
            session.setAttribute("error", "X??a Th???t B???i");
            response.sendRedirect("/Contract");
            e.printStackTrace();
        }
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TypecontractEntity> dstype=this.typeDao.all();
        request.setAttribute("dstype",dstype);
        int id = Integer.parseInt(request.getParameter("id"));
        ContractEntity entity = this.contractDao.findByID(id);
        request.setAttribute("contract", entity);
        List<RoomEntity> dsroom=this.roomDao.empty();
        dsroom.add(entity.getIdRoom());
        request.setAttribute("dsroom",dsroom);
        List<ContractEntity> list = this.contractDao.all();
        request.setAttribute("list", list);
        request.setAttribute("view", "/views/admin/contract/edit.jsp");
        request.setAttribute("view1", "/views/admin/contract/table.jsp");
        request.getRequestDispatcher("views/admin/admin.jsp").forward(request, response);
    }
    protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int id= Integer.parseInt(request.getParameter("id"));
       ContractEntity contract =this.contractDao.findByID(id);
       request.setAttribute("contract",contract);
        request.setAttribute("view", "/views/admin/contract/detail.jsp");
        request.getRequestDispatcher("views/admin/admin.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String cmnd= request.getParameter("cmnd");
        int id2= Integer.parseInt(request.getParameter("room_id"));
        int id3= Integer.parseInt(request.getParameter("type_id"));
        ContractEntity entity = new ContractEntity();
        List<ContractEntity> list = new ArrayList<>();
        try {
            BeanUtils.populate(entity, request.getParameterMap());
            Customer customer=this.customerDao.findByCMND(cmnd);
            RoomEntity room=this.roomDao.findByID(id2);
            if (customer==null){
                session.setAttribute("error","S??? CMND Kh??ch H??ng Kh??ng T???n T???i");
                response.sendRedirect("/Contract");
                return;
                }else {
                    TypecontractEntity type= this.typeDao.findByID(id3);
                    if (type.getId()==1){
                        room.setClassify(1);
                        this.roomDao.update(room);
                    }else if (type.getId()==2){
                        room.setClassify(2);
                        this.roomDao.update(room);
                    }
                    UsersEntity user = (UsersEntity) session.getAttribute("user");
                    entity.setIdUser(user);
                    entity.setIdtype(type);
                    entity.setIdCustomer(customer);
                    entity.setIdRoom(room);
                    entity.setStatus(true);
                    java.util.Date t = new java.util.Date();
                    entity.setDateCreate(new Date(t.getTime()));
                    this.contractDao.create(entity);
                    session.setAttribute("message", "Th??m M???i Th??nh C??ng");
                    list.add(entity);
                    request.setAttribute("list", list);
                    List<ContractEntity> all = this.contractDao.all();
                    request.setAttribute("list", all);
                    response.sendRedirect("/Contract");
                }
        } catch (Exception e) {
            session.setAttribute("error", "Th??m M???i Th???t B???i");
            response.sendRedirect("/Contract");
            e.printStackTrace();
        }
    }
}
