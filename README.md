# Number Range Summarizer

A Java application that summarizes a collection of integers into a **comma-delimited string**, grouping sequential numbers into ranges.  

This project demonstrates **Java 8 features**, **object-oriented design**, and **unit testing** best practices.

---

## Requirements

- Java 8 or higher
- Maven 3 or higher
- JUnit 5 (included via Maven dependencies)

---

## Example

**Input:**  
```
1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31
```

**Output:**  
```
1, 3, 6-8, 12-15, 21-24, 31
```

---

## Usage

### 1. Collect numbers
```java
NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();
Collection<Integer> numbers = summarizer.collect("1,3,6,7,8");
```

### 2. Summarize numbers
```java
String result = summarizer.summarizeCollection(numbers);
System.out.println(result); // Output: 1, 3, 6-8
```

---

## Running Tests

To run the tests, use Maven: `mvn test`. The test suite covers valid and invalid input parsing, summarization of single numbers and ranges, duplicates, unordered numbers, empty input, and integration of `collect()` and `summarizeCollection()`.
