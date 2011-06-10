PROGRAMS = vpointer
CC = gcc
CFLAGS = -Wall -Werror -std=c99

.PHONY: clean
all: $(PROGRAMS)

clean:
	$(RM) *.o $(PROGRAMS)

$(PROGRAMS): %: %.o
	$(CC) $(CFLAGS) -o $(@) $(^)

%.o: %.c
	$(CC) $(CFLAGS) -c -o $(@) $(<)
