// Agent bob in project hello_world

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").

+hello[source(A)]
    <-  .print("I received a 'hello' from ", A);
        .send(A, tell, hello).
