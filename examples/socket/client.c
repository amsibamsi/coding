#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

int main(int argc, char *argv[]) {
  int sock, port, count;
  struct sockaddr_in addr;
  char buf[256];
  char *ping = "ping";
  const int true = 1;

  // Check arguments

  if (argc < 3) {
    fprintf(stderr, "Usage: %s <ip_address> <port>\n", argv[0]);
    exit(1);
  }
  if (inet_aton(argv[1], &addr.sin_addr) == 0) {
    fprintf(stderr, "Invalid ip address: %s\n", argv[1]);
    exit(1);
  }
  port = atoi(argv[2]);

  // Create socket

  sock = socket(AF_INET, SOCK_STREAM, 0);
  if (sock < 0) {
    perror("Failed to create socket");
    exit(1);
  }
  // Make socket reusable after exit
  if (setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &true, sizeof(int)) < 0 ) {
    perror("Failed to set socket options");
    exit(1);
  }
  bzero((char *) &addr, sizeof(addr));
  addr.sin_family = AF_INET;
  addr.sin_port = htons(port);

  // Connect

  if (connect(sock, (struct sockaddr *) &addr, sizeof(addr)) < 0) {
    perror("Failed to connect");
    exit(1);
  }
  count = write(sock, ping, strlen(ping));
  if (count < strlen(ping)) {
    perror("Failed to write");
    exit(1);
  }
  bzero(buf, sizeof(buf));
  count = read(sock, buf, sizeof(buf));
  if (count < 0) {
    perror("Failed to read");
    exit(1);
  }
  printf("%s\n", buf);

  close(sock);
  return 0;
}
