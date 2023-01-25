package Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BEditAction implements BAction {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        BDao dao = new BDao();
        BDto dto = new BDto();
        dto.setBno(Integer.parseInt(request.getParameter("bno")));
        dto.setBpass(request.getParameter("bpass"));
        dto.setBtitle(request.getParameter("btitle"));
        dto.setBcontent(request.getParameter("bcontent"));

        int result = dao.update(dto);
        request.setAttribute("dto", dao.read(dto));

        System.out.println("수정기능");
        if (result > 0) {
            out.println("<script>alert('글수정성공!'); location.href='" + request.getContextPath() + "/detail.do?bno=" + request.getParameter("bno") + "';</script>");
        } else {
            out.println("<script>alert('비밀번호를 확인해주세요.'); history.go(-1)</script>");
        }
    }
}