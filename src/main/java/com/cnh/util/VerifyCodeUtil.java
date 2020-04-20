package com.cnh.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证码校验器
 */
public class VerifyCodeUtil {


    /**
     * 根据name取值
     * @param request
     * @param key
     * @return
     */
    public static String getString(HttpServletRequest request, String key) {
        try {
            String result = request.getParameter(key);
            if(result != null) {
                result = result.trim();
            }
            if("".equals(result)) {
                result = null;
            }
            return result;
        }catch(Exception e) {
            return null;
        }
    }


    /**
     * 校验开始
     * @param request
     * @return
     */


    public static boolean checkVerifyCode(HttpServletRequest request) {
        //获取生成的验证码
        String verifyCodeExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        //获取用户输入的验证码
        String verifyCodeActual = VerifyCodeUtil.getString(request, "verifyCodeActual");
        if(verifyCodeActual == null ||!verifyCodeActual.equals(verifyCodeExpected)) {
            return false;
        }
        return true;
    }

}
