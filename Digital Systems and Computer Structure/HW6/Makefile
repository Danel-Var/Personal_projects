# Compiler
CXX = g++

# Compiler flags
CXXFLAGS = -g -Wall -std=c++11

# Object files
OBJS = string.o string-array.o port.o ip.o

# Executable name (with .exe extension for Windows)
EXEC = fierwall.exe

#dynamic library
LIBS=libfirewall.so libinput.so

# Command to remove files
RM = $(OBJS) $(EXEC) libfirewall.so main.o

firewall.exe: main.o $(LIBS)
	$(CXX) $(CXXFLAGS) main.o -lfirewall -linput -L. -o firewall.exe

#makeing the library
libfirewall.so: $(OBJS)
	$(CXX) $(CXXFLAGS) -shared $(OBJS) -o libfirewall.so
	
# Compilation rules
main.o: main.cpp port.h ip.h input.h
	$(CXX) $(CXXFLAGS) -c -fpic main.cpp

string.o: string.cpp string.h
	$(CXX) $(CXXFLAGS) -c -fpic string.cpp

string-array.o: string-array.cpp string-array.h
	$(CXX) $(CXXFLAGS) -c -fpic string-array.cpp

port.o: port.cpp port.h 
	$(CXX) $(CXXFLAGS) -c -fpic port.cpp

ip.o: ip.cpp ip.h 
	$(CXX) $(CXXFLAGS) -c -fpic ip.cpp

# Clean up rule
clean:
	rm -rf $(RM)

# Phony targets
.PHONY: all clean
