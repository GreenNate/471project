JAVAC = javac
JAVA = java
SRC = src
OUT = classes

SOURCES = $(SRC)/Main.java $(SRC)/Producer.java $(SRC)/Consumer.java $(SRC)/Buffer.java

compile:
	mkdir -p $(OUT)
	$(JAVAC) -d $(OUT) -sourcepath $(SRC) $(SOURCES)

run: compile
	$(JAVA) -cp $(OUT) problem2.src.Main


test:
	@mkdir -p outputs
	@echo "Running all test cases..."
	@set -e; \
	times="5 10 20"; \
	cases="1 1 4 1 16 1 1 2 4 2 16 2 1 4 4 4 16 4 1 16 4 16 16 16"; \
	i=1; \
	while [ "$$i" -le 12 ]; do \
		idx=$$(( ($$i - 1) * 2 )); \
		producers=$$(echo $$cases | cut -d' ' -f$$((idx + 1))); \
		consumers=$$(echo $$cases | cut -d' ' -f$$((idx + 2))); \
		tcdir="outputs/test$$i"; \
		mkdir -p $$tcdir; \
		for t in $$times; do \
			echo "Running Test $$i with $$producers producers, $$consumers consumers, $$t seconds..."; \
			java -cp classes problem2.src.Main $$t $$producers $$consumers > $$tcdir/time$$t.txt; \
		done; \
		i=$$((i + 1)); \
	done
	@echo "All test cases completed. Check the outputs/ directory."

clean:
	rm -rf $(OUT)
	rm -rf outputs
	