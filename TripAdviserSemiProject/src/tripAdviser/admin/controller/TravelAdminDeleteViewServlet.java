package tripAdviser.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tripAdviser.admin.model.service.TravelAdminService;
import tripAdviser.travel.product.model.vo.TravelProduct;

/**
 * Servlet implementation class TravelAdminDeleteViewServlet
 */
@WebServlet("/travel/travelAdminDeleteView")	/*/travel/travelDelete*/
public class TravelAdminDeleteViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TravelAdminDeleteViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*int trvNo=Integer.parseInt(request.getParameter("trvNo"));*/
		int trvNo=0;
		
		int result=new TravelAdminService().deleteAdmin();
		
		System.out.println(result);
		
		String msg="";
		String loc="/travel/travelProductDetail?trvNo="/*+tp.getTrvNo()*/;   /*+trvNo*/ /*tp.getTrvNo*/
		String view="/views/common/msg.jsp";
		
		if(result>0)
		{
			msg="여행지상품삭제성공";
		}
		else
		{
			msg="여행지상품삭제실패";
		}
		
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}