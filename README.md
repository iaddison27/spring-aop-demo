# spring-aop-demo

Demo application showing some examples of using AOP in a Spring/Hibernate application.

Pointcuts by annotations are used in order to output timings in the application log file. Methods can be annotated with @TimingLogging which will write to the log file before the method is called and after so the time taken can be seen.

An aspect has been created using a pointcut expression which is used for audit purposes (whenever a controller method is called, it is audited and written to the database)

Useful definitions:
Aspect - modularization of cross cutting concerns (e.g. AuditAspect)
Join point - point in program execution where an aspect can be plugged in (e.g. methods inside EmployeeController)
Advice - action taken by an aspect at a join point (e.g. AuditAspect.auditSearch(..))
Point cut - defines (the rules) where the advice should be applied. Expressions identifying the join points. One expression may result in many join points (e.g. execution(* org.test.controller.EmployeeController.*(..)) )

Types of advice:
Before - before a join point
After returning - after a join point completes normally
After throwing - after a method exits by throwing an exception
After - both "After returning" and "After throwing"
Around - surrounds a join point - allows us to do something before AND after the method execution

The following Javascript can be used (from the Firebug console) in order to perform a POST request to create an Employee:
var xhr = new XMLHttpRequest();
xhr.open("POST", "http://localhost:8080/spring-aop-demo/employee/create");
xhr.setRequestHeader("Content-type", "application/json");
xhr.send('{"name":"Ian", "salary":125.00, "startDate":"2016-05-02" }');
