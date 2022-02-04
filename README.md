# Getting this to work

This sample was created starting with Spring's own SAML2 sample application at https://github.com/spring-projects/spring-security-samples/tree/5.6.x/servlet/spring-boot/java/saml2/login-single-tenant

The template file at `src/resources/templates/index.html` is copied from that repository whereas other code is very similar.

The intention is to provide a minimal open source sample for how to get a working Spring Boot SAML and AWS SSO integration.

## Resources
- Spring security 5.6 docs https://docs.spring.io/spring-security/reference/servlet/saml2/login/index.html (surprisingly hard to find)
- Sample saml2 application https://github.com/spring-projects/spring-security-samples/tree/5.6.x/servlet/spring-boot/java/saml2/login-single-tenant

## Notable
- Single logout is supported since Spring 5.6

## Instructions
* Clone the repository and open the code in your IDE
* Add a new Custom Application in AWS SSO
* Copy the AWS SSO SAML metadata file URL and set it using env var METADATA\_URI
* From AWS SSO download the AWS SSO certificate and move it to `src/main/resources/credentials/idp-certificate.crt`
* Go to the `src/main/resources/credentials` folder and generate a self-signed certificate and private key using `openssl req -x509 -newkey rsa:4096 -keyout key.pem -out cert.pem -sha256 -days 700 -nodes`
* Start your spring boot application (remember the METADATA\_URI env var).
* Download your applications metadata at `{baseUri}:8080/saml2/service-provider-metadata/aws` (f.e `http://localhost:8080/saml2/service-provider-metadata/aws`)
* In AWS SSO, upload this metadata file. Save the configuration.
* In AWS SSO go to Assigned users and assign your user to this custom application.
* Next, in AWS SSO,  go to Attribute mappings and map the Subject attribute to `${user:subject}` with format `unspecified` and save.
* Now, browse to your site (by default `localhost:8080`) and it should direct to AWS SSO. Log in with the user you gave access to. You should be authorized and see the sample page!
