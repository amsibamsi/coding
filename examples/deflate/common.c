#include "common.h"
#include <stdarg.h>

void err_msg(const char *string, ...) {
  va_list args;
  va_start(args, string);
  fputs("error: ", stderr);
  vfprintf(stderr, string, args);
  fputs("\n", stderr);
}

void debug_msg(const char *string, ...) {
  va_list args;
  va_start(args, string);
  fputs("debug: ", stderr);
  vfprintf(stderr, string, args);
  fputs("\n", stderr);
}
