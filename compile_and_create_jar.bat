javac -sourcepath ./source_code -d ./byte_code/production ./source_code/ru/skbkontur/autocompleter/Autocompleter.java
jar -cvfm autocompleter.jar MANIFEST.MF -C ./byte_code/production ru