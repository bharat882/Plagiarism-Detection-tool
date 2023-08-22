build: 
	javac s40202642_detector.java

run: build
	java s40202642_detector "$(FILE1)" "$(FILE2)"
