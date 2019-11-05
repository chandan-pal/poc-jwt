<h1 align="center">
  <br>
  
  <br>
  POC - JWT
  <br>
</h1>

<h4 align="center">A POC project for implementing Json Web Tokens for a RESTFul API</h4>


<p align="center">
    <a alt="Java">
        <img src="https://img.shields.io/static/v1?label=Java&message=v1.8&color=blue" />
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/static/v1?label=Spring%20Boot&message=2.2.0.RELEASE&color=brightgreen" />
    </a>
    <a alt="MySQL">
        <img src="https://img.shields.io/static/v1?label=MySQL&message=8.0.15&color=orange" />
    </a>
    <a alt="JWT">
        <img src="https://img.shields.io/static/v1?label=JWT&message=0.9.1&color=green" />
    </a>
</p>


## Concept ##
This is a Spring application which utilizes Spring Security to block all incoming requests without proper authentication and authorization.

It uses jdbc authentication to validate users from MySQL database using JPI - Java Persistence API.

To implement JWT-
  1. send a 'Json Web Token' with first request to authentication page '/authenticate' in this project.
  2. Apply a filter before every subsequent requests to validate the JWT (with the help of Spring Security).


