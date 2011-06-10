#include <stdio.h>
#include <zlib.h>

#define CHUNK 1024

//#define DEBUG
#ifdef DEBUG
  #define debug(...) debug_msg(__VA_ARGS__);
#else
  #define debug(...)
#endif

void err_msg(const char *, ...);
void debug_msg(const char *, ...);
