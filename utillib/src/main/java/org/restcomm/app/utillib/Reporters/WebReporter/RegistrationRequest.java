/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2011-2016, Telestax Inc and individual contributors
 * by the @authors tag.
 *
 * This program is free software: you can redistribute it and/or modify
 * under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 * For questions related to commercial use licensing, please contact sales@telestax.com.
 *
 */

package org.restcomm.app.utillib.Reporters.WebReporter;

import org.restcomm.app.utillib.DataObjects.DeviceInfo;
import org.restcomm.app.utillib.Utils.LoggerUtil;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class represents the registation of a device api call to the server
 * @author brad scheurman
 *
 */
public class RegistrationRequest  {


    private static final String TAG = RegistrationRequest.class.getSimpleName();
    private static final String END_POINT = "/api/devices/register";

    public static HttpURLConnection POSTConnection(String host, DeviceInfo device, String email, String password, boolean share) throws Exception
    {
        URL url = new URL(host + END_POINT);
        String message = toJSON(device, email, password, share);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setFixedLengthStreamingMode(message.getBytes().length);

        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");

        LoggerUtil.logToFile(LoggerUtil.Level.DEBUG, TAG, "authorizeDevice", url.toString());

        //open
        conn.connect();

        //setup send
        OutputStream os = new BufferedOutputStream(conn.getOutputStream());
        os.write(message.getBytes());
        //clean up
        os.flush();
        return conn;

    }

    /**
     * @return String the json representation of the body of the request.
     */
    public static String toJSON(DeviceInfo device, String email, String password, boolean share) {
        String json = "";
        HashMap<String, String> phoneProperties = device.getProperties();
        if(phoneProperties != null) {
          JSONObject data = new JSONObject(phoneProperties);
          try {
            data.put("login", email);
            data.put("share", share);
              if (password != null)
              {
                  data.put("password", password);
              }
            json = data.toString();
          } catch (JSONException e) {
            LoggerUtil.logToFile(LoggerUtil.Level.ERROR, TAG, "toJSON", e.getMessage());
          }
        }
        return json;
    }

}
