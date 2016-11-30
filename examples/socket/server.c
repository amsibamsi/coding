#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <ifaddrs.h>

int main(int argc, char *argv[]) {
  int sock, conn, port, count, found_dev;
  struct sockaddr_in addr;
  struct ifaddrs *ifaddr;
  char *dev;
  char buf[256];
  char *ping = "ping";
  char *pong = "pong";
  const int true = 1;

  // Check arguments

  if (argc < 3) {
    fprintf(stderr, "Usage: %s <interface> <port>\n", argv[0]);
    exit(1);
  }
  dev = argv[1];
  port = atoi(argv[2]);

  // Create socket

  sock = socket(AF_INET, SOCK_STREAM, 0);
  if (sock < 0) {
    perror("Failed to create socket");
    exit(1);
  }
  // Make socket reusable after exit
  if (setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &true, sizeof(true)) < 0 ) {
    perror("Failed to set socket options");
    exit(1);
  }
  bzero((char *) &addr, sizeof(addr));
  addr.sin_family = AF_INET;
  addr.sin_port = htons(port);
  // Search all interfaces for specified name and use address to listen
  if (getifaddrs(&ifaddr) < 0) {
    perror("Failed to get interface addresses");
    exit(1);
  }
  found_dev = 0;
  for (struct ifaddrs *ifa = ifaddr; ifa != NULL; ifa = ifa->ifa_next) {
    if ((strcmp(dev, ifa->ifa_name) == 0) && (ifa->ifa_addr->sa_family == AF_INET)) {
      struct sockaddr_in *ifa_addr = (struct sockaddr_in *) ifa->ifa_addr;
      addr.sin_addr.s_addr = ifa_addr->sin_addr.s_addr;
      found_dev = 1;
      break;
    }
  }
  freeifaddrs(ifaddr);
  if (!found_dev) {
    fprintf(stderr, "Error: No suitable interface with name '%s' found\n", dev);
    exit(1);
  }

  // Start listening

  if (bind(sock, (struct sockaddr *) &addr, sizeof(addr)) < 0) {
    perror("Failed to bind");
    exit(1);
  }
  if (listen(sock, 0) < 0) {
    perror("Failed to listen");
    exit(1);
  }

  // Handle one client at a time

  while (1) {
    conn = accept(sock, (struct sockaddr *) NULL, NULL);
    if (conn < 0) {
      perror("Failed to accept");
      exit(1);
    }
    bzero(buf, sizeof(buf));
    count = read(conn, buf, sizeof(buf));
    if (count < 0) {
      perror("Failed to read");
      exit(1);
    }
    printf("%s\n", buf);
    if (strncmp(ping, buf, strlen(ping)) == 0) {
      count = write(conn, pong, strlen(pong));
      if (count < strlen(pong)) {
        perror("Failed to write");
        exit(1);
      }
    }
    close(conn);
  }

  close(sock);
  return 0;
}
