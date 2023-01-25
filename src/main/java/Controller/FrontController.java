package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FrontController", value = "*.do")
public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FrontController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        actionTest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        actionTest(request, response);
    }

    protected void actionTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        BAction comm = null; // 인터페이스소환
        //String path = request.getServletPath();
        String path = request.getRequestURI().substring(request.getContextPath().length());

        String view = "/board/list.jsp";
        if (path.equals("/list.do")) {
            System.out.println("get-list로 이동!");
            //☆1. 전체데이터 가져오기 ★2./board/list.jsp로 이동//☆1. 전체데이터 가져오기 ★2./board/list.jsp로 이동
            comm = new BListAction();
            comm.execute(request, response); // 여기가 Blistaction을 실행한거니까
            view = "/board/list.jsp";

        } else if (path.equals("/write_view.do")) {
            System.out.println("get-write 폼페이지로 이동!");
            //★1. ------ 	★2.  /board/list.jsp로 이동
            view = "/board/write.jsp";

        } else if (path.equals("/write.do")) {
            System.out.println("post-write 기능 이동!");
            //☆1. 글삽입기능	★2.  /list.do로 이동
            comm = new BWriteAction();
            comm.execute(request, response);
            view = "/BListAction?pstartno=0"; // /board/list.jsp 이 경로에서 변경
            return;

        } else if (path.equals("/detail.do")) {
            System.out.println("get-상세페이지로 이동!");
            //☆1. 글상세가져오기/ 조회수 올리기	★2.  /board/detail.jsp로 이동
            comm = new BDetailAction();
            comm.execute(request, response);
            view = "/board/detail.jsp";

        } else if (path.equals("/edit_view.do")) {
            System.out.println("get-edit_view 수정폼페이지로 이동!");
            //☆1. 글 상세가져오기	★2.  /board/edit.jsp로 이동
            comm = new BEditViewAction();
            comm.execute(request, response);
            view = "/board/edit.jsp";

        } else if (path.equals("/edit.do")) {
            System.out.println("post-edit 페이지로 이동!");
            //☆1. 글 수정하기	★2.  /board/detail.jsp로 이동
            comm = new BEditAction();
            comm.execute(request, response);
            view = "/list.do"; // 여기 경로에서 막 뭐라하던데 뭐지 그치? 그래서 아까 그걸로 바꿨떤건데 진짜 틀렸네 선생님?.......나눔이꺼보고올게

        } else if (path.equals("/delete_view.do")) {
            System.out.println("get-삭제폼페이지로 이동!");
            //★1. ------ 	★2.  /board/delete.jsp로 이동
            comm = new BDeleteAction();
            comm.execute(request, response);
            view = "/board/delete.jsp";

        } else if (path.equals("/delete.do")) {
            System.out.println("get-삭제 기능 이동!");
            //☆1. 글 삭제하기	★2.  /board/list.jsp로 이동
            comm = new BDeleteAction();
            comm.execute(request, response);
            view = "/list.do";

        }
        request.getRequestDispatcher(view).forward(request, response);
    }

}