JAVAC = javac
JAVA = java
SRC = src
OUT = classes

SOURCES = $(SRC)/Main.java $(SRC)/CPUScheduler.java $(SRC)/Process.java

compile:
	mkdir -p $(OUT)
	$(JAVAC) -d $(OUT) -sourcepath $(SRC) $(SOURCES)

run: compile
	$(JAVA) -cp $(OUT) problem1.src.Main

clean:
	rm -rf $(OUT)
