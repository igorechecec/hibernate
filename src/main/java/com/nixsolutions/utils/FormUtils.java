package com.nixsolutions.utils;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public class FormUtils {

    /**
     * Method checks input from user.
     *
     * @param req request
     * @return
     */
    public static boolean checkForm(HttpServletRequest req) {
        String[] formParams = {"login", "password", "email", "firstname",
            "lastname", "birthday", "role"};
        for (String param: formParams) {
            if (StringUtils.isEmpty(req.getParameter(param))) {
                req.getSession(false).setAttribute("error",
                    "Field " + param + " should be define");
                return false;
            }
        }
        return true;
    }
}
