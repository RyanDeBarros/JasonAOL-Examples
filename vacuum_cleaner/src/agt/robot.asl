// Agent robot in project vacuum_cleaner

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("Hello!").

+dirty <- .print("On dirty spot!"); suck.
+clean <- .print("Dirt is cleaned!").
+pos(1) <- right.
+pos(2) <- down.
+pos(3) <- up.
+pos(4) <- left.
