# Compiler and Java commands
JAVAC = javac
JAVA = java

# Directories
P1_SRC = problem1/src
P2_SRC = problem2/src
P1_OUT = problem1/classes
P2_OUT = problem2/classes

# Problem 1 Sources
P1_SOURCES = $(P1_SRC)/Main.java $(P1_SRC)/CPUScheduler.java $(P1_SRC)/Process.java

# Problem 2 Sources
P2_SOURCES = $(P2_SRC)/Main.java $(P2_SRC)/Producer.java $(P2_SRC)/Consumer.java $(P2_SRC)/Buffer.java

# ======== Targets ========

all: compile1 compile2

compile1:
	mkdir -p $(P1_OUT)
	$(JAVAC) -d $(P1_OUT) -sourcepath $(P1_SRC) $(P1_SOURCES)

compile2:
	mkdir -p $(P2_OUT)
	$(JAVAC) -d $(P2_OUT) -sourcepath $(P2_SRC) $(P2_SOURCES)

run1: compile1
	java -cp problem1/classes problem1.src.Main problem1/input/Datafile1-txt.txt

run2: compile2
	java -cp problem2/classes problem2.src.Main $(ARGS)

test: compile2
	@echo "Running all test cases from tests.txt..."
	@mkdir -p problem2/outputs
	@i=1; \
	while read -r producers consumers; do \
		tcdir="problem2/outputs/test$$i"; \
		mkdir -p $$tcdir; \
		for t in 5 10 20; do \
			echo "Running Test $$i: time=$$t, producers=$$producers, consumers=$$consumers"; \
			$(JAVA) -cp $(P2_OUT) problem2.src.Main $$t $$producers $$consumers > $$tcdir/time$$t.txt; \
		done; \
		i=$$((i + 1)); \
	done < problem2/input/tests.txt
	@echo "All test cases completed. Check the problem2/outputs/ directory."


clean:
	rm -rf $(P1_OUT) $(P2_OUT) problem1/output problem2/outputs problem1Docs problem2Docs
	

docs1:
	javadoc -d problem1Docs -subpackages problem1.src

docs2:
	javadoc -d problem2Docs -subpackages problem2.src

docs: docs1 docs2
