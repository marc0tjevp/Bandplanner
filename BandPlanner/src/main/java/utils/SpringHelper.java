/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author maxim
 */
public class SpringHelper {
 
    //Read the AJAX String from bite array
    public static String getAjaxStringFromRequest(HttpServletRequest request) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        byte[] bytes = IOUtils.toByteArray(inputStream);
        return new String(bytes, "UTF-8");
    }

    //Read value of associative JSON array by key
    public static String getParameterFromAjaxString(String ajaxString, String paramKey) throws UnsupportedEncodingException, IOException {
        Gson gson = new Gson();
        LinkedTreeMap result = gson.fromJson(ajaxString, LinkedTreeMap.class);
        return (String) result.getOrDefault(paramKey, null);
    }
}
