#include <stdio.h>
#include <fcntl.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {

        int inFile, outFile;
        char *inFileName = "./in.txt";
        char *outFileName = "./out.txt";
        int len;
        char c;

        if ((inFile = open(inFileName, O_RDONLY)) == -1) {
                printf("%s could not be opened\n", inFileName); 
                exit(1);
        }

        if ((outFile = open(outFileName, O_RDWR | O_CREAT | O_TRUNC, 0600)) == -1) {
                printf("%s could not be opened\n", outFileName); 
                exit(1);
        }

        while ((len = read(inFile, &c, 1)) > 0)
                write(outFile, &c, 1);

        close(inFile);
        close(outFile);

        return 0;
}
