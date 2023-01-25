package Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class BListAction implements BAction {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        BDao dao = new BDao();
        //request.setAttribute("list", dao.listAll());
        System.out.println("전체 데이터 가져오기");
        //////////////////////////////////////////////////////////////////////////////////
        int pageTotal = dao.listcnt();
        System.out.println("1. 전체 게시판(레코드)갯수" + pageTotal);
        int onepagelimit = 10;
        System.out.println("2. 한페이지당 보여주는(레코드)갯수" + onepagelimit);
        int pageAll = (int) Math.ceil(pageTotal / (float) onepagelimit);
        System.out.println("3. 전체페이지 : " + pageAll);
        int pstartno = 0;
        if (request.getParameter("pstartno") != null) {
            pstartno = Integer.parseInt(request.getParameter("pstartno"));
        }
        System.out.println("4. db에서 가져올 번호 : " + pstartno);
        ArrayList<BDto> list = dao.list10(pstartno);
        System.out.println("4-2. db에서 가져온 리스트 : " + list);
        int bottomlist = 10;
        System.out.println("5. 하단페이지네비 : " + bottomlist);
        int current = (int) Math.ceil((pstartno + 1) / (float) onepagelimit);
        System.out.println("6. 현재페이지 : " + current);
        int start = (int) Math.floor((current - 1) / (float) bottomlist) * bottomlist + 1;
        System.out.println("7. 하단네비 시작페이지 : " + start);
        int end = start + bottomlist - 1;
        if (pageAll < end) {
            end = pageAll;
        }
        System.out.println("8. 하단네비 끝페이지 : " + end);
        request.setAttribute("pstartno", pstartno);
        request.setAttribute("pageTotal", pageTotal);
        request.setAttribute("onepagelimit", onepagelimit);
        request.setAttribute("pageAll", pageAll);
        request.setAttribute("bottomlist", bottomlist);
        request.setAttribute("current", current);
        request.setAttribute("start", start);
        request.setAttribute("end", end);
        request.setAttribute("paginglist", list);
        //////////////////////////////////////////////////////////////////////////////////
    }
}