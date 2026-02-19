// Agent alice in project hello_world

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .send(bob, tell, hello).

+hello[source(A)]
    <-  .print("I received a hello from ", A);
        .send(A, tell, hello).
