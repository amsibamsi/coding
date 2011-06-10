PROGRAMS = static dynamic flexible
CC = gcc
CFLAGS = -Wall -Werror -std=c99

.PHONY: clean
all: $(PROGRAMS)

clean:
	$(RM) *.o $(PROGRAMS)

static: static.o
	$(CC) -o $(@) $(^)

dynamic flexible: %: %.o arguments.o
	$(CC) -o $(@) $(^)

static.o: static.c
	$(CC) -c $(CFLAGS) -o $(@) $(<)

dynamic.o flexible.o: %.o: %.c arguments.c
	$(CC) -c $(CFLAGS) -o $(@) $(<)
