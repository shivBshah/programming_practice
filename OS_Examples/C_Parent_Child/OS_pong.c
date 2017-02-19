#include <stdio.h>
#include <sys/wait.h>

int main(int argc, char *argv[]) {

   printf("Pong\n");
   sleep(1);
   execl("OS_ping", NULL);

   return 0;
}
