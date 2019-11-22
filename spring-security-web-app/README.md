---
page_type: sample
languages:
- java
- html
author: ramya25
products:
- spring security
- azure-active-directory
description: "This sample demonstrates a Java web application showcasing how to use Spring security to validate the access token obtained using OAuth2.0."
urlFragment: ms-identity-java-webapp
---

# A Java web application using Spring security which signs in users with the Microsoft identity platform

## About this sample

### Overview

This sample demonstrates a Java web application showcasing how to use spring security to validate the access token obtained using OAuth2.0 and authenticates the user.

1. The Java web application obtains an:

   - Id Token from Azure Active Directory (Azure AD) to sign in an user
   - Access token that is validated using Spring security.

### Scenario

This sample shows how to build a Java web app that uses OpenId Connect to sign-in/ sign-out an user and to use spring security to validate access token obtained from the Authorization headers. For more information about how the protocols work in this scenario and other scenarios, see [Authentication Scenarios for Azure AD](https://docs.microsoft.com/en-us/azure/active-directory/develop/active-directory-authentication-scenarios).

## How to run this sample

To run this sample, you'll need:

- Working installation of Java and Maven
- An Azure Active Directory (Azure AD) tenant. For more information on how to get an Azure AD tenant, see [How to get an Azure AD tenant](https://azure.microsoft.com/en-us/documentation/articles/active-directory-howto-tenant/)
- One or more user accounts in your Azure AD tenant.

### Step 1: Download Java (8 and above) for your platform

To successfully use this sample, you need a working installation of [Java](https://openjdk.java.net/install/) and [Maven](https://maven.apache.org/).

### Step 2:  Clone or download this repository

From your shell or command line:

- `git clone https://github.com/Azure-Samples/ms-identity-java-webapp.git`

Go to `spring-security-web-app` folder

- cd `spring-security-web-app`

### Step 3:  Register the sample with your Azure Active Directory tenant

To register the project, you can follow the steps in the paragraphs below:

#### First step: choose the Azure AD tenant where you want to create your applications

#### Choose the Azure AD tenant where you want to create your applications

As a first step you'll need to:

1. Sign in to the [Azure portal](https://portal.azure.com) using either a work or school account or a personal Microsoft account.
1. If your account is present in more than one Azure AD tenant, select your profile at the top right corner in the menu on top of the page, and then **switch directory**.
   Change your portal session to the desired Azure AD tenant.
1. In the portal menu, select the **Azure Active Directory** service, and then select **App registrations**.

> In the next steps, you might need the tenant name (or directory name) or the tenant ID (or directory ID). These are presented in the **Properties** of the Azure Active Directory window respectively as *Name* and *Directory ID*

#### Register the client app (spring-security-web-app)

1. Navigate to the Microsoft identity platform for developers [App registrations](https://go.microsoft.com/fwlink/?linkid=2083908) page.
1. Select **New registration**.
   - In the **Name** section, enter a meaningful application name that will be displayed to users of the app, for example `spring-security-web-app`.
   - In the **Supported account types** section, select **Accounts in any organizational directory**.
   - Click **Register** button at the bottom to create the application.
1. On the application **Overview** page, find the **Application (client) ID** and **Directory (tenant) ID** values and record it for later. You'll need it to configure the configuration file(s) later in your code.
1. In the Application menu blade, click on **Authentication**, under **Redirect URIs**, select **Web** and enter the redirect URL.
   By default, the sample uses:

   - `http://localhost:8080/login`

    Click on **save**.

1. In the Application menu blade, click on **Certificates & Secrets** and click on `New client secret` in the **Client Secrets** section:

   - Type a key description (for instance `app secret`),
   - Select a key duration of either **In 1 year**, **In 2 years**, or **Never Expires** as per your security concerns.
   - The generated key value will be displayed when you click the **Add** button. Copy the generated value for use in the steps later.
   - You'll need this key later in your code's configuration files. This key value will not be displayed again, and is not retrievable by any other means, so make sure to note it from the Azure portal before navigating to any other screen or blade.

### Step 4:  Configure the sample to use your Azure AD tenant

Open `application.properties` in the src/main/resources folder. Fill in with your tenant and app registration information noted in registration step. Replace *Enter_the_Tenant_Info_Here* with the **Tenant Id**, *Enter_the_Application_Id_here* with the **Application Id** and *Enter_the_Client_Secret_Here* with the **key value** noted.

### Step 5: Run the application

To run the project, you can either:

Run it directly from your IDE by using the embedded spring boot server or package it to a WAR file using [maven](https://maven.apache.org/plugins/maven-war-plugin/usage.html) and deploy it a J2EE container solution such as [Apache Tomcat](http://tomcat.apache.org/).

#### Running from IDE

If you running you web application from an IDE, click on **run**, then navigate to the home page of the project. For this sample, the standard home page URL is <http://localhost:8080>

#### Packaging and deploying to container

If you would like to deploy the web sample to Tomcat, you will need to make a couple of changes to the source code.

1. Open spring-security-web-app/pom.xml
    - Under `<name>spring-security-web-app</name>` add `<packaging>war</packaging>`
    - Add dependency:

         ```xml
         <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-tomcat</artifactId>
          <scope>provided</scope>
         </dependency>
         ```

2. Open spring-security-web-app/src/main/java/com.microsoft.azure.springsecuritywebapp/SpringSecurityWebAppApplication

    - Delete all source code and replace with the following:

   ```Java
        package com.microsoft.azure.springsecuritywebapp;

        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.boot.builder.SpringApplicationBuilder;
        import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

        @SpringBootApplication
        public class SpringSecurityWebAppApplication extends SpringBootServletInitializer {

         public static void main(String[] args) {
          SpringApplication.run(SpringSecurityWebAppApplication.class, args);
         }

         @Override
         protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
          return builder.sources(SpringSecurityWebAppApplication.class);
         }
        }
   ```

3. Open a command prompt, go to the root folder of the project, and run `mvn package`
    - This will generate a `spring-security-web-app.war` file in your /targets directory.
    - Rename this file to `ROOT.war`
    - Deploy this war file using Tomcat or any other J2EE container solution.
        - To deploy on Tomcat container, copy the .war file to the springsecuritywebapp's folder under your Tomcat installation and then start the Tomcat server.

    This WAR will automatically be hosted at `http://<yourserverhost>:<yourserverport>/`
        - Tomcats default port is 8080. This can be changed by
        - Going to tomcat/conf/server.xml
        - Search "Connector Port"
        - Replace "8080" with your desired port number

    Example: `http://localhost:8080`

### You're done

Click on "Login" to start the process of logging in. Once logged in, you'll see the name of the user who is authenticated. You'll then have the option to "Sign out".

## About the code

The relevant code for this sample is in the `AppConfiguration.java` file.

This class extends `WebSecurityConfigurerAdapter` from which the `configure` method is overridden.

```Java
public void configure(HttpSecurity http) throws Exception {

        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/error**")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .logout()
                        .deleteCookies()
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/");
    }
```

## Community Help and Support

Use [Stack Overflow](http://stackoverflow.com/questions/tagged/msal) to get support from the community.
Ask your questions on Stack Overflow first and browse existing issues to see if someone has asked your question before.
Make sure that your questions or comments are tagged with [`Java`].

If you find a bug in the sample, please raise the issue on [GitHub Issues](https://github.com/Azure-Samples/ms-identity-java-webapp/issues).

To provide a recommendation, visit the following [User Voice page](https://feedback.azure.com/forums/169401-azure-active-directory).

## Contributing

If you'd like to contribute to this sample, see [CONTRIBUTING.MD](https://github.com/Azure-Samples/ms-identity-java-webapp/blob/master/CONTRIBUTING.md).

This project has adopted the [Microsoft Open Source Code of Conduct](https://opensource.microsoft.com/codeofconduct/). For more information, see the [Code of Conduct FAQ](https://opensource.microsoft.com/codeofconduct/faq/) or contact [opencode@microsoft.com](mailto:opencode@microsoft.com) with any additional questions or comments.

## More information

For more information, see MSAL4J [conceptual documentation](https://github.com/AzureAD/azure-activedirectory-library-for-java/wiki)

For more information, see how spring security is used in [Java web api sample](https://github.com/Azure-Samples/ms-identity-java-webapi)

For more information about web apps scenarios on the Microsoft identity platform see [Scenario: Web app that signs in users](https://docs.microsoft.com/en-us/azure/active-directory/develop/scenario-web-app-sign-user-overview) and [Scenario: Web app that calls web APIs](https://docs.microsoft.com/en-us/azure/active-directory/develop/scenario-web-app-call-api-overview)

For more information about how OAuth 2.0 protocols work in this scenario and other scenarios, see [Authentication Scenarios for Azure AD](http://go.microsoft.com/fwlink/?LinkId=394414).