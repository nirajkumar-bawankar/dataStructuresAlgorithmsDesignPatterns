CC = g++ -ggdb3 -O0
CFLAGS = -Wall -Werror -pedantic
OBJECTS = run.o

all: run

run.o: run.cc run.h binTree.h binTree.cc
	$(CC) $(CFLAGS) -c -o $@ $<

run: $(OBJECTS)
	$(CC) $(CFLAGS) -o $@ $(OBJECTS)

clean:
	rm -f $(PROGS) *.o *~ run
