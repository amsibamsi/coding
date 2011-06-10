#include <stdio.h>

#define VERBOSE

#ifdef VERBOSE
  #define DEBUG(...) puts(__VA_ARGS__);
#else
  #define DEBUG(...)
#endif

int main(void) {
  DEBUG("debug");
}
