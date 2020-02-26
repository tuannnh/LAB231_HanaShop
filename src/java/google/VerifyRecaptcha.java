/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package google;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.Serializable;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author tuannnh
 */
public class VerifyRecaptcha implements Serializable {

    public static final String url = "https://www.google.com/recaptcha/api/siteverify";

    public static boolean verify(String gRecaptchaResponse) throws Exception {
        if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
            return false;
        }

        //parse JSON response and return 'success' value
        String response = Request.Post(url)
                .bodyForm(Form.form()
                        .add("secret", Constants.GOOGLE_RECAPTCHA_SECRET_KEY)
                        .add("response", gRecaptchaResponse).build())
                .execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);

        return jobj.get("success").getAsBoolean();
    }
}
