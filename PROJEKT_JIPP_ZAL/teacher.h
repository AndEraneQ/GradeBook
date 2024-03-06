#ifndef TEACHER_H
#define TEACHER_H

#include "person.h"

class Teacher : public Person
{
    vector<string> students; /**< Vector storing the names of students associated with the teacher. */

public:
    /**
     * @brief Parameterized constructor for the Teacher class.
     * @param p1 The base Person object.
     */
    Teacher(Person p1);

    /**
     * @brief Print grades for a specific student to a file stream.
     * @param file The output file stream.
     * @param grade The grade to be printed.
     */
    void printGrades(ofstream &file, int grade);
};

#endif // TEACHER_H
