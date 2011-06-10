#include "common.h"

int main(int argc, char **argv) {
  z_stream stream;
  Bytef in[CHUNK];
  Bytef out[CHUNK];
  int flush;
  int ret;
  int eof;
  unsigned int inflated;

  stream.zalloc = Z_NULL;
  stream.zfree = Z_NULL;
  stream.opaque = Z_NULL;
  inflateInit(&stream);

  do {
    stream.next_in = in;
    stream.avail_in = fread(in, 1, CHUNK, stdin);
    if(ferror(stdin)) {
      inflateEnd(&stream);
      err_msg("while reading from standard input");
      return 1;
    }
    debug("read %u units from stdin", stream.avail_in);
    eof = feof(stdin);
    flush = eof ? Z_FINISH : Z_NO_FLUSH;
    do {
      stream.next_out = out;
      stream.avail_out = CHUNK;
      ret = inflate(&stream, flush);
      if (ret == Z_STREAM_ERROR) {
        inflateEnd(&stream);
	err_msg("while inflating");
	return 1;
      }
      inflated = CHUNK - stream.avail_out;
      debug("inflated %u units", inflated);
      ret = fwrite(out, 1, inflated, stdout);
      if ( ret != inflated || ferror(stdout)) {
        inflateEnd(&stream);
	err_msg("while writing to standard output");
	return 1;
      };
      debug("wrote %i units to stdout", ret);
    } while (stream.avail_out == 0);
  } while (!eof);
}
