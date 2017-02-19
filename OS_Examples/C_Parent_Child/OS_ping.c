#include <stdio.h>
#include <sys/wait.h>

int main(int argc, char *argv[]) {

   printf("Ping\n");
   sleep(1);
   execl("OS_pong", NULL);

   return 0;
}
