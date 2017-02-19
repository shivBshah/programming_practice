#include <stdio.h>

int main(int argc, char *argv[]) {

        /* The child process's new program.
           This program replaces the parent's program */

        printf("Process[%d]: Child in execution ... \n", getpid());
        sleep(5);
        printf("Process[%d]: Child terminating ... \n", getpid());
		  
		  return 0;
} 
