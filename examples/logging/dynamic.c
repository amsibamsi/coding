#include <stdio.h>
#include "arguments.h"

#define DEBUG(a) if (is_verbose) puts(a);

int main(int argc, char **argv) {
  int is_verbose = has_verbose_option(argc, argv);
  DEBUG("debug");
}
