package Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BDeleteAction implements BAction {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 한국어
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        BDao dao = new BDao();
        BDto dto = new BDto();
        dto.setBno(Integer.parseInt(request.getParameter("bno")));
        dto.setBpass(request.getParameter("bpass"));

        int result = dao.delete(dto);
        request.setAttribute("delete", result);
        System.out.println("삭제기능");

        if (result > 0) {
            out.println("<script>alert('글삭제성공!'); location.href='" + request.getContextPath() + "/list.do';</script>");
        } else {
            out.println("<script>alert('비밀번호를 확인해주세요.'); history.go(-1)</script>");
        }
    }
}