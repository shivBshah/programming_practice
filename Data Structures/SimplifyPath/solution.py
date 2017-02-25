#use of stack 

def simplifyPath(path):
    directories = []
    string_array = path.split("/")
    
    for s in string_array:
      if s != "" and s!= ".":
        if s == "..":
          if len(directories) != 0:
            directories.pop()
        else:
          directories.append(s)
          
    return "/" + "/".join(directories)
      
      
def main():
  print(simplifyPath("/home/a/./x/../b//c/"))
  
main()