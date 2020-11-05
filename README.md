# Word Frequency Pipes and Filters

## SE480 - Software Architecture

### Homework #4

#### Create by: Pedro Rebollar

### Project Filters

```
PipeFilterWordFrequency
    ├── App
    ├── FileGenerator
    ├── StopWordFilter
    ├── StemmingFilter
    ├── FrequencyFilter
    └── ListSink
```

### Running Instructions (Command Line)

1. Go to the project root directory

2. Build

   ```
   $ mvn clean package
   ```

3. Execute the jar file in the terminal window

   ```
   $ java -jar target/pipeFilterWordFrequency-1.0-SNAPSHOT.jar
   ```

4. Enter .txt file location:

    ex. ```$ /Users/Username/Foler/file.txt```

