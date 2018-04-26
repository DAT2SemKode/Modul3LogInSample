/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import FunctionLayer.LogicFacade;
import FunctionLayer.LoginSampleException;
import FunctionLayer.User;
import FunctionLayer.requestException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author martin
 */
public class flatRoofRequest extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws requestException, LoginSampleException {
        HttpSession session = request.getSession();

        int width = Integer.parseInt(request.getParameter("width"));
        int length = Integer.parseInt(request.getParameter("length"));
        int roofId = Integer.parseInt(request.getParameter("roof"));
        int roofPitch = 0;
        int shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
        int shedLength = Integer.parseInt(request.getParameter("shedLength"));
        String name;
        String address;
        String city;
        int phone;
        String email;
        String comments = request.getParameter("comments");
        int userId = 0;
        User user = null;

        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
            userId = user.getId();
            name = user.getName();
            address = user.getAddress();
            city = user.getCity();
            phone = user.getPhone();
            email = user.getEmail();
        } else {
            name = request.getParameter("name");
            address = request.getParameter("address");
            city = request.getParameter("city");
            phone = Integer.parseInt(request.getParameter("phone"));
            email = request.getParameter("email");
            user = LogicFacade.createUser(email, "1234", name, address, phone, city);
            userId = user.getId();
        }
        if (LogicFacade.createOrder(width, length, roofId, roofPitch, shedWidth, shedLength, userId, 0, false, comments) == true) {
            return "success";
        } else {
            return "error";
        }

    }

}
