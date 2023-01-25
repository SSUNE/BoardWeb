<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../inc/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--  END HEADER -->
<!--  END HEADER -->

<div class="container"   style="margin-top:5%; min-height:500px">
    <h3>MULTIBOARD</h3>
    <table  class="table table-striped">
        <caption>MULTIBOARD - MVC1</caption>
        <thead>
        <tr><th scope="col">NO</th><th scope="col">TITLE</th>
            <th scope="col">WRITER</th><th scope="col">DATE</th><th scope="col">HIT</th></tr>
        </thead>
        <tbody>
        <%--<c:set     var= "total" value="${list.size()}"/>--%>
        <c:forEach var= "dto" items="${paginglist}" varStatus = "status">
            <tr><td>${pageTotal-status.index-pstartno}</td>
                <td><a href="<%=request.getContextPath()%>/detail.do?bno=${dto.bno}">${dto.btitle}</a></td>
                <td>${dto.bname}</td>
                <td>${dto.bdate}</td>
                <td>${dto.bhit}</td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot><tr><td colspan="5" class="text-center">
            <ul class = "pagination">
                <c:if test="${start>=bottomlist}">
                    <li><a href="${pageContext.request.contextPath}/list.do?pstartno=${(start-2)*onepagelimit}">이전</a></li>
                </c:if>
                <c:forEach var="i" begin="${start}" end="${end}">
                    <li><a href="${pageContext.request.contextPath}/list.do?pstartno=${(i-1)*onepagelimit}">${i}</a></li>
                </c:forEach>
                <c:if test="${pageAll>end}">
                    <li><a href="${pageContext.request.contextPath}/list.do?pstartno=${(end)*onepagelimit}">다음</a></li>
                </c:if>
            </ul>
        </td></tr></tfoot>
    </table>
    <p  class="text-right"><a href="<%=request.getContextPath()%>/write_view.do"   class="btn btn-basic">글쓰기</a></p>
</div>


<!-- END FOOTER -->
<!-- END FOOTER -->
<%@ include file="../inc/footer.jsp" %>