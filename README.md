# oss-automation
# Interview task for OSS Engineer

# It uses JWT token based authentication flow can be designated as the following steps.

Get the JWT based token from the authentication endpoint, eg /auth/signin.
Extract token from the authentication result.
Set the HTTP header Authorization value as Bearer jwt_token.
Then send a request to access the protected resources.
If the requested resource is protected, Spring Security will use our custom Filter to validate the JWT token, and build an Authentication object and set it in Spring Security specific SecurityContextHolder to complete the authentication progress.
If the JWT token is valid it will return the requested resource to client.

## Generate the project skeleton

The quickest way to create a new Spring Boot project is using [Spring Initializr](http://start.spring.io) to generate the base codes.

Open your browser, and go to http://start.spring.io.  In the **Dependencies** field, select **Web**, **Security**, **JPA**, **Lombok**, then click **Generate** button or press **ALT+ENTER** keys to generate the project skeleton codes. 
