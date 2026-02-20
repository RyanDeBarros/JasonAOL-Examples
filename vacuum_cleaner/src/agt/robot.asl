// Agent robot in project vacuum_cleaner

/* Initial beliefs and rules */

/* Initial goals */

!start.
!act.
!pause.

/* Plans */

+!start : true <- .print("Hello!").

-!act <- !act.
+!act : dirty <- suck; !act.
+!act : clean <- !move; !act.

+!move : pos(1) <- right.
+!move : pos(2) <- down.
+!move : pos(3) <- up.
+!move : pos(4) <- left.

+!pause <-
    .wait(2000);
    .suspend(act);
    .print("Pausing...");
    .wait(1000);
    .print("Back to cleaning");
    .resume(act);
    !pause.
