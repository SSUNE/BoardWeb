package Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BWriteAction implements BAction {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        BDao dao = new BDao();
        BDto dto = new BDto();

        dto.setBname(request.getParameter("bname"));
        dto.setBpass(request.getParameter("bpass"));
        dto.setBtitle(request.getParameter("btitle"));
        dto.setBcontent(request.getParameter("bcontent"));

        int result = dao.create(dto);
        request.setAttribute("create", result);
        System.out.println("글삽입기능");

        if (result > 0) {
            out.println("<script>alert('글쓰기 성공!'); location.href='" + request.getContextPath() + "/list.do';</script>");
        } else {
            out.println("<script>alert('관리자에게 문의해주세요.'); history.go(-1)</script>");
        }
    }
}