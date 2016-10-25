# spring-aop-demo

Demo application showing some examples of using AOP in a Spring/Hibernate application.

Pointcuts by annotations are used in order to output timings in the application log file. Methods can be annotated with @TimingLogging which will write to the log file before the method is called and after so the time taken can be seen.

An aspect has been created using a pointcut expression which is used for audit purposes (whenever a controller method is called, it is audited and written to the database)
