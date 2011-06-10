PROGRAMS = deflate inflate

CC = gcc
CFLAGS = -Wall -Werror -std=c99
LDFLAGS = -lz

.PHONY: clean
all: $(PROGRAMS)

clean:
	$(RM) $(PROGRAMS) *.o

$(PROGRAMS): %: %.o common.o
	$(CC) $(LDFLAGS) -o $@ $^

%.o: %.c common.h
	$(CC) -c $(CFLAGS) -o $@ $<
