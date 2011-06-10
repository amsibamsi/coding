#include <stdio.h>
#include "vpointer.h"


foobar_t foobar = "foobar";

void foo(void) {
  puts("foo");
}

void bar(char *msg) {
  puts(msg);
}

int main(void) {
  void *p;
  p = foobar;
  puts(p);
  p = foo;
  ((foo_f *)p)();
  p = bar;
  ((bar_f *)p)("bar");
}
