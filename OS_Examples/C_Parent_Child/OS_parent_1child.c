#include <stdio.h>
#include <sys/wait.h>

int main(int argc, char *argv[]) {

	/* This is the child process */
	if (fork() == 0) { 
		execl("OS_child", NULL);
		exit(0); /* should never get here, terminate */
	}
		  
	/* parent code here */
	printf("Process[%d]: Parent is execution ... \n", 
		getpid());

	/* child terminating */
	if (wait(NULL) > 0) 
		printf("Process[%d]: Parent detects terminating child\n", 
			getpid());

	printf("Process[%d]: Parent terminating ... \n", getpid());

	return 0;
}
