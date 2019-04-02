package com.genius;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class GeniusServer
 */
@WebServlet("/GeniusServer")
public class GeniusServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeniusServer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		String action = request.getParameter("action");
		String jsonstr = CodeHandler.do_Decoder(request.getParameter("jsonstr"));
		
		switch(action) {
		case "login":
			result = LoginAndRegister.do_Login(jsonstr);
			break;
		case "register":
			result = LoginAndRegister.do_Register(jsonstr);
			break;
		case "checkregister":
			result = LoginAndRegister.do_CheckRegister(jsonstr);
			break;
		case "changepassword":
			result = LoginAndRegister.do_ChangePassword(jsonstr);
			break;
		case "forgetpassword":
			result = LoginAndRegister.do_ForgetPassword(jsonstr);
			break;
		case "getvendorinfo":
			result = GetInformation.get_VendorInfo(jsonstr);
			break;
		case "getcustomerinfo":
			result = GetInformation.get_CustomerInfo(jsonstr);
			break;
		case "getgoodsinfo":
			result = GetInformation.get_GoodsInfo(jsonstr);
			break;
		case "getstoreinfo":
			result = GetInformation.get_StoreInfo(jsonstr);
			break;
		case "getorderlist":
			result = GetInformation.get_Orderlist(jsonstr);
			break;
		case "getgoodsid":
			result = GetInformation.get_Goodsid(jsonstr);
			break;
		case "getorderlistcid":
			result = GetInformation.get_Orderlistcid(jsonstr);
			break;
		case "getorderlistvid":
			result = GetInformation.get_Orderlistvid(jsonstr);
			break;
		case "getcollect":
			result = GetInformation.get_Collect(jsonstr);
			break;
		case "getshopcar":
			result = GetInformation.get_Shopcar(jsonstr);
			break;
		case "getvip":
			result = GetInformation.get_Vip(jsonstr);
			break;
		case "getallgoods":
			result = GetInformation.get_AllGoods(jsonstr);
			break;
		case "getgoodsclass":
			result = GetInformation.get_GoodsClass(jsonstr);
			break;
		case "updatevendorinfo":
			result = UpdateInformation.update_VendorInfo(jsonstr);
			break;
		case "updatecustomerinfo":
			result = UpdateInformation.update_CustomerInfo(jsonstr);
			break;
		case "updategoodsinfo":
			result = UpdateInformation.update_GoodsInfo(jsonstr);
			break;
		case "updatestoreinfo":
			result = UpdateInformation.update_StoreInfo(jsonstr);
			break;
		case "updateorderlist":
			result = UpdateInformation.update_Orderlist(jsonstr);
			break;
		case "updateshopcar":
			result = UpdateInformation.update_Shopcar(jsonstr);
			break;
		case "addgoods":
			result = AddInformation.add_GoodsInfo(jsonstr);
			break;
		case "addstore":
			result = AddInformation.add_StoreInfo(jsonstr);
			break;
		case "addorderlist":
			result = AddInformation.add_Orderlist(jsonstr);
			break;
		case "addcollect":
			result = AddInformation.add_Collect(jsonstr);
			break;
		case "addshopcar":
			result = AddInformation.add_Shopcar(jsonstr);
			break;
		case "addvip":
			result = AddInformation.add_Vip(jsonstr);
			break;
		case "deletegoods":
			result = DeleteInfo.delete_Goods(jsonstr);
			break;
		case "deleteorderlist":
			result = DeleteInfo.delete_Orderlist(jsonstr);
			break;
		case "deleteshopcar":
			result = DeleteInfo.delete_Shopcar(jsonstr);
			break;
		case "deletecollect":
			result = DeleteInfo.delete_Collect(jsonstr);
			break;
		case "deletevip":
			result = DeleteInfo.delete_Vip(jsonstr);
			break;
		default:
			break;
		}
		out.write(CodeHandler.do_Encoder(result));
		out.flush();
		out.close();
	}

}
