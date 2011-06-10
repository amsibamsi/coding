#include "common.h"

int main(int argc, char **argv) {
  z_stream stream;
  Bytef in[CHUNK];
  Bytef out[CHUNK];
  int flush;
  int ret;
  int eof;
  unsigned int deflated;

  stream.zalloc = Z_NULL;
  stream.zfree = Z_NULL;
  stream.opaque = Z_NULL;
  deflateInit(&stream, Z_DEFAULT_COMPRESSION);

  do {
    stream.next_in = in;
    stream.avail_in = fread(in, 1, CHUNK, stdin);
    if(ferror(stdin)) {
      deflateEnd(&stream);
      err_msg("while reading from standard input");
      return 1;
    }
    debug("read %u units from stdin", stream.avail_in);
    eof = feof(stdin);
    flush = eof ? Z_FINISH : Z_NO_FLUSH;
    do {
      stream.next_out = out;
      stream.avail_out = CHUNK;
      ret = deflate(&stream, flush);
      if (ret == Z_STREAM_ERROR) {
        deflateEnd(&stream);
	err_msg("while deflating");
	return 1;
      }
      deflated = CHUNK - stream.avail_out;
      debug("deflated %u units", deflated);
      ret = fwrite(out, 1, deflated, stdout);
      if ( ret != deflated || ferror(stdout)) {
        deflateEnd(&stream);
	err_msg("while writing to standard output");
	return 1;
      };
      debug("wrote %i units to stdout", ret);
    } while (stream.avail_out == 0);
  } while (!eof);
}
