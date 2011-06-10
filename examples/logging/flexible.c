#include <stdio.h>
#include "arguments.h"

typedef int logger_f(const char *);

void debug(logger_f *logger, const char *msg) {
  (*logger)(msg);
}

logger_f log_empty;
int log_empty(const char *msg) {
  return 1;
}

int main(int argc, char **argv) {
  int is_verbose;
  logger_f *logger;
  is_verbose = has_verbose_option(argc, argv);
  if (is_verbose)
    logger = puts;
  else
    logger = log_empty;
  debug(logger, "debug");
}
