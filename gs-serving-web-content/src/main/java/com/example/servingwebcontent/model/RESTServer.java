package com.example.servingwebcontent.model;

import lombok.Data;

/**
 * Date:   6/7/13 / 8:29 AM
 * Author: Johnathan Mark Smith
 * Email:  john@johnathanmarksmith.com
 * <p/>
 * Comments:
 * <p/>
 * This bean is used to hold the user name, password and host which we need to make the
 * call to the REST service.
 */
@Data
public class RESTServer
{
    private String user;
    private String password;
    private String host;
    private String contextPath;


    public RESTServer(String user, String password, String host, String contextPath)
    {
        this.user = user;
        this.password = password;
        this.host = host;
        this.contextPath = contextPath;
    }
}
