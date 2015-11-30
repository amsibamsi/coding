CC=clang
CFLAGS=-I/usr/local/include -L/usr/local/lib
LIBS=-lxcb

.PHONY: clean

all: test

test: out/cmove
	out/cmove

out/cmove: src/cmove.c
	mkdir -p out
	$(CC) $(CFLAGS) -o $@ $(LIBS) $>

clean:
	rm -rf out
