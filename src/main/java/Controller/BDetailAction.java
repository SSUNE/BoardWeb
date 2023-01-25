package Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BDetailAction implements BAction {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        BDao dao = new BDao();
        BDto dto = new BDto();
        dto.setBno(Integer.parseInt(request.getParameter("bno")));
        dao.update_hit(dto); //조회수올리기
        request.setAttribute("dto",dao.read(dto));

        System.out.println("글 상세가져오기 / 조회수 올리기");
    }
}
