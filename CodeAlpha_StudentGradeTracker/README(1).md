# Student Grade Tracker

A simple and professional **Java console-based application** for managing student grades and generating a summary report. The program uses Java's `ArrayList` collection to store student names and grades, then calculates the **average, highest, and lowest scores**.

## Features

- Add multiple students dynamically
- Store student names and grades using `ArrayList`
- Calculate the average grade
- Find the highest grade
- Find the lowest grade
- Display all students in a formatted report
- Easy-to-use command-line interface

## Technologies Used

- **Java**
- **ArrayList**
- **Scanner**
- **Java Collections Framework**

## Project Structure

```text
StudentGradeTracker/
│
├── StudentGradeTracker.java
└── README.md
```

## How It Works

1. The program asks for the number of students.
2. It takes the name and grade of each student.
3. Names and grades are stored in separate `ArrayList` collections.
4. The program processes all grades to calculate:
   - Average score
   - Highest score
   - Lowest score
5. A formatted student report is displayed.

## Example

### Input

```text
Enter number of students: 3

Student 1
Enter name: Rahul
Enter grade: 85

Student 2
Enter name: Ankit
Enter grade: 92

Student 3
Enter name: Priya
Enter grade: 78
```

### Output

```text
========== STUDENT REPORT ==========
Student Name         Grade
Rahul                85
Ankit                92
Priya                78

Average Score : 85.00
Highest Score : 92
Lowest Score  : 78
```

## How to Run

### 1. Clone the Repository

```bash
git clone <your-repository-url>
```

### 2. Navigate to the Project Directory

```bash
cd StudentGradeTracker
```

### 3. Compile the Program

```bash
javac StudentGradeTracker.java
```

### 4. Run the Program

```bash
java StudentGradeTracker
```

## Concepts Demonstrated

This project demonstrates several fundamental Java programming concepts:

- Classes and objects
- `main()` method
- User input using `Scanner`
- `ArrayList`
- Loops
- Conditional statements
- Enhanced `for` loop
- Type casting
- Basic mathematical calculations
- Formatted console output
- String formatting

## Future Improvements

The project can be extended with features such as:

- Student ID management
- Grade validation
- Letter grade calculation
- Pass/fail status
- Searching for a student
- Updating or deleting student records
- Sorting students by grade
- File-based data storage
- Graphical user interface
- Database integration

## Learning Objective

The main objective of this project is to practice **Java collections, user input, loops, conditional logic, and basic data processing** through a practical console application.

## Author

**aryanscriptz**

If you found this project useful, consider ⭐ starring the repository.

---

## License

This project is available for educational and personal use.
